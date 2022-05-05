import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	private String ipAddress = "127.0.0.1"; // Specify what it is: 127.0.0.1
	private int portNumber = 5555; // Specify what it is: 5555
	ObjectOutputStream out;
	ObjectInputStream in;
	String endOfRound = "";
	String message = "";
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call, String ipAddress, int portNumber){
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
		callback = call;
	}
	
	public void run() {
		
		try {
		socketClient= new Socket(ipAddress, portNumber);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {
			System.out.println("Client-run exception");
		}
		
		while(true) {
			 
			try {
			String message = in.readObject().toString();
			callback.accept(message);
			endOfRound = message;
			}
			catch(Exception e) {
				System.out.println("Baccrat Info not retrieved");
				break;
			}
		}
	
    }
	
	public void send(String data) {
		
		try {
			out.writeObject(data);
			out.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't send thru clientConnection");
			e.printStackTrace();
		}
	}
	
	public void setUpdates(BaccaratInfo reader) { // double check
		reader.setGameStatus(endOfRound);
	}

}
