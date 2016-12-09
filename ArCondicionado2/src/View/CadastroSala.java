package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Model.Conexao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CadastroSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Conexao conexao = new Conexao();
	private JPanel contentPane;
	private JTextField textFieldNSala;
	private JTextField textFielBloco;
	private JLabel lblNomeDaSala;
	private JLabel lblBloco;
	private JLabel lblTipo;
	private JTextField textFieldNDoSensor1;
	private JButton btnCadastrar;
	private JButton btn_sair;
	private Connection con;
	private PreparedStatement prs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroSala frame = new CadastroSala();
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
	public CadastroSala() {
		
		super("Nova sala ");
		//conexao();
		Conexao conexao = new Conexao();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 463);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNSala = new JTextField();
		textFieldNSala.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNSala.setBounds(325, 33, 78, 33);
		textFieldNSala.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		textFieldNSala.setColumns(10);
		contentPane.add(textFieldNSala);

		textFielBloco = new JTextField();
		textFielBloco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFielBloco.setColumns(10);
		textFielBloco.setBounds(325, 83, 78, 33);
		textFielBloco.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		contentPane.add(textFielBloco);

		lblNomeDaSala = new JLabel("Numero Da Sala :");
		lblNomeDaSala.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomeDaSala.setBounds(186, 34, 129, 33);
		contentPane.add(lblNomeDaSala);

		lblBloco = new JLabel("Bloco :");
		lblBloco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBloco.setBounds(186, 83, 129, 33);
		contentPane.add(lblBloco);

		lblTipo = new JLabel("IP Arduino:");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipo.setBounds(186, 128, 129, 33);
		contentPane.add(lblTipo);

		textFieldNDoSensor1 = new JTextField();
		textFieldNDoSensor1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNDoSensor1.setBounds(325, 127, 260, 33);
		textFieldNDoSensor1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		textFieldNDoSensor1.setColumns(10);
		contentPane.add(textFieldNDoSensor1);

		btnCadastrar = new JButton("Cadastrar ");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarAction(e);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCadastrar.setBackground(new Color(0, 153, 51));
		btnCadastrar.setBounds(186, 299, 152, 52);
		btnCadastrar.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		contentPane.add(btnCadastrar);

		btn_sair = new JButton("Sair");
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_sairAction(evt);
			}
		});
		btn_sair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_sair.setBackground(new Color(0, 153, 51));
		btn_sair.setBounds(433, 299, 152, 52);
		btn_sair.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		contentPane.add(btn_sair);

		JLabel nivel_de_acesso = new JLabel();
		nivel_de_acesso.setBounds(10, 33, 166, 147);
		ImageIcon icon_nivel = new ImageIcon(getClass().getResource("imgs/numero3.jpg"));
		Image imgem = icon_nivel.getImage().getScaledInstance(nivel_de_acesso.getWidth(), nivel_de_acesso.getHeight(),
				Image.SCALE_DEFAULT);
		nivel_de_acesso.setIcon(new ImageIcon(imgem));
		nivel_de_acesso.setToolTipText("NIVEL DE ACESSO, PROGRAMADOR !");
		nivel_de_acesso.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		contentPane.add(nivel_de_acesso);

		java.net.URL url = this.getClass().getResource("imgs/Logo_1.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);
		setLocationRelativeTo(null);

	}

	private void conexao() {
		con = conexao.getConnetion();
		

	}

	protected void btnCadastrarAction(ActionEvent e) {
		cadastrarSalas();

	}

	private void cadastrarSalas() {
			String ip = "http://"+textFieldNDoSensor1.getText();
			String sql = "INSERT INTO salas(numero_sala,bloco,ip_arduino) VALUES( " + textFieldNSala.getText() + ", '"	+ textFielBloco.getText() + "','"+ip+"')";
			conexao.updateSql(sql);			
			JOptionPane.showMessageDialog(null, "Sala cadastrada com sucesso!");
			}

	protected void btn_sairAction(ActionEvent evt) {
		conexao.fecharConexao();
		sair();
	}

	private void sair() {		
			dispose();
		
		dispose();

	}
}
