package NaiveBayes;

public class NaiveBayes {

	
	public static void main(String... args) {
		
		final int NUMBERFILES = 4;
		
		for (int i = 1; i < NUMBERFILES + 1; i++) {
			
			NaiveBayesServices naiveServices = new NaiveBayesServices();
			AccuracyAndPrecision accuracyAndPrecision = new AccuracyAndPrecision();
			FileProcessT file = new FileProcessT("files/"+i+"-trn.data");
			file.readFile();
			Probabilidades probabilidades = new Probabilidades(file.getNumColunas());
			file.calcOdds(probabilidades);
			file.printMatrix();
			file.getProbsTotais();
			
			NaiveBayesFile naiveBayesFile = file.readNaiveBayesFile();
			accuracyAndPrecision = naiveServices.computeAccuracyAndPrecision(file, accuracyAndPrecision, probabilidades, naiveBayesFile);
			
			
			NaiveBayesFile naiveBayesFileUnlabeled = new NaiveBayesFile("files/"+ i +"-unlabeled.data", false);
			naiveServices.computeResultFromUnlabeledFile(file, naiveBayesFileUnlabeled, probabilidades);
			naiveBayesFileUnlabeled.writeFile("files/" + i +"-labeled.data");
			
			System.out.println("ACCURACY AND PRECISION TABLE");
			System.out.println(accuracyAndPrecision.toString());
		}
		
	}
	
}
