package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class JogoCaraCara extends Thread{
    private Socket cliente1;
    private Socket cliente2;
    private Servidor servidor;

    public JogoCaraCara( Servidor servidor, Socket cliente1, Socket cliente2){
        this.servidor = servidor;
        this.cliente1 = cliente1;
        this.cliente2 = cliente2;
    }

    public void run() {
    	System.out.println("Cliente conectado com  thread ("+this.getId()+ ") : "+ cliente1.getInetAddress() );
		Scanner teclado =	new Scanner(System.in);
		Scanner chegada; // o cliente vai digitar
		
		//InputStreamReader fluxoDados;
		
		try {
			
            InputStream inputStream1 = cliente1.getInputStream();
            InputStream inputStream2 = cliente2.getInputStream();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(inputStream1));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(inputStream2));
            
            while (true) {
                // Ler mensagens do primeiro cliente
            	if(in1.ready()) {
            		String mensagemCliente1 = in1.readLine();
                    if (mensagemCliente1 == null) {
                        System.out.println("Cliente 1 desconectado.");
                        break;
                    }
                    System.out.println("Cliente 1 diz: " + mensagemCliente1);
            	}

            	if(in2.ready()) {
            		String mensagemCliente2 = in2.readLine();
                    if (mensagemCliente2 == null) {
                        System.out.println("Cliente 1 desconectado.");
                        break;
                    }
                    System.out.println("Cliente 2 diz: " + mensagemCliente2);
            	}
            }
            
            cliente1.close();
            cliente2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}