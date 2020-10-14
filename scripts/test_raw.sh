#!/bin/bash
while :
do
	python /home/pi/git/gas_meter_rest_service/scripts/test_raw.py >> /home/pi/git/gas_meter_rest_service/input/test_raw.log
	sleep 1
done
