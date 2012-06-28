package main;

import java.util.concurrent.ConcurrentHashMap;

public class Main {

	public static void main(String[] args) {
		new Main();
	}

	private boolean run = true;
	private ClientAccepter clientaccepter = new ClientAccepter(this);
	ConcurrentHashMap<Client, Integer> clients = new ConcurrentHashMap<Client, Integer>();

	public Main() {
		init();
	}

	private void shutdown() {
		System.out.println("Stopping server");
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
		if (run == false)
			shutdown();
	}

	public void addClient(Client client) {
		for (int i = 0; true; i += 1) {
			if (!clients.containsValue(i)) {
				clients.put(client, i);
				client.id = i;
				break;
			}
		}

		for (Client currentClient : clients.keySet()) {
			if (currentClient != client) {
				currentClient.out.println("3|" + client.id);
			}
		}
	}

	public void removeClient(Client client) {
		clients.remove(client);
	}

}
