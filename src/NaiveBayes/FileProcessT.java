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

public class FileProcessT {

	List<TreeMap<Integer, Integer>> listExpTrue;
	List<TreeMap<Integer, Integer>> listExpFalse;
	int[][] totalPorColuna = null;
	int numColunas = 0;
	int maxValues = 0;
	Probabilidades prob = null;

	float[] probabilidadesTotais = new float[2];

	public int getNumColunas() {
		return numColunas;
	}

	String filename;

	public FileProcessT(String filename) {
		this.filename = filename;
	}

	public NaiveBayesFile readNaiveBayesFile() {

		List<Data> data = new ArrayList<Data>();
		FileReader file;
		BufferedReader reader;
		try {
			
			file = new FileReader(filename);
			reader = new BufferedReader(file);
			String line;
			String[] words;

			while ((line = reader.readLine()) != null) {
				
				Data lineData = new Data(line, true);
				data.add(lineData);

			}

			reader.close();
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return new NaiveBayesFile(data);
		
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
				if (words[words.length - 1].trim().equals("1")) { // Caso a
					// saida
					// seja true
					this.numColunas = words.length - 1;

					for (int i = 0; i < words.length; i++) {
						int value = Integer.parseInt(words[i].trim());

						if (maxValues<value){
							maxValues = value;
						}

						if (listExpTrue.size() <= i) {
							listExpTrue.add(i, new TreeMap<Integer, Integer>());
							listExpTrue.get(i).put(value, new Integer(1));
						} else {
							if (listExpTrue.get(i).get(value) == null) {
								listExpTrue.get(i).put(value,new Integer(1));
							} else {
								Integer integer = listExpTrue.get(i).get(value);
								int inteiro = integer.intValue();
								inteiro++;
								listExpTrue.get(i).put(value,new Integer(inteiro));
							}
						}
					}

				} else {
					for (int i = 0; i < words.length; i++) {

						int value = Integer.parseInt(words[i].trim());
						if (maxValues<value) {
							maxValues = value;
						}

						if (listExpFalse.size() <= i) {
							listExpFalse
							.add(i, new TreeMap<Integer, Integer>());
							listExpFalse.get(i).put(
									Integer.parseInt(words[i].trim()),
									new Integer(1));
						} else {
							if (listExpFalse.get(i).get(
									Integer.parseInt(words[i].trim())) == null) {
								listExpFalse.get(i).put(
										Integer.parseInt(words[i].trim()),
										new Integer(1));
							} else {
								Integer integer = listExpFalse.get(i).get(
										Integer.parseInt(words[i].trim()));
								int inteiro = integer.intValue();
								inteiro++;
								listExpFalse.get(i).put(
										Integer.parseInt(words[i].trim()),
										new Integer(inteiro));
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

	public void fillOddsTrue(List<TreeMap<Integer, Integer>> lista,
			Probabilidades prob) {
		int column = 0;
		float total;
		for (TreeMap<Integer, Integer> tm : lista) {
			total = totalPorColuna[1][column];
			for (Entry<Integer, Integer> entry : tm.entrySet()) {
				float probValue = entry.getValue() / total;
				if (column < numColunas) {
					prob.setProbabilidadeTrue(column, entry.getKey(),probValue);
				}
			}
			column++;
		}
	}

	public void fillOddsFalse(List<TreeMap<Integer, Integer>> lista,
			Probabilidades prob) {
		int column = 0;
		for (TreeMap<Integer, Integer> tm : lista) {
			float total = totalPorColuna[0][column];
			for (Entry<Integer, Integer> entry : tm.entrySet()) {
				if (column < numColunas) {
					prob.setProbabilidadeFalse(column, entry.getKey(),
							entry.getValue() / total);
				}
			}
			column++;
		}
	}

	public void fillTotalColumn(List<TreeMap<Integer, Integer>> lista, boolean btrue) {
		int column = 0;
		if (totalPorColuna==null) {
			totalPorColuna = new int[2][numColunas+1];
		}
		for (TreeMap<Integer, Integer> tm : lista) {
			for (Entry<Integer, Integer> entry : tm.entrySet()) {
				if (column < numColunas) {
					if (btrue) {
						if (entry.getKey() == 0) {
							totalPorColuna[1][column] = entry.getValue();
						}
						else {
							totalPorColuna[1][column]+= entry.getValue();
						}
					}
					else {
						if (entry.getKey() == 0)
							totalPorColuna[0][column] = entry.getValue();
						else
							totalPorColuna[0][column]+= entry.getValue();
					}
				}
			}
			column++;
		}
	}

	public void printPretty(TreeMap<Integer, Integer> tm, int column) {
		for (Entry<Integer, Integer> entry : tm.entrySet()) {
			System.out.println("C: " + column + " K:  "
					+ entry.getKey().toString() + " V: "
					+ entry.getValue().toString());
		}
	}

	public void printList(List<TreeMap<Integer, Integer>> lista) {
		int count = 0;
		for (TreeMap<Integer, Integer> value : lista) {
			printPretty(value, count);
			count++;
		}
	}

	public void printMatrix() {

		System.out.println("Printing total por coluna: ");

		if(totalPorColuna != null) {

			for(int i = 0; i < totalPorColuna.length; i++) {
				System.out.print("C" + i + ": ");
				for(int j = 0; j < totalPorColuna[0].length - 1; j++) {
					System.out.print(totalPorColuna[i][j] + " ");
				}
				System.out.println();
			}

		}

	}

	public void getProbsTotais() {

		float numberOfFalses = totalPorColuna[0][0];
		float numberOfTrues = totalPorColuna[1][0];

		probabilidadesTotais[0] = (numberOfFalses / (numberOfFalses + numberOfTrues));
		probabilidadesTotais[1] = (numberOfTrues / (numberOfFalses + numberOfTrues));

	}

	public void calcOdds(Probabilidades prob) {
		this.fillTotalColumn(listExpTrue, true);
		this.fillTotalColumn(listExpFalse, false);
		this.fillOddsTrue(listExpTrue, prob);
		this.fillOddsFalse(listExpFalse, prob);		
	}

	

	
}
