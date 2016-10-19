package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NivelDeAcesso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NivelDeAcesso frame = new NivelDeAcesso();
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
	public NivelDeAcesso() {
		

		super(" Niveis de acessos ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 345);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNivel = new JLabel("Nivel 1 = Coordenador (Acesso apenas as salas associadas)");
		lblNivel.setBackground(new Color(0, 153, 51));
		lblNivel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNivel.setBounds(10, 31, 481, 53);
		contentPane.add(lblNivel);
		
		
		JLabel lblNivel_1 = new JLabel("Nivel 2 = Monitor (Acesso a todas as salas)");
		lblNivel_1.setBackground(new Color(0, 153, 51));
		lblNivel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNivel_1.setBounds(10, 99, 481, 56);
		contentPane.add(lblNivel_1);
		
		JLabel lblNivel_2 = new JLabel("Nivel 3 = Programador (Pode cadastrar novos ares-condicionados)");
		lblNivel_2.setBackground(new Color(0, 153, 51));
		lblNivel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNivel_2.setBounds(10, 166, 507, 53);
		contentPane.add(lblNivel_2);
		
		JButton btn_sair = new JButton("OK");
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_sairActionEvent(e);
			}
		});
		btn_sair.setBackground(new Color(0, 153, 51));
		btn_sair.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_sair.setBounds(160, 230, 172, 38);
		contentPane.add(btn_sair);
		
		java.net.URL url = this.getClass().getResource("imgs/Logo_1.png");  
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url); 
		this.setIconImage(iconeTitulo);
		setLocationRelativeTo(null);
	}

	protected void btn_sairActionEvent(ActionEvent e) {
		sair();
		
	}

	private void sair() {
		setVisible(false);
	}
}
