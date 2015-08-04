package me.jershdervis.netj;

import me.jershdervis.netj.server.ClientListenerThread;
import me.jershdervis.netj.server.ServerListener;
import me.jershdervis.netj.server.ServerListenerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class Server {

    private ArrayList<ClientListenerThread> clientList = new ArrayList<>();

    private List<ServerListener> serverListenerList = new ArrayList<ServerListener>();

    private boolean isServerOpen;

    private ServerSocket serverSocket;

    private final int port;

    /**
     * Establishes the Server settings
     * @param port
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * Register a listener to post events to
     * @param listener
     */
    public void addServerListener(ServerListener listener) {
        this.serverListenerList.add(listener);
    }

    /**
     * Unregister a listener to stop posting events to
     * @param listener
     */
    public void removeServerListener(ServerListener listener) {
        this.serverListenerList.remove(listener);
    }

    /**
     * Opens the current Server port and begins packet listening process
     * @throws IOException
     */
    public void openServer() throws Exception {
        if(!this.isServerOpen) {
            this.serverSocket = new ServerSocket(this.port);
            this.isServerOpen = true;
            new Thread(new ServerListenerThread(this)).start();
        }
    }

    /**
     * Closes the current Server port
     * @throws IOException
     */
    public void closeServer() throws IOException {
        if(this.isServerOpen) {
            this.serverSocket.close();
            this.isServerOpen = false;
        }
    }

    /**
     * Returns true if the current server is listening
     * @return
     */
    public synchronized boolean isServerOpen() {
        return this.isServerOpen;
    }

    /**
     * Returns the current open ServerSocket
     * @return
     */
    public synchronized ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    /**
     * Gets the Server's currently connected clients
     * @return
     */
    public synchronized ArrayList<ClientListenerThread> getClientList() {
        return this.clientList;
    }

    /**
     * Get ServerListener list
     * @return
     */
    public synchronized List<ServerListener> getServerListenerList() {
        return serverListenerList;
    }
}
