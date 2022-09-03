package com.plantity.server.util;


public class KeyService {
    private static String baseURL = "http://localhost:8080";
    private static String RESTAPIKey = "538dc8c76ce7fafe94d53adbf71b98db";

    private static String secretKey = "KS8KZCFDhPKxOPQvpsOE4DNtZcXa8QHU";

    public static String getBaseURL(){
        return baseURL;
    }

    public static String getRESTAPIKey() {
        return RESTAPIKey;
    }

    public static String getSecretKey() {
        return secretKey;
    }
}
