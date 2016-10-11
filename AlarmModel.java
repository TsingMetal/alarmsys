import java.util.TreeSet;
import java.util.Collection;
import java.util.ArrayList;
import java.io.*;

public class AlarmModel {
	private TreeSet<String> clientsSet;
	private ArrayList<Observer> observers;

	public AlarmModel() {
		clientsSet = new TreeSet<String>();
		observers = new ArrayList<Observer>();
	}

	/*
	public void initialize() {
		// Try reading data from disk
		try {
			ObjectInputStream in = 
			new ObjectInputStream(new FileInputStream("clients.data"));
			clientsSet.addAll((TreeSet<String>)in.readObject());
			in.close();
		} catch (IOException ex) {
			throw ex; // Re-throw exception
			System.out.println("Failed reading data");
		}
	}
	*/
	
	public TreeSet<String> getClients() {
		return clientsSet;
	}

	public int getClientsSize() {
		return clientsSet.size();
	}

	public void add(String client) {
		clientsSet.add(client);
		for (Observer observer : observers) 
			observer.clientAdded(client);
	}

	public void remove(String client) {
		clientsSet.remove(client);
		for (Observer observer : observers)
			observer.clientRemoved(client);
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void addAll(String[] clients) {
		for (String client : clients)
			add(client);
	}

	public void addAll(Collection<String> clients) {
		for (String client : clients) 
			add(client);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void removeAll(String[] clients) {
		for (String client : clients) 
			remove(client);
	}

	public void removeAll(Collection<String> clients) {
		for (String client : clients)
			remove(client);
	}
}
