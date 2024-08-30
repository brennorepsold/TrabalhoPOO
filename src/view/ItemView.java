package view;

//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ItemView extends JFrame {

	private static final long serialVersionUID = -956531223574304627L;

	private JPanel contentPane;

	private JTabbedPane tabbedPane;

	private JPanel listPane; // Painel de Listagem

	private JComboBox<String> cbbCategoriaList;
	private JTextArea textAreaList;

	private JPanel formPane; // Painel do Formulario

	private JTextField txtCodigo;

//	private JComboBox<ETipoItem> cbbTipoItem;
	private JComboBox<String> cbbCategoriaForm;

	private JTextField txtDescricao;
	private JTextField txtPreco;

	public ItemView() {
		initialize();
	}

	private void initialize() {

		setTitle("Item");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 360);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	//	contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	//	contentPane.add(tabbedPane, BorderLayout.CENTER);

		listPane = new JPanel();
		formPane = new JPanel();

		initListPane();
		initFormPane();

		tabbedPane.add("Listagem", listPane);
		tabbedPane.add("Formulário", formPane);
	}

	private void initListPane() {

	//	CatalogoController controller = MainController.getCatalogoController();

		listPane.setLayout(null);

	//	cbbCategoriaList = new JComboBox<String>(new Vector<String>(controller.getCategorias()));

		cbbCategoriaList.setBounds(6, 21, 330, 27);

		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(346, 20, 109, 29);

		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});

		textAreaList = new JTextArea();
		textAreaList.setBounds(12, 60, 320, 193);

		listPane.add(cbbCategoriaList);
		listPane.add(textAreaList);

		listPane.add(btnListar);
	}

	private void initFormPane() {

	//	CatalogoController controller = MainController.getCatalogoController();

		formPane.setLayout(null);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(17, 27, 61, 16);

		JLabel lblTipoItem = new JLabel("Tipo");
		lblTipoItem.setBounds(17, 67, 61, 16);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(17, 147, 71, 16);

		JLabel lblPreco = new JLabel("Preço");
		lblPreco.setBounds(17, 189, 61, 16);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(17, 106, 61, 16);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(101, 22, 86, 26);
		txtCodigo.setColumns(10);

	//	cbbTipoItem = new JComboBox<>();
	//	cbbTipoItem.setBounds(101, 60, 130, 27);

	//	cbbTipoItem.setModel(new DefaultComboBoxModel<ETipoItem>(ETipoItem.values()));

	//	cbbCategoriaForm = new JComboBox<String>(new Vector<String>(controller.getCategorias()));
		cbbCategoriaForm.setBounds(101, 102, 216, 27);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(101, 142, 305, 26);
		txtDescricao.setColumns(10);

		txtPreco = new JTextField();
		txtPreco.setBounds(101, 184, 130, 26);
		txtPreco.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(101, 233, 117, 29);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvar();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(218, 233, 117, 29);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});

		formPane.add(lblCodigo);
		formPane.add(txtCodigo);

		formPane.add(lblTipoItem);
	//	formPane.add(cbbTipoItem);

		formPane.add(lblCategoria);
		formPane.add(cbbCategoriaForm);

		formPane.add(lblDescricao);
		formPane.add(txtDescricao);

		formPane.add(lblPreco);
		formPane.add(txtPreco);

		formPane.add(btnSalvar);
		formPane.add(btnCancelar);
	}

	private void limparForm() {

		txtCodigo.setText("");
		txtDescricao.setText("");
		txtPreco.setText("");
	}

	private void actionListar() {

	//	CatalogoController controller = MainController.getCatalogoController();

	//	String nomeCategoria = (String) cbbCategoriaList.getSelectedItem();

	//	List<String> lista = controller.getItens(nomeCategoria);

		textAreaList.setText(null);

	//	for (String strItem : lista) {
		//	textAreaList.append(String.format("%s\n", strItem));
		}
//	}

	private void actionSalvar() {

	//	CatalogoController controller = MainController.getCatalogoController();

		try {
			//String nomeCategoria = (String) cbbCategoriaForm.getSelectedItem();

	//		ETipoItem tipo = (ETipoItem) cbbTipoItem.getSelectedItem();

		//	int codigo = Integer.parseInt(txtCodigo.getText());

			//String descricao = txtDescricao.getText();

		//	double preco = Double.parseDouble(txtPreco.getText());

	//		controller.addItem(nomeCategoria, tipo, codigo, descricao, preco);

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(this, "Formato campo codigo: 0\nFormato campo preço: 0.00");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}

		limparForm();
	}

	private void actionCancelar() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
