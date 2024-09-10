package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.MainController;
import controller.PessoaController;
import controller.TribunalController;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ProcessoView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTabbedPane mainTabbedPane;

	// Componentes de Processo
	private JTextField txtNumeroProcesso;
	private JTextField txtDataAbertura;
	private JComboBox<String> comboClientes;
	private JComboBox<String> comboParteContraria;
	private JComboBox<String> cbbTribunal;
	private JButton btnSalvarProcesso;
	private JComboBox<String> comboClientesListar;
	private JButton btnListarProcessos;
	private JTextArea textAreaProcessos;

	// Componentes de Audiências
	private JTextField txtDataAudiencia;
	private JTextField txtRecomendacaoAudiencia;
	private JComboBox<String> comboAdvogadoAudiencia;
	private JButton btnSalvarAudiencia;
	private JComboBox<String> comboProcessoListarAudiencias;
	private JButton btnListarAudiencias;
	private JTextArea textAreaAudiencias;

	// Componentes de Custas
	private JComboBox<String> comboFormaPagamento;
	private JTextField txtDataPagamento;
	private JTextField txtValorPagamento;
	private JButton btnSalvarPagamento;
	private JTextField txtDataDespesa;
	private JTextField txtDescricaoDespesa;
	private JTextField txtValorDespesa;
	private JButton btnSalvarDespesa;
	private JButton btnListarExtrato;
	private JTextArea textAreaExtratoConta;

	public ProcessoView() {
		initialize();
	}

	private void initialize() {
		setTitle("Gerenciamento de Processos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(mainTabbedPane, BorderLayout.CENTER);

		// Abas principais
		JPanel panelProcesso = new JPanel(new BorderLayout());
		JPanel panelAudiencias = new JPanel(new BorderLayout());
		JPanel panelCustas = new JPanel(new BorderLayout());

		mainTabbedPane.addTab("Processo", panelProcesso);
		mainTabbedPane.addTab("Audiências", panelAudiencias);
		mainTabbedPane.addTab("Custas", panelCustas);

		initProcessoPane(panelProcesso);
		initAudienciasPane(panelAudiencias);
		initCustasPane(panelCustas);
	}

	private void initProcessoPane(JPanel panelProcesso) {
		TribunalController tribunalController = MainController.getTribunalController();
		PessoaController pessoaController = MainController.getPessoaController();

		JTabbedPane processoTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelProcesso.add(processoTabbedPane, BorderLayout.CENTER);

		// Cadastrar Processo
		JPanel panelCadastrarProcesso = new JPanel(new GridLayout(7, 2, 5, 5));
		processoTabbedPane.addTab("Cadastrar Processo", panelCadastrarProcesso);

		JLabel lblNumeroProcesso = new JLabel("Número do Processo:");
		txtNumeroProcesso = new JTextField();
		panelCadastrarProcesso.add(lblNumeroProcesso);
		panelCadastrarProcesso.add(txtNumeroProcesso);

		JLabel lblDataAbertura = new JLabel("Data de Abertura:");
		txtDataAbertura = new JTextField();
		panelCadastrarProcesso.add(lblDataAbertura);
		panelCadastrarProcesso.add(txtDataAbertura);

		JLabel lblCliente = new JLabel("Cliente:");
		comboClientes = new JComboBox<>(new Vector<>(pessoaController.getNomesPessoas()));

		panelCadastrarProcesso.add(lblCliente);
		panelCadastrarProcesso.add(comboClientes);

		JLabel lblParteContraria = new JLabel("Parte Contrária:");
		comboParteContraria = new JComboBox<>(new Vector<>(pessoaController.getNomesPessoas()));
		panelCadastrarProcesso.add(lblParteContraria);
		panelCadastrarProcesso.add(comboParteContraria);

		JLabel lblTribunal = new JLabel("Tribunal:");
		cbbTribunal = new JComboBox<>(new Vector<>(tribunalController.getSiglasTribunais()));
		panelCadastrarProcesso.add(lblTribunal);
		panelCadastrarProcesso.add(cbbTribunal);

		btnSalvarProcesso = new JButton("Salvar Processo");
		btnSalvarProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvarProcesso();
			}
		});
		panelCadastrarProcesso.add(new JLabel()); // Placeholder
		panelCadastrarProcesso.add(btnSalvarProcesso);

		// Listar Processos
		JPanel panelListarProcessos = new JPanel(new BorderLayout());
		processoTabbedPane.addTab("Listar Processos", panelListarProcessos);

		JPanel panelTopListar = new JPanel(new FlowLayout());
		comboClientesListar = new JComboBox<>();
		btnListarProcessos = new JButton("Listar Processos");
		panelTopListar.add(comboClientesListar);
		panelTopListar.add(btnListarProcessos);
		panelListarProcessos.add(panelTopListar, BorderLayout.NORTH);

		textAreaProcessos = new JTextArea();
		JScrollPane scrollProcessos = new JScrollPane(textAreaProcessos);
		panelListarProcessos.add(scrollProcessos, BorderLayout.CENTER);
	}

	private void initAudienciasPane(JPanel panelAudiencias) {
		JTabbedPane audienciasTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelAudiencias.add(audienciasTabbedPane, BorderLayout.CENTER);

		// Cadastrar Audiência
		JPanel panelCadastrarAudiencia = new JPanel(new GridLayout(5, 2, 5, 5));
		audienciasTabbedPane.addTab("Cadastrar Audiência", panelCadastrarAudiencia);

		JLabel lblDataAudiencia = new JLabel("Data da Audiência:");
		txtDataAudiencia = new JTextField();
		panelCadastrarAudiencia.add(lblDataAudiencia);
		panelCadastrarAudiencia.add(txtDataAudiencia);

		JLabel lblRecomendacao = new JLabel("Recomendação:");
		txtRecomendacaoAudiencia = new JTextField();
		panelCadastrarAudiencia.add(lblRecomendacao);
		panelCadastrarAudiencia.add(txtRecomendacaoAudiencia);

		JLabel lblAdvogado = new JLabel("Advogado:");
		comboAdvogadoAudiencia = new JComboBox<>();
		panelCadastrarAudiencia.add(lblAdvogado);
		panelCadastrarAudiencia.add(comboAdvogadoAudiencia);

		btnSalvarAudiencia = new JButton("Salvar Audiência");
		panelCadastrarAudiencia.add(new JLabel()); // Placeholder
		panelCadastrarAudiencia.add(btnSalvarAudiencia);

		// Listar Audiências
		JPanel panelListarAudiencias = new JPanel(new BorderLayout());
		audienciasTabbedPane.addTab("Listar Audiências", panelListarAudiencias);

		JPanel panelTopListarAudiencias = new JPanel(new FlowLayout());
		comboProcessoListarAudiencias = new JComboBox<>();
		btnListarAudiencias = new JButton("Listar Audiências");
		panelTopListarAudiencias.add(comboProcessoListarAudiencias);
		panelTopListarAudiencias.add(btnListarAudiencias);
		panelListarAudiencias.add(panelTopListarAudiencias, BorderLayout.NORTH);

		textAreaAudiencias = new JTextArea();
		JScrollPane scrollAudiencias = new JScrollPane(textAreaAudiencias);
		panelListarAudiencias.add(scrollAudiencias, BorderLayout.CENTER);
	}

	private void initCustasPane(JPanel panelCustas) {
		JTabbedPane custasTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelCustas.add(custasTabbedPane, BorderLayout.CENTER);

		// Adicionar Pagamento
		JPanel panelPagamento = new JPanel(new GridLayout(5, 2, 5, 5));
		custasTabbedPane.addTab("Adicionar Pagamento", panelPagamento);

		JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
		comboFormaPagamento = new JComboBox<>();
		panelPagamento.add(lblFormaPagamento);
		panelPagamento.add(comboFormaPagamento);

		JLabel lblDataPagamento = new JLabel("Data do Pagamento:");
		txtDataPagamento = new JTextField();
		panelPagamento.add(lblDataPagamento);
		panelPagamento.add(txtDataPagamento);

		JLabel lblValorPagamento = new JLabel("Valor:");
		txtValorPagamento = new JTextField();
		panelPagamento.add(lblValorPagamento);
		panelPagamento.add(txtValorPagamento);

		btnSalvarPagamento = new JButton("Salvar Pagamento");
		panelPagamento.add(new JLabel()); // Placeholder
		panelPagamento.add(btnSalvarPagamento);

		// Adicionar Despesa
		JPanel panelDespesa = new JPanel(new GridLayout(5, 2, 5, 5));
		custasTabbedPane.addTab("Adicionar Despesa", panelDespesa);

		JLabel lblDataDespesa = new JLabel("Data da Despesa:");
		txtDataDespesa = new JTextField();
		panelDespesa.add(lblDataDespesa);
		panelDespesa.add(txtDataDespesa);

		JLabel lblDescricaoDespesa = new JLabel("Descrição:");
		txtDescricaoDespesa = new JTextField();
		panelDespesa.add(lblDescricaoDespesa);
		panelDespesa.add(txtDescricaoDespesa);

		JLabel lblValorDespesa = new JLabel("Valor:");
		txtValorDespesa = new JTextField();
		panelDespesa.add(lblValorDespesa);
		panelDespesa.add(txtValorDespesa);

		btnSalvarDespesa = new JButton("Salvar Despesa");
		panelDespesa.add(new JLabel()); // Placeholder
		panelDespesa.add(btnSalvarDespesa);

		// Extrato da Conta
		JPanel panelExtrato = new JPanel(new BorderLayout());
		custasTabbedPane.addTab("Extrato da Conta", panelExtrato);

		btnListarExtrato = new JButton("Listar Extrato");
		panelExtrato.add(btnListarExtrato, BorderLayout.NORTH);

		textAreaExtratoConta = new JTextArea();
		JScrollPane scrollExtratoConta = new JScrollPane(textAreaExtratoConta);
		panelExtrato.add(scrollExtratoConta, BorderLayout.CENTER);

	}

	private void actionSalvarProcesso() {

	}

}
