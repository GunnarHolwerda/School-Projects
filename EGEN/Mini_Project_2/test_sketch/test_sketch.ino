int pin = 13;

void setup(){
  pinMode(pin, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  Serial.print(F("What is going on here?\n"));
  delay(1000);
}
