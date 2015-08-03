package me.jershdervis.netj.server;

import com.darkmagician6.eventapi.EventManager;
import me.jershdervis.netj.Server;
import me.jershdervis.netj.events.EventServerClientConnect;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class ServerListenerThread implements Runnable {

    private final Server server;

    public ServerListenerThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = this.server.getServerSocket();
        while(!serverSocket.isClosed()) {
            try {
                ClientListenerThread client = new ClientListenerThread(this.server, serverSocket.accept());
                this.server.getClientList().add(client);
                EventManager.call(new EventServerClientConnect(client));
                new Thread(client).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
