package org.apereo.cas.adaptors.duo.web.flow.action;



import org.apereo.cas.adaptors.duo.DuoSecurityUserAccountStatus;
import org.apereo.cas.adaptors.duo.authn.DuoSecurityAuthenticationService;
import org.apereo.cas.adaptors.duo.authn.DuoSecurityMultifactorAuthenticationProvider;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.flow.actions.AbstractMultifactorAuthenticationAction;
import org.apereo.cas.web.support.WebUtils;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import java.util.concurrent.TimeUnit;


/**
 * This is {@link DuoSecurityDetermineUserAccountAction}.
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
@Slf4j
public class DuoSecurityDetermineUserAccountAction extends AbstractMultifactorAuthenticationAction<DuoSecurityMultifactorAuthenticationProvider> {

    @Override
    protected Event doExecute(final RequestContext requestContext) {
        val authentication = WebUtils.getAuthentication(requestContext);
        val principal = resolvePrincipal(authentication.getPrincipal());

        val duoAuthenticationService = provider.getDuoAuthenticationService();

        val preAuthenticatedAttempts = 0;
        val eventFactorySupport = new EventFactorySupport();

        return doPreAuth(duoAuthenticationService, requestContext, principal, eventFactorySupport, preAuthenticatedAttempts);
    }

    private Event doPreAuth(final DuoSecurityAuthenticationService duoAuthenticationService, final RequestContext requestContext,
                            final Principal principal, final EventFactorySupport eventFactorySupport, final int preAuthenticated) {
        val account = duoAuthenticationService.getUserAccount(principal.getId());
        var returnEvent = success();
        if (account.getStatus() == DuoSecurityUserAccountStatus.ENROLL) {
            if (StringUtils.isNotBlank(provider.getRegistrationUrl())) {
                requestContext.getFlowScope().put("duoRegistrationUrl", provider.getRegistrationUrl());
                returnEvent = eventFactorySupport.event(this, CasWebflowConstants.TRANSITION_ID_ENROLL);
            }
        }
        if (account.getStatus() == DuoSecurityUserAccountStatus.ALLOW) {
            returnEvent = eventFactorySupport.event(this, CasWebflowConstants.TRANSITION_ID_BYPASS);
        }
        if (account.getStatus() == DuoSecurityUserAccountStatus.DENY) {
            returnEvent = eventFactorySupport.event(this, CasWebflowConstants.TRANSITION_ID_DENY);
        }
        if (account.getStatus() == DuoSecurityUserAccountStatus.UNAVAILABLE) {
            returnEvent = eventFactorySupport.event(this, CasWebflowConstants.TRANSITION_ID_UNAVAILABLE);
        }
        if (account.getStatus() == DuoSecurityUserAccountStatus.AUTH && preAuthenticated >= 5){
            returnEvent = eventFactorySupport.event(this, CasWebflowConstants.TRANSITION_ID_UNAVAILABLE);
        }
        if (account.getStatus() == DuoSecurityUserAccountStatus.AUTH && preAuthenticated < 5){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (final InterruptedException e) {
                LOGGER.warn("Interrupted while waiting to retry Duo pre-authentication", e);
            }
            returnEvent = doPreAuth(duoAuthenticationService, requestContext, principal, eventFactorySupport, preAuthenticated + 1);
        }
        return returnEvent;
    }

}
