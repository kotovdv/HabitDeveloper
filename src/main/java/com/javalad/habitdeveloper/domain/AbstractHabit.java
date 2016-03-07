package com.javalad.habitdeveloper.domain;

/**
 * @author KotovDV
 */
public abstract class AbstractHabit {

    protected long id;
    protected String name;
    protected String description;
    protected Profile profile;
    protected String cronExpression;

}
