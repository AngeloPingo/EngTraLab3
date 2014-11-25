package NaiveBayes;

public class Atributo {

	//numero de exp
	private int number;
	//contador
	private int count;
	
	public Atributo(int number, int count) {
		this.number = number;
		this.count = count;
	}
	
	public int getNumber() {
		return number;
	}
	public int getCount() {
		return count;
	}
	
}
