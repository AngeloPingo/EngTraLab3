package Nmea;

public class TrainnerExperience {
	
	int [] data;
	int result;

	public TrainnerExperience(String words[]) {
		
		int i = 0;
		this.data = new int[words.length-1];
		
		for (i = 0; i < (words.length-1); i++) {
			this.data[i] = Integer.parseInt(words[i]);			
		}
		
		this.result = Integer.parseInt(words[i].trim());
		
	}
	
	public String toString() {
		String result = "";
		for (int value : data) {
			result+=value + ",";
		}
		result+= " " + this.result + "\n";
		return result;
	}

}
