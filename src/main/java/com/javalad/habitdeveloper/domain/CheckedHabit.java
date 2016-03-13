package com.javalad.habitdeveloper.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KotovDV
 */
public class CheckedHabit {

    private long id;
    private String name;
    private String description;
    private long profileId;
    private String cronExpression;
    private List<CheckedHabitHistory> habitHistories = new ArrayList<>();

    protected CheckedHabit() {
    }

    public CheckedHabit(String name, String description, long profileId, String cronExpression) {
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

    public List<CheckedHabitHistory> getHabitHistories() {
        return habitHistories;
    }

    public void setHabitHistories(List<CheckedHabitHistory> habitHistories) {
        this.habitHistories = habitHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CheckedHabit)) return false;

        CheckedHabit that = (CheckedHabit) o;

        if (profileId != that.profileId) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) (profileId ^ (profileId >>> 32));
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckedHabit{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", profileId=").append(profileId);
        sb.append(", cronExpression='").append(cronExpression).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
