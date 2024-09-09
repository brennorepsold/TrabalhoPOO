package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ClienteController;
import model.Cliente;
import model.Pessoa;
import model.Processo;
import model.Tribunal;

public class ClienteView extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextArea textAreaClientes;
	private JTextField txtNumeroProcesso, txtParteContraria;
	private JComboBox<Tribunal> comboTribunal;
	private ClienteController clienteController; // Controlador de Cliente

	public ClienteView() {
		
		initialize();
	}

	private void initialize() {
		setTitle("Gestão de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		JPanel panelCadastro = new JPanel(new GridLayout(5, 2, 5, 5));
		JLabel lblNumeroProcesso = new JLabel("Número do Processo:");
		txtNumeroProcesso = new JTextField();
		JLabel lblParteContraria = new JLabel("Parte Contrária:");
		txtParteContraria = new JTextField();
		JLabel lblTribunal = new JLabel("Tribunal:");
		comboTribunal = new JComboBox<>(); // Supondo que o comboBox esteja preenchido com tribunais disponíveis
		
		panelCadastro.add(lblNumeroProcesso);
		panelCadastro.add(txtNumeroProcesso);
		panelCadastro.add(lblParteContraria);
		panelCadastro.add(txtParteContraria);
		panelCadastro.add(lblTribunal);
		panelCadastro.add(comboTribunal);

		JButton btnAdicionarProcesso = new JButton("Adicionar Processo ao Cliente");
		btnAdicionarProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProcessoAoCliente();
			}
		});

		panelCadastro.add(btnAdicionarProcesso);

		textAreaClientes = new JTextArea();
		contentPane.add(new JScrollPane(textAreaClientes), BorderLayout.CENTER);
		contentPane.add(panelCadastro, BorderLayout.NORTH);

		JButton btnListarClientes = new JButton("Listar Clientes");
		btnListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarClientes();
			}
		});

		contentPane.add(btnListarClientes, BorderLayout.SOUTH);
	}

	private void adicionarProcessoAoCliente() {
		long numeroProcesso = Long.parseLong(txtNumeroProcesso.getText());
		String parteContraria = txtParteContraria.getText();
		Tribunal tribunal = (Tribunal) comboTribunal.getSelectedItem();
		Cliente clienteSelecionado = obterClienteSelecionado();

		if (clienteSelecionado != null) {
			//Processo novoProcesso = new Processo(numeroProcesso, new Date(), new Pessoa(parteContraria), tribunal, new Conta());
			//clienteSelecionado.addProcesso(novoProcesso);
			//clienteController.save(); // Salvar no controlador
			listarClientes(); // Atualizar a lista
		} else {
			// Mostra uma mensagem de erro
		}
	}

	private Cliente obterClienteSelecionado() {
		// Método para obter o cliente selecionado
		// Aqui você pode implementar uma lógica para selecionar o cliente desejado
		return null; // Substituir pela lógica de seleção do cliente
	}

	private void listarClientes() {
		textAreaClientes.setText(""); // Limpar área de texto
		Map<Long, Cliente> clientes = clienteController.getClientes();
		for (Cliente cliente : clientes.values()) {
			textAreaClientes.append(cliente.toString() + "\n");
		}
	}
}
