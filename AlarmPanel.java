import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlarmPanel extends JPanel {
  private String clientName;
  private JLabel alarmLabel; 
  private JButton resetButton = new JButton("Reset");
  private Timer timer = new Timer(800, new AlarmListener());

  private static final Color DEFAULT_COLOR = SystemColor.desktop;

  public AlarmPanel(String clientName) {
    this.clientName = clientName;
  
    setUI();
  }

  public void setUI() {
    alarmLabel = new JLabel(clientName, SwingConstants.CENTER);
    alarmLabel.setFont(new Font("SansSerif", Font.BOLD, 36));

    resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    resetButton.setToolTipText("Click to reset");
    resetButton.setVisible(false);
    resetButton.addActionListener((event) -> reset());

    setLayout(new BorderLayout());
    add(alarmLabel, BorderLayout.CENTER);
    add(resetButton, BorderLayout.SOUTH);

    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    setBackground(DEFAULT_COLOR);
  }

  public void alarm() {
    resetButton.setVisible(true);
    timer.start();
  }

  public void reset() {
    timer.stop();
    setBackground(DEFAULT_COLOR);
    resetButton.setVisible(false);
  }

  class AlarmListener implements ActionListener {
    private boolean toRed = true;

    public void actionPerformed(ActionEvent e) {
      if (toRed)
        setBackground(Color.RED);
      else 
        setBackground(Color.WHITE);
      toRed = !toRed;
    }
  }
}
