package controller;

import java.io.Serializable;

import persistence.Serializer;

public class MainController implements Serializable {

	private static final long serialVersionUID = 6341528978400871936L;

	private static MainController instance;

	private TribunalController tribunalController;

	private PessoaController pessoaController;

	private ProcessoController processoController;

	private MainController() {
		tribunalController = new TribunalController();
		pessoaController = new PessoaController();
		processoController = new ProcessoController();

	}

	public static MainController getInstance() {
		return instance;
	}

	public static TribunalController getTribunalController() {
		return instance.tribunalController;
	}

	public static PessoaController getPessoaController() {
		return instance.pessoaController;
	}

	public static ProcessoController getProcessoController() {
		return instance.processoController;
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