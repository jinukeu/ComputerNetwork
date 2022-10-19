
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    // TCP Server
    ServerSocket serverSocket;
    Socket clientSocket;

    BufferedInputStream bufferedInputStream;

    FileInputStream fileInputStream;

    BufferedOutputStream bufferedOutputStream;


    public void startServer() throws Exception {
        fileInputStream = new FileInputStream("C:\\Users\\aaa\\Desktop\\1.png");
        bufferedInputStream = new BufferedInputStream(fileInputStream);

        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(Inet4Address.getByName("127.0.0.1"), 8000));

        clientSocket = serverSocket.accept();

        bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());
        int data;
        while ((data = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(data);
        }
    }

    public void stopServer() throws Exception {
        bufferedInputStream.close();
        fileInputStream.close();
        bufferedOutputStream.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.startServer();
        server.stopServer();
    }

}