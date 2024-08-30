package controller;


import java.io.Serializable;

/*
import model.Categoria;
import model.ETipoItem;
import model.IItem;
import model.Item;

EXEMPLO DE OUTRO TRABALHO
*/
public class CatalogoController implements Serializable {

	private static final long serialVersionUID = 1945660900431033557L;

	/*
	private Map<String, Categoria> categorias;
	private Map<Integer, Item> itens;

	public CatalogoController() {

		categorias = new TreeMap<>();
		itens = new TreeMap<>();
	}

	public void addCategoria(String nome) {

		categorias.put(nome, new Categoria(nome));	// insere novo objeto categoria no map categorias
		MainController.save(); //Ele salva na arvore cada metodo de controle 
	}

	public Set<String> getCategorias() {
		return categorias.keySet();	 // retorna lista das chaves do map categorias
	}

	public void addItem(String nomeCategoria, ETipoItem tipo, int codigo, String descricao, double preco) {

		Categoria categoria = categorias.get(nomeCategoria);  // retorna objeto Categoria para chave do map

		Item item = new Item(tipo, codigo, descricao);
		item.setPreco(preco);

		itens.put(item.getCodigo(), item);

		if (categoria != null)
			categoria.addItem(item);

		MainController.save();
	}

	public List<String> getItens() {

		List<String> lista = new ArrayList<>();

		for (IItem item : itens.values())
			lista.add(String.format("%s\t%d\t%s", item.getTipo().name(), item.getCodigo(), item.getDescricao()));

		return lista;
	}

	public List<String> getItens(String nomeCategoria) {

		Categoria categoria = categorias.get(nomeCategoria);
		
		List<String> lista = new ArrayList<>();

		for (IItem item : categoria.getItens())
			lista.add(String.format("%s\t%d\t%s", item.getTipo().name(), item.getCodigo(), item.getDescricao()));

		return lista;
	}
	
	*/
	
}
