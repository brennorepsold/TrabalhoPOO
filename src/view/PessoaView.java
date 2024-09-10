package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.MainController;
import controller.PessoaController;
import model.Advogado;
import model.Pessoa;
import model.PessoaFisica;

public class PessoaView extends JFrame {

	private static final long serialVersionUID = -404362399095556136L;

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	private JPanel listPane; // Painel de Listagem por categoria
	private JPanel listPaneJ;
	private JPanel listPaneA; // Painel de Listagem por categoria

	private JTextArea textAreaList;
	private JTextArea textAreaListJ;
	private JTextArea textAreaListA;

	private JPanel formPane; // Painel de cadastro

	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JComboBox<String> cbbTipoUsuario;
	private JTextField txtCPF;
	private JTextField txtCNPJ;
	private JTextField txtRegistro;
	private JComboBox<String> comboPreposto; // Combobox para selecionar preposto

	PessoaController controller = MainController.getPessoaController();

	public PessoaView() { // Alterando o construtor para aceitar PessoaController
		initialize();
	}

	private void initialize() {
		setTitle("Cadastro Pessoa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		formPane = new JPanel();
		listPane = new JPanel();
		listPaneJ = new JPanel();
		listPaneA = new JPanel();

		initFormPane();
		initListPane();
		initListPaneJ();
		initListPaneA();

		tabbedPane.add("Cadastro", formPane);
		tabbedPane.add("Listagem Pessoa Física", listPane);
		tabbedPane.add("Listagem Pessoa Jurídica", listPaneJ);
		tabbedPane.add("Listagem Advogados", listPaneA);
	}

	private void initListPane() {
		listPane.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblClientes = new JLabel("Lista de pessoas físicas");
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarPessoaFisica();
			}
		});

		topPanel.add(lblClientes);
		topPanel.add(btnListar);
		listPane.add(topPanel, BorderLayout.NORTH);

		textAreaList = new JTextArea();
		listPane.add(new JScrollPane(textAreaList), BorderLayout.CENTER);
	}

	private void initListPaneJ() {
		listPaneJ.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblClientesJ = new JLabel("Lista de pessoas jurídicas");
		JButton btnListarJ = new JButton("Listar");
		btnListarJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarPessoaJuridica();
			}
		});

		topPanel.add(lblClientesJ);
		topPanel.add(btnListarJ);
		listPaneJ.add(topPanel, BorderLayout.NORTH);

		textAreaListJ = new JTextArea();
		listPaneJ.add(new JScrollPane(textAreaListJ), BorderLayout.CENTER);
	}

	private void initListPaneA() {
		listPaneA.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblClientesA = new JLabel("Lista de advogados");
		JButton btnListarA = new JButton("Listar");
		btnListarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarAdvogados();
			}
		});

		topPanel.add(lblClientesA);
		topPanel.add(btnListarA);
		listPaneA.add(topPanel, BorderLayout.NORTH);

		textAreaListA = new JTextArea();
		listPaneA.add(new JScrollPane(textAreaListA), BorderLayout.CENTER);
	}

	private void initFormPane() {

		formPane.setLayout(new GridLayout(10, 2, 5, 5));

		JLabel lblNome = new JLabel("Nome");
		txtNome = new JTextField();
		JLabel lblEmail = new JLabel("Email");
		txtEmail = new JTextField();
		JLabel lblTelefone = new JLabel("Telefone");
		txtTelefone = new JTextField();
		JLabel lblTipoUsuario = new JLabel("Tipo de Usuário:");
		cbbTipoUsuario = new JComboBox<>(new String[] { "Pessoa Física", "Pessoa Jurídica", "Advogado" });
		JLabel lblCPF = new JLabel("CPF");
		txtCPF = new JTextField();
		JLabel lblCNPJ = new JLabel("CNPJ:");
		txtCNPJ = new JTextField();
		JLabel lblPreposto = new JLabel("Preposto");
		comboPreposto = new JComboBox<>(new Vector<>(controller.getNomesPessoasFisicas()));
		JLabel lblRegistro = new JLabel("Registro");
		txtRegistro = new JTextField();

		formPane.add(lblNome);
		formPane.add(txtNome);
		formPane.add(lblEmail);
		formPane.add(txtEmail);
		formPane.add(lblTelefone);
		formPane.add(txtTelefone);
		formPane.add(lblTipoUsuario);
		formPane.add(cbbTipoUsuario);
		formPane.add(lblCPF);
		formPane.add(txtCPF);
		formPane.add(lblCNPJ);
		formPane.add(txtCNPJ);
		formPane.add(lblPreposto);
		formPane.add(comboPreposto); // Adiciona combobox no lugar da lista
		formPane.add(new JLabel()); // Label vazia para alinhar o botão
		formPane.add(lblRegistro);
		formPane.add(txtRegistro);

		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvar();
			}
		});

		formPane.add(btnSalvar);
		formPane.add(btnCancelar);

		// Configuração inicial de visibilidade dos campos
		lblCPF.setVisible(false);
		txtCPF.setVisible(false);
		lblCNPJ.setVisible(false);
		txtCNPJ.setVisible(false);
		lblPreposto.setVisible(false);
		comboPreposto.setVisible(false); // Esconde combobox inicialmente
		lblRegistro.setVisible(false);
		txtRegistro.setVisible(false);

		cbbTipoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tipoUsuarioSelecionado = (String) cbbTipoUsuario.getSelectedItem();
				if ("Pessoa Física".equals(tipoUsuarioSelecionado)) {
					lblCPF.setVisible(true);
					txtCPF.setVisible(true);
					lblCNPJ.setVisible(false);
					txtCNPJ.setVisible(false);
					lblPreposto.setVisible(false);
					comboPreposto.setVisible(false);
					lblRegistro.setVisible(false);
					txtRegistro.setVisible(false);
				} else if ("Pessoa Jurídica".equals(tipoUsuarioSelecionado)) {
					lblCNPJ.setVisible(true);
					txtCNPJ.setVisible(true);
					lblPreposto.setVisible(true);
					comboPreposto.setVisible(true); // Mostra combobox
					lblCPF.setVisible(false);
					txtCPF.setVisible(false);
					lblRegistro.setVisible(false);
					txtRegistro.setVisible(false);
				} else {
					lblRegistro.setVisible(true);
					txtRegistro.setVisible(true);
					lblCPF.setVisible(true);
					txtCPF.setVisible(true);
					lblCNPJ.setVisible(false);
					txtCNPJ.setVisible(false);
					lblPreposto.setVisible(false);
					comboPreposto.setVisible(false);
				}
			}
		});
	}

	private void actionSalvar() {
		PessoaController controller = MainController.getPessoaController();
		String tipoUsuarioSelecionado = (String) cbbTipoUsuario.getSelectedItem();

		String nome = txtNome.getText();
		String email = txtEmail.getText();

		// Validação do telefone
		String telefoneStr = txtTelefone.getText();
		long telefone = Long.parseLong(telefoneStr);

		if ("Pessoa Física".equals(tipoUsuarioSelecionado)) {
			// Validação do CPF
			String cpf = txtCPF.getText();

			// Adicionar pessoa física
			controller.addPessoaFisica(nome, email, telefone, cpf);
			if (controller.addPessoaFisica(nome, email, telefone, cpf)) {
				util.Verificacoes.exibirPopupSucesso("Sucesso", "Cadastro de Pessoa Física realizado com sucesso!");
			}

		} else if ("Pessoa Jurídica".equals(tipoUsuarioSelecionado)) {
			String cnpj = txtCNPJ.getText();

			// Obter o preposto selecionado
			String prepostoNome = (String) comboPreposto.getSelectedItem();

			// Procurar o objeto PessoaFisica correspondente ao nome do preposto
			PessoaFisica preposto = null;
			for (Pessoa pessoa : controller.getPessoasFisicas()) { // Itera sobre os valores do mapa
				if (pessoa instanceof PessoaFisica && ((PessoaFisica) pessoa).getNome().equals(prepostoNome)) {
					preposto = (PessoaFisica) pessoa;
					break;
				}
			}

			// Adicionar pessoa jurídica
			controller.addPessoaJuridica(nome, email, telefone, cnpj, preposto);

			if (controller.addPessoaJuridica(nome, email, telefone, cnpj, preposto)) {
				util.Verificacoes.exibirPopupSucesso("Sucesso", "Cadastro de Pessoa Jurídica realizado com sucesso!");
			}

		} else if ("Advogado".equals(tipoUsuarioSelecionado)) {
			String cpf = txtCPF.getText();
			String registroStr = txtRegistro.getText();
			long registro = Long.parseLong(registroStr);

			// Adicionar advogado
			controller.addAdvogado(nome, email, telefone, cpf, registro);

			if (controller.addAdvogado(nome, email, telefone, cpf, registro)) {
				util.Verificacoes.exibirPopupSucesso("Sucesso", "Cadastro de Advogado realizado com sucesso!");
			}
		}

		limparForm();
	}

	private void actionListarPessoaFisica() {
		List<Pessoa> lista = controller.getPessoasFisicas();

		textAreaList.setText("");

		for (Pessoa p : lista) {
			textAreaList.append(String.format("Nome: %s Email: %s Telefone: %s CPF: %s\n", p.getNome(), p.getEmail(),
					p.getTelefone(), p.getCadastroRF()));
		}
	}

	private void actionListarPessoaJuridica() {
		List<Pessoa> lista = controller.getPessoasJuridicas();

		textAreaListJ.setText("");

		for (Pessoa p : lista) {
			textAreaListJ.append(String.format("Nome: %s Email: %s Telefone: %s CNPJ: %s\n", p.getNome(), p.getEmail(),
					p.getTelefone(), p.getCadastroRF()));
		}
	}

	private void actionListarAdvogados() {
		List<Advogado> lista = controller.getAdvogados();

		textAreaListA.setText("");

		for (Advogado p : lista) {
			textAreaListA.append(String.format("Nome: %s Email: %s Telefone: %s CNPJ: %s Registro: %s\n", p.getNome(),
					p.getEmail(), p.getTelefone(), p.getCadastroRF(), p.getRegistro()));
		}
	}

	private void limparForm() {
		txtNome.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtCPF.setText("");
		txtCNPJ.setText("");
		comboPreposto.setSelectedIndex(-1); // Limpa seleção do combobox
		txtRegistro.setText("");
	}

	private void actionCancelar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
