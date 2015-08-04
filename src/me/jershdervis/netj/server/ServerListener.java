package me.jershdervis.netj.server;

import me.jershdervis.netj.Server;
import me.jershdervis.netj.transfer.Packet;

/**
 * Created by JershDervis on 8/4/2015.
 */
public interface ServerListener {

    /**
     * Called when a Client connects to Server
     * @param server
     * @param client
     */
    void clientConnect(Server server, ClientListenerThread client);

    /**
     * Called when Server receives packet from Client
     * @param client
     * @param packet
     */
    void receivePacket(ClientListenerThread client, Packet packet);

    /**
     * Called when a Client disconnects from Server
     * @param server
     * @param client
     */
    void clientDisconnect(Server server, ClientListenerThread client);
}
