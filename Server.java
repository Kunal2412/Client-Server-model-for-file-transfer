import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{
    public static void main(String[] args) 
    {
        System.out.println("==================Server Program==================");
        int byteRead = 0;
        try 
        {
            ServerSocket serverSocket = new ServerSocket(9999);
            if (!serverSocket.isBound())
                System.out.println("Sever socket not bounded");
            else
                System.out.println("Server socket bounded to Port : " + serverSocket.getLocalPort());

            Socket clientSocket = serverSocket.accept();

            if (!clientSocket.isConnected())
                System.out.println("Client socket not connected");
            else
                System.out.println("Client socket connected : " + clientSocket.getInetAddress());

            InputStream in = clientSocket.getInputStream();
            DataInputStream clientData = new DataInputStream(in);
            System.out.println("Enter file name with extension(eg:.txt,.pdf) :");
            Scanner sc=new Scanner(System.in);
            String filename= sc.nextLine();
            OutputStream os = new FileOutputStream("C:/Users/HP/Desktop/"+filename);
            byte[] byteArray = new byte[1048576];
            while ((byteRead = clientData.read(byteArray)) != -1) 
            {
                os.write(byteArray, 0, byteRead);
                System.out.println("Bytes received : " + byteRead);
            }
            os.close();
            serverSocket.close();
            System.out.println("File received");
        } 
        catch (Exception e) 
        {
            System.out.println("Server exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}