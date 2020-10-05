# electricity_meter_rest_service
SpringBoot REST Service to provide gas meter reading via i2c QMC5883L 3-Axis Magnetic Sensor on Raspberry Pi.
Depends on Python driver for the QMC5883L 3-Axis Magnetic Sensor project 

e.g. http://localhost:8080/energy
>*{
>*	"timestamp": "2020-10-03T15:19:17Z",
>*	"power": 2352.0,
>*	"serverID": "09 01 49 ... 4F 3E 5D",
>*}

## Rest Service for use with OpenHAB
Rest Service can run on other server (Server B / Raspberry Pi) than OpenHAB server

## Configuration for OpenHAB to visualize electricity meter data
openhab config files to visualize electricity meter included in project

## Prerequisites
1. Install OpenHab on Linux Server A e.g. via docker-compose.yml -> https://github.com/openhab/openhab-docker
>* Install HTTP Binding (http://ServerA:8080/paperui/index.html#/extensions?tab=binding)
>* Install RRD4j Persistence (http://ServerA:8080/paperui/index.html#/extensions?tab=persistence)
>* Config files will be placed below /var/lib/docker/volumes/openhab_openhab_config
>* Summery page will be availible on http://ServerA:8080/basicui/app
2. Pepare Linux Server B to serve the REST Service
>* Connect cable from 3-axis compass to raspberry pi
>* TODO: some more details.... (see below part)
3. Build REST Service (tested with java11) 
>* maven based SpringBoot Service (mvn clean package)
4. Transfer build jar file to Server B and start REST Service
>* Start REST Service via "java -jar gas_meter_rest_service-1.0-SNAPSHOT.jar" 
>* Crontab restart scenario "@reboot /usr/bin/java -jar /home/USERNAME/gas_meter_rest_service-1.0-SNAPSHOT.jar > /dev/null 2>&1"

## Java
Following parameters are relevant
>* value of current gas meter

## Related links
1. https://github.com/abhiTronix/py-qmc5883l


---------------------
gas meter 

sudo apt-get update
ls /dev/*i2c*
-> ls: cannot access '/dev/*i2c*': No such file or directory
sudo apt-get install -y i2c-tools
sudo raspi-config
-> Enable "ARM I2C interface"
sudo i2cdetect -y 1
-> shows some table information now
ls /dev/*i2c*
-> shows "/dev/i2c-1"
sudo i2cget 1 0x0d 0x01
-> 0x00
sudo apt-get install python
sudo apt-get install python-smbus
sudo apt-get install python-pip
git clone https://github.com/abhiTronix/py-qmc5883l.git
vim py-qmc5883l/py_qmc5883l/__init__.py
-> change from smbus2 to smbus
cd py-qmc5883l
sudo pip install .
-> Successfully installed py-qmc5883l-0.1.1

cd ../gasmeter/
./test_raw.sh 

java -jar qmc5883l-0.0.1-SNAPSHOT.jar 
cd git/gasmeter/input/