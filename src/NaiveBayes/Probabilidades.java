/**
 * 
 */
package NaiveBayes;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 *
 */
public class Probabilidades {

	ArrayList<TreeMap<Integer, Atributo>> columValueTrue = null;
	ArrayList<TreeMap<Integer, Atributo>> columValueFalse = null;

	/**
	 * 
	 */
	public Probabilidades(int colums) {
		init(colums);
	}

	private void init(int colums) {

		columValueTrue = new ArrayList<TreeMap<Integer, Atributo>>(colums);
		columValueFalse = new ArrayList<TreeMap<Integer, Atributo>>(colums);
		for (int i = 0; i < colums; i++) {
			columValueTrue.add(i, new TreeMap<Integer, Atributo>());
			columValueFalse.add(i, new TreeMap<Integer, Atributo>());
		}
	}

	public void setProbabilidadeTrue(int coluna, int valor, float probabilidade) {
		if (coluna <= columValueTrue.size()) {
			if (columValueTrue.get(coluna).isEmpty()
					|| columValueTrue.get(coluna).get(valor) == null) {
				Atributo atrb = new Atributo(coluna);
				atrb.setProbabilidade(probabilidade);
				columValueTrue.get(coluna).put(valor, atrb);
			} else {
				Atributo atrb = columValueTrue.get(coluna).get(valor);
				atrb.setProbabilidade(probabilidade);
				columValueTrue.get(coluna).put(valor, atrb);
			}
		}
	}

	public void setProbabilidadeFalse(int coluna, int valor, float probabilidade) {
		if (coluna <= columValueTrue.size()) {
			if (columValueFalse.get(coluna).isEmpty()
					|| columValueFalse.get(coluna).get(valor) == null) {
				Atributo atrb = new Atributo(coluna);
				atrb.setProbabilidade(probabilidade);
				columValueFalse.get(coluna).put(valor, atrb);
			} else {
				Atributo atrb = columValueFalse.get(coluna).get(valor);
				atrb.setProbabilidade(probabilidade);
				columValueTrue.get(coluna).put(valor, atrb);
			}
		}
	}

	public float getProbabilidadeTrue(int coluna, int valor) {
		if (!columValueTrue.get(coluna).isEmpty())
			return columValueTrue.get(coluna).get(valor).getProbabilidade();
		else
			return 0;
	}

	public float getProbabilidadeFalse(int coluna, int valor) {
		if (!columValueFalse.get(coluna).isEmpty())
			return columValueFalse.get(coluna).get(valor).getProbabilidade();
		else
			return 0;
	}

	public TreeMap<Integer, Atributo> getProbabilidadesTrue(int coluna) {
		if (!columValueTrue.get(coluna).isEmpty())
			return columValueTrue.get(coluna);
		return null;
	}

	public TreeMap<Integer, Atributo> getProbabilidadesFalse(int coluna) {
		if (!columValueFalse.isEmpty())
			return columValueFalse.get(coluna);
		return null;
	}

	public void printProbabilidadesTrue() {
		for (TreeMap<Integer, Atributo> tm : columValueTrue) {
			for (Entry<Integer, Atributo> pair : tm.entrySet()){
				System.out.println("KEY/Column: " + pair.getKey() + ";  Value: " + pair.getValue().getNumber() + ";  Prob: " + pair.getValue().getProbabilidade());
			}
		}
	}
	
	public void printProbabilidadesFalse() {
		for (TreeMap<Integer, Atributo> tm : columValueFalse) {
			for (Entry<Integer, Atributo> pair : tm.entrySet()){
				System.out.println("KEY/Column: " + pair.getKey() + ";  Value: " + pair.getValue().getNumber() + ";  Prob: " + pair.getValue().getProbabilidade());
			}
		}
	}
	
}
