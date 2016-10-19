package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import Model.Conexao;
import net.proteanit.sql.DbUtils;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaSalasAssociadas extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	private final String desligarAr = "0";
	private final String LigarAr = "1";

	private final String aumentarTemperatura = "2";
	private final String diminuirTemperatura = "3";

	private final String aumentarFan = "4";
	private final String diminuirFan = "5";

	private final String turboAtivado = "6";
	private final String turboDesativado = "7";

	private final String blowingDirectAutoAtivado = "8";
	private final String blowingDirectAutoDesativado = "9";

	private final String alterarModo = "10";

	private JPanel contentPane;
	private JTable table;
	private JTextField txtFildSensor1, txtFildSensor2, txtFildSensor3, textBusca, txtFan;
	private JTextField txtfildTemperaturaAtual, textFieldLiberarSala;
	private JButton btnAumentarTemperatura, btnModo;
	private ResultSet rs;
	private JMenuBar menuBar;
	private JMenuItem mntmCadastraSala;
	private JMenuItem mntmAssociar;
	private JMenuItem mntmMonitorar;
	private JMenuItem mntmCadastrarUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSalasAssociadas frame = new TelaSalasAssociadas();
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
	public TelaSalasAssociadas() {

		super(" Tela de Monitoramento ");
		Conexao conexao = new Conexao();
		java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBounds(100, 100, d.width, d.height);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		mntmCadastrarUsuario = new JMenuItem("Novo Usu\u00E1rio");
		mntmCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarUsuario();
			}
		});

		mntmMonitorar = new JMenuItem("Todas As Salas");
		mntmMonitorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostarTelaDeSalasAssociadas(conexao);
			}
		});
		mnOpcoes.add(mntmMonitorar);

		mntmAssociar = new JMenuItem("Associamento");
		mntmAssociar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTelaDeAssociamento(conexao);
			}
		});
		mnOpcoes.add(mntmAssociar);

		JSeparator separator1Menu = new JSeparator();
		mnOpcoes.add(separator1Menu);
		mnOpcoes.add(mntmCadastrarUsuario);

		JMenuItem mntmTrocarDeUsuario = new JMenuItem("Trocar de Usu\u00E1rio");
		mntmTrocarDeUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocarUsuarios(conexao);
			}
		});
		mntmCadastraSala = new JMenuItem("Nova Sala");
		mntmCadastraSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarSala();
			}
		});
		mnOpcoes.add(mntmCadastraSala);

		JSeparator separator2Menu = new JSeparator();
		mnOpcoes.add(separator2Menu);
		mnOpcoes.add(mntmTrocarDeUsuario);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sairDoAplicativo(conexao);
			}
		});
		mnOpcoes.add(mntmTrocarDeUsuario);

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

		JMenuItem mntmAterarNome = new JMenuItem("Alterar Nome ");
		mntmAterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterarNome(conexao);
			}
		});
		mnMinhaConta.add(mntmAterarNome);

		JSeparator separator3Menu = new JSeparator();
		mnOpcoes.add(separator3Menu);
		mnOpcoes.add(mntmSair);
		mnOpcoes.add(mntmSair);

		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setBackground(new Color(153, 255, 153));
		mnAjuda.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnAjuda);

		JMenuItem mntmVersao = new JMenuItem("Vers\u00E3o");
		mnAjuda.add(mntmVersao);

		JMenuItem menuNivelDeAcesso = new JMenuItem("N\u00EDveis");
		menuNivelDeAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuNivelDeAcessoAction(e);
			}
		});
		mnAjuda.add(menuNivelDeAcesso);
		mntmVersao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTelaDeVersao();
			}
		});

		JLabel telaDaDalaSeparada = new JLabel();
		telaDaDalaSeparada.setBounds(63, 406, 222, 143);

		ImageIcon icon_sala_separada = new ImageIcon(getClass().getResource("imgs/sala.jpg"));
		Image img = icon_sala_separada.getImage().getScaledInstance(telaDaDalaSeparada.getWidth(),
				telaDaDalaSeparada.getHeight(), Image.SCALE_DEFAULT);
		telaDaDalaSeparada.setIcon(new ImageIcon(img));

		telaDaDalaSeparada.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		telaDaDalaSeparada.setBackground(new Color(0, 255, 204));
		getContentPane().add(telaDaDalaSeparada);

		setLocationRelativeTo(null);
		setNomeUsuario();

		JPanel panelControles = new JPanel();
		panelControles.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		panelControles.setBackground(new Color(102, 255, 153));
		panelControles.setBounds(347, 406, 998, 226);
		contentPane.add(panelControles);
		panelControles.setLayout(null);

		btnModo = new JButton("Modo");
		btnModo.setForeground(Color.BLACK);
		btnModo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModoAction(conexao);
			}
		});
		btnModo.setBackground(new Color(0, 153, 51));
		btnModo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModo.setBounds(691, 161, 283, 40);
		panelControles.add(btnModo);
		btnModo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));

		JButton btnFanMais = new JButton("Fan +");
		btnFanMais.setForeground(Color.BLACK);
		btnFanMais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnFanMaisAction(conexao);
			}
		});
		btnFanMais.setBackground(new Color(0, 153, 51));
		btnFanMais.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFanMais.setBounds(556, 168, 69, 33);
		panelControles.add(btnFanMais);
		btnFanMais.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));

		JButton btnFanMenos = new JButton("Fan -");
		btnFanMenos.setForeground(Color.BLACK);
		btnFanMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFanMenosAction(conexao);
			}
		});
		btnFanMenos.setBackground(new Color(0, 153, 51));
		btnFanMenos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFanMenos.setBounds(403, 168, 69, 33);
		panelControles.add(btnFanMenos);
		btnFanMenos.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));

		txtFan = new JTextField();
		txtFan.setEditable(false);
		txtFan.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFan.setHorizontalAlignment(SwingConstants.CENTER);
		txtFan.setForeground(Color.BLACK);
		txtFan.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		txtFan.setBounds(482, 168, 64, 33);
		panelControles.add(txtFan);
		txtFan.setColumns(10);

		Label labelVentilacao = new Label("Ventila\u00E7\u00E3o");
		labelVentilacao.setBounds(403, 129, 222, 33);
		panelControles.add(labelVentilacao);
		labelVentilacao.setForeground(Color.BLACK);
		labelVentilacao.setFont(new Font("Dialog", Font.BOLD, 14));
		labelVentilacao.setAlignment(Label.CENTER);

		btnAumentarTemperatura = new JButton("+");
		btnAumentarTemperatura.setForeground(Color.BLACK);
		btnAumentarTemperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAumentarTemperaturaAction(conexao);
			}
		});
		btnAumentarTemperatura.setBounds(575, 57, 51, 33);
		panelControles.add(btnAumentarTemperatura);
		btnAumentarTemperatura.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAumentarTemperatura.setBackground(new Color(0, 153, 51));
		btnAumentarTemperatura.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));

		txtfildTemperaturaAtual = new JTextField();
		txtfildTemperaturaAtual.setEditable(false);
		txtfildTemperaturaAtual.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtfildTemperaturaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		txtfildTemperaturaAtual.setForeground(Color.BLACK);
		txtfildTemperaturaAtual.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));
		txtfildTemperaturaAtual.setBounds(464, 57, 101, 33);
		panelControles.add(txtfildTemperaturaAtual);
		txtfildTemperaturaAtual.setColumns(10);

		JButton btnDiminuirTemperatura = new JButton("-");
		btnDiminuirTemperatura.setForeground(Color.BLACK);
		btnDiminuirTemperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDiminuirTemperaturaAction(conexao);
			}
		});
		btnDiminuirTemperatura.setBounds(403, 57, 51, 33);
		panelControles.add(btnDiminuirTemperatura);
		btnDiminuirTemperatura.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDiminuirTemperatura.setBackground(new Color(0, 153, 51));
		btnDiminuirTemperatura.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));

		Label lblReguladorDeTemperatura = new Label("Temperatura");
		lblReguladorDeTemperatura.setBounds(404, 18, 222, 33);
		panelControles.add(lblReguladorDeTemperatura);
		lblReguladorDeTemperatura.setAlignment(Label.CENTER);
		lblReguladorDeTemperatura.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReguladorDeTemperatura.setForeground(new Color(0, 0, 0));

		Label labelOutrasFuncoes = new Label("Outas Fun\u00E7\u00F5es");
		labelOutrasFuncoes.setBounds(691, 17, 283, 33);
		panelControles.add(labelOutrasFuncoes);
		labelOutrasFuncoes.setForeground(Color.BLACK);
		labelOutrasFuncoes.setFont(new Font("Dialog", Font.BOLD, 14));
		labelOutrasFuncoes.setAlignment(Label.CENTER);

		JSeparator separator1PainelDeControle = new JSeparator();
		separator1PainelDeControle.setForeground(new Color(0, 153, 51));
		separator1PainelDeControle.setBackground(new Color(0, 153, 51));
		separator1PainelDeControle.setOrientation(SwingConstants.VERTICAL);
		separator1PainelDeControle.setBounds(349, 0, 2, 274);
		panelControles.add(separator1PainelDeControle);

		txtFildSensor1 = new JTextField();
		txtFildSensor1.setEditable(false);
		txtFildSensor1.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFildSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		txtFildSensor1.setBounds(182, 17, 110, 40);
		panelControles.add(txtFildSensor1);
		txtFildSensor1.setColumns(10);
		txtFildSensor1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));

		txtFildSensor3 = new JTextField();
		txtFildSensor3.setEditable(false);
		txtFildSensor3.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFildSensor3.setHorizontalAlignment(SwingConstants.CENTER);
		txtFildSensor3.setBounds(182, 160, 110, 40);
		panelControles.add(txtFildSensor3);
		txtFildSensor3.setColumns(10);
		txtFildSensor3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));

		txtFildSensor2 = new JTextField();
		txtFildSensor2.setEditable(false);
		txtFildSensor2.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFildSensor2.setHorizontalAlignment(SwingConstants.CENTER);
		txtFildSensor2.setBounds(182, 88, 110, 40);
		panelControles.add(txtFildSensor2);
		txtFildSensor2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		txtFildSensor2.setColumns(10);

		JLabel lbl_1_sensor = new JLabel("1\u00B0 Sensor :");
		lbl_1_sensor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_1_sensor.setBounds(62, 17, 110, 40);
		panelControles.add(lbl_1_sensor);

		JLabel lbl_2_sensor = new JLabel("2\u00B0 Sensor :");
		lbl_2_sensor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_2_sensor.setBounds(62, 86, 110, 40);
		panelControles.add(lbl_2_sensor);

		JLabel lblSensor = new JLabel("3\u00BA Sensor :");
		lblSensor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSensor.setBounds(62, 161, 110, 40);
		panelControles.add(lblSensor);

		JToggleButton tglbtnTurbo = new JToggleButton("Turbo");
		tglbtnTurbo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnTurbo.isSelected() == true) {
					//Model.ConexaoComArduino.enviarDados(turboAtivado);
				} else if (tglbtnTurbo.isSelected() == false) {
					//Model.ConexaoComArduino.enviarDados(turboDesativado);
				}
				atualizarTabelaDoAr(conexao);
			}
		});
		tglbtnTurbo.setForeground(Color.BLACK);
		tglbtnTurbo.setFont(new Font("Tahoma", Font.BOLD, 16));
		tglbtnTurbo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51)));
		tglbtnTurbo.setBackground(new Color(0, 153, 51));
		tglbtnTurbo.setBounds(691, 56, 283, 40);
		panelControles.add(tglbtnTurbo);

		JToggleButton tglbtnAutoBlowing = new JToggleButton("Auto Blowing");
		tglbtnAutoBlowing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnAutoBlowing.isSelected() == true) {
					//Model.ConexaoComArduino.enviarDados(blowingDirectAutoAtivado);
				} else if (tglbtnAutoBlowing.isSelected() == false) {
					//Model.ConexaoComArduino.enviarDados(blowingDirectAutoDesativado);
				}
				atualizarTabelaDoAr(conexao);

			}
		});
		tglbtnAutoBlowing.setForeground(Color.BLACK);
		tglbtnAutoBlowing.setFont(new Font("Tahoma", Font.BOLD, 16));
		tglbtnAutoBlowing.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51)));
		tglbtnAutoBlowing.setBackground(new Color(0, 153, 51));
		tglbtnAutoBlowing.setBounds(691, 107, 283, 40);
		panelControles.add(tglbtnAutoBlowing);

		JToggleButton tglbtnOnOff = new JToggleButton("Ligar");
		tglbtnOnOff.setBounds(63, 333, 222, 40);
		contentPane.add(tglbtnOnOff);
		tglbtnOnOff.setFont(new Font("Tahoma", Font.BOLD, 18));
		tglbtnOnOff.setBackground(new Color(0, 153, 51));
		tglbtnOnOff.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		tglbtnOnOff.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (tglbtnOnOff.isSelected() == true) {
					tglbtnOnAction(conexao);
					getRootPane().dispatchEvent(e);
				} else if (tglbtnOnOff.isSelected() == false) {
					tglbtnOffAction(conexao);
					getRootPane().dispatchEvent(e);
				}
				atualizarTabelaDoAr(conexao);
			}
		});
		tglbtnOnOff.setForeground(new Color(0, 0, 0));

		textBusca = new JTextField();
		textBusca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textBusca.setForeground(Color.LIGHT_GRAY);
		textBusca.setText("Busca por Bloco");
		textBusca.addKeyListener(new KeyAdapter() {

			private int contadordeclick;

			public void keyPressed(KeyEvent arg0) {
				textBusca.setFont(new Font("Dialog", Font.PLAIN, 14));
				textBusca.setForeground(Color.BLACK);
				contadordeclick++;
				if (contadordeclick == 1) {
					textBusca.setText("");
				}

			}

		});
		textBusca.addMouseListener(new MouseAdapter() {

			private int contadordeclick;

			public void mouseClicked(MouseEvent arg0) {
				textBusca.setFont(new Font("Dialog", Font.PLAIN, 14));
				textBusca.setForeground(Color.BLACK);
				contadordeclick += arg0.getClickCount();
				if (contadordeclick == 1) {
					textBusca.setText("");
				}

			}
		});
		textBusca.setBounds(978, 32, 184, 28);
		contentPane.add(textBusca);
		textBusca.setColumns(10);
		textBusca.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));

		JButton btnBusca = new JButton("Busca");
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarSala(conexao);
			}
		});
		btnBusca.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBusca.setForeground(Color.BLACK);
		btnBusca.setBackground(new Color(0, 153, 51));
		btnBusca.setBounds(1172, 32, 78, 28);
		contentPane.add(btnBusca);
		btnBusca.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));

		JLabel nivelDeAcesso = new JLabel();
		nivelDeAcesso.setBounds(63, 104, 222, 218);
		ImageIcon icon_nivel = new ImageIcon(getClass().getResource("imgs/numero1.jpg"));
		Image imgem = icon_nivel.getImage().getScaledInstance(nivelDeAcesso.getWidth(), nivelDeAcesso.getHeight(),
				Image.SCALE_DEFAULT);
		nivelDeAcesso.setIcon(new ImageIcon(imgem));
		nivelDeAcesso.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		contentPane.add(nivelDeAcesso);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 51)));
		scrollPane.setBounds(347, 72, 993, 301);
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.DARK_GRAY));
		contentPane.add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setValoresSelecionadosNaTabelaParaCampos(conexao);
				textFieldLiberarSala.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				setBotoesPorNivelDeAcesso(TelaDeLogin.getNivelDeAcesso());
			}
		});
		table.setBorder(null);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.CYAN);

		JLabel lblSuasSalas = new JLabel("Suas salas");
		lblSuasSalas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSuasSalas.setBounds(347, 32, 206, 28);
		contentPane.add(lblSuasSalas);

		JButton btnLiberarSala = new JButton("Liberar Sala");
		btnLiberarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liberarSala(conexao);
			}
		});
		btnLiberarSala.setForeground(Color.BLACK);
		btnLiberarSala.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLiberarSala.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51)));
		btnLiberarSala.setBackground(new Color(0, 153, 51));
		btnLiberarSala.setBounds(63, 599, 222, 33);
		contentPane.add(btnLiberarSala);

		textFieldLiberarSala = new JTextField();
		textFieldLiberarSala.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLiberarSala.setFont(new Font("Tahoma", Font.BOLD, 13));
		textFieldLiberarSala.setEditable(false);
		textFieldLiberarSala.setColumns(10);
		textFieldLiberarSala
				.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),

						new Color(0, 153, 51), new Color(0, 153, 51)));
		textFieldLiberarSala.setBounds(63, 560, 222, 28);
		contentPane.add(textFieldLiberarSala);

		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		java.net.URL url = this.getClass().getResource("imgs/Logo_1.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);

		atualizarTabelaDoAr(conexao);

	}

	private void alterarNome(Conexao conexao) {
		try {
			String nomeAlterado = JOptionPane.showInputDialog("Digite o seu nome!");

			String sql = "UPDATE \"Usuários\"  SET \"Nome\" = '" + nomeAlterado + "' WHERE \"ID_Usuário\" = '"
					+ TelaDeLogin.getid() + "'";

			conexao.updateSql(sql);

			JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o nome! \n" + e);
		}

	}

	private void liberarSala(Conexao conexao) {

		try {
			String sql = "DELETE FROM \"AssociadosAsSalas\" WHERE \"ID_SalaPK\" = '" + textFieldLiberarSala.getText()
					+ "'";
			conexao.updateSql(sql);

			JOptionPane.showMessageDialog(null, "Sala Liberada com sucesso!");
			atualizarTabelaDoAr(conexao);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível liberar sala! \n " + e);
		}
	}

	private void setBotoesPorNivelDeAcesso(int nivelDeAcesso) {
		switch (nivelDeAcesso) {
		case 1:
			mntmAssociar.setEnabled(false);
			mntmCadastrarUsuario.setEnabled(false);
			mntmCadastraSala.setEnabled(false);
			mntmMonitorar.setEnabled(false);
			break;
		case 2:
			mntmCadastraSala.setEnabled(false);
			break;
		default:
			break;
		}

	}

	private void mostrarTelaDeAssociamento(Conexao conexao) {
		new TelaDeAssociacao().setVisible(true);
		conexao.fecharConexao();
		dispose();

	}

	private void buscarSala(Conexao conexao) {

		try {
			String sql = "SELECT \"N° da sala\",\"Bloco\",\"Posição das Pás\",\"Temperatura\",\"Turbo\",\"Ventilação\" "
					+ "FROM \"Salas\",\"Ar Condicionado\", \"AssociadosAsSalas\" "
					+ "WHERE \"ID_Ar\" = \"ID_Sala\" AND \"ID_Sala\" = \"ID_SalaPK\" AND \"Bloco\" ~* '"
					+ textBusca.getText() + "'";

			rs = conexao.executeSql(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception error) {
			error.printStackTrace();
		}

	}

	private void setValoresSelecionadosNaTabelaParaCampos(Conexao conexao) {
		try {
			String sql = "SELECT \"N° do sensor\",\"Temperatura\",\"Ventilação\",\"N° do sensor 2\",\"N° do sensor 3\""
					+ " FROM \"Salas\",\"Ar Condicionado\",\"Sensores\" "
					+ " WHERE \"ID_Ar\" = \"ID_Sala\" AND \"ID_Ar\" = \"ID_Sensor\" AND \"ID_Sala\" = "
					+ table.getValueAt(table.getSelectedRow(), 0);

			rs = conexao.executeSql(sql);

			while (rs.next()) {
				txtFildSensor1.setText(rs.getString(1));
				txtfildTemperaturaAtual.setText(rs.getString(2));
				txtFan.setText(rs.getString(3));
				txtFildSensor2.setText(rs.getString(4));
				txtFildSensor3.setText(rs.getString(5));
			}

		} catch (SQLException e) {

			txtfildTemperaturaAtual.setText("");
			txtFan.setText("");
			txtFildSensor1.setText("");
			txtFildSensor2.setText("");
			txtFildSensor3.setText("");

			JOptionPane.showMessageDialog(null, e);

		} finally {

		}
	}

	private void atualizarTabelaDoAr(Conexao conexao) {
		try {
			String sql = "SELECT id_sala,numero_sala,bloco,temperatura,auto_blowing,turbo,ventilacao FROM salas,ar_condicionado,associadosassalas WHERE id_ar = id_sala AND id_sala = id_salapk";

			rs = conexao.executeSql(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		setBotoesPorNivelDeAcesso(TelaDeLogin.getNivelDeAcesso());
	}

	private void tglbtnOffAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(desligarAr);
		atualizarTabelaDoAr(conexao);

	}

	private void setNomeUsuario() {
		Label bemVindo = new Label("Seja bem vindo, " + TelaDeLogin.getNome() + " !!");
		bemVindo.setFont(new Font("Dialog", Font.BOLD, 13));
		bemVindo.setBounds(63, 72, 222, 26);
		getContentPane().add(bemVindo);

	}

	private void btnModoAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(alterarModo);
		atualizarTabelaDoAr(conexao);
	}

	private void btnFanMaisAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(aumentarFan);
		atualizarTabelaDoAr(conexao);

	}

	private void btnFanMenosAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(diminuirFan);
		atualizarTabelaDoAr(conexao);
	}

	private void btnDiminuirTemperaturaAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(diminuirTemperatura);
		atualizarTabelaDoAr(conexao);
	}

	private void btnAumentarTemperaturaAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(aumentarTemperatura);
		atualizarTabelaDoAr(conexao);
	}

	private void tglbtnOnAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(LigarAr);
		atualizarTabelaDoAr(conexao);

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
					JOptionPane.showMessageDialog(null, "Suas senhas não confere!");
					antigaSenha = JOptionPane.showInputDialog("Digite sua Senha atual!", "Senha");

				}
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
				JOptionPane.showMessageDialog(null, "Não foi possível atualizar senha!\n" + e);

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

		boolean verificaPrimeiroIgualSegundo = false;

		do {
			JOptionPane.showMessageDialog(null, p, primeiroNome, JOptionPane.QUESTION_MESSAGE);

			if (primeiro.getText().equals(segundo.getText())) {
				verificaPrimeiroIgualSegundo = true;

			} else {

				JOptionPane.showMessageDialog(null, "Os campos não conferem!\n            Tente Novamente!");
				primeiro.setText("");
				segundo.setText("");
			}
		} while (false || verificaPrimeiroIgualSegundo == false);
		return primeiro.getText();
	}

	private void mostarTelaDeSalasAssociadas(Conexao conexao) {
		new TelaDeTodasAsSalas().setVisible(true);
		conexao.fecharConexao();
		dispose();

	}

	private void menuNivelDeAcessoAction(ActionEvent e) {
		acessarNivelDeAcesso();

	}

	private void acessarNivelDeAcesso() {
		new NivelDeAcesso().setVisible(true);

	}

	private void mostrarTelaDeVersao() {
		new Versao().setVisible(true);
	}

	private void cadastrarUsuario() {
		new TelaDeCadastramentoUsuario().setVisible(true);
	}

	private void cadastrarSala() {
		new CadastroSala().setVisible(true);
	}

	private void trocarUsuarios(Conexao conexao) {
		new TelaDeLogin().setVisible(true);
		conexao.fecharConexao();
		dispose();
	}


	private void sairDoAplicativo(Conexao conexao) {
		if (JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmação",
				JOptionPane.YES_NO_OPTION) == 0) {
			conexao.fecharConexao();
			System.exit(1);

		}
	}
}
