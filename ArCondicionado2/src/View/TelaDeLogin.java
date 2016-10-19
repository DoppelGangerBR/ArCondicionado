package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import Model.Conexao;
import java.awt.Cursor;

public class TelaDeLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField campoPassSenha;
	private JTextField campoTextNome;
	private boolean verificadorDeSucessoNoLogin = false;
	private ResultSet rs;
	private static String id;
	private static String nomeString;
	private static int nivelDeAcesso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeLogin window = new TelaDeLogin();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDeLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		new JFrame();
		setTitle("Login");

		Conexao conexao = new Conexao();
		getContentPane().setBackground(new Color(153, 255, 153));
		getContentPane().setForeground(Color.BLACK);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblSenha = new JLabel("Senha :");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(83, 91, 46, 25);
		getContentPane().add(lblSenha);

		setLocationRelativeTo(null);

		JButton btnEntrar = new JButton("Entrar ");
		btnEntrar.setSelectedIcon(null);
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setBorder(null);
		ImageIcon icon_btnentrar = new ImageIcon(getClass().getResource("imgs/true.png"));
		Image img = icon_btnentrar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnEntrar.setIcon(new ImageIcon(img));
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEntrar.setBackground(new Color(0, 153, 51));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntrarNaAplicacao(conexao);
			}

		});

		btnEntrar.setBounds(83, 146, 121, 38);
		getContentPane().add(btnEntrar);

		campoPassSenha = new JPasswordField();
		campoPassSenha.setBounds(139, 93, 196, 22);
		getContentPane().add(campoPassSenha);

		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(83, 55, 46, 25);
		getContentPane().add(lblNome);

		campoTextNome = new JTextField();
		campoTextNome.setBounds(139, 57, 196, 22);
		getContentPane().add(campoTextNome);
		campoTextNome.setColumns(10);

		getRootPane().setDefaultButton(btnEntrar);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 153, 51));
		menuBar.setBounds(0, 0, 434, 21);
		getContentPane().add(menuBar);

		JMenu mn_ajuda_login = new JMenu("Ajuda");
		mn_ajuda_login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mn_ajuda_login);

		JMenuItem mntmVersao = new JMenuItem("Vers\u00E3o");
		mntmVersao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmVersao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTelaDeVersao();
			}
		});
		mn_ajuda_login.add(mntmVersao);

		JButton btn_sair = new JButton("Sair");
		btn_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_sair.setBorder(null);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				sairDaAplicacao(conexao);
			}
		});
		ImageIcon icon_btnsair = new ImageIcon(getClass().getResource("imgs/false.png"));
		Image imgsair = icon_btnsair.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btn_sair.setIcon(new ImageIcon(imgsair));
		btn_sair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_sair.setBackground(new Color(0, 153, 51));
		btn_sair.setBounds(214, 146, 121, 38);
		getContentPane().add(btn_sair);

		java.net.URL url = this.getClass().getResource("imgs/Logo_1.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);

	}

	private void sairDaAplicacao(Conexao conexao) {
		conexao.fecharConexao();
		System.exit(1);

	}

	private void mostrarTelaDeVersao() {
		new Versao().setVisible(true);

	}

	@SuppressWarnings("deprecation")
	private void EntrarNaAplicacao(Conexao conexao) {

		try {
			rs = conexao.executeSql(
					"SELECT \"login\", \"senha\",\"nome\",\"id_usuario\",\"nivel_acesso\" FROM \"usuarios\" ");

			while (rs.next()) {

				if (rs.getString(1).equals(campoTextNome.getText())
						&& rs.getString(2).equals(campoPassSenha.getText())) {

					setNomeUsuario(rs.getString(3));
					setId(rs.getString(4));
					setNivelDeAcesso(rs.getInt(5));

					new TelaSalasAssociadas().setVisible(true);
					verificadorDeSucessoNoLogin = true;
					conexao.fecharConexao();
					dispose();
				}
			}

			if (verificadorDeSucessoNoLogin == false) {
				campoTextNome.setText("");
				campoPassSenha.setText("");
				JOptionPane.showMessageDialog(null, " Lamento informar que sua senha ou login estão errados, \n "
						+ "                           Tente novamente !!");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar Login !" + e);
		}

	}

	private void setNomeUsuario(String nome) {
		TelaDeLogin.nomeString = nome;
	}

	public static String getNome() {
		return nomeString;
	}

	public void setId(String id) {
		TelaDeLogin.id = id;
	}

	public static String getid() {
		return id;
	}

	public static int getNivelDeAcesso() {
		return nivelDeAcesso;
	}

	public static void setNivelDeAcesso(int nivelDeAcesso) {
		TelaDeLogin.nivelDeAcesso = nivelDeAcesso;
	}
}
