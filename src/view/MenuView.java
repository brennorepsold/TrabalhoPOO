package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
		contentPane.setLayout(new GridBagLayout());
		this.setContentPane(contentPane);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JButton btnCadastroTribunalView = new JButton("Cadastro Tribunal");
		btnCadastroTribunalView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionTribunalView();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		contentPane.add(btnCadastroTribunalView, gbc);

		JButton btnCadastroPessoas = new JButton("Cadastro de Pessoas");
		btnCadastroPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPessoaView();
			}
		});
		gbc.gridy = 1;
		contentPane.add(btnCadastroPessoas, gbc);

		JButton btnCadastroProcessos = new JButton("Cadastro de Processos");
		btnCadastroProcessos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionProcessoView();
			}
		});
		gbc.gridy = 2;
		contentPane.add(btnCadastroProcessos, gbc);
	}

	private void actionTribunalView() {
		TribunalView tribunalView = new TribunalView();
		tribunalView.setVisible(true);
	}

	private void actionPessoaView() {
		PessoaView pessoaView = new PessoaView();
		pessoaView.setVisible(true);
	}

	private void actionProcessoView() {
		ProcessoView processoView = new ProcessoView();
		processoView.setVisible(true);
	}
}
