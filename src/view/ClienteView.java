package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ClienteController;
import controller.PessoaController;
import model.Cliente;
import model.Pessoa;

public class ClienteView extends JFrame {

    private static final long serialVersionUID = -654321987654321L;

    private JPanel contentPane;
    private JTabbedPane tabbedPane;

    private JPanel listPane; // Painel de listagem de clientes
    private JPanel formPane; // Painel de cadastro de clientes
    private JPanel processoPane; // Painel de gerenciamento de processos de clientes

    private JTextArea textAreaList; // Área de texto para listar clientes
    private JComboBox<String> cbbTipoCadastroRF; // ComboBox para selecionar CPF ou CNPJ
    private JTextArea textAreaPessoas; // Área de texto para listar pessoas físicas ou jurídicas
    private JTextArea textAreaProcessos; // Área de texto para listar processos
    private JTextField txtCadastroRFProcesso; // Campo de texto para cadastro RF do cliente

    private ClienteController clienteController; // Controlador de clientes
    private PessoaController pessoaController; // Controlador de pessoas

    public ClienteView(ClienteController clienteController, PessoaController pessoaController) {
        this.clienteController = clienteController; // Inicializa o controlador de clientes
        this.pessoaController = pessoaController; // Inicializa o controlador de pessoas
        initialize();
    }

    private void initialize() {
        setTitle("Gestão de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600); // Define o tamanho da janela

        contentPane = new JPanel();
        contentPane.setLayout(null); // Usando layout absoluto
        setContentPane(contentPane);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 760, 540); // Posição e tamanho fixos
        contentPane.add(tabbedPane);

        formPane = new JPanel();
        formPane.setLayout(null); // Layout absoluto para o painel de cadastro de clientes
        listPane = new JPanel();
        listPane.setLayout(null); // Layout absoluto para o painel de listagem de clientes
        processoPane = new JPanel();
        processoPane.setLayout(null); // Layout absoluto para o painel de gerenciamento de processos

        initFormPane();
        initListPane();
        initProcessoPane();

        tabbedPane.add("Cadastro de Cliente", formPane);
        tabbedPane.add("Listagem de Clientes", listPane);
        tabbedPane.add("Gerenciar Processos", processoPane);
    }

