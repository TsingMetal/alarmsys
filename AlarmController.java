import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AlarmController extends JPanel 
                             implements AlarmControllerInterface
{
  AlarmModel model;
  DefaultListModel listModel = new DefaultListModel();
  JPanel addPanel = new JPanel();
  JPanel removePanel = new JPanel();
  JPanel savePanel = new JPanel();

  public AlarmController(AlarmModel model) {
    this.model = model;
    listModel = new DefaultListModel();

    setUI();
  }

  public void setUI() {

    // setting addPanel
    JPanel panel1 = new JPanel(new FlowLayout());
    JLabel addLbl = new JLabel("Input clients to add(seperate with ','):");
    JTextField inputField = new JTextField(20);
    panel1.add(addLbl);
    panel1.add(inputField);

    JPanel panel2 = new JPanel(new FlowLayout());

    JButton confirmAddButton = new JButton("Comfirm");
    confirmAddButton.addActionListener((event) -> {
      String[] clients = inputField.getText().split(",");
      for (String client : clients)
        if (client.trim().length() > 2) 
          model.add(client.toUpperCase().trim());

      JOptionPane.showMessageDialog(this, "clients successfully added");
      inputField.setText("");
      addPanel.setVisible(false);
      this.setVisible(false);
    });

    JButton cancelAddButton = new JButton("Cancel");
    cancelAddButton.addActionListener((event) -> {
      addPanel.setVisible(false);
      this.setVisible(false);
    });

    panel2.add(confirmAddButton);
    panel2.add(cancelAddButton);

    addPanel.setLayout(new GridLayout(2, 1, 5, 5));
    addPanel.add(panel1);
    addPanel.add(panel2);

    addPanel.setVisible(false);

    // setting removePanel
    JPanel panel3 = new JPanel(new FlowLayout());
    JLabel removeLbl = new JLabel("Select clients to remove");
    JList clientsList = new JList();
    clientsList.setModel(listModel);

    panel3.add(removeLbl);
    panel3.add(clientsList);

    JPanel panel4 = new JPanel(new FlowLayout());

    JButton confirmRmButton = new JButton("Confirm");
    confirmRmButton.addActionListener((event) -> {
      Object[] clients = clientsList.getSelectedValues();
      for (Object client : clients)
        model.remove(String.valueOf(client));

      JOptionPane.showMessageDialog(this, "clients successfully removed");
      removePanel.setVisible(false);
      this.setVisible(false);
    });
    
    JButton cancelRmButton = new JButton("Cancel");
    cancelRmButton.addActionListener((event) -> {
      removePanel.setVisible(false);
      this.setVisible(false);
    });

    panel4.add(confirmRmButton);
    panel4.add(cancelRmButton);

    removePanel.setLayout(new GridLayout(2, 1, 5, 5));
    removePanel.add(panel3);
    removePanel.add(panel4);

    removePanel.setVisible(false);

    // setting savePanel
    JLabel saveLbl = new JLabel("save data?");
    JButton confirmSaveButton = new JButton("Confirm");
    confirmSaveButton.addActionListener((event) -> {
      try{
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("clients.data"));
        out.writeObject(model.getClients());
        JOptionPane.showMessageDialog(this, "data saved successfully");
        out.close();
        savePanel.setVisible(false);
        this.setVisible(false);
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "something wrong");
      }
    });
    JButton cancelSaveButton = new JButton("Cancel");
    cancelSaveButton.addActionListener((event) -> {
      savePanel.setVisible(false);
      this.setVisible(false);
    });

    savePanel.setLayout(new FlowLayout());
    savePanel.add(saveLbl);
    savePanel.add(confirmSaveButton);
    savePanel.add(cancelSaveButton);

    savePanel.setVisible(false);

    add(addPanel);
    add(removePanel);
    add(savePanel);

    this.setVisible(false);
  }

  @Override
  public void alarmAdd() {
    setVisible(true);
    addPanel.setVisible(true);
  }

  @Override
  public void alarmRemove() {
    updateListModel();
    setVisible(true);
    removePanel.setVisible(true);
  }

  @Override
  public void save() {
    setVisible(true);
    savePanel.setVisible(true);
  }

  public void updateListModel() {
    listModel.clear();
    for (String client: model.getClients())
      listModel.addElement(client);
  }
}
