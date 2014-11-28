package NaiveBayes;

import java.util.List;

public class NaiveBayesServices {

	public AccuracyAndPrecision computeAccuracyAndPrecision(FileProcessT file, AccuracyAndPrecision table, Probabilidades probabilidades, NaiveBayesFile naiveBayesFile) {
		
		int result;
		
		for(Data data : naiveBayesFile.getNaiveBayesFile()) {
			result = getResult(file, data, probabilidades);
			
			if(result == 1) {
				if(result == data.getResult()) {
					table.addOneTp();
				} else {
					table.addOneFp();
				}
			} else {
				if(result == data.getResult()) {
					table.addOneTn();
				} else {
					table.addOneFn();
				}
			}
				
		}
		
		return table;

	}
	
	public int getResult(FileProcessT file, Data data, Probabilidades prob) {

		double probFalse = 1;
		double probTrue = 1;

		for(int i = 0; i < (data.getEntrys().length - 1); i++) {
			probFalse *= prob.getProbabilidadeFalse(i, data.getEntrys()[i]);
			probTrue *= prob.getProbabilidadeTrue(i, data.getEntrys()[i]);
		}

		return (  (probFalse * file.probabilidadesTotais[0]) > (probTrue * file.probabilidadesTotais[1])  ) ? 0 : 1;

	}
	
	public void computeResultFromUnlabeledFile(FileProcessT file, NaiveBayesFile naiveBayesFile, Probabilidades probabilidades) {
		
		for(Data d : naiveBayesFile.getNaiveBayesFile()) {
			d.setResult(getResult(file, d, probabilidades));
		}
		
	}
	
}
