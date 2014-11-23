/*
  Graph
 
 The circuit:
 The salinity sensor is attached to A5.
*/

float ppt = 0;
float refVoltage = 5.0;
int calibratedWaterLevel = 461;

void setup() {
  // Initialize the serial communication:
  Serial.begin(9600);
}

void loop() {
  float samples = 20; //How many times we will read in
  float aRead = 0;
  
  for (int i = 0; i < samples ; i++) 
  {
    int reading = analogRead(A5);
    aRead += (reading - calibratedWaterLevel);
  }
  
  float voltage = refVoltage * aRead/ (1023.0 * samples);  // assuming 5V reference 
  
  ppt = 16.3 * voltage;
  
  //Convert voltage to ppt
  Serial.print("Analog in reading: ");
  Serial.print(aRead);
  
  //Print ppt value on serial monitor
  Serial.print(" - Calculated ppt: ");
  Serial.println(ppt, 4); // the 4 is for 4 decimal places
  delay(500);
}

