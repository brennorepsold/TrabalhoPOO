package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import model.PessoaJuridica;

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
	private JTextField txtPrepostoSelecionado; // Campo para mostrar o preposto selecionado
	private JButton btnSelecionarPreposto; // Botão para abrir diálogo de seleção de preposto

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

		tabbedPane.add("Listagem Pessoa Física", listPane);
		tabbedPane.add("Listagem Pessoa Jurídica", listPaneJ);
		tabbedPane.add("Listagem Advogados", listPaneA);
		tabbedPane.add("Cadastro", formPane);
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
		txtPrepostoSelecionado = new JTextField();
		txtPrepostoSelecionado.setEditable(false); // Campo de texto não editável, apenas exibição
		btnSelecionarPreposto = new JButton("Selecionar Preposto");
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
		formPane.add(txtPrepostoSelecionado); // Mostra o preposto selecionado
		formPane.add(new JLabel()); // Label vazia para alinhar o botão
		formPane.add(btnSelecionarPreposto); // Botão para selecionar preposto
		formPane.add(lblRegistro);
		formPane.add(txtRegistro);

		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});

		btnSelecionarPreposto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDialogoSelecionarPreposto(); // Abre o diálogo de seleção de preposto
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
		txtPrepostoSelecionado.setVisible(false);
		btnSelecionarPreposto.setVisible(false);
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
					txtPrepostoSelecionado.setVisible(false);
					btnSelecionarPreposto.setVisible(false);
					lblRegistro.setVisible(false);
					txtRegistro.setVisible(false);
				} else if ("Pessoa Jurídica".equals(tipoUsuarioSelecionado)) {
					lblCNPJ.setVisible(true);
					txtCNPJ.setVisible(true);
					lblPreposto.setVisible(true);
					txtPrepostoSelecionado.setVisible(true);
					btnSelecionarPreposto.setVisible(true);
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
					txtPrepostoSelecionado.setVisible(false);
					btnSelecionarPreposto.setVisible(false);
				}
			}
		});
	}

	private void abrirDialogoSelecionarPreposto() {
		PessoaController controller = MainController.getPessoaController();
		JDialog dialog = new JDialog(this, "Selecionar Preposto", true);
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		List<String> nomesPessoasFisicas = controller.getNomesPessoasFisicas(); // Obtém a lista de nomes

		JList<String> list = new JList<>(nomesPessoasFisicas.toArray(new String[0]));
		JScrollPane scrollPane = new JScrollPane(list);
		panel.add(scrollPane, BorderLayout.CENTER);

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomeSelecionado = list.getSelectedValue();
				if (nomeSelecionado != null) {
					txtPrepostoSelecionado.setText(nomeSelecionado); // Exibe o nome selecionado na interface principal
					dialog.dispose(); // Fecha o diálogo
				} else {
					util.Verificacoes.exibirPopup("Erro", "Selecione um preposto da lista.");
				}
			}
		});

		panel.add(btnSelecionar, BorderLayout.SOUTH);
		dialog.add(panel);
		dialog.setVisible(true);
	}

	private void actionSalvar() {
		PessoaController controller = MainController.getPessoaController();
		String tipoUsuarioSelecionado = (String) cbbTipoUsuario.getSelectedItem();

		try {
			String nome = txtNome.getText();
			String email = txtEmail.getText();

			// Validação do email
			if (!util.Verificacoes.validarEmail(email)) {
				util.Verificacoes.exibirPopup("Erro", "Email inválido!");
				return;
			}

			// Validação do telefone
			String telefoneStr = txtTelefone.getText();
			long telefone = Long.parseLong(telefoneStr);

			if (telefoneStr.length() != 12 || telefoneStr.charAt(3) != '9') {
				util.Verificacoes.exibirPopup("Erro",
						"O telefone deve ter exatamente 12 dígitos: (DDD)9XXXX-XXXX (sem espaços ou caracteres entre os números)");
				return;
			}

			if ("Pessoa Física".equals(tipoUsuarioSelecionado)) {
				// Validação do CPF
				String cpfStr = txtCPF.getText();
				if (!util.Verificacoes.validarCPF(cpfStr)) {
					util.Verificacoes.exibirPopup("Erro", "CPF inválido!");
					return;
				}

				long cpf = Long.parseLong(cpfStr);

				// Adicionar pessoa física
				if (controller.addPessoaFisica(nome, email, telefone, cpf)) {
					util.Verificacoes.exibirPopupSucesso("Sucesso", "Cadastro de Pessoa Física realizado com sucesso!");
				}

			} else if ("Pessoa Jurídica".equals(tipoUsuarioSelecionado)) {
				// Validação do CNPJ
				String cnpjStr = txtCNPJ.getText();
				/*
				 * if (!util.Verificacoes.validarCNPJ(cnpjStr)) {
				 * util.Verificacoes.exibirPopup("Erro", "CNPJ inválido!"); return; }
				 */

				long cnpj = Long.parseLong(cnpjStr);

				// Obter o preposto selecionado
				String prepostoNome = txtPrepostoSelecionado.getText();
				if (prepostoNome == null || prepostoNome.isEmpty()) {
					util.Verificacoes.exibirPopup("Erro", "Por favor, selecione um preposto.");
					return;
				}

				// Procurar o objeto PessoaFisica correspondente ao nome do preposto
				PessoaFisica preposto = null;
				for (Pessoa pessoa : controller.getPessoasFisicas().values()) { // Itera sobre os valores do mapa
					if (pessoa instanceof PessoaFisica && ((PessoaFisica) pessoa).getNome().equals(prepostoNome)) {
						preposto = (PessoaFisica) pessoa;
						break;
					}
				}

				if (preposto == null) {
					util.Verificacoes.exibirPopup("Erro", "Preposto selecionado não encontrado.");
					return;
				}

				// Adicionar pessoa jurídica
				if (controller.addPessoaJuridica(nome, email, telefone, cnpj, preposto)) {
					util.Verificacoes.exibirPopupSucesso("Sucesso",
							"Cadastro de Pessoa Jurídica realizado com sucesso!");
				}

			} else if ("Advogado".equals(tipoUsuarioSelecionado)) {
				// Validação do CPF
				String cpfStr = txtCPF.getText();
				if (!util.Verificacoes.validarCPF(cpfStr)) {
					util.Verificacoes.exibirPopup("Erro", "CPF inválido!");
					return;
				}

				long cpf = Long.parseLong(cpfStr);

				// Validação do Registro
				String registroStr = txtRegistro.getText();
				long registro = Long.parseLong(registroStr);

				// Adicionar advogado
				if (controller.addAdvogado(nome, email, telefone, cpf, registro)) {
					util.Verificacoes.exibirPopupSucesso("Sucesso", "Cadastro de Advogado realizado com sucesso!");
				}
			}

		} catch (NumberFormatException e) {
			util.Verificacoes.exibirPopup("Erro",
					"O telefone, CPF/CNPJ ou registro informado não é válido, digite somente números.");
		} catch (Exception e) {
			util.Verificacoes.exibirPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage());
		}

		limparForm();
	}

	private void actionListarPessoaFisica() {
		PessoaController controller = MainController.getPessoaController();
		// Limpa a área de texto antes de adicionar os dados
		textAreaList.setText("");

		// Obtém todas as pessoas físicas do controlador
		Map<Long, Pessoa> pessoasFisicas = controller.getPessoasFisicas();

		// Adiciona cada pessoa física na área de texto usando toString()
		for (Pessoa pessoa : pessoasFisicas.values()) {
			if (pessoa instanceof PessoaFisica) {
				textAreaList.append(pessoa.toString());
			}
		}
	}

	private void actionListarPessoaJuridica() {
		PessoaController controller = MainController.getPessoaController();
		// Limpa a área de texto antes de adicionar os dados
		textAreaListJ.setText("");

		// Obtém todas as pessoas jurídicas do controlador
		Map<Long, Pessoa> pessoasJuridicas = controller.getPessoasJuridicas();

		// Adiciona cada pessoa jurídica na área de texto usando toString()
		for (Pessoa pessoa : pessoasJuridicas.values()) {
			if (pessoa instanceof PessoaJuridica) {
				textAreaListJ.append(pessoa.toString());
			}
		}
	}

	private void actionListarAdvogados() {
		PessoaController controller = MainController.getPessoaController();
		// Limpa a área de texto antes de adicionar os dados
		textAreaListA.setText("");

		// Obtém todos os advogados do controlador
		Map<Long, Pessoa> advogados = controller.getAdvogados();

		// Adiciona cada advogado na área de texto usando toString()
		for (Pessoa pessoa : advogados.values()) {
			if (pessoa instanceof Advogado) {
				textAreaListA.append(pessoa.toString());
			}
		}
	}

	private void limparForm() {
		txtNome.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtCPF.setText("");
		txtCNPJ.setText("");
		txtPrepostoSelecionado.setText("");
		txtRegistro.setText("");
	}

	private void actionCancelar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
