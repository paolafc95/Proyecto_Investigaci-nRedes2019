package mundo;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Servidor {
	
	public static void main(String[] args) {
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

/**
 * Clase que genera la interfaz gráfica del servidor
 * @author Beycker
 *
 */
class MarcoServidor extends JFrame implements Runnable {
	
	public static final int PUERTO_ESCUCHA = 9090;
	
	private	JTextArea areatexto;
	
	public MarcoServidor(){
		
		setBounds(50,50,380,550);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread mihilo = new Thread(this);
		mihilo.start();
		
	}

	@Override
	public void run() {
		
		try {
			
			ServerSocket server = new ServerSocket(PUERTO_ESCUCHA);
			
			while(true) {
				
				Socket misocket = server.accept();
				
				//A flujoEntrada le llega la cadena con la direccion publica del cliente que ejecuto el programa cliente
				DataInputStream flujoEntrada = new DataInputStream(misocket.getInputStream());
				
				String ipPublica = flujoEntrada.readUTF();
				
				InetAddress localizacion = misocket.getInetAddress();
				String ipPrivada = localizacion.getHostAddress();
				
				areatexto.append("IP publica: " + ipPublica + " - IP privada: " + ipPrivada + "\n");
				
				misocket.close();
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
