package data_access;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

/**
 * The Plaid API keys: client id and secret.
 *
 * <p>Each key is read from an environment variable first and, if that isn't set, from the local
 * {@code plaid.properties} file in the folder the app is started from (the project folder in IntelliJ).
 * Git ignores that file; copy {@code plaid.properties.example} to create it.
 */
public final class PlaidCredentials {

    /** Name of the client id, both as an environment variable and as a key in the properties file. */
    public static final String CLIENT_ID = "PLAID_CLIENT_ID";

    /** Name of the secret, both as an environment variable and as a key in the properties file. */
    public static final String SECRET = "PLAID_SECRET";

    /** The local file holding the keys, relative to the folder the app is started from. */
    public static final Path DEFAULT_FILE = Path.of("plaid.properties");

    private final String clientId;
    private final String secret;

    private PlaidCredentials(String clientId, String secret) {
        this.clientId = clientId;
        this.secret = secret;
    }

    /**
     * Reads the keys from the environment, falling back to {@link #DEFAULT_FILE}.
     *
     * @return the keys
     * @throws IllegalStateException if a key is in neither place, or the file exists but can't be read
     */
    public static PlaidCredentials load() {
        return fromSources(System.getenv(), readFile(DEFAULT_FILE));
    }

    /**
     * Picks each key from {@code environment} if it is set there, otherwise from {@code file}.
     *
     * @param environment environment variables, by name
     * @param file the contents of the properties file (empty if there is no file)
     * @return the keys, with surrounding spaces removed
     * @throws IllegalStateException if a key is in neither place
     */
    static PlaidCredentials fromSources(Map<String, String> environment, Properties file) {
        return new PlaidCredentials(find(CLIENT_ID, environment, file), find(SECRET, environment, file));
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret() {
        return secret;
    }

    /** Leaves the secret out, so printing or logging this object never leaks it. */
    @Override
    public String toString() {
        return "PlaidCredentials[clientId=" + clientId + ", secret=(hidden)]";
    }

    private static String find(String name, Map<String, String> environment, Properties file) {
        String value = environment.get(name);
        if (isBlank(value)) {
            value = file.getProperty(name);
        }
        if (isBlank(value)) {
            throw new IllegalStateException(name + " is not set. Set it as an environment variable, or copy "
                    + "plaid.properties.example to plaid.properties and fill it in.");
        }
        return value.trim();
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private static Properties readFile(Path path) {
        final Properties properties = new Properties();
        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                properties.load(reader);
            } catch (IOException exception) {
                throw new IllegalStateException("Could not read " + path.toAbsolutePath(), exception);
            }
        }
        return properties;
    }
}
