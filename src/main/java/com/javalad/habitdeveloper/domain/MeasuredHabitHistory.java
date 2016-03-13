package com.javalad.habitdeveloper.domain;

import java.util.Date;

/**
 * @author KotovDV
 */
public class MeasuredHabitHistory {
    private long id;
    private long measuredHabitId;
    private Date checkDate;
    private double measuredValue;


    protected MeasuredHabitHistory() {
    }

    public MeasuredHabitHistory(long measuredHabitId, Date checkTime, double measuredValue) {
        this.measuredHabitId = measuredHabitId;
        this.checkDate = checkTime;
        this.measuredValue = measuredValue;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMeasuredHabitId() {
        return measuredHabitId;
    }

    public void setMeasuredHabitId(long measuredHabitId) {
        this.measuredHabitId = measuredHabitId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof MeasuredHabitHistory)) return false;

        MeasuredHabitHistory that = (MeasuredHabitHistory) o;

        if (measuredHabitId != that.measuredHabitId) return false;
        if (Double.compare(that.measuredValue, measuredValue) != 0) return false;
        return checkDate.equals(that.checkDate);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (measuredHabitId ^ (measuredHabitId >>> 32));
        result = 31 * result + checkDate.hashCode();
        temp = Double.doubleToLongBits(measuredValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
