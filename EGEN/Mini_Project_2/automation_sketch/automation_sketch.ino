//---Library Callout---//
#include <Wire.h>
#include <Adafruit_MotorShield.h>
#include "utility/Adafruit_PWMServoDriver.h"
#include <Servo.h> 

Adafruit_MotorShield AFMS = Adafruit_MotorShield(); 
Adafruit_DCMotor *FAN = AFMS.getMotor(3);//Fan in Motor Port 3
Adafruit_DCMotor *SOLENOID = AFMS.getMotor(4);//Solenoid in Motor Port 4
Servo gateServo, stirringServo;  // create servo object to control a servo

int servoButtonPin = 4, solenoidButtonPin = 2;        //Pins the buttons are plugged into
int servoButtonState = 0, solenoidButtonState = 0;    //Starting state of buttons 0 = off
int gateServoPin = 10, stirringServoPin = 9;          //Pin of servo motors
int solenoidTime = 3380;                              // How long to open the solenoid valve for
int stirSwipes = 60;                                 //how many times the stirrer swipes
boolean solenoidRan = true, servoRan = false;        // booleans to tell if the solenoid or servo have run, used to determine if button was pressed on
int gateState = 0;                                   // gateServo state, 0 = close, 1 = open      

void setup(){
  gateServo.attach(gateServoPin);        // attaches the gateServo to the gateServoPin
  stirringServo.attach(stirringServoPin);// attaches the stirringServo to the stirringServoPin
  pinMode(servoButtonPin, INPUT);        //Sets the servoButtonPin to take input
  pinMode(solenoidButtonPin, INPUT);    //Sets the solenoidButtonPin to take input  
  Serial.begin(9600);          //Starts serial connection
  AFMS.begin();                //Begins Adafruit motorshield
  gateServo.write(180);        //Turns gate to the closed position at startup
}

void loop() {
  //Start Fan when button is turned on
  FAN->run(BACKWARD);          //Set to FORWARD/BACKWARDif FAN does not run
  FAN->setSpeed(255); 
  
  servoButtonState = digitalRead(servoButtonPin);        //Read the state of the servoButtonPin
  solenoidButtonState = digitalRead(solenoidButtonPin);  //Read the state of the solenoidButtonPin
  if (solenoidButtonState == HIGH) {
    //The solenoid button was pressed
    Serial.println("The solenoid button is on");
    while(solenoidRan) {
      //---Open solenoid valve---//
      SOLENOID->run(FORWARD);//Set to FORWARD/BACKWARD if SOLENOID does not run
      SOLENOID->setSpeed(255);
      delay(solenoidTime);      //Open solenoid for this amount of time  
      SOLENOID->run(RELEASE);
      solenoidRan = false;      //Set solenoid to have run so that we don't run this every time if the button remains on
    }
  }
  else {
    Serial.println("The solenoid button is off");
    solenoidRan = true;
  }
  
  //---Hopper Gate Control---//
  if (servoButtonState == HIGH) {
   Serial.println("The servo button is on");
   while (!servoRan) {    //Makes sure the servo's don't keep running if the buton remains on
     if (gateState != 1) {      //If gate is not open
       //We need to open the gate
       for(int pos = 180; pos >= 90; pos -= 1) // goes from 0 degrees to 180 degrees 
       { 
           gateServo.write(pos);               // tell servo to go to position in variable 'pos' 
           delay(15);                         // waits 15ms for the servo to reach the position
       }
       gateState = 1;    //Set gate state to 1 (open)
     }
     for (int i = 0; i < stirSwipes; i++) {    //Run the stirrer for stirSwipes amount of swipes
       if (digitalRead(servoButtonPin) == LOW)  {  //Check if the button has been turned off since the swiping started
         //Shut the gate and stop the stirring
         shutGate();
         break;
       }
       stir();
     }
     servoRan = true;    //Set the servoRan to true so that we don't keep running the stirring if the button remains on
     if (gateState != 0) {    //Check to make sure the gate isn't already shut
       shutGate();
     }
   }
  }
  else {
    Serial.println("The servo button is off");
    servoRan = false;
  }
}

void shutGate() {
  for(int pos = 90; pos <= 180; pos += 1)     // goes from 90 degrees to 180 degrees 
  {                
    gateServo.write(pos);              // tell servo to go to position in variable 'pos' 
    delay(15);                       // waits 15ms for the servo to reach the position 
  }
  gateState = 0;      //Set gate to closed
}

void stir() {
  for(int pos = 88; pos <= 110; pos += 1) // goes from 60 degrees to 97 degrees 
  {                                  // in steps of 1 degree 
    stirringServo.write(pos);        // tell servo to go to position in variable 'pos' 
    delay(10);                       // waits 15ms for the servo to reach the position 
  } 
  for(int pos = 110; pos >= 88; pos -= 1) // goes from 97 degrees to 60 degrees 
  {                                
    stirringServo.write(pos);  
    delay(10);                       // waits 15ms for the servo to reach the position 
  }
  stirringServo.write(99);    //Set Stirrer to middle position;
}
