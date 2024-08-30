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

	private JButton btnCategoriaView;
	private JButton btnItemView;

	public MenuView() {
		initialize();
	}

	private void initialize() {

		setTitle("App Catalogo");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		this.setContentPane(contentPane);

		btnCategoriaView = new JButton("Categorias");
		btnCategoriaView.setBounds(6, 18, 228, 29);

		btnCategoriaView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCategoriaView();
			}
		});

		btnItemView = new JButton("Itens");
		btnItemView.setBounds(6, 52, 228, 29);

		btnItemView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionItemView();
			}
		});

		contentPane.add(btnCategoriaView);
		contentPane.add(btnItemView);
	}

	private void actionCategoriaView() {

		CategoriaView categoriaView = new CategoriaView();
		categoriaView.setVisible(true);
	}

	private void actionItemView() {

		ItemView itemView = new ItemView();
		itemView.setVisible(true);
	}
}
