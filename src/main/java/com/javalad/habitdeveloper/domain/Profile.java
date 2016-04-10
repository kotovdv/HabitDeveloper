package com.javalad.habitdeveloper.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KotovDV
 */
public class Profile {

    private Long id;
    private String name;
    private String description;
    private List<CheckedHabit> checkedHabits = new ArrayList<>();
    private List<MeasuredHabit> measuredHabits = new ArrayList<>();

    protected Profile() {
    }

    public Profile(String name, String description) {
        this.name = name;
        this.description = description;
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

    public List<CheckedHabit> getCheckedHabits() {
        return checkedHabits;
    }

    public void setCheckedHabits(List<CheckedHabit> checkedHabits) {
        this.checkedHabits = checkedHabits;
    }

    public List<MeasuredHabit> getMeasuredHabits() {
        return measuredHabits;
    }

    public void setMeasuredHabits(List<MeasuredHabit> measuredHabits) {
        this.measuredHabits = measuredHabits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return name != null ? name.equals(profile.name) : profile.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
