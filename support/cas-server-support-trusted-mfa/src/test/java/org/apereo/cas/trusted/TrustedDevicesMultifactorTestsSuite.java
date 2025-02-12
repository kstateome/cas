package org.apereo.cas.trusted;

import org.apereo.cas.trusted.authentication.keys.DefaultMultifactorAuthenticationTrustRecordKeyGeneratorTests;
import org.apereo.cas.trusted.authentication.storage.InMemoryMultifactorAuthenticationTrustStorageTests;
import org.apereo.cas.trusted.authentication.storage.JsonMultifactorAuthenticationTrustStorageTests;
import org.apereo.cas.trusted.authentication.storage.MultifactorAuthenticationTrustStorageCleanerTests;
import org.apereo.cas.trusted.authentication.storage.fingerprint.ClientIpDeviceFingerprintComponentManagerTests;
import org.apereo.cas.trusted.authentication.storage.fingerprint.DefaultDeviceFingerprintStrategyTests;
import org.apereo.cas.trusted.authentication.storage.fingerprint.DeviceFingerprintComponentManagerTests;
import org.apereo.cas.trusted.authentication.storage.fingerprint.GeoLocationDeviceFingerprintComponentManagerTests;
import org.apereo.cas.trusted.authentication.storage.fingerprint.UserAgentDeviceFingerprintComponentManagerTests;
import org.apereo.cas.trusted.config.MultifactorAuthnTrustedDeviceFingerprintConfigurationTests;
import org.apereo.cas.trusted.web.MultifactorAuthenticationTrustReportEndpointTests;
import org.apereo.cas.trusted.web.flow.MultifactorAuthenticationPrepareTrustDeviceViewActionTests;
import org.apereo.cas.trusted.web.flow.MultifactorAuthenticationSetTrustActionTests;
import org.apereo.cas.trusted.web.flow.MultifactorAuthenticationVerifyTrustActionTests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * This is {@link TrustedDevicesMultifactorTestsSuite}.
 *
 * @author Misagh Moayyed
 * @since 5.3.0
 */
@SelectClasses({
    ClientIpDeviceFingerprintComponentManagerTests.class,
    UserAgentDeviceFingerprintComponentManagerTests.class,
    JsonMultifactorAuthenticationTrustStorageTests.class,
    InMemoryMultifactorAuthenticationTrustStorageTests.class,
    MultifactorAuthenticationVerifyTrustActionTests.class,
    DefaultDeviceFingerprintStrategyTests.class,
    DeviceFingerprintComponentManagerTests.class,
    MultifactorAuthnTrustedDeviceFingerprintConfigurationTests.class,
    MultifactorAuthenticationTrustReportEndpointTests.class,
    DefaultMultifactorAuthenticationTrustRecordKeyGeneratorTests.class,
    MultifactorAuthenticationSetTrustActionTests.class,
    MultifactorAuthenticationPrepareTrustDeviceViewActionTests.class,
    GeoLocationDeviceFingerprintComponentManagerTests.class,
    MultifactorAuthenticationTrustStorageCleanerTests.class
})
@Suite
public class TrustedDevicesMultifactorTestsSuite {
}
