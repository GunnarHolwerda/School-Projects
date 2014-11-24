/*
  Graph
 
 The circuit:
 The salinity sensor is attached to A5.
 The temperature/humidity sensor is attaached to digital 5
*/

#include <dht.h>

//VARIABLES
dht DHT;
int DHT11_PIN = 5;
int SALINITY_PIN = 5;
float ppt = 0;
float refVoltage = 5.0;
// This is the calibration value, it gets subtracted from the reading in
// to give you the conductivity
int calibratedWaterLevel = 317;

void setup() {
  // Initialize the serial communication:
  Serial.begin(9600);
}

void loop() {
  float samples = 1; //How many times we will read in
  float aRead = 0;
  
  for (int i = 0; i < samples ; i++) 
  {
    int reading = analogRead(SALINITY_PIN);
    aRead += (reading - calibratedWaterLevel);
  }
  
  float voltage = refVoltage * aRead/ (1023.0 * samples);  // assuming 5V reference 
  
  ppt = 16.3 * voltage;    //ppt is 16.3 * voltage
  
  Serial.print("Analog in reading: ");
  Serial.println(aRead);

  //Serial.print(" - Calculated ppt: ");
  DHT.read11(DHT11_PIN);
  Serial.print(DHT.humidity, 1);          //Send humidity percentage
  Serial.print(" ");
  Serial.print(DHT.temperature, 1);     //Send temperature in Celsius
  Serial.print(" ");
  Serial.println(ppt, 4);                 //Send salinity calculation
  delay(500);
}

