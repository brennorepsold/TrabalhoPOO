package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuView extends JFrame {

    private static final long serialVersionUID = -2113576277373274435L;

    private JPanel contentPane;

    public MenuView() {
        initialize();
    }

    private void initialize() {
        setTitle("App Escritório de Advocacia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());  // Usando GridBagLayout para responsividade

        this.setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Margens internas para espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Os botões vão se expandir horizontalmente para preencher o espaço

        // Botão de cadastro de tribunais
        JButton btnCadastroTribunalView = new JButton("Cadastro Tribunal");
        btnCadastroTribunalView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionTribunalView();
            }
        });
        gbc.gridx = 0;  // Coluna
        gbc.gridy = 0;  // Linha
        gbc.weightx = 1;  // Expande para ocupar o espaço horizontalmente
        gbc.weighty = 0.1;  // Peso vertical menor para distribuir melhor
        contentPane.add(btnCadastroTribunalView, gbc);

        // Botão de cadastro de pessoas (advogados, partes contrárias)
        JButton btnCadastroPessoas = new JButton("Cadastro de Pessoas");
        btnCadastroPessoas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionPessoaView();
            }
        });
        gbc.gridy = 1;  // Próxima linha
        contentPane.add(btnCadastroPessoas, gbc);

        // Botão de cadastro de processos
        JButton btnCadastroProcessos = new JButton("Cadastro de Processos");
        btnCadastroProcessos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionProcessoView();
            }
        });
        gbc.gridy = 2;  // Próxima linha
        contentPane.add(btnCadastroProcessos, gbc);
    }

    private void actionTribunalView() {
        TribunalView tribunalView = new TribunalView();
        tribunalView.setVisible(true);
    }

    private void actionPessoaView() {
        PessoaView pessoaView = new PessoaView();
        pessoaView.setVisible(true);
    }

    private void actionProcessoView() {
        ProcessoView processoView = new ProcessoView();
        processoView.setVisible(true);
    }
}