    private void initFormPane() {
        JLabel lblTipoCadastroRF = new JLabel("Tipo de Cadastro RF:");
        lblTipoCadastroRF.setBounds(10, 10, 150, 25); // Posição e tamanho fixos
        formPane.add(lblTipoCadastroRF);

        cbbTipoCadastroRF = new JComboBox<>(new String[]{"CPF", "CNPJ"});
        cbbTipoCadastroRF.setBounds(170, 10, 100, 25); // Posição e tamanho fixos
        formPane.add(cbbTipoCadastroRF);

        textAreaPessoas = new JTextArea();
        textAreaPessoas.setEditable(false); // Apenas leitura
        JScrollPane scrollPanePessoas = new JScrollPane(textAreaPessoas);
        scrollPanePessoas.setBounds(10, 50, 720, 300); // Posição e tamanho fixos
        formPane.add(scrollPanePessoas);

        // Alternar visibilidade de acordo com a seleção do ComboBox
        cbbTipoCadastroRF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoCadastroSelecionado = (String) cbbTipoCadastroRF.getSelectedItem();
                atualizarListaPessoas(tipoCadastroSelecionado);
            }
        });

        JButton btnSelecionarPessoa = new JButton("Selecionar Pessoa como Cliente");
        btnSelecionarPessoa.setBounds(260, 370, 200, 30); // Posição e tamanho fixos
        btnSelecionarPessoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSelecionarPessoa();
            }
        });
        formPane.add(btnSelecionarPessoa);
    }

    private void initListPane() {
        JLabel lblClientes = new JLabel("Lista de Clientes");
        lblClientes.setBounds(10, 10, 150, 25); // Posição e tamanho fixos
        listPane.add(lblClientes);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(170, 10, 100, 25); // Posição e tamanho fixos
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionListarClientes();
            }
        });
        listPane.add(btnListar);

        textAreaList = new JTextArea();
        JScrollPane scrollPaneList = new JScrollPane(textAreaList);
        scrollPaneList.setBounds(10, 50, 720, 400); // Posição e tamanho fixos
        listPane.add(scrollPaneList);
    }

    private void initProcessoPane() {
        JLabel lblCadastroRF = new JLabel("Cadastro RF do Cliente:");
        lblCadastroRF.setBounds(10, 10, 150, 25); // Posição e tamanho fixos
        processoPane.add(lblCadastroRF);

        txtCadastroRFProcesso = new JTextField();
        txtCadastroRFProcesso.setBounds(170, 10, 200, 25); // Posição e tamanho fixos
        processoPane.add(txtCadastroRFProcesso);

        textAreaProcessos = new JTextArea();
        textAreaProcessos.setEditable(false); // Apenas leitura
        JScrollPane scrollPaneProcessos = new JScrollPane(textAreaProcessos);
        scrollPaneProcessos.setBounds(10, 50, 720, 350); // Posição e tamanho fixos
        processoPane.add(scrollPaneProcessos);

        JButton btnAdicionarProcesso = new JButton("Adicionar Processo");
        btnAdicionarProcesso.setBounds(150, 420, 200, 30); // Posição e tamanho fixos
        processoPane.add(btnAdicionarProcesso);

        JButton btnRemoverProcesso = new JButton("Remover Processo");
        btnRemoverProcesso.setBounds(400, 420, 200, 30); // Posição e tamanho fixos
        processoPane.add(btnRemoverProcesso);
    }

    private void abrirDialogoSelecionarProcesso(JTextField txtCadastroRFProcesso) {
        JDialog dialog = new JDialog(this, "Selecionar Processo", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Obtém a lista de números de processos do controlador
        List<Long> numerosProcessos = clienteController.getClientesComProcessos();
        JList<String> list = new JList<>(numerosProcessos.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnSelecionar = new JButton("Selecionar");
        btnSelecionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSelecionado = list.getSelectedValue();
                if (numeroSelecionado != null) {
                    txtCadastroRFProcesso.setText(numeroSelecionado); // Exibe o número selecionado na interface principal
                    dialog.dispose(); // Fecha o diálogo
                } else {
                    JOptionPane.showMessageDialog(dialog, "Selecione um processo da lista.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(btnSelecionar, BorderLayout.SOUTH);
        dialog.getContentPane().add(panel);
        dialog.setVisible(true);
    }

    private void actionSelecionarPessoa() {
        String tipoCadastroSelecionado = (String) cbbTipoCadastroRF.getSelectedItem();
        String pessoaSelecionada = textAreaPessoas.getSelectedText(); // Obtém o texto selecionado

        if (pessoaSelecionada != null) {
            if ("CPF".equals(tipoCadastroSelecionado)) {
                long cpf = Long.parseLong(pessoaSelecionada.split(" ")[1]); // Assumindo que o formato de exibição seja "Nome CPF"
                Pessoa pessoa = pessoaController.getPessoasFisicas().get(cpf);
                if (clienteController.addCliente(pessoa)) {
                    JOptionPane.showMessageDialog(this, "Pessoa Física adicionada como cliente com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar a pessoa como cliente. Verifique se ela já é um cliente.");
                }
            } else if ("CNPJ".equals(tipoCadastroSelecionado)) {
                long cnpj = Long.parseLong(pessoaSelecionada.split(" ")[1]); // Assumindo que o formato de exibição seja "Nome CNPJ"
                Pessoa pessoa = pessoaController.getPessoasJuridicas().get(cnpj);
                if (clienteController.addCliente(pessoa)) {
                    JOptionPane.showMessageDialog(this, "Pessoa Jurídica adicionada como cliente com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar a pessoa como cliente. Verifique se ela já é um cliente.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa da lista.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarListaPessoas(String tipoCadastro) {
        textAreaPessoas.setText(""); // Limpa a área de texto

        if ("CPF".equals(tipoCadastro)) {
            Map<Long, Pessoa> pessoasFisicas = pessoaController.getPessoasFisicas();
            for (Pessoa pessoa : pessoasFisicas.values()) {
                textAreaPessoas.append(pessoa.getNome() + " " + pessoa.getCadastroRF() + "\n");
            }
        } else if ("CNPJ".equals(tipoCadastro)) {
            Map<Long, Pessoa> pessoasJuridicas = pessoaController.getPessoasJuridicas();
            for (Pessoa pessoa : pessoasJuridicas.values()) {
                textAreaPessoas.append(pessoa.getNome() + " " + pessoa.getCadastroRF() + "\n");
            }
        }
    }

    private void actionListarClientes() {
        textAreaList.setText(""); // Limpa a área de texto antes de adicionar os dados

        Map<Long, Cliente> clientes = clienteController.getClientes(); // Corrigido para Map<Long, Cliente>
        for (Cliente cliente : clientes.values()) {  // Itera sobre Cliente em vez de Pessoa
            textAreaList.append(cliente.toString() + "\n"); // Exibe a lista de clientes
        }
    }

    private void actionCancelar() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
