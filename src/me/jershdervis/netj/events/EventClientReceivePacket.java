package me.jershdervis.netj.events;

import com.darkmagician6.eventapi.events.Event;
import me.jershdervis.netj.client.ClientConnectionThread;
import me.jershdervis.netj.transfer.Packet;

/**
 * Created by JershDervis on 8/2/2015.
 */
public class EventClientReceivePacket implements Event {

    private final ClientConnectionThread client;
    private final Packet packet;

    public EventClientReceivePacket(ClientConnectionThread client, Packet packet) {
        this.client = client;
        this.packet = packet;
    }

    /**
     * Get the client that received the Packet
     * @return
     */
    public ClientConnectionThread getClientConnectionThread() {
        return client;
    }

    /**
     * Get the received Packet
     * @return
     */
    public Packet getPacket() {
        return packet;
    }
}
