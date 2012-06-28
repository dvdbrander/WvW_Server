package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientAccepter implements Runnable{
	ServerSocket server = null;
	Main main;
	
	public ClientAccepter(Main main) {
		this.main = main;
	}
	
	
	public void init() {
		try {
			server = new ServerSocket(25367);
		} catch (IOException e) {
			System.out.println("Could not listen on port 25367");
			System.exit(-1);
		}
	}

	@Override
	public void run() {
		FPS fps = new FPS();
		while (main.isRunning()){
			try {
				Socket clientsocket = server.accept();
				Client client = new Client(clientsocket,main);
				main.addClient(client);
				client.init(main.clients.get(client));
				((Thread)client).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fps.updateFPS();
			try {
				Thread.sleep(fps.getSleepmillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
