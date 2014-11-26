package NaiveBayes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NaiveBayesFile {

	private List<Data> data;

	public NaiveBayesFile(List<Data> data) {
		this.data = data;
	}

	public NaiveBayesFile(String path, boolean withResult) {
		this.data = readFile(path, withResult);
	}

	public List<Data> getNaiveBayesFile() {
		return this.data;
	}

	public static List<Data> readFile(String path, boolean withResult) {

		List<Data> dataList = new ArrayList<Data>();
		FileReader file;
		try {
			file = new FileReader(path);
		
		BufferedReader reader = new BufferedReader(file);
		String line;

		while ((line = reader.readLine()) != null) {
			Data data = new Data(line, withResult);
			dataList.add(data);
		}
		
		reader.close();
		file.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataList;

	}

	public void writeFile(String path) {

		FileWriter file;
		BufferedWriter writer;
		try {

			file = new FileWriter(path);
			writer = new BufferedWriter(file);

			for(Data data : this.data) {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < data.getSizeEntrys(); i++) {
					sb.append(data.getIndex(i));
					sb.append(",");
				}
				sb.append(" ");
				sb.append(data.getResult());
				sb.append("\n");

				writer.append(sb.toString());

			}

			writer.close();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}