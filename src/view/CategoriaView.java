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

public class CategoriaView extends JFrame {

	private static final long serialVersionUID = -5659880888694503154L;

	private JPanel contentPane;

	private JLabel lblNome;

	private JTextField txtNome;
	private JTextArea textArea;

	private JButton btnSalvar;
	private JButton btnListar;

	public CategoriaView() {
		initialize();
	}

	private void initialize() {

		setTitle("Categoria");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 281);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(6, 21, 61, 16);

		txtNome = new JTextField();
		txtNome.setBounds(52, 16, 294, 26);
		txtNome.setColumns(20);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(360, 16, 117, 29);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			actionSalvar();
			}
		});

		textArea = new JTextArea();
		textArea.setBounds(6, 63, 344, 167);

		btnListar = new JButton("Listar");
		btnListar.setBounds(360, 57, 117, 29);

		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			actionListar();
			}
		});

		contentPane.add(lblNome);
		contentPane.add(txtNome);
		contentPane.add(btnSalvar);

		contentPane.add(textArea);
		contentPane.add(btnListar);

		//actionListar();
	}

/*
	private void actionSalvar() {

		CatalogoController controller = MainController.getCatalogoController();

		String nome = txtNome.getText();

		controller.addCategoria(nome);

		limparForm();

		actionListar();
	}

	private void actionListar() {

		CatalogoController controller = MainController.getCatalogoController();

		textArea.setText(null);
		for (String nomeCategoria : controller.getCategorias()) {
			textArea.append(String.format("%s\n", nomeCategoria));
		}
	}

	private void limparForm() {
		txtNome.setText("");
	}
	
	*/
}
