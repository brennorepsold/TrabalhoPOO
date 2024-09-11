package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.MainController;
import controller.PessoaController;
import controller.ProcessoController;
import controller.TribunalController;
import exception.AudienciaException;
import exception.DespesaException;
import exception.PagamentoException;
import exception.ProcessoException;
import model.EFormaPagamento;
import model.Pessoa;
import model.Processo;

public class ProcessoView extends JFrame {

	private static final long serialVersionUID = 1L;

	private ProcessoController processoController = MainController.getProcessoController();
	private TribunalController tribunalController = MainController.getTribunalController();
	private PessoaController pessoaController = MainController.getPessoaController();

	private JPanel contentPane;
	private JTabbedPane mainTabbedPane;

	private JTextField txtNumeroProcesso;
	private JTextField txtDataAbertura;
	private JComboBox<String> comboClientes;
	private JComboBox<String> comboParteContraria;
	private JComboBox<String> cbbTribunal;
	private JButton btnSalvarProcesso;
	private JComboBox<String> comboClientesListar;
	private JButton btnListarProcessos;
	private JTextArea textAreaProcessos;

	private JTextField txtDataAudiencia;
	private JTextField txtRecomendacaoAudiencia;
	private JComboBox<String> comboAdvogadoAudiencia;
	private JButton btnSalvarAudiencia;
	private JButton btnListarAudiencias;
	private JTextArea textAreaAudiencias;

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
	private JTextArea textAreaExtratoCliente;

	private JComboBox<Long> comboProcessos;
	private JComboBox<Long> comboProcessosListar;
	private JComboBox<Long> comboProcessosPagamento;
	private JComboBox<Long> comboProcessosDespesa;
	private JComboBox<Long> comboProcessosExtrato;
	private JComboBox<String> comboFaseProcesso;
	private JComboBox<String> comboTribunalEditar;
	private JComboBox<String> comboClientesExtrato;

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

		JTabbedPane processoTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelProcesso.add(processoTabbedPane, BorderLayout.CENTER);

		JPanel panelCadastrarProcesso = new JPanel(new GridLayout(7, 2, 5, 5));
		processoTabbedPane.addTab("Cadastrar Processo", panelCadastrarProcesso);

		JLabel lblNumeroProcesso = new JLabel("Número do Processo:");
		txtNumeroProcesso = new JTextField();
		panelCadastrarProcesso.add(lblNumeroProcesso);
		panelCadastrarProcesso.add(txtNumeroProcesso);

