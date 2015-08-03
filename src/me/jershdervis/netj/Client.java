package me.jershdervis.netj;

import me.jershdervis.netj.client.ClientConnectionThread;

import java.net.Socket;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class Client {

    private ClientConnectionThread clientConnectionThread;

    private final String address;
    private final int port;

    /**
     * Establishes the client settings
     * @param address
     * @param port
     */
    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Initialize the Client connection to Server
     */
    public void connectToServer() throws Exception {
        new Thread(this.clientConnectionThread = new ClientConnectionThread(this, new Socket(this.address, this.port))).start();
    }

    /**
     * Returns the current client connection thread. (Will be null if connectToServer method isn't run)
     * @return
     */
    public synchronized ClientConnectionThread getClientConnectionThread() {
        return this.clientConnectionThread;
    }
}
