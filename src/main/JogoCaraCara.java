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
    
    private Boolean clientePronto1;
    private Boolean clientePronto2;
    private Servidor servidor;

    public JogoCaraCara( Servidor servidor, Socket cliente1, Socket cliente2){
        this.servidor = servidor;
        this.cliente1 = cliente1;
        this.clientePronto1 = false;
        
        this.cliente2 = cliente2;
        this.clientePronto2 = false;
    }

    public void run() {
    	System.out.println("Cliente conectado com  thread ("+this.getId()+ ") : "+ cliente1.getInetAddress()+" porta: "+cliente1.getLocalPort() );
    	System.out.println("Cliente conectado com  thread ("+this.getId()+ ") : "+ cliente2.getInetAddress()+" porta: "+cliente2.getLocalPort() );
	
		//InputStreamReader fluxoDados;
		
		try {
			
            InputStream inputStream1 = cliente1.getInputStream();
            InputStream inputStream2 = cliente2.getInputStream();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(inputStream1));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(inputStream2));
		    OutputStream outputStream1 = cliente1.getOutputStream();
		    OutputStream outputStream2 = cliente2.getOutputStream();
            PrintWriter out1 = new PrintWriter(outputStream1, true);
            PrintWriter out2 = new PrintWriter(outputStream2, true);
            
            while (true) {
                // Ler mensagens do primeiro cliente
            	if(in1.ready()) {
            		String mensagemCliente1 = in1.readLine();
            		System.out.println(mensagemCliente1);
                    if (mensagemCliente1 == null) {
                        System.out.println("Cliente 1 desconectado.");
                        break;
                    }
                    System.out.println("Cliente 1 diz: " + mensagemCliente1);
                    if(mensagemCliente1.equals("%pronto%")) {
                    	System.out.println("AAAAAAAAAA");
                    	clientePronto1 = true;
                    }else {
                    	out1.println( mensagemCliente1);
                    	out2.println( mensagemCliente1);
                    	System.out.println("Mensagem repassada");
                    }
            	}

            	if(in2.ready()) {
            		String mensagemCliente2 = in2.readLine();
            		System.out.println(mensagemCliente2);
                    if (mensagemCliente2 == null) {
                        System.out.println("Cliente 1 desconectado.");
                        break;
                    }
                    System.out.println("Cliente 2 diz: " + mensagemCliente2);
                    if(mensagemCliente2.equals("%pronto%")) {
                    	System.out.println("BBBBBBBBBBBBBB");
                    	clientePronto2 = true;
                    }else {
                    	out1.println( mensagemCliente2);
                    	out2.println( mensagemCliente2);
                    	System.out.println("Mensagem repassada");
                    }
            	}
            	if(clientePronto1 && clientePronto2) {
            		out1.println("%iniciarJogo%");
            		out2.println("%iniciarJogo%");
            		clientePronto1 = false;
            		clientePronto2 = false;
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