package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import exception.TribunalException;
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblSigla = new JLabel("Sigla");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lblSigla, gbc);

        txtSigla = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        contentPane.add(txtSigla, gbc);

        JLabel lblNome = new JLabel("Nome");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        contentPane.add(lblNome, gbc);

        txtDescricao = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        contentPane.add(txtDescricao, gbc);

        JLabel lblSecao = new JLabel("Secao");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        contentPane.add(lblSecao, gbc);

        txtSecao = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        contentPane.add(txtSecao, gbc);

        JButton btnSalvar = new JButton("Salvar");
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    actionSalvar();
                } catch (TribunalException e1) {
                    e1.printStackTrace();
                }
            }
        });
        contentPane.add(btnSalvar, gbc);

        textArea = new JTextArea();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        contentPane.add(textArea, gbc);

        btnListar = new JButton("Listar");
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    actionListar();
                } catch (TribunalException e1) {
                    e1.printStackTrace();
                }
            }
        });
        contentPane.add(btnListar, gbc);
    }

    private void actionSalvar() throws TribunalException {

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

    private void actionListar() throws TribunalException {

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
