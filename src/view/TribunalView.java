package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.MainController;
import controller.TribunalController;

public class TribunalView extends JFrame {
    private static final long serialVersionUID = -5659880888694503154L;

    private JPanel contentPane;

    private JLabel lblSigla;
    private JLabel lblDescricao;
    private JLabel lblSecao;

    private JTextField txtSigla;
    private JTextField txtDescricao;
    private JTextField txtSecao;
    private JTextArea textArea;

    private JButton btnSalvar;
    private JButton btnListar;

    public TribunalView() {
        initialize();
    }

    private void initialize() {
        setTitle("Tribunal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400); // Ajuste o tamanho da janela conforme necessário

        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Configuração para labels e campos de texto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.3;
        gbc.weighty = 0.0;

        lblSigla = new JLabel("Sigla");
        contentPane.add(lblSigla, gbc);

        gbc.gridy = 1;
        lblDescricao = new JLabel("Descrição");
        contentPane.add(lblDescricao, gbc);

        gbc.gridy = 2;
        lblSecao = new JLabel("Seção");
        contentPane.add(lblSecao, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtSigla = new JTextField();
        contentPane.add(txtSigla, gbc);

        gbc.gridy = 1;
        txtDescricao = new JTextField();
        contentPane.add(txtDescricao, gbc);

        gbc.gridy = 2;
        txtSecao = new JTextField();
        contentPane.add(txtSecao, gbc);

        // Configuração para painel de botões
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(5, 5, 5, 5);
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.fill = GridBagConstraints.HORIZONTAL;

        btnSalvar = new JButton("Salvar");
        buttonPanel.add(btnSalvar, buttonGbc);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionSalvar();
            }
        });

        buttonGbc.gridy = 1;
        btnListar = new JButton("Listar");
        buttonPanel.add(btnListar, buttonGbc);
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionListar();
            }
        });

        contentPane.add(buttonPanel, gbc);

        // Configuração para textArea
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        contentPane.add(scrollPane, gbc);

        actionListar();
    }

    private void actionSalvar() {
        TribunalController controller = MainController.getTribunalController();

        String sigla = txtSigla.getText();
        String descricao = txtDescricao.getText();
        String secao = txtSecao.getText();

        controller.addTribunal(sigla, descricao, secao);

        limparForm();

        actionListar();
    }

    private void actionListar() {
        TribunalController controller = MainController.getTribunalController();

        textArea.setText(null);
        for (String siglaTribunal : controller.getTribunais()) {
            textArea.append(String.format("%s\n", siglaTribunal));
        }
    }

    private void limparForm() {
        txtSigla.setText("");
        txtDescricao.setText("");
        txtSecao.setText("");
    }
}
