package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import controller.PessoaController;

public class MenuView extends JFrame {

	private static final long serialVersionUID = -2113576277373274435L;

	private JPanel contentPane;

	public MenuView() {
		initialize();
	}

	private void initialize() {

		setTitle("App Escritorio");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		this.setContentPane(contentPane);

		JButton btnCadastroTribunalView = new JButton("Cadastro Tribunal");
		btnCadastroTribunalView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionTribunalView();
			}
		});
		btnCadastroTribunalView.setBounds(10, 11, 228, 29);
		contentPane.add(btnCadastroTribunalView);
		
		JButton btnCadastroPessoas = new JButton("Cadastro Pessoas");
		btnCadastroPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPessoaView();
			}
		});
		btnCadastroPessoas.setBounds(10, 51, 228, 29);
		contentPane.add(btnCadastroPessoas);
		
		JButton btnCadastroClientes = new JButton("Cadastro Clientes");
		btnCadastroClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionClienteView();
			}
		});
		btnCadastroClientes.setBounds(10, 91, 228, 29);
		contentPane.add(btnCadastroClientes);
	}

	private void actionTribunalView() {

		TribunalView tribunalView = new TribunalView();
		tribunalView.setVisible(true);
	}
	
	private void actionPessoaView() {

		PessoaController pessoaController = new PessoaController();
		PessoaView pessoaView = new PessoaView(pessoaController);
		pessoaView.setVisible(true);
	}
	
	private void actionClienteView() {
		PessoaController pessoaController = new PessoaController();
        ClienteController clienteController = new ClienteController();

		ClienteView clienteView = new ClienteView(clienteController, pessoaController);
		clienteView.setVisible(true);
	}
}
