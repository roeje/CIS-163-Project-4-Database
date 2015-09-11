/**
 * ContactList encapsulates an ArrayList of Contact objects, 
 * each comprising a name and a phone number.
 * 
 * @author (Jesse Roe) 
 * @version (11-10-2014)
 */
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

public class ContactList
{
    private ArrayList<Contact> list;

    /**
     * Constructor for a ContactList, to initialize the list from file.
     * 
     * @param  fileName   a reference to an existing file
     * @param  window     a reference to an existing window to display the list contacts
     */
    public ContactList( String fileName, Window window )
    {
        list = new ArrayList<Contact>( );

        File file = new File( fileName );

        // Remark. A file needs to have been written, before it can be read.
        if (file.exists())
        {
            try
            {
                Scanner fileReader = new Scanner( file ); 
                readFile( fileReader );
                window.print( this.toString () );
            }
            catch( FileNotFoundException error )   // could not find file
            {
                System.out.println( "File not found " );
            }
        }
    }

    /********************************************************************
     * Searches the list for a particular contact, 
     * comparing this name and number (in lower case) 
     * with the contact name and number (in lower case).
     * 
     * @param   contact
     * @return  returns true if the contact is found, otherwise false
     */
    public boolean found( Contact contact )
    {
        for(Contact a : list){
            if(a.getName().toLowerCase().equals(contact.getName().toLowerCase()) && 
                    a.getNumber().toLowerCase().equals(contact.getNumber().toLowerCase()))
                        return true;
        }
        return false;
    }

    /******************************************************************
     * Adds one contact to the list
     * 
     * @param   contact
     */
    public void add( Contact contact )
    {
        list.add(contact);
    }

    /******************************************************************
     * searches the list for each name that contains the specified substring
     * 
     * @param   substring
     * @return  returns the result as an ArrayList
     */
    public ArrayList<Contact> searchByName(String substring) 
    {
        ArrayList<Contact> found = new ArrayList<Contact>();
        for(Contact a : list){
            if(a.getName().toLowerCase().contains(substring.toLowerCase()))
                found.add(a);
        }
        return found;     
    }

    /******************************************************************
     * searches the list for a contact that has the specified number
     * 
     * Since phone numbers are not shared, a phone number can appear 
     * in the list at most once.
     * 
     * @param   substring
     * @return  returns the unique contact found, possibly null
     */
    public Contact searchByNumber( String number ) 
    {
        for(Contact a : list){
            if(a.getNumber().equals(number)) {
                return a;
            }
        }
        return null;
    }

    /**
     * reads the text file and adds each entry as a contact to the list of contacts
     * 
     * @param  fileReader 
     */
    public void readFile( Scanner fileReader ) 
    {
        Contact contact = new Contact( null );

        try
        {
            // read one line for each Contact
            while( fileReader.hasNext( ) )
            {
                contact.readFile( fileReader );
                list.add( new Contact( contact ) );  
            }
        }
        catch (Exception e)
        {
            System.out.println( "Error" );
        }
    }

    /**
     * writes the entire list of contacts as text to the specified file.
     * 
     * @param  writer 
     */
    public void writeFile( FileWriter writer ) 
    {
        for ( Contact c: list )
        {
            c.writeFile( writer  );
        }
    }

    /**
     * returns the entire list as a string
     * 
     * @return  the list as a string
     */
    public String toString()
    {
        String str = "";
        for ( Contact c: list)
        {
            str += c.toString() + "\r\n";
        }

        return str;        
    }
}