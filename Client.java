import java.io.*;
import java.net.*;
import java.util.*;

public class Client{

public static void main(String[] args)
{
    System.out.println("==================Client Program==================");
    Socket socket;
    try
    {
        socket = new Socket("localhost", 9999);            
        if(!socket.isConnected())
            System.out.println("Socket connection not established");
        else
            System.out.println("Socket connection established : "+socket.getInetAddress());

        System.out.println("Enter file path :");
        Scanner sc=new Scanner(System.in);
        String filepath= sc.nextLine();
        File myfile = new File(filepath);


        if(!myfile.exists())
            System.out.println("File not existing.");
        else
            System.out.println("File existing.");

        byte[] byteArray = new byte[1048576];
        FileInputStream fis = new FileInputStream(myfile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        int trxBytes =0;

        while((trxBytes = dis.read(byteArray)) !=-1)
        {           
            dos.write(byteArray, 0, trxBytes);
            System.out.println("Transfering bytes : "+trxBytes );
        }
        os.flush();
        bis.close();
        socket.close();
        System.out.println("File transfered");
    }
    catch(Exception e)
    {
        System.out.println("Client exception : "+e.getMessage());
        e.printStackTrace();

    }
}
} 