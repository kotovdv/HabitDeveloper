package com.javalad.habitdeveloper.domain;

import java.util.Date;

/**
 * @author KotovDV
 */
public class CheckedHabitHistory {

    private long id;
    private long checkedHabitId;
    private Date checkDate;
    private boolean checkFlag;

    protected CheckedHabitHistory(){

    }

    public CheckedHabitHistory(long checkedHabitId, Date checkDate, boolean checkFlag) {
        this.checkedHabitId = checkedHabitId;
        this.checkDate = checkDate;
        this.checkFlag = checkFlag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCheckedHabitId() {
        return checkedHabitId;
    }

    public void setCheckedHabitId(long checkedHabitId) {
        this.checkedHabitId = checkedHabitId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckedHabitHistory that = (CheckedHabitHistory) o;

        if (checkedHabitId != that.checkedHabitId) return false;
        if (checkFlag != that.checkFlag) return false;
        return checkDate.equals(that.checkDate);

    }

    @Override
    public int hashCode() {
        int result = (int) (checkedHabitId ^ (checkedHabitId >>> 32));
        result = 31 * result + checkDate.hashCode();
        result = 31 * result + (checkFlag ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckedHabitHistory{");
        sb.append("id=").append(id);
        sb.append(", checkedHabitId=").append(checkedHabitId);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", checkFlag=").append(checkFlag);
        sb.append('}');
        return sb.toString();
    }
}
