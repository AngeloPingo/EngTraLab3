/**
 * 
 */
package NaiveBayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Angelo Pingo
 *
 */
public class FileProcessT {

	List<TreeMap<Integer, Integer>> listExpTrue;
	List <TreeMap<Integer, Integer>> listExpFalse;

	String filename;

	public FileProcessT(String filename) {
		this.filename = filename;
	}

	public void readFile() {

		listExpTrue = new ArrayList<TreeMap<Integer, Integer>>();
		listExpFalse = new ArrayList<TreeMap<Integer, Integer>>();


		try {
			FileReader file = new FileReader(filename);
			BufferedReader reader = new BufferedReader(file);
			String line;
			String[] words;


			while ((line = reader.readLine()) != null) {

				words = line.split(",");
				if (words[words.length - 1].trim().equals("1")) {

					for(int i = 0; i < words.length; i++) {
						if(listExpTrue.size() <= i) {
							listExpTrue.add(i, new TreeMap<Integer, Integer>());
							listExpTrue.get(i).put(Integer.parseInt(words[i].trim()), new Integer(1));
						} else {
							if(listExpTrue.get(i).get(Integer.parseInt(words[i].trim())) == null) {
								listExpTrue.get(i).put(Integer.parseInt(words[i].trim()), new Integer(1));
							} else {
								Integer integer = listExpTrue.get(i).get(Integer.parseInt(words[i].trim()));
								int inteiro = integer.intValue();
								inteiro++;
								listExpTrue.get(i).put(Integer.parseInt(words[i].trim()), new Integer(inteiro));
							}
						}

					}
					
				} else {
					for(int i = 0; i < words.length; i++) {

						if(listExpFalse.size() <= i) {
							listExpFalse.add(i, new TreeMap<Integer, Integer>());
							listExpFalse.get(i).put(Integer.parseInt(words[i].trim()), new Integer(1));
						} else {
							if(listExpFalse.get(i).get(Integer.parseInt(words[i].trim())) == null) {
								listExpFalse.get(i).put(Integer.parseInt(words[i].trim()), new Integer(1));
							} else {
								Integer integer = listExpFalse.get(i).get(Integer.parseInt(words[i].trim()));
								int inteiro = integer.intValue();
								inteiro++;
								listExpFalse.get(i).put(Integer.parseInt(words[i].trim()), new Integer(inteiro));
							}
							
						}

					}

				}

			}

			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printPretty(TreeMap<Integer, Integer> tm, int column) {
		
		
		for(Entry<Integer, Integer> entry : tm.entrySet()) {
			
			System.out.println("C: " + column + " K:  " + entry.getKey().toString() + " V: " + entry.getValue().toString());
			
		}
		
	}
	
	public void printList(List<TreeMap<Integer, Integer>> lista) {
		int count = 0;
		for(TreeMap<Integer, Integer> value : lista) {
			printPretty(value, count);
			count++;
		}	
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileProcessT file = new FileProcessT("files/1-trn.data");
		file.readFile();

		System.out.println("TRUE");
		file.printList(file.listExpTrue);
		System.out.println("FALSE");
		file.printList(file.listExpFalse);
		

		

	}

}
