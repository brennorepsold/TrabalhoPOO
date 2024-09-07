package controller;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Tribunal;

public class TribunalController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Tribunal> tribunais;

	public TribunalController() {

		tribunais = new TreeMap<>();
	}
	
	public void addTribunal(String sigla, String descricao, String secao) {
		tribunais.put(sigla,new Tribunal(sigla, descricao, secao));
		MainController.save();
	}
	
	public Set<String> getTribunais(){
		return tribunais.keySet();
	}
}