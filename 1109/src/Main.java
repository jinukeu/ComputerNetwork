import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Main {
    // DatagramSocket
    // DatagramPacket

    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(8080)) {
            byte[] buffer = new byte[1024 * 1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                System.out.println("UDP Client Waiting .....");
                socket.receive(packet);
                byte[] receivedData = packet.getData();
                System.out.printf("[Client] IP: %s, Port: %d\n", packet.getAddress().getHostAddress(), packet.getPort());
                System.out.printf("Offset: %d, Length: %d\n", packet.getOffset(), packet.getLength());
                String message = new String(receivedData, packet.getOffset(), packet.getLength());
                System.out.println("[Message] " + message);
            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
