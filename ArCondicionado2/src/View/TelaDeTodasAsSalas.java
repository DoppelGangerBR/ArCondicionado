package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Model.Conexao;
import net.proteanit.sql.DbUtils;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaDeTodasAsSalas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableTodasAsSalas;
	private JTextField textFieldTemperatura;
	private final String aumentarTemperatura = "2";
	private final String diminuirTemperatura = "3";
	private JTextField textFieldLiberarSala;
	private JMenuItem mntmAssociar;
	private JMenuItem mntmMonitorar;
	private JMenuItem mntmCadastrarUsuario;
	private JMenuItem mntmCadastraSala;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeTodasAsSalas frame = new TelaDeTodasAsSalas();
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
	public TelaDeTodasAsSalas() {

		super(" Monitorameto Geral ");
		Conexao conexao = new Conexao();

		java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, d.width, d.height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.setBackground(new Color(0, 153, 51));
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		java.net.URL url = this.getClass().getResource("imgs/Logo_1.png");
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(iconeTitulo);

		JMenu mn_opcoes = new JMenu("Menu");
		mn_opcoes.setBackground(new Color(153, 255, 153));
		mn_opcoes.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mn_opcoes.setForeground(new Color(0, 0, 0));
		menuBar.add(mn_opcoes);

		mntmCadastrarUsuario = new JMenuItem("Novo Usu\u00E1rio");
		mntmCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCadastrarUsuario();
			}
		});

		mntmMonitorar = new JMenuItem("Minhas Salas");
		mntmMonitorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaSalasAssociadas().setVisible(true);
				conexao.fecharConexao();
				dispose();
			}
		});
		mn_opcoes.add(mntmMonitorar);

		mntmAssociar = new JMenuItem("Associamento");
		mntmAssociar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTelaDeAssociamento(conexao);
			}
		});
		mn_opcoes.add(mntmAssociar);

		JSeparator separator_1 = new JSeparator();
		mn_opcoes.add(separator_1);
		mn_opcoes.add(mntmCadastrarUsuario);

		JMenuItem mntmTrocarDeUsuario = new JMenuItem("Trocar de Usu\u00E1rio");
		mntmTrocarDeUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocarUsuarios(conexao);
			}
		});

		mntmCadastraSala = new JMenuItem("Nova Sala");
		mntmCadastraSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarSala(conexao);
			}
		});
		mn_opcoes.add(mntmCadastraSala);

		JSeparator separator = new JSeparator();
		mn_opcoes.add(separator);
		mn_opcoes.add(mntmTrocarDeUsuario);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sairDaAplicacao(conexao);
			}
		});

		JMenuItem mntmTrocarLogin = new JMenuItem("Alterar Login");
		mntmTrocarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pedirLoginAntigo(conexao);
			}
		});
		JMenuItem mntmAterarSenha = new JMenuItem("Alterar Senha");
		mntmAterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pedirSenhaAntiga(conexao);
			}
		});

		JMenu mnMinhaConta = new JMenu("Minha Conta");
		mn_opcoes.add(mnMinhaConta);

		mnMinhaConta.add(mntmTrocarLogin);
		mnMinhaConta.add(mntmAterarSenha);

		JMenuItem mntmAlterarNome = new JMenuItem("Alterar Nome");
		mntmAlterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmAlterarNomeAction(conexao);
			}
		});
		mnMinhaConta.add(mntmAlterarNome);

		JSeparator separator_2 = new JSeparator();
		mn_opcoes.add(separator_2);
		mn_opcoes.add(mntmSair);

		JMenu mn_ajuda = new JMenu("Ajuda");
		mn_ajuda.setBackground(new Color(153, 255, 153));
		mn_ajuda.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mn_ajuda);

		JMenuItem mntmVersao = new JMenuItem("Vers\u00E3o");
		mn_ajuda.add(mntmVersao);

		JMenuItem mntmNveis = new JMenuItem("N\u00EDveis");
		mntmNveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NivelDeAcesso nivel = new NivelDeAcesso();
				nivel.setVisible(true);
			}
		});
		mn_ajuda.add(mntmNveis);
		mntmVersao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTelaDeVersao();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 153, 51)));
		scrollPane.setBounds(291, 85, 1017, 443);
		scrollPane.setEnabled(false);
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.DARK_GRAY));
		contentPane.add(scrollPane);
		tableTodasAsSalas = new JTable();
		tableTodasAsSalas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setDadosNosCamposTextoAction(conexao);
			}
		});
		tableTodasAsSalas.setBorder(null);
		tableTodasAsSalas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableTodasAsSalas.setAutoCreateRowSorter(true);
		tableTodasAsSalas.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableTodasAsSalas);
		scrollPane.setBackground(Color.CYAN);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sairDaAplicacao(conexao);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSair.setBackground(new Color(0, 153, 51));
		btnSair.setBounds(1053, 552, 231, 66);
		btnSair.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		contentPane.add(btnSair);

		JLabel nivel_de_acesso = new JLabel();
		nivel_de_acesso.setBounds(33, 120, 231, 190);
		ImageIcon icon_nivel = new ImageIcon(getClass().getResource("imgs/numero2.jpg"));
		Image img = icon_nivel.getImage().getScaledInstance(nivel_de_acesso.getWidth(), nivel_de_acesso.getHeight(),
				Image.SCALE_DEFAULT);
		nivel_de_acesso.setIcon(new ImageIcon(img));
		nivel_de_acesso.setToolTipText("N\u00CDVEL DE ACESSO");
		nivel_de_acesso.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51)));
		contentPane.add(nivel_de_acesso);

		Label bemVindo = new Label("Seja bem vindo, " + TelaDeLogin.getNome() + " !!");
		bemVindo.setFont(new Font("Dialog", Font.BOLD, 13));
		bemVindo.setBounds(33, 85, 222, 26);
		getContentPane().add(bemVindo);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				atualizarTabelaDoAr(conexao);
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtualizar.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51)));
		btnAtualizar.setBackground(new Color(0, 153, 51));
		btnAtualizar.setBounds(317, 552, 231, 66);
		contentPane.add(btnAtualizar);

		JButton btnAssociarUmResponsavel = new JButton("Associamento de salas");
		btnAssociarUmResponsavel.setBounds(661, 554, 297, 62);
		contentPane.add(btnAssociarUmResponsavel);
		btnAssociarUmResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTelaDeAssociamento(conexao);
			}
		});
		btnAssociarUmResponsavel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAssociarUmResponsavel.setBackground(new Color(0, 153, 51));
		btnAssociarUmResponsavel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),
				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));

		JButton buttonMaisTemperatura = new JButton("+");
		buttonMaisTemperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAumentarTemperaturaAction(conexao);
			}
		});
		buttonMaisTemperatura.setForeground(Color.BLACK);
		buttonMaisTemperatura.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonMaisTemperatura.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));
		buttonMaisTemperatura.setBackground(new Color(0, 153, 51));
		buttonMaisTemperatura.setBounds(213, 360, 51, 33);
		contentPane.add(buttonMaisTemperatura);

		textFieldTemperatura = new JTextField();
		textFieldTemperatura.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTemperatura.setForeground(Color.BLACK);
		textFieldTemperatura.setFont(new Font("Tahoma", Font.BOLD, 13));
		textFieldTemperatura.setEditable(false);
		textFieldTemperatura.setColumns(10);
		textFieldTemperatura.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));
		textFieldTemperatura.setBounds(94, 360, 109, 33);
		contentPane.add(textFieldTemperatura);

		JButton buttonMenosTemperatura = new JButton("-");
		buttonMenosTemperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDiminuirTemperaturaAction(conexao);
			}
		});
		buttonMenosTemperatura.setForeground(Color.BLACK);
		buttonMenosTemperatura.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonMenosTemperatura.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));
		buttonMenosTemperatura.setBackground(new Color(0, 153, 51));
		buttonMenosTemperatura.setBounds(33, 360, 51, 33);
		contentPane.add(buttonMenosTemperatura);

		Label labelTemperatura = new Label("Temperatura");
		labelTemperatura.setForeground(Color.BLACK);
		labelTemperatura.setFont(new Font("Dialog", Font.BOLD, 14));
		labelTemperatura.setAlignment(Label.CENTER);
		labelTemperatura.setBounds(34, 321, 230, 33);
		contentPane.add(labelTemperatura);

		textFieldLiberarSala = new JTextField();
		textFieldLiberarSala.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLiberarSala.setForeground(Color.BLACK);
		textFieldLiberarSala.setFont(new Font("Tahoma", Font.BOLD, 13));
		textFieldLiberarSala.setEditable(false);
		textFieldLiberarSala.setColumns(10);
		textFieldLiberarSala.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));
		textFieldLiberarSala.setBounds(33, 457, 231, 33);
		contentPane.add(textFieldLiberarSala);

		JButton btnLiberarSala = new JButton("LiberarSala");
		btnLiberarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liberarSala(conexao);
			}
		});
		btnLiberarSala.setForeground(Color.BLACK);
		btnLiberarSala.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLiberarSala.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 153, 51),

				new Color(0, 153, 51), new Color(0, 153, 51), new Color(0, 153, 51)));
		btnLiberarSala.setBackground(new Color(0, 153, 51));
		btnLiberarSala.setBounds(34, 495, 230, 33);
		contentPane.add(btnLiberarSala);

		Label label = new Label("Respons\u00E1vel ");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setAlignment(Label.CENTER);
		label.setBounds(33, 418, 231, 33);
		contentPane.add(label);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(33, 404, 222, 2);
		contentPane.add(separator_3);

		setBotoesPorNivelDeAcesso(TelaDeLogin.getNivelDeAcesso());
		atualizarTabelaDoAr(conexao);

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

	private void liberarSala(Conexao conexao) {
		if (textFieldLiberarSala.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Não ha associado a esta sala !");
		} else {
			try {
				String sql = "DELETE FROM \"AssociadosAsSalas\" WHERE \"ID_SalaPK\" = '"
						+ tableTodasAsSalas.getValueAt(tableTodasAsSalas.getSelectedRow(), 0) + "'";

				conexao.updateSql(sql);

				JOptionPane.showMessageDialog(null, "Sala Liberada com sucesso!");
				textFieldLiberarSala.setText("");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Não foi possível liberar sala!\n" + e);
			}
			atualizarTabelaDoAr(conexao);
		}
	}

	protected void setDadosNosCamposTextoAction(Conexao conexao) {

		try {
			textFieldTemperatura
					.setText(tableTodasAsSalas.getValueAt(tableTodasAsSalas.getSelectedRow(), 3).toString());
		} catch (NullPointerException e) {
			textFieldTemperatura.setText("");
		}
		try {
			String sql = "SELECT \"Nome\" FROM \"Usuários\",\"AssociadosAsSalas\""
					+ " WHERE \"ID_Usuário\" = \"ID_UsuárioPK\" AND \"ID_SalaPK\" = "
					+ tableTodasAsSalas.getValueAt(tableTodasAsSalas.getSelectedRow(), 0);

			rs = conexao.executeSql(sql);
			if (rs.next()) {
				textFieldLiberarSala.setText(rs.getString(1));
			} else {
				textFieldLiberarSala.setText("");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	private void atualizarTabelaDoAr(Conexao conexao) {
		try {
			String sql = "SELECT \"ID_Sala\",\"N° da sala\",\"Bloco\", \"Temperatura\", \"Modo\", \"Turbo\" "
					+ "FROM \"Salas\",\"Ar Condicionado\" WHERE \"ID_Ar\" = \"ID_Sala\"";

			rs = conexao.executeSql(sql);
			tableTodasAsSalas.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void cadastrarSala(Conexao conexao) {
		CadastroSala sala = new CadastroSala();
		sala.setVisible(true);
	}

	private void sairDaAplicacao(Conexao conexao) {
		try {
			if (JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmação",
					JOptionPane.YES_NO_OPTION) == 0) {
				conexao.fecharConexao();
				System.exit(1);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void mostrarTelaDeVersao() {
		new Versao().setVisible(true);

	}

	private void mostrarTelaDeAssociamento(Conexao conexao) {
		TelaDeAssociacao socio = new TelaDeAssociacao();
		socio.setVisible(true);
		conexao.fecharConexao();
		dispose();

	}

	private void trocarUsuarios(Conexao conexao) {
		new TelaDeLogin().setVisible(true);
		conexao.fecharConexao();
		dispose();
	}

	private void mntmCadastrarUsuario() {
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
				JOptionPane.showMessageDialog(null, "Não foi possível atualizar senha! \n" + e);

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

	protected void btnDiminuirTemperaturaAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(diminuirTemperatura);
		atualizarTabelaDoAr(conexao);
	}

	protected void btnAumentarTemperaturaAction(Conexao conexao) {
		//Model.ConexaoComArduino.enviarDados(aumentarTemperatura);
		atualizarTabelaDoAr(conexao);
	}
}
