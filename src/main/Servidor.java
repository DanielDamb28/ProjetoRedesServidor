package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Servidor {
    public static void main(String[] args) throws IOException{
        new Servidor(12345).executa();
    }

    private int porta;
    private List<PrintStream> clientes;

    public Servidor(int porta){
        this.porta = porta;
        this.setClientes(new ArrayList<PrintStream>());
    }

    public void executa () throws IOException{
        try (ServerSocket servidor = new ServerSocket(this.porta)) {
			System.out.println("Porta 12345 aberta!");

			while(true){
			    Socket cliente = servidor.accept();
			    System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
			    
			    TrataCliente tc = new TrataCliente( this,cliente);
			    tc.run();
			    /*
			    PrintStream saida = new PrintStream(cliente.getOutputStream());
			    //PrintStream saida= new PrintStream(cliente.getOutputStream());
			    this.clientes.add(saida);
			    
			    chegada= new Scanner(cliente.getInputStream());
			    TrataCliente tc = new TrataCliente( this);
			    //new Thread(tc).start();*/
			}
		}
    }

    public void distribuiMensagem(String msg){
        for(PrintStream cliente: this.getClientes()){
            cliente.println("Msg:"+ msg);
        }
    }

	public List<PrintStream> getClientes() {
		return clientes;
	}

	public void setClientes(List<PrintStream> clientes) {
		this.clientes = clientes;
	}
}