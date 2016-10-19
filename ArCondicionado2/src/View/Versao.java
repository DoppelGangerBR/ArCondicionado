package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Versao extends JFrame {

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
					Versao frame = new Versao();
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
	public Versao() {

		super(" Versão ");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVerso = new JLabel("Vers\u00E3o 1.0");
		lblVerso.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerso.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVerso.setBounds(10, 25, 414, 26);
		contentPane.add(lblVerso);

		JLabel lblDesenvolvidaPor = new JLabel("Desenvolvida por : Jorge Biela e Daniel Malagurti");
		lblDesenvolvidaPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesenvolvidaPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesenvolvidaPor.setBounds(10, 62, 414, 48);
		contentPane.add(lblDesenvolvidaPor);

		JLabel lblSupervisionadaPeloProfessor = new JLabel("Supervisionada pelo Professor : Renato Torres");
		lblSupervisionadaPeloProfessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupervisionadaPeloProfessor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSupervisionadaPeloProfessor.setBounds(10, 114, 414, 36);
		contentPane.add(lblSupervisionadaPeloProfessor);

		JLabel lblObrigadoPelaAteno = new JLabel("Obrigado pela aten\u00E7\u00E3o !!");
		lblObrigadoPelaAteno.setVerticalAlignment(SwingConstants.BOTTOM);
		lblObrigadoPelaAteno.setHorizontalAlignment(SwingConstants.CENTER);
		lblObrigadoPelaAteno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObrigadoPelaAteno.setBounds(10, 176, 414, 14);
		contentPane.add(lblObrigadoPelaAteno);

		JButton btn_ok = new JButton("OK");
		btn_ok.setToolTipText("96268664");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnokActionPerforme(e);
			}
		});
		btn_ok.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_ok.setBackground(new Color(255, 215, 0));
		btn_ok.setBounds(174, 201, 89, 23);
		contentPane.add(btn_ok);

		getRootPane().setDefaultButton(btn_ok);
		
		java.net.URL url = this.getClass().getResource("imgs/Logo_1.png");  
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);  
		this.setIconImage(iconeTitulo);
		setLocationRelativeTo(null);

	}

	protected void btnokActionPerforme(ActionEvent e) {
		ok();

	}

	private void ok() {
		setVisible(false);
	}
}
