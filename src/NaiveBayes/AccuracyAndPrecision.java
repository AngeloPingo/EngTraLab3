package NaiveBayes;

public class AccuracyAndPrecision {

	int tp = 0;
	int fn = 0;
	int fp = 0;
	int tn = 0;
			
	public int getTp() {
		return tp;
	}

	public int getFn() {
		return fn;
	}

	public int getFp() {
		return fp;
	}

	public int getTn() {
		return tn;
	}

	public void addOneTp() {
		this.tp++;
	}
	
	public void addOneFn() {
		this.fn++;
	}
	
	public void addOneFp() {
		this.fp++;
	}
	
	public void addOneTn() {
		this.tn++;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("INPUT / OUTPUT  | \t 1 \t | \t 0 \t  \n");
		sb.append("1\t\t| ");
		sb.append(this.tp);
		sb.append("\t \t | ");
		sb.append(this.fn);
		sb.append("\n");
		sb.append("0\t\t| ");
		sb.append(this.fp);
		sb.append("\t \t | ");
		sb.append(this.tn);
		sb.append("\n");
		
		return sb.toString();
		
	}
	
}
