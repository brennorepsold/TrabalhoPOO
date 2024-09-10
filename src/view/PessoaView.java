package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import controller.PessoaController;
import exception.PessoaFisicaException;
import model.Advogado;
import model.Pessoa;
import model.PessoaFisica;

public class PessoaView extends JFrame {

	private static final long serialVersionUID = 1L;

	// Controlador
	private PessoaController controller = MainController.getPessoaController();

	// Painéis
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel formPane;
	private JPanel listPane;
	private JPanel listPaneJ;
	private JPanel listPaneA;

	// Campos de texto
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtCPF;
	private JTextField txtCNPJ;
	private JTextField txtRegistro;

	// Combobox
	private JComboBox<String> cbbTipoUsuario;
	private JComboBox<String> comboPreposto;

	// Área de texto
	private JTextArea textAreaList;
	private JTextArea textAreaListJ;
	private JTextArea textAreaListA;

	// Botões
	private JButton btnListarFisica;
	private JButton btnListarJuridica;
	private JButton btnListarAdvogados;
	private JButton btnSalvar;

	public PessoaView() {
		initialize();
	}

	private void initialize() {
		setTitle("Cadastro Pessoa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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

	private void initFormPane() {
		formPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL; // Para que os componentes ocupem todo o espaço horizontal

		// Labels e Campos de Texto
		JLabel lblNome = new JLabel("Nome");
		txtNome = new JTextField(15);
		JLabel lblEmail = new JLabel("Email");
		txtEmail = new JTextField(15);
		JLabel lblTelefone = new JLabel("Telefone");
		txtTelefone = new JTextField(15);
		JLabel lblTipoUsuario = new JLabel("Tipo de Usuário:");
		cbbTipoUsuario = new JComboBox<>(new String[] { "Pessoa Física", "Pessoa Jurídica", "Advogado" });

		// Adicionando componentes de maneira ordenada
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.3; // Proporção da largura que cada componente deve ocupar
		formPane.add(lblNome, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.7;
		formPane.add(txtNome, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.3;
		formPane.add(lblEmail, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.7;
		formPane.add(txtEmail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.3;
		formPane.add(lblTelefone, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.7;
		formPane.add(txtTelefone, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.3;
		formPane.add(lblTipoUsuario, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.7;
		formPane.add(cbbTipoUsuario, gbc);

		// Campos sobrepostos: CPF e CNPJ
		JLabel lblCPF = new JLabel("CPF");
		txtCPF = new JTextField(15);
		JLabel lblCNPJ = new JLabel("CNPJ:");
		txtCNPJ = new JTextField(15);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0.3;
		formPane.add(lblCPF, gbc);
		formPane.add(lblCNPJ, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.7;
		formPane.add(txtCPF, gbc);
		formPane.add(txtCNPJ, gbc);

		// Campos sobrepostos: Registro e Preposto
		JLabel lblRegistro = new JLabel("Registro");
		txtRegistro = new JTextField(15);
		JLabel lblPreposto = new JLabel("Preposto");
		comboPreposto = new JComboBox<>(new Vector<>(controller.getNomesPessoasFisicas()));

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0.3;
		formPane.add(lblRegistro, gbc);
		formPane.add(lblPreposto, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.7;
		formPane.add(txtRegistro, gbc);
		formPane.add(comboPreposto, gbc);

		// Configuração inicial de visibilidade dos campos
		lblCPF.setVisible(false);
		txtCPF.setVisible(false);
		lblCNPJ.setVisible(false);
		txtCNPJ.setVisible(false);
		lblRegistro.setVisible(false);
		txtRegistro.setVisible(false);
		lblPreposto.setVisible(false);
		comboPreposto.setVisible(false);

		// Botão Salvar
		btnSalvar = new JButton("Salvar");
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.weightx = 1; // Botão ocupa todo o espaço horizontal restante
		formPane.add(btnSalvar, gbc);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionSalvar();
				} catch (PessoaFisicaException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Ação ao selecionar o tipo de usuário
		cbbTipoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tipoUsuarioSelecionado = (String) cbbTipoUsuario.getSelectedItem();
				boolean isPessoaFisica = "Pessoa Física".equals(tipoUsuarioSelecionado);
				boolean isPessoaJuridica = "Pessoa Jurídica".equals(tipoUsuarioSelecionado);
				boolean isAdvogado = "Advogado".equals(tipoUsuarioSelecionado);

				lblCPF.setVisible(isPessoaFisica || isAdvogado);
				txtCPF.setVisible(isPessoaFisica || isAdvogado);

				lblCNPJ.setVisible(isPessoaJuridica);
				txtCNPJ.setVisible(isPessoaJuridica);
				lblPreposto.setVisible(isPessoaJuridica);
				comboPreposto.setVisible(isPessoaJuridica);

				lblRegistro.setVisible(isAdvogado);
				txtRegistro.setVisible(isAdvogado);
			}
		});
	}

	private void initListPane() {
		listPane.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblClientes = new JLabel("Lista de pessoas físicas");
		btnListarFisica = new JButton("Listar");
		btnListarFisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarPessoaFisica();
			}
		});

		topPanel.add(lblClientes);
		topPanel.add(btnListarFisica);
		listPane.add(topPanel, BorderLayout.NORTH);

		textAreaList = new JTextArea();
		listPane.add(new JScrollPane(textAreaList), BorderLayout.CENTER);
	}

	private void initListPaneJ() {
		listPaneJ.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblClientesJ = new JLabel("Lista de pessoas jurídicas");
		btnListarJuridica = new JButton("Listar");
		btnListarJuridica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarPessoaJuridica();
			}
		});

		topPanel.add(lblClientesJ);
		topPanel.add(btnListarJuridica);
		listPaneJ.add(topPanel, BorderLayout.NORTH);

		textAreaListJ = new JTextArea();
		listPaneJ.add(new JScrollPane(textAreaListJ), BorderLayout.CENTER);
	}

	private void initListPaneA() {
		listPaneA.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblClientesA = new JLabel("Lista de advogados");
		btnListarAdvogados = new JButton("Listar");
		btnListarAdvogados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarAdvogados();
			}
		});

