package NaiveBayes;

public class Atributo {

	//numero de exp
	private int number;
	//contador
	
	private float probabilidade;
	
	public float getProbabilidade() {
		return probabilidade;
	}


	public Atributo(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public void setProbabilidade(float probabilidade) {
		this.probabilidade = probabilidade;
	}
}