		JLabel lblDataAbertura = new JLabel("Data de Abertura:");
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter(' ');
			txtDataAbertura = new JFormattedTextField(dateMask);
			txtDataAbertura.setColumns(10);
			txtDataAbertura.setHorizontalAlignment(JFormattedTextField.CENTER);
			txtDataAbertura.setFont(new Font("SansSerif", Font.PLAIN, 14));
		} catch (Exception e) {
			e.printStackTrace();
			txtDataAbertura = new JTextField();
		}
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
				try {
					actionSalvarProcesso();
				} catch (ProcessoException ex) {
					JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelCadastrarProcesso.add(new JLabel());
		panelCadastrarProcesso.add(btnSalvarProcesso);

		JPanel panelEditarProcesso = new JPanel(new GridLayout(3, 2, 5, 5));
		processoTabbedPane.addTab("Editar Processo", panelEditarProcesso);

		JLabel lblFaseProcesso = new JLabel("Fase do Processo:");
		comboFaseProcesso = new JComboBox<>(processoController.getFasesProcessoArray());
		panelEditarProcesso.add(lblFaseProcesso);
		panelEditarProcesso.add(comboFaseProcesso);

		JLabel lblTribunalEditar = new JLabel("Tribunal:");
		comboTribunalEditar = new JComboBox<>(new Vector<>(tribunalController.getSiglasTribunais()));
		panelEditarProcesso.add(lblTribunalEditar);
		panelEditarProcesso.add(comboTribunalEditar);

		JButton btnSalvarAlteracoes = new JButton("Salvar Alterações");
		btnSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvarAlteracoesProcesso();
			}
		});
		panelEditarProcesso.add(new JLabel());
		panelEditarProcesso.add(btnSalvarAlteracoes);

		JPanel panelListarProcessos = new JPanel(new BorderLayout());
		processoTabbedPane.addTab("Listar Processos", panelListarProcessos);

		JPanel panelTopListar = new JPanel(new FlowLayout());

		JLabel lblClientes = new JLabel("Cliente:");
		panelTopListar.add(lblClientes);

		comboClientesListar = new JComboBox<>(new Vector<>(processoController.getClientes()));
		comboClientesListar.setPreferredSize(new Dimension(200, 25));
		panelTopListar.add(comboClientesListar);

		btnListarProcessos = new JButton("Listar Processos");
		btnListarProcessos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarProcessos();
			}
		});
		panelTopListar.add(btnListarProcessos);

		panelListarProcessos.add(panelTopListar, BorderLayout.NORTH);

		textAreaProcessos = new JTextArea();
		JScrollPane scrollProcessos = new JScrollPane(textAreaProcessos);
		panelListarProcessos.add(scrollProcessos, BorderLayout.CENTER);
	}

	private void initAudienciasPane(JPanel panelAudiencias) {
		JTabbedPane audienciasTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelAudiencias.add(audienciasTabbedPane, BorderLayout.CENTER);

		JPanel panelCadastrarAudiencia = new JPanel(new GridLayout(6, 2, 5, 5));
		audienciasTabbedPane.addTab("Cadastrar Audiência", panelCadastrarAudiencia);

		JLabel lblProcesso = new JLabel("Processo:");
		comboProcessos = new JComboBox<>(new Vector<>(processoController.getProcessos()));
		panelCadastrarAudiencia.add(lblProcesso);
		panelCadastrarAudiencia.add(comboProcessos);

		JLabel lblDataAudiencia = new JLabel("Data da Audiência:");
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter(' ');
			txtDataAudiencia = new JFormattedTextField(dateMask);
			txtDataAudiencia.setColumns(10);
			txtDataAudiencia.setHorizontalAlignment(JFormattedTextField.CENTER);
			txtDataAudiencia.setFont(new Font("SansSerif", Font.PLAIN, 14));
		} catch (Exception e) {
			e.printStackTrace();
			txtDataAudiencia = new JTextField();
		}
		panelCadastrarAudiencia.add(lblDataAudiencia);
		panelCadastrarAudiencia.add(txtDataAudiencia);

		JLabel lblRecomendacao = new JLabel("Recomendação:");
		txtRecomendacaoAudiencia = new JTextField();
		panelCadastrarAudiencia.add(lblRecomendacao);
		panelCadastrarAudiencia.add(txtRecomendacaoAudiencia);

		JLabel lblAdvogado = new JLabel("Advogado:");
		comboAdvogadoAudiencia = new JComboBox<>(new Vector<>(pessoaController.getNomesAdvogados()));
		panelCadastrarAudiencia.add(lblAdvogado);
		panelCadastrarAudiencia.add(comboAdvogadoAudiencia);

		btnSalvarAudiencia = new JButton("Salvar Audiência");
		btnSalvarAudiencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionSalvarAudiencia();
				} catch (AudienciaException ex) {
					JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelCadastrarAudiencia.add(new JLabel());
		panelCadastrarAudiencia.add(btnSalvarAudiencia);

		JPanel panelListarAudiencias = new JPanel(new BorderLayout());
		audienciasTabbedPane.addTab("Listar Audiências", panelListarAudiencias);

		JPanel panelTopListarAudiencias = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel lblProcessosListar = new JLabel("Processos:");
		comboProcessosListar = new JComboBox<>(new Vector<>(processoController.getProcessos()));
		comboProcessosListar.setPreferredSize(new Dimension(200, 25));
		panelTopListarAudiencias.add(lblProcessosListar);
		panelTopListarAudiencias.add(comboProcessosListar);

		btnListarAudiencias = new JButton("Listar Audiências");
		btnListarAudiencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarAudiencias();
			}
		});
		panelTopListarAudiencias.add(btnListarAudiencias);

		panelListarAudiencias.add(panelTopListarAudiencias, BorderLayout.NORTH);

		textAreaAudiencias = new JTextArea();
		JScrollPane scrollAudiencias = new JScrollPane(textAreaAudiencias);
		panelListarAudiencias.add(scrollAudiencias, BorderLayout.CENTER);
	}

	private void initCustasPane(JPanel panelCustas) {
		JTabbedPane custasTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelCustas.add(custasTabbedPane, BorderLayout.CENTER);

		JPanel panelPagamento = new JPanel(new GridLayout(6, 2, 5, 5));
		custasTabbedPane.addTab("Adicionar Pagamento", panelPagamento);

		JLabel lblProcessoPagamento = new JLabel("Processo:");
		comboProcessosPagamento = new JComboBox<>(new Vector<>(processoController.getProcessos()));
		panelPagamento.add(lblProcessoPagamento);
		panelPagamento.add(comboProcessosPagamento);

		JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
		comboFormaPagamento = new JComboBox<>(processoController.getFormasPagamentoArray());
		panelPagamento.add(lblFormaPagamento);
		panelPagamento.add(comboFormaPagamento);

		JLabel lblDataPagamento = new JLabel("Data do Pagamento:");
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter(' ');
			txtDataPagamento = new JFormattedTextField(dateMask);
			txtDataPagamento.setColumns(10);
			txtDataPagamento.setHorizontalAlignment(JFormattedTextField.CENTER);
			txtDataPagamento.setFont(new Font("SansSerif", Font.PLAIN, 14));
		} catch (Exception e) {
			e.printStackTrace();
			txtDataPagamento = new JTextField();
		}
		panelPagamento.add(lblDataPagamento);
		panelPagamento.add(txtDataPagamento);

		JLabel lblValorPagamento = new JLabel("Valor:");
		txtValorPagamento = new JTextField();
		panelPagamento.add(lblValorPagamento);
		panelPagamento.add(txtValorPagamento);

		btnSalvarPagamento = new JButton("Salvar Pagamento");
		btnSalvarPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionSalvarPagamento();
				} catch (PagamentoException ex) {
					JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelPagamento.add(new JLabel());
		panelPagamento.add(btnSalvarPagamento);

		JPanel panelDespesa = new JPanel(new GridLayout(6, 2, 5, 5));
		custasTabbedPane.addTab("Adicionar Despesa", panelDespesa);

		JLabel lblProcessoDespesa = new JLabel("Processo:");
		comboProcessosDespesa = new JComboBox<>(new Vector<>(processoController.getProcessos()));
		panelDespesa.add(lblProcessoDespesa);
		panelDespesa.add(comboProcessosDespesa);

		JLabel lblDataDespesa = new JLabel("Data da Despesa:");
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter(' ');
			txtDataDespesa = new JFormattedTextField(dateMask);
			txtDataDespesa.setColumns(10);
			txtDataDespesa.setHorizontalAlignment(JFormattedTextField.CENTER);
			txtDataDespesa.setFont(new Font("SansSerif", Font.PLAIN, 14));
		} catch (Exception e) {
			e.printStackTrace();
			txtDataDespesa = new JTextField();
		}
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
		btnSalvarDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionSalvarDespesa();
				} catch (DespesaException ex) {
					JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelDespesa.add(btnSalvarDespesa);

		JPanel panelExtrato = new JPanel(new BorderLayout());
		custasTabbedPane.addTab("Extrato da Conta", panelExtrato);

		JPanel panelTopExtrato = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel lblProcessosExtrato = new JLabel("Processos:");
		comboProcessosExtrato = new JComboBox<>(new Vector<>(processoController.getProcessos()));
		comboProcessosExtrato.setPreferredSize(new Dimension(200, 25));
		panelTopExtrato.add(lblProcessosExtrato);
		panelTopExtrato.add(comboProcessosExtrato);

		btnListarExtrato = new JButton("Listar Extrato");
		btnListarExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarConta();
			}
		});
		panelTopExtrato.add(btnListarExtrato);

		panelExtrato.add(panelTopExtrato, BorderLayout.NORTH);

		textAreaExtratoConta = new JTextArea();
		JScrollPane scrollExtratoConta = new JScrollPane(textAreaExtratoConta);
		panelExtrato.add(scrollExtratoConta, BorderLayout.CENTER);

		JPanel panelExtratoCliente = new JPanel(new BorderLayout());
		custasTabbedPane.addTab("Extrato Cliente", panelExtratoCliente);

		JPanel panelTopExtratoCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel lblClientesExtrato = new JLabel("Cliente:");
		comboClientesExtrato = new JComboBox<>(new Vector<>(processoController.getClientes()));
		comboClientesExtrato.setPreferredSize(new Dimension(200, 25));
		panelTopExtratoCliente.add(lblClientesExtrato);
		panelTopExtratoCliente.add(comboClientesExtrato);

		JButton btnListarExtratoCliente = new JButton("Listar Extrato");
		btnListarExtratoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListarExtratoCliente();
			}
		});
		panelTopExtratoCliente.add(btnListarExtratoCliente);

		panelExtratoCliente.add(panelTopExtratoCliente, BorderLayout.NORTH);

		textAreaExtratoCliente = new JTextArea();
		JScrollPane scrollExtratoCliente = new JScrollPane(textAreaExtratoCliente);
		panelExtratoCliente.add(scrollExtratoCliente, BorderLayout.CENTER);
	}

	private void actionSalvarProcesso() throws ProcessoException {
		long numeroProcesso;
		try {
			numeroProcesso = Long.parseLong(txtNumeroProcesso.getText());
		} catch (NumberFormatException e) {
			throw new ProcessoException("Número de processo inválido.");
		}
		String cadastroCliente = (String) comboClientes.getSelectedItem();
		String cadastroParteContraria = (String) comboParteContraria.getSelectedItem();
		String siglaTribunal = (String) cbbTribunal.getSelectedItem();
		String dataAbertura = txtDataAbertura.getText();

		try {
			processoController.addProcesso(numeroProcesso, cadastroCliente, cadastroParteContraria, siglaTribunal,
					dataAbertura);
			JOptionPane.showMessageDialog(this, "Cadastro de Processo realizado com sucesso!");
		} catch (ProcessoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Data de abertura inválida. Formato esperado: dd/MM/yyyy", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actionSalvarAudiencia() throws AudienciaException {
		Long numeroProcesso = (Long) comboProcessos.getSelectedItem();
		if (numeroProcesso == null) {
			throw new AudienciaException("Por favor, selecione um processo.");
		}

		String recomendacao = txtRecomendacaoAudiencia.getText();
		String nomeAdvogado = (String) comboAdvogadoAudiencia.getSelectedItem();
		Pessoa advogado = pessoaController.getAdvogadosByCadastro(nomeAdvogado);
		String dataAudiencia = txtDataAudiencia.getText();

		try {
			processoController.addAudiencia(numeroProcesso, recomendacao, advogado, dataAudiencia);
			JOptionPane.showMessageDialog(this, "Audiência salva com sucesso!");
		} catch (AudienciaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Data da audiência inválida. Formato esperado: dd/MM/yyyy", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actionSalvarPagamento() throws PagamentoException {
		Long numeroProcesso = (Long) comboProcessosPagamento.getSelectedItem();
		if (numeroProcesso == null) {
			throw new PagamentoException("Por favor, selecione um processo.");
		}

		String formaPagamentoStr = (String) comboFormaPagamento.getSelectedItem();
		EFormaPagamento formaPagamento = EFormaPagamento.valueOf(formaPagamentoStr);
		String dataPagamento = txtDataPagamento.getText();

		double valorPagamento;
		try {
			valorPagamento = Double.parseDouble(txtValorPagamento.getText());
		} catch (NumberFormatException e) {
			throw new PagamentoException("Valor inválido. Por favor, insira um número válido.");
		}

		try {
			processoController.addPagamento(numeroProcesso, formaPagamento, valorPagamento, dataPagamento);
			JOptionPane.showMessageDialog(this, "Pagamento salvo com sucesso!");
		} catch (PagamentoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Data de pagamento inválida. Formato esperado: dd/MM/yyyy", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actionSalvarDespesa() throws DespesaException {
		Long numeroProcesso = (Long) comboProcessosDespesa.getSelectedItem();
		if (numeroProcesso == null) {
			throw new DespesaException("Por favor, selecione um processo.");
		}

		String descricaoDespesa = txtDescricaoDespesa.getText();
		if (descricaoDespesa.isEmpty()) {
			throw new DespesaException("Por favor, insira uma descrição para a despesa.");
		}

		double valorDespesa;
		String dataDespesa = txtDataDespesa.getText();
		try {
			valorDespesa = Double.parseDouble(txtValorDespesa.getText());
		} catch (NumberFormatException e) {
			throw new DespesaException("Valor inválido. Por favor, insira um número válido.");
		}

		try {
			processoController.addDespesa(numeroProcesso, descricaoDespesa, valorDespesa, dataDespesa);
			JOptionPane.showMessageDialog(this, "Despesa salva com sucesso!");
		} catch (DespesaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Data da despesa inválida. Formato esperado: dd/MM/yyyy", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actionSalvarAlteracoesProcesso() {
		try {
			Long numeroProcesso = (Long) comboProcessos.getSelectedItem();
			if (numeroProcesso == null) {
				JOptionPane.showMessageDialog(this, "Por favor, selecione um processo.", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String faseSelecionada = (String) comboFaseProcesso.getSelectedItem();
			String tribunalSelecionado = (String) comboTribunalEditar.getSelectedItem();

			processoController.atualizarProcesso(numeroProcesso, faseSelecionada, tribunalSelecionado);
			JOptionPane.showMessageDialog(this, "Alterações do processo salvas com sucesso!");
		} catch (ProcessoException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actionListarProcessos() {
		String cadastroCliente = (String) comboClientesListar.getSelectedItem();
		if (cadastroCliente == null || cadastroCliente.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		List<Processo> listaProcessos = null;
		try {
			listaProcessos = processoController.getProcessosCliente(cadastroCliente);
		} catch (ProcessoException e) {

			e.printStackTrace();
		}
		textAreaProcessos.setText("");

		if (listaProcessos.isEmpty()) {
			textAreaProcessos.append("Nenhum processo encontrado para o cliente selecionado.\n");
		} else {
			for (Processo processo : listaProcessos) {
				textAreaProcessos.append(String.format("Número: %d, Cliente: %s, Parte Contrária: %s, Tribunal: %s\n",
						processo.getNumero(), processo.getCliente().getPessoa().getNome(),
						processo.getParteContraria().getNome(), processo.getTribunal().getSigla()));
			}
		}
	}

	private void actionListarAudiencias() {
		Long numeroProcesso = (Long) comboProcessosListar.getSelectedItem();

		if (numeroProcesso == null) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um processo.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		List<String> listaAudiencias = null;
		try {
			listaAudiencias = processoController.getAudienciasPorProcesso(numeroProcesso);
		} catch (AudienciaException e) {
			e.printStackTrace();
		}
		textAreaAudiencias.setText("");

		if (listaAudiencias.isEmpty()) {
			textAreaAudiencias.append("Nenhuma audiência encontrada para o processo selecionado.\n");
		} else {
			for (String audiencia : listaAudiencias) {
				textAreaAudiencias.append(audiencia + "\n");
			}
		}
	}

	private void actionListarConta() {
		Long numeroProcesso = (Long) comboProcessosExtrato.getSelectedItem();

		if (numeroProcesso == null) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um processo.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String extratoConta = null;
		try {
			extratoConta = processoController.getExtratoContaPorProcesso(numeroProcesso);
		} catch (ProcessoException e) {
			e.printStackTrace();
		}
		textAreaExtratoConta.setText("");

		if (extratoConta.equals("Processo não encontrado.")) {
			textAreaExtratoConta.append("Nenhum extrato encontrado para o processo selecionado.\n");
		} else {
			textAreaExtratoConta.append(extratoConta + "\n");
		}
	}
	
	private void actionListarExtratoCliente() {
	    String cadastroCliente = (String) comboClientesExtrato.getSelectedItem();
	    if (cadastroCliente == null || cadastroCliente.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String extratoCliente;
	    try {
	        extratoCliente = processoController.getExtratoPorCliente(cadastroCliente);
	    } catch (ProcessoException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    textAreaExtratoCliente.setText(extratoCliente);
	}
}
