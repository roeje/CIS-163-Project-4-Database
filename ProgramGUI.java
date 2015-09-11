/**
 * ProgramGUI, as a JFrame, is graphic user interface of three JPanels:
 * 
 * PhoneNumberDisplayPanel
 * KeypadPanel
 * ControlPanal
 * 
 * @author (Jesse Roe) 
 * @version (11-10-2014)
 */
import javax.swing.*;
import java.awt.*;

public class ProgramGUI extends JFrame
{
    /**
     * Constructor for an Program GUI
     * 
     * @param  title   a string to define the title of this JFrame.
     */
    public ProgramGUI( String title )
    {
        super( title );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Declaration of three JPanels, to be added to this JFrame.
        PhoneNumberDisplayPanel phoneNumberDisplayPanel;
        KeypadPanel             keypadPanel;
        ControlPanel            controlPanel;

        /**
         * Declaration of phoneNumberLabel, which is added to the PhoneNumberDisplayPanel,
         * to be shared by the KeypadPanel and ControlPanel.
         */ 
        JLabel                  phoneNumberLabel;

        phoneNumberLabel = new JLabel( "" );
        phoneNumberLabel.setPreferredSize( new Dimension( 140, 25 ) );    
        phoneNumberLabel.setFont( new Font( "Calibri", 1, 24 ) );

        phoneNumberDisplayPanel = new PhoneNumberDisplayPanel(phoneNumberLabel);
        keypadPanel = new KeypadPanel(phoneNumberLabel);
        controlPanel = new ControlPanel(phoneNumberLabel);

        setLayout( new BorderLayout( ) );

        getContentPane().add(phoneNumberDisplayPanel, "North");
        getContentPane().add(keypadPanel, "Center");
        getContentPane().add(controlPanel, "South");

        this.setLocation( 10, 100 );
        this.pack();
        this.setVisible(true);
    }

    /**
     * main is the program point of entry; execution starts here.
     */
    public static void main( )
    {
        /***********************************************************************
         *  Your name needs to be part of the JFrame title.
         */
        ProgramGUI frame = new ProgramGUI( "Jesse Roe" + ", CIS 162 Project 4" );
    }
}