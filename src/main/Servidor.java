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
    private List<Socket> clientes;

    public Servidor(int porta){
        this.porta = porta;
        this.setClientes(new ArrayList<Socket>());
    }

    public void executa () throws IOException{
        try (ServerSocket servidor = new ServerSocket(this.porta)) {
			System.out.println("Porta 12345 aberta!");

			while(true){
			    Socket cliente = servidor.accept();
			    System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
			    clientes.add(cliente);
			    
			    if(clientes.size() > 1) {
			    	JogoCaraCara jogoCaraCara = new JogoCaraCara( this,clientes.get(0), clientes.get(1));
			    	jogoCaraCara.run();
			    }
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

    private Socket clientes(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Socket> getClientes() {
		return clientes;
	}

	public void setClientes(List<Socket> clientes) {
		this.clientes = clientes;
	}
}