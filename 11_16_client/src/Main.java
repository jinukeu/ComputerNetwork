import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(4000)) {
            InetAddress groupAddress = InetAddress.getByName("224.0.0.1");
            socket.setInterface(InetAddress.getLocalHost());
            socket.joinGroup(groupAddress);
            byte[] buffer = new byte[1024 * 1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // 연결 지향이 아니기때문에 받기만 하는 입장인 Client는 주소와 포트는 필요가 없다.
            while (true) {
                socket.receive(packet); // receive는 packet으로 받아야함
                byte[] receiveData = packet.getData();
                String data = new String(receiveData, 0, packet.getLength());
                System.out.println(data);
            }
        }
        catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}