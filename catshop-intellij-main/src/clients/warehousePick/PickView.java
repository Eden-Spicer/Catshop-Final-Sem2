package clients.warehousePick;

import catalogue.Basket;
import middle.MiddleFactory;
import middle.OrderProcessing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class PickView implements Observer
{
  private static final String PICKED = "Picked";

  private static final int H = 300;       // Height of window pixels
  private static final int W = 400;       // Width  of window pixels

  private final JLabel      theAction  = new JLabel();
  private final JLabel      theCount   = new JLabel();
  private final JLabel      pickingTime   = new JLabel();
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtPicked= new JButton( PICKED );
 
  private OrderProcessing theOrder     = null;
  
  private PickController cont= null;

  private Timer timer;
  public int counterSecs, counterMins;
  private String ddSecond;

  // For testing timer
  public PickView(){
    pickTimer();
    timer.start();
  }

  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-cordinate of position of window on screen 
   * @param y     y-cordinate of position of window on screen  
   */
  public PickView(  RootPaneContainer rpc, MiddleFactory mf, int x, int y )
  {
    try                                           // 
    {      
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );
    
    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is
    Font fontTimer = new Font("Arial", Font.PLAIN, 24);

    theBtPicked.setBounds( 16, 25+60*0, 80, 40 );   // Check Button
    theBtPicked.addActionListener(                   // Call back code
      e -> {cont.doPick(); counterSecs = 0;} );
    cp.add( theBtPicked );                          //  Add to canvas

    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setText( "" );                        // Blank
    cp.add( theAction );                            //  Add to canvas

    theCount.setBounds(30, 25+40*2, 80, 40);
    theCount.setFont(fontTimer);
    theCount.setText("");
    cp.add(theCount);

    pickingTime.setBounds(16, 25+60*1, 80, 40);
    pickingTime.setText("");
    cp.add(pickingTime);

    theSP.setBounds( 110, 55, 270, 205 );           // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font  
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible

    pickTimer();
  }

  public void setController( PickController c )
  {
    cont = c;
  }

  public void pickTimer() {
    DecimalFormat formatTime = new DecimalFormat("00");
    timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        counterSecs++;
        ddSecond = formatTime.format(counterSecs);
        theCount.setText(counterMins +":" + ddSecond);

        if(counterSecs == 60){
          counterSecs = 0;
          counterMins++;
        }
      }
    });
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
    PickModel model  = (PickModel) modelC;
    String        message = (String) arg;
    theAction.setText( message );
    
    Basket basket =  model.getBasket();
    if ( basket != null )
    {
      theOutput.setText( basket.getDetails() );
      timer.start();
      pickingTime.setText("Time to pick:");
    } else {
      theOutput.setText("");
      timer.stop();
      theCount.setText("");
      pickingTime.setText("");
    }
  }

}
