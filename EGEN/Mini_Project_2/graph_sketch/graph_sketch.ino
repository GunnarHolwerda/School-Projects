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
float refVoltage = 5.0;

void setup() {
  // Initialize the serial communication:
  Serial.begin(9600);
}

void loop() {
  
  int aRead = analogRead(SALINITY_PIN);
  
  //Calculate voltage
  float voltage = refVoltage * aRead / 1023.0;  // assuming 5V reference   
  
  //Calculate salinity based on calibration formula
  float salinity = 194.96 * voltage - 654.253;

  DHT.read11(DHT11_PIN);
  Serial.print(DHT.humidity, 1);          //Send humidity percentage
  Serial.print(" ");
  Serial.print(DHT.temperature, 1);     //Send temperature in Celsius
  Serial.print(" ");
  Serial.println(salinity, 4);                 //Send salinity calculation
  delay(500);
}

