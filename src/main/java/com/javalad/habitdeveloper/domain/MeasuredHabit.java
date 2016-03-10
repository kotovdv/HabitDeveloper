package com.javalad.habitdeveloper.domain;

import java.util.Date;

/**
 * @author KotovDV
 */
public class MeasuredHabit {

    private long id;
    private String name;
    private String description;
    private long profileId;
    private String cronExpression;
    private Date deadline;
    private Double deadlineValue;


    protected MeasuredHabit() {
    }

    public MeasuredHabit(String name, String description, long profileId, String cronExpression, Date deadline, double deadlineValue) {
        this.name = name;
        this.description = description;
        this.profileId = profileId;
        this.cronExpression = cronExpression;
        this.deadline = deadline;
        this.deadlineValue = deadlineValue;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Double getDeadlineValue() {
        return deadlineValue;
    }

    public void setDeadlineValue(Double deadlineValue) {
        this.deadlineValue = deadlineValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasuredHabit that = (MeasuredHabit) o;

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
        final StringBuilder sb = new StringBuilder("MeasuredHabit{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", profileId=").append(profileId);
        sb.append(", cronExpression='").append(cronExpression).append('\'');
        sb.append(", deadline=").append(deadline);
        sb.append(", deadlineValue=").append(deadlineValue);
        sb.append('}');
        return sb.toString();
    }
}
