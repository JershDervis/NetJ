package me.jershdervis.netj;

import me.jershdervis.netj.client.ClientConnectionThread;
import me.jershdervis.netj.client.ClientListener;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class Client {

    private ClientConnectionThread clientConnectionThread;

    private List<ClientListener> listenerList = new ArrayList<ClientListener>();

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
     * Register a listener to post events to
     * @param listener
     */
    public void addClientListener(ClientListener listener) {
        this.listenerList.add(listener);
    }

    /**
     * Unregister a listener to stop posting events to
     * @param listener
     */
    public void removeClientListener(ClientListener listener) {
        this.listenerList.remove(listener);
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

    /**
     * Get ClientListener list
     * @return
     */
    public synchronized List<ClientListener> getListeners() {
        return this.listenerList;
    }
}
