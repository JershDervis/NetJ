package me.jershdervis.netj.client;

import me.jershdervis.netj.transfer.Packet;

/**
 * Created by JershDervis on 8/4/2015.
 */
public interface ClientListener {

    /**
     * Called when the ClientConnectionThread receives a Packet from Server
     * @param client
     * @param packet
     */
    void receivePacket(ClientConnectionThread client, Packet packet);

}
