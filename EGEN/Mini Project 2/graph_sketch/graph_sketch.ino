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
float refvoltage = 1.1;
boolean calibrate = true;
int waterLevel = 0;

void setup() {
  // Initialize the serial communication:
  Serial.begin(9600);
  analogReference(INTERNAL);
}

void loop() {
  int temperature = analogRead(A0);
  Serial.print("Temperature is ");
  Serial.println(temperature);
  int samples = 20; //How many times we will read in
  int aRead = 0;
  
  int count = 0;
  int pastWaterLevel = analogRead(A5);
  //Find the value of waterLevel to find difference caused by salt
  while(calibrate) {
    waterLevel = analogRead(A5);
    if ((waterLevel <= (pastWaterLevel + 5) || waterLevel >= (pastWaterLevel - 5))){
      //The current reading is similar to the previous reading
      if (count >= 20) {
        Serial.print("We have calibrated at a value of: ");
        Serial.println(pastWaterLevel);
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
    aRead += (analogRead(A5) - pastWaterLevel);
  }
  
  float voltage = 5.0 * aRead/ (1023 * samples);  // assuming 5V reference 
  
  ppt = 16.3 * voltage;
  
  // convert voltage to ppt
  Serial.print("Analog in reading: ");
  Serial.print(aRead);
  
  // print ppt value on serial monitor
  Serial.print(" - Calculated ppt: ");
  Serial.println(ppt, 4); // the 4 is for 4 decimal places
  delay(1000);
  
  // Wait a bit for the analog-to-digital converter 
  // To stabilize after the last reading:
  delay(2);
}

