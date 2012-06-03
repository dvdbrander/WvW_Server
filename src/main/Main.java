package main;

import java.util.ArrayList;

public class Main {

	

	public static void main(String[] args) {
		new Main();
	}

	private boolean run = true;
	private FPS fps = new FPS();
	private ClientAccepter clientaccepter = new ClientAccepter(this);
	private ArrayList<Client> clients = new ArrayList<Client>();
	
	
	public Main(){
		init();
		while (isRunning()){
			step();
		}
		shutdown();
	}
		
	private void shutdown() {
		System.out.println("Stopping server");
	}

	private void step() {
		fps.updateFPS();
		try {
			Thread.sleep(fps.getSleepmillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		System.out.println("Starting server");
		clientaccepter.init();
		
		clientaccepter.run();
	}

	public boolean isRunning() {
		return run;
	}

	public void setRunning(boolean run) {
		this.run = run;
	}

	public void addClient(Client client) {
		clients.add(client);
	}

}
