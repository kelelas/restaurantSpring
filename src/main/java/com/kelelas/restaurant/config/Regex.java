package com.kelelas.restaurant.config;

public interface Regex {
    String NAME_REGEX = "[A-Z]{1}[a-z]{1,15}";
    String NAME_UKR_REGEX = "[A-ЯЁҐІЇЄ]{1}[а-яёґіїє']{1,15}";
    String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    String PASSWORD_REGEX = "[a-zA-Z0-9]{8,20}";
}
