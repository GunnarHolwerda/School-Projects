/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author Josh and Gunnar
 */
public class Word {
    
    private String entry;
    private String definition;
    
    public Word(String entry, String definition) {
        this.entry = entry;
        this.definition = definition;
    }
    
    public String getWord() {
        return entry;
    }
    
    public String getDefinition() {
        return definition;
    }
    
    public void print() {
        System.out.println(entry + ": " + definition);
    }
}
