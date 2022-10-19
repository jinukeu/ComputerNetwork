
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    Socket clientSocket;

    BufferedInputStream bufferedInputStream;

    BufferedOutputStream bufferedOutputStream;

    FileOutputStream fileOut;

    public void serverConnection() throws Exception {
        InetAddress inetAddress = Inet4Address.getByName("127.0.0.1");
        clientSocket = new Socket(inetAddress, 8000);

        bufferedInputStream = new BufferedInputStream(clientSocket.getInputStream());

        fileOut = new FileOutputStream("copy.png");
        bufferedOutputStream = new BufferedOutputStream(fileOut);

        int data;
        while ((data = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(data);
        }
    }

    public void stop() throws Exception {
        bufferedOutputStream.close();
        bufferedInputStream.close();
        clientSocket.close();
        fileOut.close();
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.serverConnection();
        client.stop();
    }
}