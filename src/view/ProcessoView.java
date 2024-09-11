package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

    private JComboBox<Long> comboProcessos;
    private JComboBox<Long> comboProcessosListar;
    private JComboBox<Long> comboProcessosPagamento;
    private JComboBox<Long> comboProcessosDespesa;
    private JComboBox<Long> comboProcessosExtrato;

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
                try {
                    actionSalvarProcesso();
                } catch (ProcessoException ex) {
                    JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelCadastrarProcesso.add(new JLabel());
        panelCadastrarProcesso.add(btnSalvarProcesso);

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
        txtDataAudiencia = new JTextField();
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
                    JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        txtDataPagamento = new JTextField();
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
                    JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        btnSalvarDespesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    actionSalvarDespesa();
                } catch (DespesaException ex) {
                    JOptionPane.showMessageDialog(ProcessoView.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

        processoController.addProcesso(numeroProcesso, cadastroCliente, cadastroParteContraria, siglaTribunal);
        JOptionPane.showMessageDialog(this, "Cadastro de Processo realizado com sucesso!");
    }

    private void actionSalvarAudiencia() throws AudienciaException {

        Long numeroProcesso = (Long) comboProcessos.getSelectedItem();
        if (numeroProcesso == null) {
            throw new AudienciaException("Por favor, selecione um processo.");
        }

        String recomendacao = txtRecomendacaoAudiencia.getText();
        String nomeAdvogado = (String) comboAdvogadoAudiencia.getSelectedItem();

        Pessoa advogado = pessoaController.getAdvogadosByCadastro(nomeAdvogado);
        processoController.addAudiencia(numeroProcesso, recomendacao, advogado);

        JOptionPane.showMessageDialog(this, "Audiência salva com sucesso!");
    }

    private void actionSalvarPagamento() throws PagamentoException {
        Long numeroProcesso = (Long) comboProcessosPagamento.getSelectedItem();
        if (numeroProcesso == null) {
            throw new PagamentoException("Por favor, selecione um processo.");
        }

        String formaPagamentoStr = (String) comboFormaPagamento.getSelectedItem();

        EFormaPagamento formaPagamento = EFormaPagamento.valueOf(formaPagamentoStr);

        double valorPagamento;
        try {
            valorPagamento = Double.parseDouble(txtValorPagamento.getText());
        } catch (NumberFormatException e) {
            throw new PagamentoException("Valor inválido. Por favor, insira um número válido.");
        }

        processoController.addPagamento(numeroProcesso, formaPagamento, valorPagamento);
        JOptionPane.showMessageDialog(this, "Pagamento salvo com sucesso!");
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
        try {
            valorDespesa = Double.parseDouble(txtValorDespesa.getText());
        } catch (NumberFormatException e) {
            throw new DespesaException("Valor inválido. Por favor, insira um número válido.");
        }

        processoController.addDespesa(numeroProcesso, descricaoDespesa, valorDespesa);
        JOptionPane.showMessageDialog(this, "Despesa salva com sucesso!");
    }

    private void actionListarProcessos() {
        String cadastroCliente = (String) comboClientesListar.getSelectedItem();
        if (cadastroCliente == null || cadastroCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Processo> listaProcessos = processoController.getProcessosCliente(cadastroCliente);
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

    // Dentro da classe ProcessoView
    private void actionListarAudiencias() {
        Long numeroProcesso = (Long) comboProcessosListar.getSelectedItem();

        if (numeroProcesso == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um processo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> listaAudiencias = processoController.getAudienciasPorProcesso(numeroProcesso);
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

        String extratoConta = processoController.getExtratoContaPorProcesso(numeroProcesso);
        textAreaExtratoConta.setText("");

        if (extratoConta.equals("Processo não encontrado.")) {
            textAreaExtratoConta.append("Nenhum extrato encontrado para o processo selecionado.\n");
        } else {
            textAreaExtratoConta.append(extratoConta + "\n");
        }
    }
}
