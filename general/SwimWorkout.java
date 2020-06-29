package general;
import java.util.ArrayList;

public class SwimWorkout {
	private ArrayList<Group> workout = new ArrayList<Group>();
	private int month;
	private int day;
	private int year;
	private String description;
	private boolean gameday;
	
	public SwimWorkout(int month, int day, int year, boolean gameday) {
		this.day = day;
		this.month = month;
		this.year = year; 
		this.gameday = gameday;
		addGroup(new Group());
	}
	
	public SwimWorkout(int month, int day, int year, boolean gameday, Object[][][] data) {
		this.day = day;
		this.month = month;
		this.year = year; 
		this.gameday = gameday;
		for (int i = 0; i < data.length; i++) {
			addGroup(new Group(data[i]));
		}
	}
	
	public void addGroup(Group g) {
		workout.add(g);
	}
	
	public Group getGroup(int index) {
		return workout.get(index);
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isGameday() {
		return gameday;
	}

	public void setGameday(boolean gameday) {
		this.gameday = gameday;
	}
	
		
}
