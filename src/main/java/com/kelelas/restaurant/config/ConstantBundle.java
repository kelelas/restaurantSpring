package com.kelelas.restaurant.config;

import java.util.ResourceBundle;

public class ConstantBundle {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("constants");
    private ConstantBundle() {
    }
    public static String getStringProperty(String key) {
        return resourceBundle.getString(key);
    }
    public static int getIntProperty(String key) {
        return Integer.parseInt(resourceBundle.getString(key));
    }
}
