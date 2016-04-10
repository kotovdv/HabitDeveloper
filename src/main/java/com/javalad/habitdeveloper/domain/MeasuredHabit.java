package com.javalad.habitdeveloper.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author KotovDV
 */
public class MeasuredHabit {

    private Long id;
    private String name;
    private String description;
    private Long profileId;
    private String cronExpression;
    private Date deadline;
    private Double deadlineValue;
    private List<MeasuredHabitHistory> habitHistories = new ArrayList<>();


    protected MeasuredHabit() {
    }

    public MeasuredHabit(String name, String description, Long profileId, String cronExpression, Date deadline, Double deadlineValue) {
        this.name = name;
        this.description = description;
        this.profileId = profileId;
        this.cronExpression = cronExpression;
        this.deadline = deadline;
        this.deadlineValue = deadlineValue;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
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

    public List<MeasuredHabitHistory> getHabitHistories() {
        return habitHistories;
    }

    public void setHabitHistories(List<MeasuredHabitHistory> habitHistories) {
        this.habitHistories = habitHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasuredHabit that = (MeasuredHabit) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return profileId != null ? profileId.equals(that.profileId) : that.profileId == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (profileId != null ? profileId.hashCode() : 0);
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
