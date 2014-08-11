import java.util.ArrayList;
import javax.sound.midi.*;
/*
 * The Song Player that uses Java's MIDI implementation to play your song using the default piano.
 * @author Jon Fast
 */
public class PlaySong {
    // copy of the note array also embedded here. Only sharps included.
    private static String[] noteName = {"C", "C#", "D", "D#",
            "E", "F", "F#", "G", "G#",
            "A", "A#", "B"};
    /*
     * @param Takes a note list and the tempo in beats per minute
     * Plays the song using the PC's built in MIDI synthesizer
     */
    public static void playSong(ArrayList<Note> noteList, int tempo) {
        try {
            //Display the score to the user
            displayScore(noteList);
            //Open a MIDI sequencer
            Sequencer player = MidiSystem.getSequencer();
            //Set the sequence's beats per minute
            player.setTempoInBPM(tempo);
            //get the sequencers transmitting object to send to synth
            Transmitter sendData = player.getTransmitter();
            //create and initialize the synthesizer + optional methods
            Synthesizer instrument = MidiSystem.getSynthesizer();
            //MidiChannel[] channels = s.getChannels();
            //Instrument[] instruments = s.getAvailableInstruments();
            //load the default soundbank into the synthesizer
            instrument.loadAllInstruments(instrument.getDefaultSoundbank());
            //get the synths receiving MIDI input port
            Receiver receiveData = instrument.getReceiver();
            //tie the sequencer object and synthesizer together
            sendData.setReceiver(receiveData);
            //open the sequencer's connection and the synthesizer's connection
            player.open();
            instrument.open();
            //safe to send MIDI data post-this-point
            //create a new sequence using a PPQ timebase (1 tick / quarter note)
            //we can change the number of ticks if we want smaller sub-divisions
            Sequence composition = new Sequence(Sequence.PPQ, 1);
            //create a track to sequence notes on
            //can create more if composition is multitrack
            composition.createTrack();
            //get the tracks optional, for sequencing multiple tracks
            Track[] tracks = composition.getTracks();
            //add messages to track[0], channel 5 is default, 90 is default
            //note velocity
            //keep track of the starting time (0) and the ending time
            int timestamp = 1;
            //the MIDI root note code for octave 5 is 60.
            int rootNote = 60;
            //default velocity
            int defaultVelo = 90;
            //for every note in our song
            for (Note n : noteList) {
                //add a new 'note-on' event on channel 5
                //translate the note name to a MIDI value
                //get the beginning timestamp (playhead)
                tracks[0].add(new MidiEvent(
                        new ShortMessage(ShortMessage.NOTE_ON, 0,
                            getMIDIValue(rootNote, n), defaultVelo), timestamp));
                //increase the timestamp by the number of song beats in a note
                timestamp += n.getBeats();
                //add a 'note-off' event at the ending timestamp
                tracks[0].add(new MidiEvent(
                        new ShortMessage(ShortMessage.NOTE_OFF, 0,
                            getMIDIValue(rootNote, n), defaultVelo), timestamp));
            }
            //set up our sequencer to play the sequence we created
            player.setSequence(composition);
            //start our sequence
            player.start();
            //this loop will quit once the sequence is done playing
            System.out.println("Started playing...");
            //beat is a quarter note
            //fraction of minute the song plays is number of beats over the bpm
            double songTimeMultiplier = ((double)getCurrentNumberOfBeats(noteList) / (double)tempo);
            //number of milliseconds in a minute is 60000
            int millisInMinute = 60000;
            //sleep for the fraction of a minute that is the multiplier
            try
            {
                //make the console sleep for a little
                Thread.sleep((int) ((double)millisInMinute * (double)songTimeMultiplier));
            }
            catch(InterruptedException e)
            {
                //we couldn't sleep (should never happen)
                System.out.println("Failed to sleep. Ending playback.");
            }
            //display done, and close out all other midi devices
            System.out.println("Finished playing...");
            //stop sequence once done playing
            player.stop();
            //free up the track memory
            composition.deleteTrack(tracks[0]);
            //close the sequencer and synthesizer objects to prevent hang
            //transmitter and receiver objects also freed.
            player.close();
            instrument.close();
            receiveData.close();
            sendData.close();
            //MIDI unavailable or invalid data are the caught exceptions
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            System.out.println("An error occurred with one "
                + "of the following tasks" + e.getMessage());
        }
    }

    /*
     * @param Takes the MIDI number for the root note of this scale
     * @param Takes the note object
     * @return Returns the equivalent MIDI note number
     */
    public static int getMIDIValue(int rootNote, Note note) {
        for (int i = 0; i < noteName.length; i++) {
            if (note.getNote().equals(noteName[i])) {
                //MIDI note value is exact array location as per MIDI spec.
                return rootNote + i;
            }
        }
        //this should never happen
        return rootNote;
    }

    /*
     * @param Takes the array of notes
     * Prints out the score sheet of what you've written.
     */
    private static void displayScore(ArrayList<Note> noteList) {
        //declaration of the current score
        System.out.println("Your current score:\n");
        //score board is an array of Note
        Note[] currentNoteList = new Note[noteList.size()];
        //convert the notelist to array for easier access
        for (int i = 0; i < noteList.size(); i++) {
            currentNoteList[i] = noteList.get(i);
        }
        //set the initial song length
        int songLength = 1;
        //get the total number of beats and add to the song length
        for (int i = 0; i < currentNoteList.length; i++) {
            songLength += currentNoteList[i].getBeats();
        }
        //format the song header
        System.out.format("%-3s", "");
        //print out the song header based on the song length (tick marks)
        for (int i = 0; i < songLength - 1; i++) {
            System.out.format("%-4s", " " + i + " ");
        }
        //formatting purposes, do not remove
        System.out.println();
        //start at the highest note and move to the lowest
        for (int j = noteName.length - 1; j >= 0; j--) {
            //print out the names of each note from the top
            System.out.format("%-3s", noteName[j]);
            //for each note in the list
            for (int l = 0; l < currentNoteList.length; l++) {
                //acquire its length
                int currentNoteLength = currentNoteList[l].getBeats();
                //for the length of each note
                for (int i = 0; i < currentNoteLength; i++) {
                    //if this note that we chose is the note name we
                    //are currently filling in
                    if (currentNoteList[l].getNote().equals(noteName[j])) {
                        //note 'hit' is an exclamation point, 
                        //sustain/hold is '*'
                        if (i == 0) {
                            System.out.format("%-4s", " ! ");
                        } else {
                            System.out.format("%-4s", " * ");
                        }
                        //no note hit is '-'
                    } else {
                        System.out.format("%-4s", " - ");
                    }
                }
            }
            //increment line, do not remove for formatting
            System.out.println();
        }
        //formatting
        System.out.println();
    }

    public static int getCurrentNumberOfBeats(ArrayList<Note> currentNoteList)
    {
        int songLength = 1;
        //get the total number of beats and add to the song length
        for (int i = 0; i < currentNoteList.size(); i++) {
            songLength += currentNoteList.get(i).getBeats();
        }
        return songLength;
    }
}