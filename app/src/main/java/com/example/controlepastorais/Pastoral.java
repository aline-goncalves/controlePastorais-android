package com.example.controlepastorais;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pastorals")
public class Pastoral {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String coordinator;
    private boolean isMovement;
    private String interestActivities;
    private String patronSaint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public boolean isMovement() {
        return isMovement;
    }

    public void setMovement(boolean movement) {
        isMovement = movement;
    }

    public String getInterestActivities() {
        return interestActivities;
    }

    public void setInterestActivities(String interestActivities) {
        this.interestActivities = interestActivities;
    }

    public String getPatronSaint() {
        return patronSaint;
    }

    public void setPatronSaint(String patronSaint) {
        this.patronSaint = patronSaint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
