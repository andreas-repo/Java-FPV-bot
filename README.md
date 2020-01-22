# Java-FPV-bot

Software for my FPV-bot Project. 


## How To 

To connect to the robot and the integrated first person view camera, please create hotspot with ssid = "fpv-bot" and password = "robot".
After you created the hotspot, please start/reset the robot. Now the robot should have joined the wifi network. Check your network software
for the IP's of the two devices. For the best way to directly control the robot, please run the "control-software.exe" or 
"control-software.jar" application.
Enter the two IPs in the application and the robot is ready to go.

## Programm the robot

If you want to programm the robot yourself using Java, we provide a connection/commands class so you can build on top of it without worrying
about how to connecting to the robot. 

## How to change SSID and Password

###FPV Camera (ESP32 Microcontroller)
!You need a Serial-to-USB Programmer or a Arduino Uno
If you want to change the SSID or the password your robot uses to connect to the hotspot, please open the "robot-cam-aruino.ino" file with 
Arduino and change the appropriate values and the upload the file to the Microcontroller. 

###ESP8266
IN PROGRESS
