/*
  Graph
 
 You can use the Arduino serial monitor to view the sent data, or it can
 be read by Processing, PD, Max/MSP, or any other program capable of reading 
 data from a serial port.  The Processing code below graphs the data received 
 so you can see the value of the analog input changing over time.
 
 The circuit:
 Any analog input sensor is attached to analog in pin 0.
*/

void setup() {
  // Initialize the serial communication:
  Serial.begin(9600);
}

void loop() {
  // Send the value of analog input 0:
  Serial.println("YOLO");
  
  // Wait a bit for the analog-to-digital converter 
  // To stabilize after the last reading:
  delay(2);
}

