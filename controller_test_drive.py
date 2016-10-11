import AlarmController
import AlarmModel
from javax.swing import JFrame

model = AlarmModel()
controller = AlarmController(model)
frame = JFrame("ControllerTestDrive")

frame.add(controller)
frame.setSize(500, 200)
frame.setVisible(True)
