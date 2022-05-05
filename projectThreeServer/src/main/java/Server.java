import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

//import javafx.application.Platform;
//import javafx.scene.control.ListView;

public class Server {

	int count = 1;
	ArrayList<BaccaratGameLogic> games = new ArrayList<BaccaratGameLogic>();
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	private int portNumber; //5555
	
	Server(Consumer<Serializable> call, int portNum) {
	
		callback = call;
		portNumber = portNum;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread {
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(portNumber);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		    	BaccaratGameLogic hm = new BaccaratGameLogic();
		    	games.add(hm);				
		    	ClientThread c = new ClientThread(mysocket.accept(), count, hm);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				System.out.println("client has connected to server: " + "client #" + count);
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread {
			
			BaccaratGameLogic game;
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			boolean partA = true;
			boolean partB = false;
			
			ClientThread(Socket s, int count, BaccaratGameLogic hm) {
				this.connection = s;
				this.count = count;
				game = hm;
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}
			
			public void run() {
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				updateClients("new client on server: client #"+count);
					
				 while(true) {
					    try {
					    	String data = in.readObject().toString();
					    	callback.accept("client: " + count + " sent: " + data);
					    	updateClients("client #"+count+" said: "+data);
					    	
					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					    	updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
		}//end of client thread
}


	
	

	
