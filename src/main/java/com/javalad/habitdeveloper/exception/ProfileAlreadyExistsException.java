package com.javalad.habitdeveloper.exception;

/**
 * @author KotovDV
 */
public class ProfileAlreadyExistsException extends Exception {

    private String forbiddenName;

    public ProfileAlreadyExistsException(String forbiddenName) {
        super("Profile with name " + forbiddenName + " already exists!");
        this.forbiddenName = forbiddenName;
    }

    public String getForbiddenName() {
        return forbiddenName;
    }
}
