package controller;

import java.io.Serializable;

import persistence.Serializer;

/* 
 * Design Pattern Singleton 
 * 
 * Criar Controladores para as classes:(Processo, tribunal, conta, pessoas, audiencia)
 */

public class MainController implements Serializable {

	private static final long serialVersionUID = 6341528978400871936L;

	private static MainController instance;

	private CatalogoController catalogoController;

	private TribunalController tribunalController;

	// declarar os demais controladores

	private MainController() {

		catalogoController = new CatalogoController();
		tribunalController = new TribunalController();

		// instanciar os demais controladores
		//
	}

	public static MainController getInstance() {
		return instance;
	}

	public static CatalogoController getCatalogoController() {
		return instance.catalogoController;
	}

	// implementar metodos acessadores estaticos para os demais controladores

	public static TribunalController getTribunalController() {
		return instance.tribunalController;
	}

	public static void load() {

		instance = Serializer.readFile();

		if (instance == null) {
			instance = new MainController();
		}
	}

	public static void save() {
		Serializer.writeFile(instance);
	}

}
