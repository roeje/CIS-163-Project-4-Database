/**
 * PhoneNumberDisplayPanel displays a phone number, which is to be shared by 
 * reference with both a Keypad and an auxiliary ControlPanel.
 * 
 * @author (Jesse Roe) 
 * @version (11-10-2014)
 */
import javax.swing.*;
import java.awt.*;

public class PhoneNumberDisplayPanel extends JPanel
{
    /**
     * Constructor for a PhoneNumberDisplayPanel
     * 
     * @param  phoneNumberLabel   is the primary display in this project.
     */
    public PhoneNumberDisplayPanel( JLabel phoneNumberLabel )
    {
        setBorder( BorderFactory.createLineBorder( Color.MAGENTA, 3 ) );
        setBackground( Color.WHITE );
        add(phoneNumberLabel);
    }
}
