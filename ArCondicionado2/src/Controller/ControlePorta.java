package Controller;

//import gnu.io.CommPortIdentifier;
//import gnu.io.NoSuchPortException;
//import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JOptionPane;

public class ControlePorta {
  //private OutputStream serialOut;
  private int taxa;
  private String portaCOM;

  /**
   * Construtor_da_classe ControlePorta
   * @param portaCOM - _Porta COM que_será utilizada_para enviar_os dados_ para_ o_arduino
   * @param taxa - Taxa_de transferência_da porta_ serial geralmente_ é 9600
   */
  /**
  public ControlePorta(String portaCOM, int taxa) {
    this.portaCOM = portaCOM;
    this.taxa = taxa;
    this.initialize();
  }     
 
  /**
   * Médoto_que -_verifica se a_comunicação com_ a _porta serial está_ok
   */
  /**
  private void initialize() {
    try {
      //Define uma_variável portId do_tipo CommPortIdentifier _para realizar_a_comunicação serial
      CommPortIdentifier portId = null;
      try {
        //Tenta_verificar se_a porta_COM informada_existe
        portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
      }catch (NoSuchPortException npe) {
        //Caso_a_porta COM não_exista será_exibido um_erro 
        JOptionPane.showMessageDialog(null, "Porta COM não encontrada.",
                  "Porta COM", JOptionPane.PLAIN_MESSAGE);
      }
      //Abre_a_porta COM 
      SerialPort port = (SerialPort) portId.open("Comunicação serial", this.taxa);
      serialOut = port.getOutputStream();
      port.setSerialPortParams(this.taxa, //taxa_de_transferência_da_porta serial 
                               SerialPort.DATABITS_8, //taxa_de -10 bits 8 (_envio)
                               SerialPort.STOPBITS_1, //taxa_de 10 bits 1 (_recebimento)
                               SerialPort.PARITY_NONE); //_receber e _enviar _dados
    }catch (Exception e) {
      e.printStackTrace();
    }
}

  /**
   * Método_que_fecha a comunicação_com a_porta serial
   */
  /**
  public void close() {
    try {
        serialOut.close();
    }catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Não foi possível fechar porta COM.",
                "Fechar porta COM", JOptionPane.PLAIN_MESSAGE);
    }
  }

  /**
   * @param opcao - Valor a ser_enviado pela_porta serial
   */
  /**
  public void enviaDados(int opcao){
    try {
      serialOut.write(opcao);//escreve_o valor na_porta serial para_ser_enviado
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Não foi possível enviar o dado. ",
                "Enviar dados", JOptionPane.PLAIN_MESSAGE);
    }
  } **/
}