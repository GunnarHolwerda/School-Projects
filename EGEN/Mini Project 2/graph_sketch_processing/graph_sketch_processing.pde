/* Processing code for this example
 
  Graphing sketch
 
 
  This program takes ASCII-encoded strings
  from the serial port at 9600 baud and graphs them. It expects values in the
  range 0 to 1023, followed by a newline, or newline and carriage return
 
 */
 import processing.serial.*;
 
 Serial myPort;        // The serial port
 int xPos = 1;         // horizontal position of the graph
 
 void setup () {
   // Set the window size:
   size(400, 300);        
   
   // I know that the first port in the serial list 
   // is always my  Arduino, so I open Serial.list()[0].
   myPort = new Serial(this, Serial.list()[0], 9600);
   
   // Don't generate a serialEvent() unless you get a newline character:
   myPort.bufferUntil('\n');
   
   // Set inital background:
   background(0);
 }
 
 
 void draw () {
 // everything happens in the serialEvent()
 }
 
 void serialEvent (Serial myPort) {
   // get the ASCII string:
   String inString = myPort.readStringUntil('\n');
   
   if (inString != null) {
     // trim off any whitespace:
     inString = trim(inString);
     System.out.println("We got " + inString + " from the Arduino");
     
     //Save data to a file
     String data[] = split(inString, ' ');
     saveStrings("data.txt", data);
     System.out.println("Saving " + data[0] + " to the text file.");
     
     // Convert to a float and map to the screen height:
     float inByte = float(inString); 
     inByte = map(inByte, 0, 1023, 0, height);
     
     // Draw the line:
     stroke(127,34,255);
     line(xPos, height, xPos, height - inByte);
     
     // At the edge of the screen, go back to the beginning:
     if (xPos >= width) {
       xPos = 0;
       background(0); 
     } 
     else {
       // increment the horizontal position:
       xPos++;
     }
   }
 }