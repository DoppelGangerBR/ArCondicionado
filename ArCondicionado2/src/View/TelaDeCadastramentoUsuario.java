package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.Conexao;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class TelaDeCadastramentoUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome, textFieldLogin;
	private JPasswordField passwordFieldSenha;
	private Choice choice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastramentoUsuario frame = new TelaDeCadastramentoUsuario();
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
	public TelaDeCadastramentoUsuario() {

		Conexao conexao = new Conexao();

		setTitle("Novo Usu\u00E1rio");
		setFont(new Font("Dialog", Font.BOLD, 14));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDoNovo = new JLabel("Nome :");
		lblNomeDoNovo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeDoNovo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomeDoNovo.setBounds(84, 75, 46, 20);
		contentPane.add(lblNomeDoNovo);

		JLabel lblSenha = new JLabel("Senha :");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSenha.setBounds(84, 138, 46, 20);
		contentPane.add(lblSenha);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(150, 75, 306, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cadastraUsuario(conexao);
			}
		});
		btnCadastrar.setBackground(new Color(0, 153, 51));
		btnCadastrar.setBounds(84, 222, 153, 49);
		contentPane.add(btnCadastrar);

		JLabel lbl_bem_vindo = new JLabel(" Novo Usu\u00E1rio");
		lbl_bem_vindo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_bem_vindo.setBounds(150, 27, 194, 37);
		contentPane.add(lbl_bem_vindo);

		choice = new Choice();
		choice.add("1");
		choice.add("2");
		choice.add("3");
		choice.setBounds(150, 165, 153, 20);
		contentPane.add(choice);

		Label label = new Label("N\u00EDvel de acesso :");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(33, 165, 97, 20);
		contentPane.add(label);

		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarPrimeiraTela(conexao);
			}
		});
		btn_voltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_voltar.setBackground(new Color(0, 153, 51));
		btn_voltar.setBounds(303, 222, 153, 49);
		contentPane.add(btn_voltar);

		setLocationRelativeTo(null);
		getRootPane().setDefaultButton(btnCadastrar);

		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		textFieldLogin.setBounds(150, 107, 306, 20);
		contentPane.add(textFieldLogin);

		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLogin.setBounds(84, 107, 46, 20);
		contentPane.add(lblLogin);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(150, 138, 306, 20);
		contentPane.add(passwordFieldSenha);
		java.net.URL url = this.getClass().getResource("imgs/Logo_1.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);

	}

	private void voltarPrimeiraTela(Conexao conexao) {
		conexao.fecharConexao();
		dispose();

	}

	private void cadastraUsuario(Conexao conexao) {
		int nivel = Integer.parseInt(choice.getSelectedItem());
		try {
			String sql = "INSERT INTO \"Usuários\" (\"Nome\",\"Login\",\"Senha\",\"Nível de acesso\") VALUES('"
					+ textFieldNome.getText() + "', '" + textFieldLogin.getText() + "', '"
					+ String.valueOf(passwordFieldSenha.getPassword()) + "', '" + nivel + "')";

			conexao.executeSql(sql);
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível cadastrar um novo usuário!\n" + e);

		}

	}
}
