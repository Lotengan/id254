package id254;



import org.smslib.*;
import org.smslib.Message.MessageTypes;

//import java.util.regex.Matcher;
import java.sql.*;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//import java.sql.CallableStatement;
*/
//import org.smslib.*;
//import org.smslib.Message.MessageTypes;
import org.aiti.sms.IAITIInboundMessageNotification;

public class SimpleApp implements IAITIInboundMessageNotification {
    private int getid;
	
    @Override
	public void process(Service srv, String gatewayId, MessageTypes msgType,InboundMessage msg) {
		try {
			//The response is simply the message we received

			String response = msg.getText();
                        String [] items = response.split(",");
                        String name = items[0];
                         String gender = items[1];
                         String ph = msg.getOriginator();

              try{

//call JDBC
	            Class.forName("com.mysql.jdbc.Driver");

	            //the code below shows connection to the localhost database
	            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test");
//database setup
	            String sourceURL ="jdbc:mysql://localhost/smsregister";
	            String user = "admin";
	            String password = "admin";


	            Connection con = DriverManager.getConnection(sourceURL,user,password);
	            //Statement stmt = con.createStatement();
	            System.out.println("Connection established succesfully!");
	         
	           Statement stmt = con.createStatement();
                   stmt.executeUpdate("INSERT INTO register " + "VALUES ('"+ph+"', '"+name+"', '"+gender+"')");
	           
	            //send outbound message
                       OutboundMessage out = new OutboundMessage(msg.getOriginator(), "Thank You! We will Get Back To You");
                       

                       srv.sendMessage(out);
                      
	        }
	        catch (ClassNotFoundException cnfe){
	            System.err.println(cnfe);
	        }




//                        if(response.equals("kenya")){
//
//                        String replykenya = "Kenya ni Yangu, Nitakuwepo Kila Wakati";
//
//                        OutboundMessage out = new OutboundMessage(msg.getOriginator(), replykenya);
//
//                        srv.sendMessage(out);
//
//                        }


                }
  catch (Exception ex) {
  System.out.println("There was an error."+ex.getMessage());
  }
  }

	}






