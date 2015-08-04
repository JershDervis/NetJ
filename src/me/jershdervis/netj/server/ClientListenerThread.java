package me.jershdervis.netj.server;

import me.jershdervis.netj.Server;
import me.jershdervis.netj.transfer.Packet;

import java.io.*;
import java.net.Socket;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class ClientListenerThread implements Runnable {

    private final Server server;
    private final Socket clientSocket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public ClientListenerThread(Server server, Socket newConnection) throws IOException {
        this.server = server;
        this.clientSocket = newConnection;

        this.objectOutputStream = new ObjectOutputStream(newConnection.getOutputStream());
        this.objectInputStream = new ObjectInputStream(newConnection.getInputStream());

        System.out.println("[SERVER] Successfully established connection to client through port: " + clientSocket.getLocalPort());
    }

    @Override
    public void run() {
        while(!this.clientSocket.isClosed()) {
            Object incoming;
            try {
                while((incoming = this.objectInputStream.readObject()) != null) {
                    if(incoming instanceof Packet) {
                        Packet packet = (Packet) incoming;
                        System.out.println("[SERVER] Received Packet: " + packet.getClass().getSimpleName());

                        for(ServerListener listener : this.server.getServerListenerList())
                            listener.receivePacket(this, packet);
                    }
                }
                this.server.getClientList().remove(this);

                for(ServerListener listener : this.server.getServerListenerList())
                    listener.clientDisconnect(this.server, this);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the Server the current Client is apart of
     * @return
     */
    public synchronized Server getServer() {
        return this.server;
    }

    /**
     * Gets the Client SSLSocket connection
     * @return
     */
    public synchronized Socket getClientSocket() {
        return this.clientSocket;
    }

    /**
     * Returns the client connection ObjectOutputStream
     * @return
     */
    public synchronized ObjectOutputStream getObjectOutputStream() {
        return this.objectOutputStream;
    }

    /**
     * Returns the client connection ObjectInputStream
     * @return
     */
    public synchronized ObjectInputStream getObjectInputStream() {
        return this.objectInputStream;
    }
}
