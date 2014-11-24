
//---Library Callout---//
#include <Wire.h>
#include <Adafruit_MotorShield.h>
#include "utility/Adafruit_PWMServoDriver.h"
#include <Servo.h> 

Adafruit_MotorShield AFMS = Adafruit_MotorShield(); 
Adafruit_DCMotor *FAN = AFMS.getMotor(3);//Fan in Motor Port 3
Adafruit_DCMotor *SOLENOID = AFMS.getMotor(2);//Solenoid in Motor Port 2

void setup() {
  Serial.begin(9600);// set up Serial library at 9600 bps
  AFMS.begin();//Create with the default frequency 1.6KHz
}

//--Time Set-Up--//
unsigned long Timer_Solenoid;
unsigned long Timer_Servo;

//---Magnetic Stir Bar---//
void loop() {
 
 FAN->run(FORWARD);//Set to FORWARD/BACKWARDif FAN does not run
 FAN->setSpeed(255);  
 
 //---Upper Reservoir Solenoid Valve---//
 SOLENOID->run(BACKWARD);//Set to FORWARD/BACKWARD if SOLENOID does not run
 SOLENOID->setSpeed(255);
 delay(1000);
 SOLENOID->run(RELEASE);

 //---Hopper Gate Control---//
 Servo SERVO9, SERVO10;  // create servo object to control a servo
 int pos = 0;    // variable to store the servo position 
 SERVO9.attach(9);  // attaches the servo on pin 9 to the servo object 
 SERVO10.attach(10);
 for(pos = 0; pos <= 180; pos += 1) // goes from 0 degrees to 180 degrees 
 {                                  // in steps of 1 degree 
   SERVO9.write(pos);              // tell servo to go to position in variable 'pos' 
   delay(15);                       // waits 15ms for the servo to reach the position 
 } 
 for(pos = 180; pos>=0; pos-=1)     // goes from 180 degrees to 0 degrees 
 {                                
   SERVO9.write(pos);              // tell servo to go to position in variable 'pos' 
   delay(15);                       // waits 15ms for the servo to reach the position 
 } 
} 


