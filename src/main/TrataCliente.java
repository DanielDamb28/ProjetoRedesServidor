package main;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TrataCliente extends Thread{
    private Socket cliente;
    private Servidor servidor;

    public TrataCliente( Servidor servidor, Socket cliente){
        this.servidor = servidor;
        this.cliente = cliente;
    }

    public void run() {
    	System.out.println("Cliente conectado com  thread ("+this.getId()+ ") : "+ cliente.getInetAddress() );
		Scanner teclado =	new Scanner(System.in);
		Scanner chegada;// o cliente vai digitar
		
		//InputStreamReader fluxoDados;
		
		try {
			System.out.println("recebi mensagem do cliente");
			chegada= new Scanner(cliente.getInputStream());
			PrintStream saida= new PrintStream(cliente.getOutputStream());
			this.servidor.getClientes().add(saida);
			
			while(chegada.hasNextLine()) {
				String msgChegadaCliente = chegada.nextLine();
			
				System.out.println("Informe a resposta para " + msgChegadaCliente);
				String msgResposta = teclado.nextLine();
				saida.println(msgResposta);
				System.out.println("/--------------/");
			
			}
			
			
			
			System.out.println("Cliente Finalizado: "+cliente.getInetAddress() +
					"da thread ("+ this.getId()+ ")");
		
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}