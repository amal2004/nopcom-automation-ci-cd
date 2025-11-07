package nopcom.autm.config;


import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();
    static {
        try (InputStream is = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String getBaseUrl() {
        String env = get("env");
        if ("local".equalsIgnoreCase(env)) return get("local.url");
        return get("public.url");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicit.wait"));
    }
}
