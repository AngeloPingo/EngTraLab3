package NaiveBayes;

public class Data {

	private int[] entrys;
	private String rawData;
	private int result = -1;

	public Data(String data, boolean withResult) {
		
		this.rawData = data;
		String[] split = data.split(",");
		
		entrys = new int[split.length];
		
		int max = split.length;
		if(withResult) {
			max = max-1;
		}
		
		int i = 0;
		for(i = 0; i < max; i++) {
			entrys[i] = Integer.parseInt(split[i].trim());
		}
		
		if(withResult) {
			result = Integer.parseInt(split[i].trim()); 
		}
		
	}
	
	public Data(int[] entrys) {
		this.entrys = entrys;
	}
	
	public int[] getEntrys() {
		return entrys;
	}
	
	public int getIndex(int i) {
		return entrys[i];
	}
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
	public int getSizeEntrys() {
		return entrys.length;
	}
	
	public String getRawData() {
		return this.rawData;
	}
	
}
