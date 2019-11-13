package com.lottery.app.helperPackages.networking;

public class ApiManager {

    private volatile static ApiManager apiManagerInstance = new ApiManager();

    public static ApiManager getInstance() {
        if (apiManagerInstance == null) {
            synchronized (ApiManager.class){
                apiManagerInstance = new ApiManager();
            }
        }
        return apiManagerInstance;
    }

    private ApiManager() { }
}