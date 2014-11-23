/*
  Graphing sketch
 
  This program takes ASCII-encoded strings
  from the serial port at 9600 baud and graphs them. It expects values in the
  range 0 to 1023, followed by a newline, or newline and carriage return
  
 */
 
 import processing.serial.*;
 import beads.*;
 
 //VARIABLES
 //Filename of audio file to play when finished
 String finishAudioFileName = "D:\\Documents\\Programming\\School-Projects\\EGEN\\Mini_Project_2\\graph_sketch_processing\\finish.mp3"; 
 // The serial port
 Serial myPort;   
 // Horizontal position of the graph 
 int xPos = 1;   
 //Time in milliseconds the program will stop recording data   
 long stopTime;
 
 
 void setup () {
   stopTime = System.currentTimeMillis() + 5000;  //Set up stopTime
   size(400, 300);                                //Set the window size      
   
   myPort = new Serial(this, Serial.list()[0], 9600);  //First port in serial list is Arduino so we assign that to the SerialPort
   
   myPort.bufferUntil('\n');    //serialEvent() gets called @ a newline character
   
   background(0);            //Set initial background
 }
 
 
 void draw () {
   // All drawing of the graph happens in serialEvent()
   
   if (System.currentTimeMillis() > stopTime) {  //Check if we are past our stop time
     playSound(finishAudioFileName);             //Play finishAudioFile
     delay(8000);                                //Wait for audiofile to finish
     System.exit(0);                             //Close program
   }
 }
 
 void serialEvent (Serial myPort) {
   String inString = myPort.readStringUntil('\n');  //Get the ASCII string:
   
   if (inString != null) {
     inString = trim(inString);    //Trim whitespace
     System.out.println("We got " + inString + " from the Arduino");
     
     //Save data to a file
     String data[] = split(inString, ' ');   //Split up the data by ' '
     saveStrings("data.txt", data);          //Save the data into data.txt file
     System.out.println("Saving " + data[0] + " to the text file.");
     
     // Convert to a float and map to the screen height:
     float inByte = float(inString); 
     inByte = map(inByte, 0, 100, 0, height);
     
     // Draw the line:
     stroke(127,34,255);
     line(xPos, height, xPos, height - inByte);
     
     // At the edge of the screen, go back to the beginning:
     if (xPos >= width) {
       xPos = 0;
       background(0); 
     } 
     else {
       xPos++;    //Increment the horizontal position
     }
   }   
 }
 
 // Function that plays the audio file specified in the parameter
 void playSound(String fileName) {
    AudioContext ac = new AudioContext();
    SamplePlayer player = new SamplePlayer(ac, SampleManager.sample(fileName));
    Gain g = new Gain(ac, 2, 0.2);
    g.addInput(player);
    ac.out.addInput(g);
    ac.start();
 }
