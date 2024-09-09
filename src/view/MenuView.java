package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuView extends JFrame {

	private static final long serialVersionUID = -2113576277373274435L;

	private JPanel contentPane;

	public MenuView() {
		initialize();
	}

	private void initialize() {

		setTitle("App Escritório de Advocacia");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		this.setContentPane(contentPane);

		// Botão de cadastro de tribunais
		JButton btnCadastroTribunalView = new JButton("Cadastro Tribunal");
		btnCadastroTribunalView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionTribunalView();
			}
		});
		btnCadastroTribunalView.setBounds(10, 11, 228, 29);
		contentPane.add(btnCadastroTribunalView);

		// Botão de cadastro de pessoas (advogados, partes contrárias)
		JButton btnCadastroPessoas = new JButton("Cadastro de Pessoas");
		btnCadastroPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPessoaView();
			}
		});
		btnCadastroPessoas.setBounds(10, 51, 228, 29);
		contentPane.add(btnCadastroPessoas);

		// Botão de cadastro de clientes
		JButton btnCadastroClientes = new JButton("Cadastro de Clientes");
		btnCadastroClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionClienteView();
			}
		});
		btnCadastroClientes.setBounds(10, 91, 228, 29);
		contentPane.add(btnCadastroClientes);

		// Botão de cadastro de processos
		JButton btnCadastroProcessos = new JButton("Cadastro de Processos");
		btnCadastroProcessos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionProcessoView();
			}
		});
		btnCadastroProcessos.setBounds(10, 131, 228, 29);
		contentPane.add(btnCadastroProcessos);

		// Botão de gerenciamento de contas
		JButton btnGerenciarContas = new JButton("Gerenciamento de Contas");
		btnGerenciarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionContaView();
			}
		});
		btnGerenciarContas.setBounds(10, 171, 228, 29);
		contentPane.add(btnGerenciarContas);

		// Botão de gerenciamento de processos
		JButton btnGerenciarProcessos = new JButton("Gerenciamento de Processos");
		btnGerenciarProcessos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionGerenciarProcessosView();
			}
		});
		btnGerenciarProcessos.setBounds(10, 211, 228, 29);
		contentPane.add(btnGerenciarProcessos);
	}

	private void actionTribunalView() {
		TribunalView tribunalView = new TribunalView();
		tribunalView.setVisible(true);
	}

	private void actionPessoaView() {
		PessoaView pessoaView = new PessoaView();
		pessoaView.setVisible(true);
	}

	private void actionClienteView() {
		ClienteView clienteView = new ClienteView();
		clienteView.setVisible(true);
	}

	private void actionProcessoView() {
		ProcessoView processoView = new ProcessoView();
		processoView.setVisible(true);
	}

	private void actionContaView() {
		ContaView contaView = new ContaView();
		contaView.setVisible(true);
	}

	private void actionGerenciarProcessosView() {
		GerenciarProcessosView gerenciarProcessosView = new GerenciarProcessosView();
		gerenciarProcessosView.setVisible(true);
	}
}
