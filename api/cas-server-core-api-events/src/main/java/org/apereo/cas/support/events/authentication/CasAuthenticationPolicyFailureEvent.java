package org.apereo.cas.support.events.authentication;

import org.apereo.cas.authentication.Authentication;
import org.apereo.cas.authentication.AuthenticationTransaction;
import org.apereo.cas.support.events.dao.ClientInfoDTO;

import lombok.Getter;
import lombok.ToString;
import org.apereo.inspektr.common.web.ClientInfoHolder;


import java.util.Map;

/**
 * This is {@link CasAuthenticationPolicyFailureEvent}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
@ToString(callSuper = true)
@Getter
public class CasAuthenticationPolicyFailureEvent extends CasAuthenticationTransactionFailureEvent {
    private static final long serialVersionUID = 2208076621158767073L;
    private final Authentication authentication;
    private final ClientInfoDTO clientInfoDTO;

    public CasAuthenticationPolicyFailureEvent(final Object source,
                                               final Map<String, Throwable> failures,
                                               final AuthenticationTransaction transaction,
                                               final Authentication authentication) {
        super(source, failures, transaction.getCredentials());
        this.authentication = authentication;
        this.clientInfoDTO = new ClientInfoDTO(ClientInfoHolder.getClientInfo());
    }
}
