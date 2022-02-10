package sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerClientCommunication {

    class TcpServer {

        public TcpServer(int port, String fileOutput) {

            //TODO: implement the constructor and init the TCP Server

            String path;
            int port;
            ServerSocket serverSocket = null;
            BufferedWriter writer = null;

            public Server(String path, int port)
            {
                this.path = path;
                this.port = port;

            }


        }

        public void listen() {

            try {
                this.writer = new BufferedWriter(new FileWriter(path,true));
                this.serverSocket = new ServerSocket(port);
                while(true) {
                    Socket socket = this.serverSocket.accept();
                    SocketWorker worker = new SocketWorker(socket,writer);
                    worker.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            //TODO: Implement listen method by using SocketWorkerThread

            // for each TCP connection

        }

    }

    class TcpClient {

        public TcpClient(String serverIpAddress, int port) {

            //TODO: Implement the constructor and init the connection with TCP Server
            String serverAddress;
            int port;
            String folderToSearch;

    public Client(String serverAddress, int port, String folderToSearch) {
                this.serverAddress = serverAddress;
                this.port = port;
                this.folderToSearch = folderToSearch;
            }

            @Override
            public void run() {
                int numFiles = getFiles(this.folderToSearch);
                sendDataToServer(serverAddress,port,numFiles);
            }

            private static int getFiles(String folderToSearch) {
                File file = new File(folderToSearch);
                File[] files = file.listFiles();
                int numFiles = 0;
                for (File f: files) {
                    if (f.isFile() && f.length()<20*1024) {
                        numFiles++;
                    }
                }
                return numFiles;
            }

            private static void sendDataToServer(String serverAddress, int port, int numFiles){
                Socket socket = null;
                try {
                    socket = new Socket(serverAddress,port);
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeInt(numFiles);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }


        }

    }



    class SocketWorkerThread {

        public SocketWorkerThread(Socket socket,String dir) {

            //TODO: implement server-client communication

            // by using the reference of socket

            private Socket socket = null;
            private BufferedWriter writer = null;

            public SocketWorker(Socket socket, BufferedWriter writer)
            {
                this.socket = socket;
                this.writer = writer;
            }

            @Override
            public void run() {
                try {
                    receiveData(this.socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private void receiveData(Socket socket) throws IOException {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                int numFiles = dis.readInt();
                synchronized (SocketWorker.class) {
                    writer.write(String.format("%s %d %d",socket.getInetAddress().getHostAddress(),
                            socket.getPort(),numFiles));
                    writer.newLine();
                    writer.flush();
                }


            }

    }



    public static void main(String[] args) {

        //TODO: implement initial tests with one instance of TCPServer

        //and multiple threads of TCPClient
        Server server = new Server(FILE_PATH_TO_RESULTS, port);
        server.start();
        Client c1 = new Client(IP_ADDRESS_SERVER, port, FOLDER_PATH);
        c1.start();

        Client c2 = new Client(IP_ADDRESS_SERVER, port, FOLDER_PATH);
        c2.start();

    }

    private final static String IP_ADDRESS_SERVER = "localhost";
    private final static int port = 4498;
    private final static String FOLDER_PATH = "C:\\Users\\User\\Documents\\Java";
    private final static String FILE_PATH_TO_RESULTS = "C:\\Users\\User\\Documents\\Java";


}