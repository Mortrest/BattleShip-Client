package client.util;

import client.network.ClientManager;

import java.io.IOException;

public class Manager {
    static public ClientManager clientManager;
    static public String spectateName;
    static public String AuthToken;
    public Manager() throws IOException {
        clientManager = new ClientManager();
        clientManager.start();
    }

    public static String getAuthToken() {
        return AuthToken;
    }

    public static void setAuthToken(String authToken) {
        AuthToken = authToken;
    }

    public static String getSpectateName() {
        return spectateName;
    }

    public static void setSpectateName(String spectateName) {
        Manager.spectateName = spectateName;
    }

    public static ClientManager getClientManager() {
        return clientManager;
    }
}
