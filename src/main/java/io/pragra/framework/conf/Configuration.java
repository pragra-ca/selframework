package io.pragra.framework.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {
    private Properties properties ;
    private static Configuration instance;
    private Configuration() {
        properties = new Properties();
        try {
            Path path = Paths.get("src/main/resources","framework.properties");
            InputStream stream = new FileInputStream(path.toFile());
            properties.load(stream);
        } catch (IOException e) {
           e.printStackTrace();
        }

    }

    public static String getProperty(String key) {
        synchronized (Configuration.class) {
            if(instance == null) {
                instance  = new Configuration();
            }
            return instance.properties.getProperty(key);
        }

    }
}
