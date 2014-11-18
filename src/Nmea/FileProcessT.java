/**
 * 
 */
package Nmea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Angelo Pingo
 *
 */
public class FileProcessT {

	/**
	 * 
	 */
	public FileProcessT() {
		
		String filename = "files/1-trn.DATA";
		int [][] experience = null;
		List <TrainnerExperience> listExpTrue = new ArrayList<TrainnerExperience>();
		List <TrainnerExperience> listExpFalse = new ArrayList<TrainnerExperience>();
		
		
		try {
			FileReader file = new FileReader(filename);
			BufferedReader reader = new BufferedReader(file);
			String line;
			String[] words;
			

			while ((line = reader.readLine()) != null) {

				words = line.split(",");
				TrainnerExperience f = new TrainnerExperience(words);
				if (words[words.length].trim().equals("1"))
					listExpTrue.add(f);
				else
					listExpFalse.add(f);
				
			}
		

			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileProcessT file = new FileProcessT();

	}

}
