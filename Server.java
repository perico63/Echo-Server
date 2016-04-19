
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego
 */
public class Server extends Thread {
    
    protected Socket clientSocket;
    
    public Server(Socket clientSocket){
        this.clientSocket = clientSocket;
        start(); //nova thread        
    }
       
    public static void main(String[] args) {        
        int port = 3000;
        ServerSocket serverSocket = null;
        
        try{
            serverSocket = new ServerSocket(port);            
            while(true){
                try{
                    System.out.println("ESPERANDO CONEXÕES :::: ");
                    new Server(serverSocket.accept()); //conexao com o client
                } catch (IOException e) {
                    System.err.println("Falha na conexao");
                }
            }         
        } catch (IOException e) {
            System.err.println("Nao foi possivel conectar a porta "+ port);
            System.exit(1);
        }
    }   
       
    @Override
    public void run(){
        System.out.println("Nova thread criada");
        try{
            String entrada = "";
            System.out.println("Conexao estabelecida");
            System.out.println("Esperando entrada...");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            
            while ((entrada = in.readLine()) != null) {
                if(entrada.equals("Sair"))
                    break;
                System.out.println("Dados enviados");
                out.println(entrada.toUpperCase());
            }
            System.out.println("Conexao atual encerrada");
            clientSocket.close();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
