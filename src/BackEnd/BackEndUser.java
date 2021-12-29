package BackEnd;
import java.util.ArrayList;

import Entity.Exercises.*;
import Entity.User.*;

public class BackEndUser {
    private ArrayList<Exercise> exercises;
    private ArrayList<User> users;

    // Contrutor que recebe os parametros da classe User para inicaliza-lá
    public BackEndUser() {
        users = new ArrayList<>();
        exercises = new ArrayList<>();

        exercises.add(new Abs());
        exercises.add(new Squats());
        exercises.add(new PushUps());
        exercises.add(new Bridge());
        exercises.add(new Burpees());
    }
    
    public boolean isRegistered(String name) {
    	if (getUser(name) != null) {
    		return true;
    	}
    	return false;
    }
    
    public User getUser(String name) {
    	for (User u : users) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
    	return null;
    }
    
    public boolean addUser(String name, float height, float weight, float fatPercentage) {
    	User user = getUser(name);
    	if (user == null) {
    		users.add(new User(name, height, weight, fatPercentage));
    		return true;
    	}
    	return false;
    }
    
    // Retorna o nome de todos os exercícos em array
    public String[] listExercises() {
        String[] nameExercises = new String[exercises.size()];
        for (int i = 0; i < exercises.size(); i++) {
            nameExercises[i] = exercises.get(i).getName();
        }
        return nameExercises;
    }

    // Recebe o tempo em minutos e cacula a quantidade de calorias
    public float calcCalories(User user, Exercise exercise, float time) {
        return exercise.calcCalories(user.getWeight(), time);
    }

    // Procura um exercício cadastrado
    public Exercise getExercise(String name) {
        for (Exercise s : exercises) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
    // Recebe os valores referentes ao dados e os salvas no ArrayList reports
    public boolean generateReport(String name, String nameExercise, String date, float time) {
       	Exercise exercise = getExercise(nameExercise);
       	User user = getUser(name);
       	
       	if (exercise != null && user != null) {
       		float calories = calcCalories(user, exercise, time);       		
       		
       		user.generateReport(nameExercise, date, time, calories);
       		return true;
       	}
        return false;
    }
    
    // Recebe uma data e retorna todos os valores referentes ao exercicios praticados
    public String getValueReportDay(String name, String date) {
        User user = getUser(name);
        if (user != null) {
        	return user.getValueReportDay(date);
        }
        return " Usuário não encontrado";
    }
}
