# NetJ
A Java object Packet API

This project uses DarkMagician6's EventAPI:
https://bitbucket.org/DarkMagician6/eventapi

Hosting a Server example:

    Server server = new Server(1725);
    try {
        server.openServer();
    } catch (Exception e) {
        e.printStackTrace();
    }

This code will open a ServerSocket on port 1725.

Connecting a client to the Server:

    Client client = new Client("127.0.0.1", 1725);
    try {
        client.connectToServer();
    } catch (Exception e) {
        e.printStackTrace();
    }

This code will open a Socket connecting to the localhost callback address on port 1725

Creating a Packet:
Create a new class (e.g. "PacketConnect") and extend me.jershdervis.netj.transfer.Packet
    public class PacketConnect extends Packet {
        public String connectMessage; //This is some data we will be passing through
    }
    
Sending a Packet:
    PacketConnect myCustomPacket = new PacketConnect(); //Initialize a local instance of your packet
    myCustomPacket.connectMessage = "Hello World!"; //Set the data you wish to send
    client.getClientConnectionThread().getObjectOutputStream().writeObject(myCustomPacket); //Write your packet to Server
    
Event Handling:
Event to be used for the Server:
 - EventServerConnectClient (Called when the Server receives a connection from Client)
 - EventServerDisconnectClient (Called when a Client that is connected to Server has lost connection)
 - EventServerReceivePacket (Called when the Server receives a Packet from Client)

Events to be used for the Client:
 - EventClientReceivePacket (Called when the Client receives a Packet from Server)
 - More events will be added shortly (e.g. EventClientConnectServer, EventClientDisconnectServer etc..)
 
Listening for events:
    @EventTarget
    public void eventServerReceivePacket(EventServerReceivePacket event) {
        ClientListenerThread client = event.getClient(); //This is the ClientListenerThread(Client) the Packet came from
        Packet packet = event.getPacket(); //This is the packet the Server received from the ClientListenerThread(Client)
        if(packet instanceof PacketConnect) {
          PacketConnect pc = (PacketConnect) packet;
          System.out.println(pc.connectMessage);
        }
    }
    
