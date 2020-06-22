import java.util.ArrayList;
//TODO: getter for particular cell
public class Group {

	private ArrayList<Integer> set = new ArrayList<Integer>();
	private ArrayList<Integer> rounds = new ArrayList<Integer>();
	private ArrayList<Integer> reps = new ArrayList<Integer>();
	private ArrayList<Integer> distance = new ArrayList<Integer>();
	private ArrayList<String> description = new ArrayList<String>();
	private ArrayList<Type> type = new ArrayList<Type>();
	private ArrayList<Integer> minutes = new ArrayList<Integer>();
	private ArrayList<Integer> seconds = new ArrayList<Integer>();
	private ArrayList<Integer> intensity = new ArrayList<Integer>();
	
	enum Type {
		Warm_Up, Fly, Free, Back, Breast, IM, Drill, Kick, Starts, Sprint, Loosen, N_A_V
	}
	
	enum Name {
		set, rounds, reps, distance, description, type, minutes, seconds, intensity
	}
	public void addRow (int set, int rounds, int reps, int distance, String description, Type type, int minutes, int seconds, int intensity) {
		this.set.add(new Integer(set));
		this.rounds.add(new Integer(rounds));
		this.reps.add(new Integer(reps));
		this.distance.add(new Integer(distance));
		this.description.add(description);
		this.type.add(type);
		this.minutes.add(new Integer(minutes));
		this.seconds.add(new Integer(seconds));
		this.intensity.add(new Integer(intensity));
	}
	
	public void createRow (int numRows) {
		for (int i = 0; i < numRows; i++) {
			addRow(0, 1, 0, 0, "", Type.N_A_V, 0, 0, 0);
		}
	}
	
	public void editCell(int row, Name column, Object element) {
		if (row >= intensity.size()) {
			System.out.println("uh-oh");
			createRow(row - intensity.size() + 1);
		}
		switch (column) {
		case set:
			set.set(row, (Integer)element);
		case rounds:
			rounds.set(row, (Integer)element);
		case reps:
			reps.set(row, (Integer)element);
		case distance:
			distance.set(row, (Integer)element);
		case description:
			description.set(row, (String)element);
		case type:
			type.set(row, (Type)element);
		case minutes:
			minutes.set(row, (Integer)element);
		case seconds:
			seconds.set(row, (Integer)element);
		case intensity:
			intensity.set(row, (Integer)element);
		}
	}
	
	public Object getCell(int row, Name column) {
		switch (column) {
		case set:
			return set.get(row);
		case rounds:
			return rounds.get(row);
		case reps:
			return reps.get(row);
		case distance:
			return distance.get(row);
		case description:
			return description.get(row);
		case type:
			return type.get(row);
		case minutes:
			return minutes.get(row);
		case seconds:
			return seconds.get(row);
		case intensity:
			return intensity.get(row);
		}
		return "bad";
	}
	public int totalDistance() {
		int sum = 0;
		for (int i = 0; i < intensity.size(); i++) {
			sum += rounds.get(i).intValue()* reps.get(i).intValue() * distance.get(i).intValue();
		}
		return sum;
	}
	
	public String totalTime() {
		int minutes = 0;
		int seconds = 0;
		for (int i = 0; i < intensity.size(); i++) {
			minutes += rounds.get(i).intValue() * reps.get(i).intValue() * this.minutes.get(i).intValue();
			seconds += rounds.get(i).intValue() * reps.get(i).intValue() * this.seconds.get(i).intValue();
		}
		minutes += seconds / 60;
		seconds = seconds % 60;
		return minutes + ":" + seconds;
	}
	
	public double avgIntensity() {
		int avg = 0;
		int sum = 0;
		for (int i = 0; i < intensity.size(); i++) {
			avg += rounds.get(i).intValue()* reps.get(i).intValue() * intensity.get(i).intValue();
			sum += rounds.get(i).intValue()* reps.get(i).intValue() * distance.get(i).intValue();
		}
		return avg / sum;
	}
	public int workingDistance() {
		int sum = 0;
		for (int i = 0; i < intensity.size(); i++) {
			if (type.get(i) != Type.Warm_Up || type.get(i) != Type.Loosen)
				sum += rounds.get(i).intValue()* reps.get(i).intValue() * distance.get(i).intValue();
		}
		return sum;
	}
	
	public String workingTime() {
		int minutes = 0;
		int seconds = 0;
		for (int i = 0; i < intensity.size(); i++) {
			if (type.get(i) != Type.Warm_Up || type.get(i) != Type.Loosen) {
				minutes += rounds.get(i).intValue() * reps.get(i).intValue() * this.minutes.get(i).intValue();
				seconds += rounds.get(i).intValue() * reps.get(i).intValue() * this.seconds.get(i).intValue();
			}
		}
		minutes += seconds / 60;
		seconds = seconds % 60;
		
		return minutes + ":" + seconds;
	}
	
	public double workingIntensity() {
		int avg = 0;
		int sum = 0;
		for (int i = 0; i < intensity.size(); i++) {
			if (type.get(i) != Type.Warm_Up || type.get(i) != Type.Loosen) {
				avg += rounds.get(i).intValue()* reps.get(i).intValue() * intensity.get(i).intValue();
				sum += rounds.get(i).intValue()* reps.get(i).intValue() * distance.get(i).intValue();
			}
			
		}
		return avg / sum;
	}

}
