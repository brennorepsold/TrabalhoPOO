package controller;

import java.io.Serializable;

import persistence.Serializer;

/* 
 * Design Pattern Singleton 
 * 
 */

public class MainController implements Serializable {

	private static final long serialVersionUID = 6341528978400871936L;

	private static MainController instance;

	private TribunalController tribunalController;
	
	private PessoaController pessoaController;
	
	private ClienteController clienteController;
	

	// declarar os demais controladores

	private MainController() {
		tribunalController = new TribunalController();
		pessoaController = new PessoaController();
		clienteController = new ClienteController();

		// instanciar os demais controladores
		//
	}

	public static MainController getInstance() {
		return instance;
	}

	// implementar metodos acessadores estaticos para os demais controladores

	public static TribunalController getTribunalController() {
		return instance.tribunalController;
	}
	
	public static PessoaController getPessoaController() {
		return instance.pessoaController;
	}
	
	public static ClienteController getClienteController() {
		return instance.clienteController;
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
