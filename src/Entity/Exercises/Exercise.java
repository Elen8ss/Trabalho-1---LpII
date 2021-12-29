package Entity.Exercises;

public abstract class Exercise {
    private String name;
    private float met;

    public float calcCalories(float weight, float time) {
        return this.met*weight*time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMet() {
        return met;
    }

    public void setMet(float met) {
        this.met = met;
    }
}