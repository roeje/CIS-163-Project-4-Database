/**
 * KeypadPanel displays an array of JButtons, which are used in this application to
 * to simulate the buttons on a cell phone.
 * 
 * @author (Jesse Roe) 
 * @version (11-10-2014)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeypadPanel extends JPanel implements ActionListener
{
    private JLabel     phoneNumberLabel;  // a reference to the phoneNumberLabel that is displayed in the PhoneNumberDisplayPanel.
    private JButton[ ] button;            // an array reference of buttons that make up the keypad display

    /**
     * Constructor for a KeypadPanel.
     * 
     * @param  phoneNumberLabel   a reference to the phoneNumberLabel displayed on the PhoneNumberDisplayPanel.
     */
    public KeypadPanel( JLabel phoneNumberLabel )
    {
        this.phoneNumberLabel = phoneNumberLabel;

        setBorder( BorderFactory.createEmptyBorder( 8, 8, 8, 8 ) );
        setLayout( new GridLayout( 4, 3, 5, 5 ) );

        setBackground( Color.green );
        setBorder( BorderFactory.createLineBorder( Color.BLACK, 3 ) );

        // button captions
        String[] label = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "clear #", "bs"};
        this.button = new JButton[ 12 ];

        for (int n = 0; n < 12; n++)
        {
            this.button[ n ] = this.createButton( label[ n ], JButton.CENTER, "Cambria", Color.BLACK, 14, this ); 
        }

        for (int n = 1; n <= 9; n++)
        {
            this.add( button[ n ] );
        }
        this.add( this.button[ 10 ] );
        this.add( this.button[  0 ] );
        this.add( this.button[ 11 ] );
    }

    /**
     * Creates a JButton
     * 
     * @param text       caption for the button
     * @param alignment  JButton.CENTER | JButton.LEFT | JButton.RIGHT
     * @param fontName   e.g. "Cambria"
     * @param fontColor  e.g. Color.BLACK
     * @param fontSize   e.g. 14
     * @param listener   e.g. this
     * @return           the created JButton 
     */
    public JButton createButton( String text, int alignment, String fontName, Color fontColor, int fontSize, ActionListener listener )
    {
        JButton button = new JButton( text );

        button.setPreferredSize( new Dimension( 120, 25 ) );        

        if (!fontName.equals( "Dialog" ))
        {
            button.setFont( new Font( fontName, 1, fontSize ) );
        }
        button.setForeground( fontColor );
        button.addActionListener( listener );

        return button;
    }

    /***********************************************************************
     * Removes the last character from the phoneNumberLabel.
     */
    private void backspace( )
    {
        String a = phoneNumberLabel.getText();
        int b = a.length();
        if(b>0)
            phoneNumberLabel.setText(a.substring(0,b-1));
    }

    /***********************************************************************
     * Sets the text for phoneNumberLabel to be the empty string, "".
     */
    private void clear( )
    {
        phoneNumberLabel.setText("");
    }

    /***********************************************************************
     * Appends a hyphen, "-", but only if the string length is 3 or 7.
     * Appends the text on the button to the phoneNumberLabel.
     * 
     * @param button  
     */
    private void appendDigit( JButton button )
    {
        String a = phoneNumberLabel.getText();
        if(a.length() == 3 || a.length() == 7){
            a = a + "-";
            phoneNumberLabel.setText(a);
        }
        if(a.length() < 12){
            a = a + button.getText(); 
            phoneNumberLabel.setText(a);
        }
    }

    /***********************************************************************
     * Handles the pending event, whenever it is called.
     * 
     * @param event  
     */
    public void actionPerformed( ActionEvent event )
    {
        Object source = event.getSource( );

        if ( source == this.button[10] )
        {
            clear();
        }
        else if ( source == this.button[11] )
        {
            backspace();
        }
        else
        {
            appendDigit((JButton)source);

        }
    }
}