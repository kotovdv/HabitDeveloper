package com.javalad.habitdeveloper.domain;

import java.util.Date;

/**
 * @author KotovDV
 */
public class MeasuredHabitHistory {
    private Long id;
    private Long measuredHabitId;
    private Date checkDate;
    private Double measuredValue;


    protected MeasuredHabitHistory() {
    }

    public MeasuredHabitHistory(Long measuredHabitId, Date checkTime, Double measuredValue) {
        this.measuredHabitId = measuredHabitId;
        this.checkDate = checkTime;
        this.measuredValue = measuredValue;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeasuredHabitId() {
        return measuredHabitId;
    }

    public void setMeasuredHabitId(Long measuredHabitId) {
        this.measuredHabitId = measuredHabitId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(Double measuredValue) {
        this.measuredValue = measuredValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasuredHabitHistory history = (MeasuredHabitHistory) o;

        if (measuredHabitId != null ? !measuredHabitId.equals(history.measuredHabitId) : history.measuredHabitId != null)
            return false;
        if (checkDate != null ? !checkDate.equals(history.checkDate) : history.checkDate != null) return false;
        return measuredValue != null ? measuredValue.equals(history.measuredValue) : history.measuredValue == null;

    }

    @Override
    public int hashCode() {
        int result = measuredHabitId != null ? measuredHabitId.hashCode() : 0;
        result = 31 * result + (checkDate != null ? checkDate.hashCode() : 0);
        result = 31 * result + (measuredValue != null ? measuredValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MeasuredHabitHistory{");
        sb.append("id=").append(id);
        sb.append(", measuredHabitId=").append(measuredHabitId);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", measuredValue=").append(measuredValue);
        sb.append('}');
        return sb.toString();
    }
}
