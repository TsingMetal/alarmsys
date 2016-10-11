import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class AlarmView extends JPanel
											 implements AlarmViewInterface 
{

	private AlarmModelInterface model;
	private TreeMap<String, AlarmPanel> clientsMap =
		new TreeMap<String, AlarmPanel>();

	public AlarmView(AlarmModelInterface mode) {
		this.model = model;
		model.addObserser(this);
	}

	public void update() {
		JLabel initLbl = null;
		if (model.getClientsSize() == 0) {
			initLbl =  new JLabel("No client added yet");
			add(initLbl);
		} else {	
			remove(initLbl);
			if (model.getClientsSize() <= 8) {
				setLayout(new GridLayout(0, 2, 5, 5));
			} else {
				setLayout(new GridLayout(0, 4, 5, 5));
			}
		}

		revalidate();
	}
	
	@Override
	public void clientAdded(String client) {
		AlarmPanel alarmPanel = new AlarmPanel(client);
		add(alarmPanel);
		clientsMap.put(client, alarmPanel);
		update();
	}

	public void clientRemoved(String client) {
		remove(clientsMap.get(client));
		clientsMap.remove(client);
		update();
	}
}
