package data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;

class PlaidCredentialsTest {

    private static Properties file(String clientId, String secret) {
        final Properties properties = new Properties();
        properties.setProperty(PlaidCredentials.CLIENT_ID, clientId);
        properties.setProperty(PlaidCredentials.SECRET, secret);
        return properties;
    }

    @Test
    void environmentVariablesWinOverTheFile() {
        final Map<String, String> environment = Map.of(
                PlaidCredentials.CLIENT_ID, "env-id", PlaidCredentials.SECRET, "env-secret");

        final PlaidCredentials credentials = PlaidCredentials.fromSources(environment, file("file-id", "file-secret"));

        assertEquals("env-id", credentials.getClientId());
        assertEquals("env-secret", credentials.getSecret());
    }

    @Test
    void fileIsUsedWhenEnvironmentVariablesAreMissingOrBlank() {
        final Map<String, String> environment = Map.of(PlaidCredentials.CLIENT_ID, "  ");

        final PlaidCredentials credentials = PlaidCredentials.fromSources(environment, file("file-id", "file-secret"));

        assertEquals("file-id", credentials.getClientId());
        assertEquals("file-secret", credentials.getSecret());
    }

    @Test
    void surroundingSpacesAreRemoved() {
        final PlaidCredentials credentials = PlaidCredentials.fromSources(Map.of(), file(" id ", "secret   "));

        assertEquals("id", credentials.getClientId());
        assertEquals("secret", credentials.getSecret());
    }

    @Test
    void missingKeyIsReportedByName() {
        final Properties onlyClientId = new Properties();
        onlyClientId.setProperty(PlaidCredentials.CLIENT_ID, "file-id");

        final IllegalStateException error = assertThrows(IllegalStateException.class,
                () -> PlaidCredentials.fromSources(Map.of(), onlyClientId));

        assertTrue(error.getMessage().contains(PlaidCredentials.SECRET));
    }

    @Test
    void toStringHidesTheSecret() {
        final PlaidCredentials credentials = PlaidCredentials.fromSources(Map.of(), file("file-id", "top-secret"));

        assertFalse(credentials.toString().contains("top-secret"));
    }
}
