package com.example.gymgenius;

public class StretchingExercise {
    private int repetitions;
    private int sets;
    private int weight;
    private String notes;

    // Empty constructor required for Firebase
    public StretchingExercise() {
    }

    public StretchingExercise(int repetitions, int sets, int weight, String notes) {
        this.repetitions = repetitions;
        this.sets = sets;
        this.weight = weight;
        this.notes = notes;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
