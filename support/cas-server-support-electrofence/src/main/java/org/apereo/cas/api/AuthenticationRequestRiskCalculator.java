package org.apereo.cas.api;

import org.apereo.cas.authentication.Authentication;
import org.apereo.cas.services.RegisteredService;
import org.apereo.cas.support.events.dao.CasEvent;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This is {@link AuthenticationRequestRiskCalculator}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
@FunctionalInterface
public interface AuthenticationRequestRiskCalculator {
    /**
     * Highest risk score for an authn request.
     */
    BigDecimal HIGHEST_RISK_SCORE = BigDecimal.ONE;

    /**
     * Lowest risk score for an authn request.
     */
    BigDecimal LOWEST_RISK_SCORE = BigDecimal.ZERO;

    /**
     * Calculate authentication risk score.
     *
     * @param authentication the authentication
     * @param service        the service
     * @param request        the request
     * @param events         the events
     * @return the authentication risk score
     */
    AuthenticationRiskScore calculate(Authentication authentication,
                                      RegisteredService service,
                                      HttpServletRequest request,
                                      Supplier<Stream<? extends CasEvent>> events);
}
