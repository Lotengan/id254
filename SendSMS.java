package id254;

//import org.aiti.*;
import org.aiti.sms.AITISMSServer;
import org.aiti.sms.SMSHandlerThread;
import org.smslib.OutboundMessage;

/**
 * The main driver for an SMS application in the AITI SMSlib framework.  In
 * the main method you should register your inbound handler, set the proxy (if
 * necessary) and set the comm port of the modem.  The service will then start,
 * waiting for incoming messages.
 *
 * A new thread will be spawned to service each incoming message.  The thread
 * will create a new object of the class type of the registered handler
 * (see SMSHandlerThread.setAITIInboundMessageNotification).  This new object,
 * in its own thread, can then service the inbound message, sending a response
 * if necessary.
 *
 *
 * @author AITI
 *
 */
public class SendSMS {
	/** set to true if you want debugging information */
    
	public static final boolean debug = true;

	public static void main(String args[]) {

		AITISMSServer app = new AITISMSServer(true);
		try {
			//Set your processor to create a new object of your handler
     SMSHandlerThread.setAITIInboundMessageNotification(new SimpleApp());


       app.setComPort((short)2, 460800);
//start the service so that we can send and receive messages
	app.startService();

			//send a test message
        app.getService().sendMessage(new OutboundMessage
                    ("0718552341", "Lotengan Testing Network"));
        // button.add = new confihurations test speakers

			//wait for incoming messages
	app.waitForInput();

			//stop all services and threads
	app.stopService();

	app.doIt();
		} catch (Exception e) {
		}
	}

	/**
	 * A helpful printing method to use instead of System.out.println().
	 * Use Main.debug to toggle printing the the screen.
	 *
	 * @param s The object to print
	 */
	public static void debugPrintln(Object s) {
		if (debug)
			System.out.println(s);
	}
}