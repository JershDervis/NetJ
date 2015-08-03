package me.jershdervis.netj.events;

import com.darkmagician6.eventapi.events.Event;
import me.jershdervis.netj.server.ClientListenerThread;
import me.jershdervis.netj.transfer.Packet;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class EventServerReceivePacket implements Event {

    private final ClientListenerThread client;
    private final Packet packet;

    public EventServerReceivePacket(ClientListenerThread client, Packet packet) {
        this.client = client;
        this.packet = packet;
    }

    /**
     * Returns the client that the packet was sent from
     * @return
     */
    public synchronized ClientListenerThread getClient() {
        return client;
    }

    /**
     * Returns the Packet object that was received from client
     * @return
     */
    public synchronized Packet getPacket() {
        return this.packet;
    }
}
