package main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

	private Socket socket;
	private Main main;
	private InputStreamReader in;
	private PrintWriter out;

	public Client(Socket socket, Main main) {
		this.socket = socket;
		this.main = main;
	}

	public void init(){
		System.out.println("Client connected: "+socket.getInetAddress()+":"+socket.getPort());
		try {
			in = new InputStreamReader(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (main.isRunning()){
			try {
				if (in.ready()){
					String string = "";
					int chr = in.read();
					while (chr != -1){
						string += Character.toString((char) chr);
					}
					System.out.println(string);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
