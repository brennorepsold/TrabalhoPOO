package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import controller.TribunalController;

public class TribunalView extends JFrame {
	private static final long serialVersionUID = -5659880888694503154L;

	private JPanel contentPane;

	private JLabel lblSigla;

	private JTextField txtSigla;
	private JTextArea textArea;

	private JButton btnSalvar;
	private JButton btnListar;
	private JTextField txtDescricao;
	private JTextField txtSecao;

	public TribunalView() {
		initialize();
	}

	private void initialize() {

		setTitle("Tribunal");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 281);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		lblSigla = new JLabel("Sigla");
		lblSigla.setBounds(6, 21, 61, 16);

		txtSigla = new JTextField();
		txtSigla.setBounds(62, 16, 282, 26);
		txtSigla.setColumns(20);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(360, 16, 117, 29);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvar();
			}
		});

		textArea = new JTextArea();
		textArea.setBounds(6, 160, 344, 70);

		btnListar = new JButton("Listar");
		btnListar.setBounds(360, 57, 117, 29);

		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});

		contentPane.add(lblSigla);
		contentPane.add(txtSigla);
		contentPane.add(btnSalvar);

		contentPane.add(textArea);
		contentPane.add(btnListar);
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(20);
		txtDescricao.setBounds(62, 58, 282, 35);
		contentPane.add(txtDescricao);
		
		txtSecao = new JTextField();
		txtSecao.setColumns(20);
		txtSecao.setBounds(62, 110, 282, 26);
		contentPane.add(txtSecao);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(6, 67, 61, 16);
		contentPane.add(lblDescricao);
		
		JLabel lblSecao = new JLabel("Secao");
		lblSecao.setBounds(6, 115, 61, 16);
		contentPane.add(lblSecao);

		actionListar();
	}

	private void actionSalvar() {

		TribunalController controller = MainController.getTribunalController();

		String nome = txtSigla.getText();

		controller.addTribunal(nome, nome, nome);

		limparForm();

		actionListar();
	}

	private void actionListar() {

		TribunalController controller = MainController.getTribunalController();

		textArea.setText(null);
		for (String nomeCategoria : controller.getTribunais()) {
			textArea.append(String.format("%s\n", nomeCategoria));
		}
	}

	private void limparForm() {
		txtSigla.setText("");
	}
}
