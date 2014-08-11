/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rolodex;

/**
 *
 * @author Brendan
 */
public class Contact {

    private String firstName;
    private String lastName;
    private int phone;
    private String email;

    public Contact(String firstName, String lastName, int phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public boolean match(String firstName, String lastName, String phoneStr, String email) {
        if (!firstName.equals("") && !firstName.equals(this.firstName)) {
            return false;
        }
        if (!lastName.equals("") && !lastName.equals(this.lastName)) {
            return false;
        }
        if (!phoneStr.equals("") && Integer.parseInt(phoneStr) != this.phone) {
            return false;
        }
        if (!email.equals("") && !email.equals(this.email)) {
            return false;
        }
        return true;


    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void print() {
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
}
