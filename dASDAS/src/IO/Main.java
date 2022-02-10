package IO;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client
{
    public static void main(String [] args)
    {
        Socket socket = null;
        System.out.println("Please input username");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        scan.close();
        int portNumber = 22;
        try {
            socket = new Socket("localhost", portNumber);
            Thread.sleep(1000);
            Thread server = new Thread(new ServerThread(socket));
            server.start();
        } catch (IOException e)
        {
            System.err.println("Fatal Connection error!");
            e.printStackTrace();
        }catch (InterruptedException e)
        {
            System.err.println("Fatal connection error!");
            e.printStackTrace();
        }
    }
}

public class ClientThread extends ChatServer implements Runnable
{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }
    @Override
    public void run()
    {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //dodeka socketot e seuste ziv
        while(!socket.isClosed())
        {
            String input = in.readLine();
            if(input!= null)
            {
                for(ClientThread client : clients)
                {
                    client.getWriter().write(input);
                }
            }
        }
    } catch(IOException e)
    {
        e.printStackTrace();
    }

    public PrintWriter getWriter()
    {
        return out;
    }

}

public class ServerThread implements Runnable
{
    private Socket socket;
    private String name;
    private BufferedReader serverIn;
    private BufferedReader userIn;
    private PrintWriter out;
    public ServerThread(Socket socket, String name)
    {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run()
    {
        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userIn = new BufferedReader(new InputStreamReader(System.in));

            //dodeka soketot e ziv
            while(!socket.isClosed())
            {
                if(serverIn.ready())
                {
                    String input = serverIn.readLine();
                    if(input != null)
                    {
                        System.out.println(input);
                    }
                }
                if(userIn.ready())
                {
                    out.println(name+" > "+userIn.readLine());
                }
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

public class ChatServer { //Server

    public static void main(String[] args) throws InterruptedException {
        int portNumber = 22;
        serverSocket = null;

        public static void acceptClients()
        {
            clients = new ArrayList<ClientThread>();
            while(true)
            {
                try
                {
                    Socket socket = serverSocket.accept();
                    ClientThread client = new ClientThread(socket);
                    Thread thread = new Thread(client);
                    thread.start();
                    clients.add(client);
                }catch(IOException e)
                {
                    System.out.println("Accept failed on: " + portNumber);
                }
            }
        }
        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);
            acceptClients();
        } catch(IOException e) {
            System.err.println("Could not listen on port: "+ portNumber);
            System.exit(1);
        }
    }
}
