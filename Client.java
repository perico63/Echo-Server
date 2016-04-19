
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego
 */
public class Client {
    
    public static void main(String[] args) throws IOException {
        
        String ip = "127.0.0.1";
        int porta =  3000; 
        
        try{
            String entrada = "";     
            Socket clientSocket = new Socket(ip, porta);
            PrintWriter out  = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            while((entrada = stdIn.readLine()) != null){
                if(entrada.equals("sair")) break;
                out.println(entrada);
                System.out.println("RESULTADO " + in.readLine());
            }
            in.close();
            out.close();                 
        } catch (IOException e){
            System.err.println(e.getMessage());
        }       
    }
} 
