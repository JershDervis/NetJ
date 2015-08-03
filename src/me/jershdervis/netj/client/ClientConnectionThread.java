package me.jershdervis.netj.client;

import com.darkmagician6.eventapi.EventManager;
import me.jershdervis.netj.Client;
import me.jershdervis.netj.events.EventClientReceivePacket;
import me.jershdervis.netj.transfer.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class ClientConnectionThread implements Runnable {

    private final Client client;
    private final Socket socket;

    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    public ClientConnectionThread(Client client, Socket socket) throws IOException {
        this.client = client;
        this.socket = socket;

        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());

        System.out.println("[CLIENT] Successfully established connection to server through port: " + socket.getLocalPort());
    }

    @Override
    public void run() {
        while(!this.socket.isClosed()) {
            Object incoming;
            try {
                while((incoming = this.objectInputStream.readObject()) != null) {
                    if (incoming instanceof Packet) {
                        Packet packet = (Packet) incoming;
                        System.out.println("[CLIENT] Received Packet: " + packet.getClass().getSimpleName());
                        EventManager.call(new EventClientReceivePacket(this, packet));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the Client class
     * @return
     */
    public synchronized Client getClient() {
        return client;
    }

    /**
     * Get the Client socket connection
     * @return
     */
    public synchronized Socket getSocket() {
        return this.socket;
    }

    /**
     * Get the ObjectOutputStream to Server (Outgoing Data Objects)
     * @return
     */
    public synchronized ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    /**
     * Get the ObjectInputStream from Server (Incoming Data Objects)
     * @return
     */
    public synchronized ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }
}
