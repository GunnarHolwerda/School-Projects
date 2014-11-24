//---Library Callout---//
#include <Wire.h>
#include <Adafruit_MotorShield.h>
#include "utility/Adafruit_PWMServoDriver.h"
#include <Servo.h> 

Adafruit_MotorShield AFMS = Adafruit_MotorShield(); 
Adafruit_DCMotor *FAN = AFMS.getMotor(3);//Fan in Motor Port 3
Adafruit_DCMotor *SOLENOID = AFMS.getMotor(2);//Solenoid in Motor Port 2

int servoButtonPin = 3, solenoidButtonPin = 2;
int servoButtonState = 0, solenoidButtonState = 0;
int solenoidTime = 500;
boolean notRan = true;

void setup(){
  pinMode(servoButtonPin, INPUT);
  pinMode(solenoidButtonPin, INPUT);
  Serial.begin(9600);
  AFMS.begin();
}

//--Time Set-Up--//
unsigned long Timer_Solenoid;
unsigned long Timer_Servo;

void loop() {
  delay(500);
  
  FAN->run(FORWARD);//Set to FORWARD/BACKWARDif FAN does not run
  FAN->setSpeed(255); 
  
  //servoButtonState = digitalRead(servoButtonPin);
  solenoidButtonState = digitalRead(solenoidButtonPin);
  if (solenoidButtonState == HIGH && notRan) {
    //The solenoid button was pressed
    Serial.println("The solenoid button is on");
    //---Upper Reservoir Solenoid Valve---//
    SOLENOID->run(FORWARD);//Set to FORWARD/BACKWARD if SOLENOID does not run
    SOLENOID->setSpeed(255);
    delay(solenoidTime);
    SOLENOID->run(RELEASE);
    notRan = false;
  }
  else {
    Serial.println("The solenoid button is off");
    notRan = true;
  }
}
