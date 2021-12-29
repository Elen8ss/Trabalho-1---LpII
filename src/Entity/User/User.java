package Entity.User;

import java.util.ArrayList;

import Entity.Reports.ReportDay;

public class User {
	private String name;
    private float height;
    private float weight;
    private float fatPercentage;
    private ArrayList<ReportDay> reports = new ArrayList<>();
    
    public User(String name, float height, float weight, float fatPercentage) {
    	this.name = name;
        this.height = height;
        this.weight = weight;
        this.fatPercentage = fatPercentage;
        reports = new ArrayList<>();
    }
    
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public float getFatPercentage() {
        return fatPercentage;
    }
    public void setFatPercentage(float fatPercentage) {
        this.fatPercentage = fatPercentage;
    }
    
 // Procura um relatório de um dia e retorna se existir
    public ReportDay getReportyDay(String date) {
        for (ReportDay r : reports) {
            if (r.getDate().equals(date)) {
                return r;
            }
        }
        return null;
    }
    
    // Recebe os valores referentes ao dados e os salvas no ArrayList reports
    public void generateReport(String name, String date, float time, float calories) {
    	   
        ReportDay reportDay = getReportyDay(date);

        // Se essa data já estiver cadastrada o if é verdadeiro
        if (reportDay != null) {
            // Se o exercício já estiver sido cadastrado o if é verdadeiro
            if (reportDay.isExerciseRegistered(name)) {
                reportDay.addTimeAndCalories(name, time, calories);
            } else {
                reportDay.addExercises(name, time, calories);
            }
        } else {
            reports.add(new ReportDay(date, name, time, calories));
        }
    }
    
    public String getValueReportDay(String date) {
        ReportDay reportDay = getReportyDay(date);
        if (reportDay != null) {
            return reportDay.toString();
        } else {
            return " Nenhum exercício cadastrado para esse dia";
        }
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
