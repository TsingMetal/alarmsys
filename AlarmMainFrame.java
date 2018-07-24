import javax.swing.JFrame;
import javax.swing.DefaultListModel;

public class AlarmMainFrame implements Observer 
                            extends JFrame
{
  private AlarmModel alarmModel;
  DefaultListModel listModel;
  
  public AlarmMainFrame(AlarmModel model) {
    alarmModel = model;
  }
