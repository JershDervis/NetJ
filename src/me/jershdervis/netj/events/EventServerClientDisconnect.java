package me.jershdervis.netj.events;

import com.darkmagician6.eventapi.events.Event;
import me.jershdervis.netj.Server;
import me.jershdervis.netj.server.ClientListenerThread;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class EventServerClientDisconnect implements Event {

    private final Server server;
    private final ClientListenerThread client;

    public EventServerClientDisconnect(Server server, ClientListenerThread client) {
        this.server = server;
        this.client = client;
    }

    /**
     * Returns the server that the Client has disconnected from
     * @return
     */
    public Server getServer() {
        return server;
    }

    /**
     * Returns the Client that has disconnected from server
     * @return
     */
    public ClientListenerThread getClient() {
        return client;
    }
}
