package com.javalad.habitdeveloper.domain;

import java.util.Date;

/**
 * @author KotovDV
 */
public class CheckedHabitHistory {

    private Long id;
    private Long checkedHabitId;
    private Date checkDate;
    private Boolean checkFlag;

    protected CheckedHabitHistory(){

    }

    public CheckedHabitHistory(Long checkedHabitId, Date checkDate, Boolean checkFlag) {
        this.checkedHabitId = checkedHabitId;
        this.checkDate = checkDate;
        this.checkFlag = checkFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheckedHabitId() {
        return checkedHabitId;
    }

    public void setCheckedHabitId(Long checkedHabitId) {
        this.checkedHabitId = checkedHabitId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckedHabitHistory that = (CheckedHabitHistory) o;

        if (checkedHabitId != null ? !checkedHabitId.equals(that.checkedHabitId) : that.checkedHabitId != null)
            return false;
        if (checkDate != null ? !checkDate.equals(that.checkDate) : that.checkDate != null) return false;
        return checkFlag != null ? checkFlag.equals(that.checkFlag) : that.checkFlag == null;

    }

    @Override
    public int hashCode() {
        int result = checkedHabitId != null ? checkedHabitId.hashCode() : 0;
        result = 31 * result + (checkDate != null ? checkDate.hashCode() : 0);
        result = 31 * result + (checkFlag != null ? checkFlag.hashCode() : 0);
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
