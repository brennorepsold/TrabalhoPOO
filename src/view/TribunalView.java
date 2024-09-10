package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import controller.TribunalController;

import model.Tribunal;

public class TribunalView extends JFrame {

	private static final long serialVersionUID = 1L;

	private TribunalController controller = MainController.getTribunalController();

	private JPanel contentPane;
	private JTextField txtSigla;
	private JTextField txtDescricao;
	private JTextField txtSecao;
	private JTextArea textArea;
	private JButton btnListar;

	public TribunalView() {
		initialize();
	}

	private void initialize() {

		setTitle("Tribunal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sigla");
		lblNewLabel.setBounds(10, 26, 61, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(87, 26, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Secao");
		lblNewLabel_2.setBounds(248, 26, 61, 16);
		contentPane.add(lblNewLabel_2);

		txtSigla = new JTextField();
		txtSigla.setBounds(6, 47, 73, 26);
		contentPane.add(txtSigla);
		txtSigla.setColumns(10);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(83, 47, 158, 26);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);

		txtSecao = new JTextField();
		txtSecao.setBounds(244, 47, 73, 26);

		contentPane.add(txtSecao);
		txtSecao.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvar();
			}
		});
		btnSalvar.setBounds(327, 47, 117, 29);
		contentPane.add(btnSalvar);

		textArea = new JTextArea();
		textArea.setBounds(10, 85, 300, 176);
		contentPane.add(textArea);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
		btnListar.setBounds(327, 80, 117, 29);
		contentPane.add(btnListar);
	}

	private void actionSalvar() {

		String sigla;
		String descricao;
		String secao;

		sigla = txtSigla.getText();
		descricao = txtDescricao.getText();
		secao = txtSecao.getText();

		controller.addTribunal(sigla, descricao, secao);

		JOptionPane.showMessageDialog(this, "Tribunal gravado com sucesso!");
		limparForm();
	}

	private void actionListar() {

		List<Tribunal> lista = controller.getTribunais();

		textArea.setText("");

		for (Tribunal t : lista) {
			textArea.append(String.format("%s - %s (seção: %s)\n", t.getSigla(), t.getDescricao(), t.getSecao()));
		}
	}

	private void limparForm() {
		txtSigla.setText("");
		txtDescricao.setText("");
		txtSecao.setText("");
	}
}
