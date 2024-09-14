package dev.mraksimus.types;

//for second task
public enum TrainingIntensity {

    LOW(1), MEDIUM(2), HIGH(3);

    private final int value;

    TrainingIntensity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
