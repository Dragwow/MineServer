package com.pet_project.backend_server.util;

public final class ValidatorsUtil {

    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]+$";

    public static final String GITHUB_URL_REGEX = "^(https:\\/\\/)?(www\\.)?github\\.com\\/[A-Za-z0-9_-]{1,39}$";
}
