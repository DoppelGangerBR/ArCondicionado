package Model;

import java.io.OutputStream;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JOptionPane;

//import gnu.io.CommPortIdentifier;
//import gnu.io.SerialPort;

public class ConexaoComArduino {

	/**
	 * 
	 */
	
	// Variaveis de ConexaoComArduino
	//private static OutputStream output = null;
	//SerialPort serialPort;
	//private final String porta = "COM3";
	//private static final int timeOut = 2000; // 2 segundos
	//private static final int dataRate = 9600;

	/**
    * Creates new form frmPrincipal
     */
	/**
    public ConexaoComArduino() {
        iniciarConexaoComArduino();
    }

	public void iniciarConexaoComArduino() {
		CommPortIdentifier portaId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portaEnum = CommPortIdentifier.getPortIdentifiers();

		while (portaEnum.hasMoreElements()) {
			CommPortIdentifier atualPortaId = (CommPortIdentifier) portaEnum.nextElement();
			if (porta.equals(atualPortaId.getName())) {
				portaId = atualPortaId;
				break;
			}
		}
		if (portaId == null) {
			JOptionPane.showMessageDialog(null,"Não se pode conectar a porta");
		}

		try {
			serialPort = (SerialPort) portaId.open(this.getClass().getName(), timeOut);
			// parametros da porta serial

			serialPort.setSerialPortParams(dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			output = serialPort.getOutputStream();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public static void enviarDados(String dados) {
		try {
			output.write(dados.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	**/
}
