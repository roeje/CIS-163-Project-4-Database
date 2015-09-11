/**
 * Contact encapsulates a name and a phone number.
 * 
 * @author (Jesse Roe) 
 * @version (11-10-2014)
 */
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class Contact
{
    private String name;
    private String number;
    //=======================================================================
    // Extra constructor for class. Allows name and number parameters.
    //=======================================================================
    public Contact(String pName, String pNumber) {
        name = pName;
        number = pNumber;
    }

    /**
     * Constructor for a Contact. Calls the set method, which checks for a null reference.
     * 
     * @param  contact   a reference to an existing Contact, or possibly a null reference.
     */
    public Contact( Contact contact )
    {
        set( contact );
    }

    /***************************************************************************
     * Appropriately sets the name and number fields from the contact object,
     * allowing for the possibility that the contact parameter is null.
     * 
     * @param  contact  
     */
    public void set( Contact contact )
    {
        if (contact == null)
        {
            name = "";
            number = "";        
        }
        else
        {
            name = contact.getName();
            number = contact.getNumber();
        }
    }
    //=======================================================================
    // Setters, getters, and toString methods for class
    //=======================================================================
    public String getName( )
    {
        return name; 
    }

    public String getNumber( )
    {
        return number;
    }

    public void setName( String pname )
    {
        name = pname;
    }

    public void setNumber( String pnumber )
    {
        number = pnumber;
    }

    public String toString( )
    {
        return name + "," + number;
    }

    /**
     * reads and extracts text to define the name and number of this Contact instance.
     * 
     * @param  fileReader 
     */
    public void readFile( Scanner fileReader ) 
    {
        {
            String info;
            String[] str;

            try
            {
                // read one line of text
                info = fileReader.nextLine();

                str = info.split(",");

                // extract each field
                name   = str[ 0 ];
                if (name.length() == 0)
                {
                    name = "no name";
                }
                number = str[ 1 ];
            }
            catch (Exception e)
            {
                System.out.println( "Error" );
            }
        }
    }

    /**
     * writes text for this Contact instance to file.
     * 
     * @param  writer 
     */
    public void writeFile( FileWriter writer ) 
    {
        try
        { 
            writer.write( this.toString( )+  "\r\n" );
        } 
        catch( IOException e )
        {
            System.out.println( "Error occured while writing to file: " + writer );
        }        
    }
}