package mundo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		
	}
	
	public Cliente() {
		
		
		try {
			
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			//Se usa un servicio existente que ya nos obtenga la direccion IP publica
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			//se crea un objeto URL, se establece una conexión al sitio y se leen los datos mediante un flujo de entrada.
			String ipPublica = in.readLine();
			//System.out.println(ip);

			Socket misocket = new Socket("192.168.0.14", 9090);
			
			/*
			Socket misocket2 = new Socket("186.86.186.114", 9090, "192.168.0.14", 9090);
			Socket(InetAddress address, int port, InetAddress localAddr, int localPort)
			
			Creates a socket and connects it to the specified remote address on the specified remote port.
			 * */
			 
			DataOutputStream flujoSalida = new DataOutputStream(misocket.getOutputStream());
			
			flujoSalida.writeUTF(ipPublica);
			
			flujoSalida.close();
			
			misocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
