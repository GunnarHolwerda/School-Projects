/*
  Graph
 
 You can use the Arduino serial monitor to view the sent data, or it can
 be read by Processing, PD, Max/MSP, or any other program capable of reading 
 data from a serial port.  The Processing code below graphs the data received 
 so you can see the value of the analog input changing over time.
 
 The circuit:
 The salinity sensor is attached to A5.
*/

float ppt = 0;
float refVoltage = 5.0;
boolean calibrate = true;
int waterLevel = 0;
int calibratedWaterLevel = 0;

void setup() {
  // Initialize the serial communication:
  Serial.begin(9600);
  //analogReference(INTERNAL);
}

void loop() {
  float samples = 1; //How many times we will read in
  float aRead = 0;
  
  int count = 0;
  int pastWaterLevel = analogRead(A5);
  //Find the value of waterLevel to find difference caused by salt
  while(calibrate) {
    waterLevel = analogRead(A5);
    Serial.println(waterLevel);
    if ((waterLevel <= (pastWaterLevel + 2) || waterLevel >= (pastWaterLevel - 2))){
      //The current reading is similar to the previous reading
      if (count >= 20) {
        Serial.print("We have calibrated at a value of: ");
        Serial.println(pastWaterLevel);
        calibratedWaterLevel = pastWaterLevel;
        //We have reached a place where the value has stabilized, end calibration
        calibrate = false;
      }
      else {
        //Value is close to previous add to calibration count
        count++;
      }
    }
    else {
      // Value has differentiated too much from before, restart count for calibration
      // and set new pastWaterLevel to the currentWaterLevel
      count = 0;
      pastWaterLevel = waterLevel;
    }
  }
  
  for (int i = 0; i < samples ; i++) 
  {
    int reading = analogRead(A5);
    //Serial.print("Reading: ");
    //Serial.println(reading);
    //Serial.print("Water level calibration: ");
    //Serial.println(calibratedWaterLevel);
    aRead += (reading - calibratedWaterLevel);
    //Serial.println(aRead);
  }
  
  float voltage = refVoltage * aRead/ (1023.0 * samples);  // assuming 5V reference 
  
  ppt = 16.3 * voltage;
  
  // convert voltage to ppt
  Serial.print("Analog in reading: ");
  Serial.print(aRead);
  
  // print ppt value on serial monitor
  Serial.print(" - Calculated ppt: ");
  Serial.println(ppt, 4); // the 4 is for 4 decimal places
  delay(500);
  
  // Wait a bit for the analog-to-digital converter 
  // To stabilize after the last reading:
  delay(2);
}