		topPanel.add(lblClientesA);
		topPanel.add(btnListarAdvogados);
		listPaneA.add(topPanel, BorderLayout.NORTH);

		textAreaListA = new JTextArea();
		listPaneA.add(new JScrollPane(textAreaListA), BorderLayout.CENTER);
	}

	private void actionSalvar() throws PessoaFisicaException {
		String tipoUsuarioSelecionado = (String) cbbTipoUsuario.getSelectedItem();

		String nome = txtNome.getText();
		String email = txtEmail.getText();
		long telefone = Long.parseLong(txtTelefone.getText());

		if ("Pessoa Física".equals(tipoUsuarioSelecionado)) {
			String cpf = txtCPF.getText();
			controller.addPessoaFisica(nome, email, telefone, cpf);
			JOptionPane.showMessageDialog(this, "Cadastro de Pessoa Física realizado com sucesso!");
		} else if ("Pessoa Jurídica".equals(tipoUsuarioSelecionado)) {
			String cnpj = txtCNPJ.getText();
			String prepostoNome = (String) comboPreposto.getSelectedItem();
			PessoaFisica preposto = null;
			for (Pessoa pessoa : controller.getPessoasFisicas()) {
				if (pessoa instanceof PessoaFisica && ((PessoaFisica) pessoa).getNome().equals(prepostoNome)) {
					preposto = (PessoaFisica) pessoa;
					break;
				}
			}
			controller.addPessoaJuridica(nome, email, telefone, cnpj, preposto);
			JOptionPane.showMessageDialog(this, "Cadastro de Pessoa Jurídica realizado com sucesso!");
		} else if ("Advogado".equals(tipoUsuarioSelecionado)) {
			String cpf = txtCPF.getText();
			long registro = Long.parseLong(txtRegistro.getText());
			controller.addAdvogado(nome, email, telefone, cpf, registro);
			JOptionPane.showMessageDialog(this, "Cadastro de Advogado realizado com sucesso!");
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
			textAreaListA.append(String.format("Nome: %s Email: %s Telefone: %s CPF: %s Registro: %s\n", p.getNome(),
					p.getEmail(), p.getTelefone(), p.getCadastroRF(), p.getRegistro()));
		}
	}

	private void limparForm() {
		txtNome.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtCPF.setText("");
		txtCNPJ.setText("");
		comboPreposto.setSelectedIndex(-1);
		txtRegistro.setText("");
	}
}
