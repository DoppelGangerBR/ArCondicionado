package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import Model.Conexao;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class TelaDeAssociacao extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableUsuarios;
    private JTable tableSalas;
    private JLabel lblFuncionarios;
    private JLabel lblSalas;
    private JMenuBar menuBar;
    private JTextField textFieldAssociaUsuario;
    private JTextField textFieldAssociaSala;
    private ResultSet rs;
    private JTextField textBuscaUsuario;
    private JTextField txtBuscaSala;
    private JMenuItem mntmCadastraSala;
    private JMenuItem mntmTodasAsSalas;
    private JMenuItem mntmSalasAssociadas;
    private JMenuItem mntmCadastrarUsuario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaDeAssociacao frame = new TelaDeAssociacao();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaDeAssociacao() {
        super(" Associamento ");
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(TelaDeAssociacao.class.getResource("/view/imgs/Logo_1.png")));
        Conexao conexao = new Conexao();
        java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setBounds(0, 0, d.width, d.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 255, 153));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        menuBar = new JMenuBar();
        menuBar.setBounds(new Rectangle(0, 0, 0, 4));
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        menuBar.setBackground(new Color(0, 153, 51));
        setJMenuBar(menuBar);

        JMenu mnOpcoes = new JMenu("Menu");
        mnOpcoes.setBackground(new Color(153, 255, 153));
        mnOpcoes.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mnOpcoes.setForeground(new Color(0, 0, 0));
        menuBar.add(mnOpcoes);

        mntmCadastrarUsuario = new JMenuItem("Novo usu\u00E1rio");
        mntmCadastrarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mntmCadastrarActionPerform(e);
            }
        });

        mntmSalasAssociadas = new JMenuItem("Minhas salas");
        mntmSalasAssociadas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new TelaSalasAssociadas().setVisible(true);
                conexao.fecharConexao();
                dispose();
            }
        });
        mnOpcoes.add(mntmSalasAssociadas);

        mntmTodasAsSalas = new JMenuItem("Todas as salas");
        mntmTodasAsSalas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new TelaDeTodasAsSalas().setVisible(true);
                conexao.fecharConexao();
                dispose();
            }
        });
        mnOpcoes.add(mntmTodasAsSalas);

        JSeparator separatorMenu1 = new JSeparator();
        mnOpcoes.add(separatorMenu1);
        mnOpcoes.add(mntmCadastrarUsuario);

        JMenuItem mntmTrocarDeUsuario = new JMenuItem("Trocar de usuario");
        mntmTrocarDeUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trocarUsuarios(conexao);
            }
        });

        mntmCadastraSala = new JMenuItem("Nova sala");
        mntmCadastraSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mntmCadastraSalaActionEvent(e);
            }
        });
        mnOpcoes.add(mntmCadastraSala);

        JSeparator separatorMenu2 = new JSeparator();
        mnOpcoes.add(separatorMenu2);
        mnOpcoes.add(mntmTrocarDeUsuario);

        JMenuItem mntmSair = new JMenuItem("Sair");
        mntmSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sair(conexao);
            }
        });

        JMenuItem mntmTrocarLogin = new JMenuItem("Alterar Login");
        mntmTrocarLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                pedirLoginAntigo(conexao);
            }
        });

        JMenu mnMinhaConta = new JMenu("Minha Conta");
        mnOpcoes.add(mnMinhaConta);

        mnMinhaConta.add(mntmTrocarLogin);

        JMenuItem mntmAterarSenha = new JMenuItem("Alterar Senha");
        mntmAterarSenha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                pedirSenhaAntiga(conexao);
            }
        });
        mnMinhaConta.add(mntmAterarSenha);

        JMenuItem mntmAlterarNome = new JMenuItem("Alterar Nome");
        mntmAlterarNome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mntmAlterarNomeAction(conexao);
            }
        });
        mnMinhaConta.add(mntmAlterarNome);

        JSeparator separatorMenu3 = new JSeparator();
        mnOpcoes.add(separatorMenu3);
        mnOpcoes.add(mntmSair);

        JMenu mnAjuda = new JMenu("Ajuda");
        mnAjuda.setBackground(new Color(153, 255, 153));
        mnAjuda.setFont(new Font("Segoe UI", Font.BOLD, 12));
        menuBar.add(mnAjuda);

        JMenuItem mntmVersao = new JMenuItem("Vers\u00E3o");
        mnAjuda.add(mntmVersao);
        mntmVersao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mntmVersaoActionEvent(e);
            }
        });
        JMenuItem menuNivelDeAcesso = new JMenuItem("N\u00EDveis");
        menuNivelDeAcesso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuNivelDeAcessoAction(e);
            }
        });
        mnAjuda.add(menuNivelDeAcesso);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 51)));
        scrollPane.setBounds(75, 129, 586, 340);
        scrollPane.setEnabled(false);
        scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.DARK_GRAY));
        contentPane.add(scrollPane);
        tableUsuarios = new JTable();
        tableUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textFieldAssociaUsuario.setText(tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(), 0).toString());
            }
        });
        tableUsuarios.setBorder(null);
        tableUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tableUsuarios.setAutoCreateRowSorter(true);
        tableUsuarios.setFillsViewportHeight(true);
        scrollPane.setViewportView(tableUsuarios);
        scrollPane.setBackground(Color.CYAN);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.DARK_GRAY));
        scrollPane_1.setEnabled(false);
        scrollPane_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 51)));
        scrollPane_1.setBackground(Color.CYAN);
        scrollPane_1.setBounds(726, 129, 586, 340);
        tableSalas = new JTable();
        tableSalas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                textFieldAssociaSala.setText(tableSalas.getValueAt(tableSalas.getSelectedRow(), 2).toString());
            }
        });
        tableSalas.setBorder(null);
        tableSalas.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tableSalas.setAutoCreateRowSorter(true);
        tableSalas.setFillsViewportHeight(true);
        scrollPane_1.setViewportView(tableSalas);
        scrollPane_1.setBackground(Color.CYAN);
        contentPane.add(scrollPane_1);

        lblFuncionarios = new JLabel("Usu\u00E1rios dispon\u00EDveis");
        lblFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblFuncionarios.setBounds(75, 56, 369, 62);
        contentPane.add(lblFuncionarios);

        lblSalas = new JLabel("Salas dispon\u00EDveis");
        lblSalas.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSalas.setBounds(727, 56, 369, 62);
        contentPane.add(lblSalas);

        textBuscaUsuario = new JTextField();
        textBuscaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textBuscaUsuario.setForeground(Color.LIGHT_GRAY);
        textBuscaUsuario.setText("Busca usu\u00E1rio");
        textBuscaUsuario.addKeyListener(new KeyAdapter() {

            private int contadordeclick;

            public void keyPressed(KeyEvent arg0) {
                textBuscaUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
                textBuscaUsuario.setForeground(Color.BLACK);
                contadordeclick++;
                if (contadordeclick == 1) {
                    textBuscaUsuario.setText("");
                }

            }

        });
        textBuscaUsuario.addMouseListener(new MouseAdapter() {

            private int contadordeclick;

            public void mouseClicked(MouseEvent arg0) {
                textBuscaUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
                textBuscaUsuario.setForeground(Color.BLACK);
                contadordeclick += arg0.getClickCount();
                if (contadordeclick == 1) {
                    textBuscaUsuario.setText("");
                }

            }
        });
        textBuscaUsuario.setBounds(336, 90, 237, 28);
        contentPane.add(textBuscaUsuario);
        textBuscaUsuario.setColumns(10);
        textBuscaUsuario.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
                new Color(0, 153, 51), new Color(0, 153, 51)));

        JButton btnBuscaUsuario = new JButton("Busca");
        btnBuscaUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                buscarUsuario(conexao);
            }
        });
        btnBuscaUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscaUsuario.setForeground(Color.BLACK);
        btnBuscaUsuario.setBackground(new Color(0, 153, 51));
        btnBuscaUsuario.setBounds(583, 90, 78, 28);
        contentPane.add(btnBuscaUsuario);
        btnBuscaUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

        JButton btnAssocialos = new JButton("Associa-los");
        btnAssocialos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAssocialosAction(conexao);
            }
        });
        btnAssocialos.setForeground(Color.BLACK);
        btnAssocialos.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
        btnAssocialos.setBackground(new Color(0, 153, 51));
        btnAssocialos.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAssocialos.setBounds(519, 539, 349, 62);
        contentPane.add(btnAssocialos);

        textFieldAssociaUsuario = new JTextField();
        textFieldAssociaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldAssociaUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
        textFieldAssociaUsuario.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 153, 51)));
        textFieldAssociaUsuario.setBounds(343, 547, 70, 47);
        contentPane.add(textFieldAssociaUsuario);
        textFieldAssociaUsuario.setColumns(10);

        textFieldAssociaSala = new JTextField();
        textFieldAssociaSala.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldAssociaSala.setFont(new Font("Tahoma", Font.BOLD, 14));
        textFieldAssociaSala.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 153, 51)));
        textFieldAssociaSala.setColumns(10);
        textFieldAssociaSala.setBounds(1213, 547, 70, 47);
        contentPane.add(textFieldAssociaSala);

        JSeparator separatorAssociaEsquerdo = new JSeparator();
        separatorAssociaEsquerdo.setBackground(new Color(0, 153, 51));
        separatorAssociaEsquerdo.setForeground(new Color(0, 153, 51));
        separatorAssociaEsquerdo.setBounds(423, 568, 96, 2);
        contentPane.add(separatorAssociaEsquerdo);

        JSeparator separatorAssociaDireito = new JSeparator();
        separatorAssociaDireito.setForeground(new Color(0, 153, 51));
        separatorAssociaDireito.setBackground(new Color(0, 153, 51));
        separatorAssociaDireito.setBounds(862, 568, 104, 2);
        contentPane.add(separatorAssociaDireito);

        JButton btnBuscarSala = new JButton("Busca");
        btnBuscarSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarSala(conexao);
            }
        });
        btnBuscarSala.setForeground(Color.BLACK);
        btnBuscarSala.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscarSala.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnBuscarSala.setBackground(new Color(0, 153, 51));
        btnBuscarSala.setBounds(1234, 90, 78, 28);
        contentPane.add(btnBuscarSala);

        txtBuscaSala = new JTextField();
        txtBuscaSala.addKeyListener(new KeyAdapter() {

            private int contadordeclick;

            public void keyPressed(KeyEvent arg0) {
                txtBuscaSala.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtBuscaSala.setForeground(Color.BLACK);
                contadordeclick++;
                if (contadordeclick == 1) {
                    txtBuscaSala.setText("");
                }

            }

        });
        txtBuscaSala.addMouseListener(new MouseAdapter() {

            private int contadordeclick;

            public void mouseClicked(MouseEvent arg0) {
                txtBuscaSala.setFont(new Font("Dialog", Font.PLAIN, 14));
                txtBuscaSala.setForeground(Color.BLACK);
                contadordeclick += arg0.getClickCount();
                if (contadordeclick == 1) {
                    txtBuscaSala.setText("");
                }

            }
        });
        txtBuscaSala.setText("Busca sala");
        txtBuscaSala.setForeground(Color.LIGHT_GRAY);
        txtBuscaSala.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtBuscaSala.setColumns(10);
        txtBuscaSala.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
                new Color(0, 153, 51), new Color(0, 153, 51)));
        txtBuscaSala.setBounds(987, 90, 237, 28);
        contentPane.add(txtBuscaSala);

        JButton btnAtualizarTabelas = new JButton("Atualizar Tabelas");
        btnAtualizarTabelas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizaTabelaResponsaveis(conexao);
                atualizaTabelSalasDisponiveis(conexao);
            }
        });
        btnAtualizarTabelas.setForeground(new Color(0, 0, 0));
        btnAtualizarTabelas.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAtualizarTabelas.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
        btnAtualizarTabelas.setBackground(new Color(0, 153, 51));
        btnAtualizarTabelas.setBounds(519, 480, 349, 47);
        contentPane.add(btnAtualizarTabelas);

        JSeparator separatorAtualizaInferior = new JSeparator();
        separatorAtualizaInferior.setOrientation(SwingConstants.VERTICAL);
        separatorAtualizaInferior.setForeground(new Color(0, 153, 51));
        separatorAtualizaInferior.setBackground(new Color(0, 153, 51));
        separatorAtualizaInferior.setBounds(691, 526, 2, 14);
        contentPane.add(separatorAtualizaInferior);

        ImageIcon icon_nivel = new ImageIcon(getClass().getResource("imgs/branco.png"));

        JLabel lblIdDa = new JLabel("ID da Sala =");
        lblIdDa.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdDa.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIdDa.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 153, 51)));
        lblIdDa.setBackground(Color.WHITE);
        lblIdDa.setBounds(963, 539, 338, 62);
        contentPane.add(lblIdDa);

        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 153, 51)));
        lbl.setBackground(Color.WHITE);
        lbl.setBounds(963, 539, 338, 62);
        Image imgbranco = icon_nivel.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT);
        lbl.setIcon(new ImageIcon(imgbranco));
        contentPane.add(lbl);

        JLabel label = new JLabel("Usu\u00E1rio com ID =");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        label.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 153, 51)));
        label.setBackground(Color.WHITE);
        label.setBounds(91, 539, 333, 62);
        contentPane.add(label);

        JLabel lblUsurioComId = new JLabel();
        lblUsurioComId.setBounds(91, 539, 333, 62);
        Image img = icon_nivel.getImage().getScaledInstance(lblUsurioComId.getWidth(), lblUsurioComId.getHeight(),
                Image.SCALE_DEFAULT);
        lblUsurioComId.setIcon(new ImageIcon(img));
        lblUsurioComId.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsurioComId.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 153, 51)));
        lblUsurioComId.setBackground(Color.WHITE);
        lblUsurioComId.setFont(new Font("Tahoma", Font.BOLD, 15));

        contentPane.add(lblUsurioComId);

        atualizaTabelaResponsaveis(conexao);
        atualizaTabelSalasDisponiveis(conexao);
        setBotoesPorNivelDeAcesso(TelaDeLogin.getNivelDeAcesso());
    }

    protected void mntmAlterarNomeAction(Conexao conexao) {
        String nomeAlterado = JOptionPane.showInputDialog("Digite o seu nome!");

        try {
            String sql = "UPDATE \"Usuários\"  SET \"Nome\" = '" + nomeAlterado + "' WHERE \"ID_Usuário\" = '"
                    + TelaDeLogin.getid() + "'";
            conexao.updateSql(sql);
            JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void setBotoesPorNivelDeAcesso(int nivelDeAcesso) {
        switch (nivelDeAcesso) {
            case 2:
                mntmCadastraSala.setEnabled(false);
                break;
        }
    }

    protected void menuNivelDeAcessoAction(ActionEvent e) {
        acessarNivelDeAcesso();

    }

    private void acessarNivelDeAcesso() {
        NivelDeAcesso nivel = new NivelDeAcesso();
        nivel.setVisible(true);

    }

    protected void btnAssocialosAction(Conexao conexao) {

        try {
            String sql = "INSERT INTO associadosassalas (id_usuariopk,ID_SalaPK)" + " VALUES ('"
                    + textFieldAssociaUsuario.getText() + "', '" + textFieldAssociaSala.getText() + "')";

            conexao.updateSql(sql);
            JOptionPane.showMessageDialog(null, "Associa��o realizada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        atualizaTabelaResponsaveis(conexao);
        atualizaTabelSalasDisponiveis(conexao);

    }

    private void buscarSala(Conexao conexao) {
        try {
            String sql = "SELECT * FROM \"Salas\" WHERE \"Bloco\" ~* '" + txtBuscaSala.getText()
                    + "' AND \"ID_Sala\" NOT IN (SELECT \"ID_SalaPK\" FROM \"AssociadosAsSalas\" )";

            rs = conexao.executeSql(sql);
            tableSalas.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();

        } catch (SQLException error) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, error);

        }

    }

    private void buscarUsuario(Conexao conexao) {
        try {
            String sql = "SELECT * FROM usuarios WHERE Nome ~ '" + textBuscaUsuario.getText() + "'";

            rs = conexao.executeSql(sql);
            tableUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        }

    }

    private void atualizaTabelSalasDisponiveis(Conexao conexao) {
        try {
            String sql = "SELECT * FROM Salas WHERE ID_Sala NOT IN (SELECT ID_SalaPK FROM AssociadosAsSalas)";
            rs = conexao.executeSql(sql);
            tableSalas.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atualizaTabelaResponsaveis(Conexao conexao) {
        try {
            String sql = "SELECT id_usuario,nome,nivel_acesso FROM usuarios";
            

            rs = conexao.executeSql(sql);
            tableUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    protected void mntmCadastraSalaActionEvent(ActionEvent e) {
        cadastrarSala();

    }

    private void cadastrarSala() {
        CadastroSala sala = new CadastroSala();
        sala.setVisible(true);
    }

    private void sair(Conexao conexao) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente deseja sair?", "Confirma��o",
                JOptionPane.YES_NO_OPTION) == 0) {
            conexao.fecharConexao();
            System.exit(0);

        }
    }

    protected void mntmVersaoActionEvent(ActionEvent e) {
        versao();

    }

    private void versao() {
        Versao versao = new Versao();
        versao.setVisible(true);

    }

    private void trocarUsuarios(Conexao conexao) {
        TelaDeLogin login = new TelaDeLogin();
        login.setVisible(true);
        conexao.fecharConexao();
        dispose();
    }

    protected void mntmCadastrarActionPerform(ActionEvent e) {
        telaCadastrarDeUsuario();

    }

    private void telaCadastrarDeUsuario() {
        TelaDeCadastramentoUsuario cadastro = new TelaDeCadastramentoUsuario();
        cadastro.setVisible(true);

    }

    private void pedirSenhaAntiga(Conexao conexao) {
        String antigaSenha = JOptionPane.showInputDialog("Digite sua Senha atual!", "Senha");
        try {
            while (antigaSenha != null) {
                String sql = "SELECT * FROM adms WHERE \"Senha\" = '" + antigaSenha + "' AND \"ID\" = "
                        + TelaDeLogin.getid();

                rs = conexao.executeSql(sql);
                if (rs.next()) {
                    mostrarOpcaoDeTrocaSenha(conexao);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Suas senhas não confere");
                    antigaSenha = JOptionPane.showInputDialog("Digite sua Senha atual!", "Senha");

                }
                rs.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void mostrarOpcaoDeTrocaSenha(Conexao conexao) {
        String novoSenha = showConfirmacao("Nova Senha", "Confirmação");

        if (novoSenha.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar operação");
        } else {
            try {
                String sql = "UPDATE adms SET \"Senha\" = '" + novoSenha + "' WHERE \"ID\" = " + TelaDeLogin.getid();
                conexao.updateSql(sql);

                JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar senha! \n" + e);
            }
        }
    }

    private void pedirLoginAntigo(Conexao conexao) {
        String antigoLogin = JOptionPane.showInputDialog("Digite seu login atual!", "login atual");
        try {
            while (antigoLogin != null) {
                String sql = "SELECT * FROM adms WHERE \"Login\" = '" + antigoLogin + "' AND \"ID\" = "
                        + TelaDeLogin.getid();

                rs = conexao.executeSql(sql);
                if (rs.next()) {
                    mostrarOpcaoDeTrocaLogin(conexao);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Seu login atual não confere");
                    antigoLogin = JOptionPane.showInputDialog("Digite seu login atual!", "login atual");
                }
                rs.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void mostrarOpcaoDeTrocaLogin(Conexao conexao) {
        String novoLogin = showConfirmacao("Novo Login", "Confirmação");

        if (novoLogin.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar operação");
        } else {

            try {
                String sql = "UPDATE adms SET \"Login\" = '" + novoLogin + "' WHERE \"ID\" = " + TelaDeLogin.getid();
                conexao.updateSql(sql);

                JOptionPane.showMessageDialog(null, "Login atualizado com sucesso!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar senha!");
                e.printStackTrace();
            }
        }
    }

    private String showConfirmacao(String primeiroNome, String segundoNome) {

        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        labels.add(new JLabel(primeiroNome + ":   ", SwingConstants.RIGHT));
        labels.add(new JLabel(segundoNome + ": ", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField primeiro = new JPasswordField("");
        controls.add(primeiro);
        JTextField segundo = new JPasswordField("");
        controls.add(segundo);
        p.add(controls, BorderLayout.CENTER);

        boolean verificada = false;

        do {
            JOptionPane.showMessageDialog(null, p, primeiroNome, JOptionPane.QUESTION_MESSAGE);

            if (primeiro.getText().equals(segundo.getText())) {
                verificada = true;

            } else {

                JOptionPane.showMessageDialog(null, "Os campos não conferem!\n" + "            Tente Novamente!");
                primeiro.setText("");
                segundo.setText("");
            }
        } while (false || verificada == false);
        return primeiro.getText();
    }

}
