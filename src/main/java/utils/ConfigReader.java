package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties NOT found");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getBaseUrl() {
        return getProperty("base.url", "");
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "15"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "5"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
}