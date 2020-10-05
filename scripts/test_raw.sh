#!/bin/bash
while :
do
	python /home/pi/gasmeter/test_raw.py >> /home/pi/gasmeter/input/test_raw.log
	sleep 1
done
