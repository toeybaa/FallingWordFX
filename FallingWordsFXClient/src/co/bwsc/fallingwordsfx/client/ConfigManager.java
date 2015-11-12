package co.bwsc.fallingwordsfx.client;

import java.io.*;

/**
 * @author Benjapol Worakan
 * @version 2015.11.12
 */
public class ConfigManager implements Serializable {

    public static final ConfigManager CFG = loadConfiguration();

    public static void initialize() {
        saveConfiguration();
    }

    public static ConfigManager loadConfiguration() {
        System.out.println("Loading configuration file...");
        ConfigManager configManager = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./localConfig.cfg"));
            configManager = (ConfigManager) ois.readObject();
        } catch (IOException e) {
            System.out.println("Configuration file not found!");
            e.printStackTrace();
            configManager = createDefaultConfiguration();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return configManager;
    }

    public static ConfigManager createDefaultConfiguration() {
        System.out.println("Creating a new one with default settings...");

        // Default settings /////
        String applicationName = "FallingWords FX";
        String serverURL = "127.0.0.1";
        int serverPort = 11123;
        boolean firstLaunch = true;
        String userName = "Local Player";
        ////////////////////////

        return new ConfigManager(applicationName, serverURL, serverPort, firstLaunch, userName);
    }

    public static void saveConfiguration() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./localConfig.cfg"));
            oos.writeObject(CFG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String applicationName;
    private String serverURL;
    private int serverPort;
    private boolean firstLaunch;
    private String userName;

    private ConfigManager(String applicationName, String serverURL, int serverPort, boolean firstLaunch, String userName) {
        this.applicationName = applicationName;
        this.serverURL = serverURL;
        this.serverPort = serverPort;
        this.firstLaunch = firstLaunch;
        this.userName = userName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getServerURL() {
        return serverURL;
    }

    public int getServerPort() {
        return serverPort;
    }

    public boolean isFirstLaunch() {
        return firstLaunch;
    }

    public String getUserName() {
        return userName;
    }

    public String toString() {
        return (
                "[Configuration Details]" + "\n" +
                        "Application Name: " + applicationName + "\n" +
                        "Server URL: " + serverURL + "\n" +
                        "Server Port: " + serverPort + "\n" +
                        "First Launch: " + firstLaunch + "\n" +
                        "Player Name: " + userName + "\n"
        );
    }

}
