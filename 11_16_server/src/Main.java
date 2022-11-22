import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        try(DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            String message = "노트북 친구들 하이";
            byte[] buffer = message.getBytes();
            while (true) {
                try {
                    InetAddress groupAddress = InetAddress.getByName("255.255.255.255");
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, 4000);
                    socket.send(packet);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
