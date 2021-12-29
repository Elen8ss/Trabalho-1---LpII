package Entity.Reports;

public class ReportExercise {
    private String name;
    private float time;
    private float calories;
    
    public ReportExercise(String name, float time, float calories) {
        this.name = name;
        this.time = time;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "\n" + name+" | tempo: " + time + " minutos |" +
        " calorias: " + calories + " Kcal";
    }
}
