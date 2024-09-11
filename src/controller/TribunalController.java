package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import exception.TribunalException;
import model.Tribunal;
import util.Verificacoes;

public class TribunalController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Tribunal> tribunais;

    public TribunalController() {
        tribunais = new TreeMap<>();
    }

    public void addTribunal(String sigla, String descricao, String secao) throws TribunalException {
        // Verificação de campos obrigatórios
        if (!Verificacoes.verificarCamposPreenchidos(sigla, descricao, secao)) {
            throw new TribunalException("Todos os campos devem ser preenchidos.");
        }

        // Verificação de existência da sigla
        if (getTribunalBySigla(sigla) != null) {
            throw new TribunalException("Sigla já existente!");
        }

        // Criação de um novo tribunal e adição ao mapa de tribunais
        tribunais.put(sigla, new Tribunal(sigla, descricao, secao));
        MainController.save();
    }

    public Set<String> getSiglasTribunais() {
        return tribunais.keySet();
    }

    public Tribunal getTribunalBySigla(String sigla) {
        return tribunais.get(sigla);
    }

    public List<Tribunal> getTribunais() throws TribunalException {
        List<Tribunal> lista = new ArrayList<>();

        for (Tribunal t : tribunais.values())
            lista.add(new Tribunal(t.getSigla(), t.getDescricao(), t.getSecao()));

        return lista;
    }
}
