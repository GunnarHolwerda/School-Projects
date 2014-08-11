/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roladex;

/**
 *
 * @author Gunnar
 */
public class Contact {
    
    private String firstName, lastName;
    private String email;
    private int phoneNumber;
    
    public Contact(String firstName, String lastName, String email, int phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public int getPhoneNumber()
    {
        return phoneNumber;
    }
    
}
