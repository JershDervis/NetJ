package me.jershdervis.netj.events;

import com.darkmagician6.eventapi.events.Event;
import me.jershdervis.netj.server.ClientListenerThread;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class EventServerClientConnect implements Event {

    private final ClientListenerThread client;

    public EventServerClientConnect(ClientListenerThread client) {
        this.client = client;
    }

    /**
     * Gets the connected client ClientListenerThread class
     * @return
     */
    public ClientListenerThread getClient() {
        return this.client;
    }
}
