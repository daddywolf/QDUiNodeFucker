package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    public String getConfigFromProperties(String requestProperty) {
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream("/config/config.properties");
        String responseProperty = null;
        try {
            prop.load(in);
            responseProperty = prop.getProperty(requestProperty);
            System.out.println(prop.getProperty("READ_PROPERTIES_FILE_SUCCESSFUL"));
            return responseProperty;
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}