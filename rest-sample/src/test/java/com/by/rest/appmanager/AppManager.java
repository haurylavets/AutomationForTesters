package com.by.rest.appmanager;

public class AppManager {

    private RestHelper restHelper;

    public RestHelper rest() {
        if (restHelper == null) {
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }
}
