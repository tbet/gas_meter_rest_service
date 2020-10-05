import py_qmc5883l
import time
sensor = py_qmc5883l.QMC5883L(output_range=py_qmc5883l.RNG_8G)
#m = sensor.get_magnet()
time.sleep(0.1)
(x, y, z) = sensor.get_magnet_raw()

from datetime import datetime
now = datetime.now()
current_time = now.strftime("%Y-%m-%dT%H:%M:%S")
#print(current_time, m)
print(current_time, x, y, z)

