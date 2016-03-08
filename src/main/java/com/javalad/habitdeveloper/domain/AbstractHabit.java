package com.javalad.habitdeveloper.domain;

/**
 * @author KotovDV
 */
public abstract class AbstractHabit {

    protected long id;
    protected String name;
    protected String description;
    protected long profileId;
    protected String cronExpression;


    protected AbstractHabit() {
    }

    public AbstractHabit(String name, String description, long profileId, String cronExpression) {
        this.name = name;
        this.description = description;
        this.profileId = profileId;
        this.cronExpression = cronExpression;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
