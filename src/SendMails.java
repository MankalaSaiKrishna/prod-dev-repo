import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMails {
	
	public static List<DBObject> EstablishDataBaseConnections(){
		List<DBObject> birthDaysList = new ArrayList<DBObject>();
		DBObject dbObject = null;
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");		
			con = DriverManager.getConnection("jdbc:mysql://localhost/mysql","root","");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * from Birthdays");
			
			while(rs.next()){
				dbObject = new DBObject();
				System.out.println("UserName -- "+rs.getString("Name"));
				System.out.println("EmailIds -- "+rs.getString("Email"));
				System.out.println("BirthDate -- "+rs.getString("DateOfBirth"));
				System.out.println("EmailSentYear -- "+rs.getString("EmailSentyear"));
				dbObject.setUserName(rs.getString("Name"));
				dbObject.setToEmailId(rs.getString("Email"));
				dbObject.setDateOfBirth(rs.getString("DateOfBirth"));
				dbObject.setEmailSentYear(rs.getString("EmailSentyear"));
				birthDaysList.add(dbObject);
			}
		}
		catch(Exception e){
			
			e.printStackTrace();
			System.out.println(e.getStackTrace());
		}
		finally{			
			try{
				if( st != null )
					st.close();
				if( rs != null )
					rs.close();
				if( con != null )
					con.close();
				
			}
			catch(SQLException sqlEx){
				System.out.println("finally Block"+sqlEx.getStackTrace());				
			}
		}
		
		return birthDaysList;
	}
	public static void main(String arguments[]){
		final String username = arguments[0];//;"saikrishna.m@imaginea.com"
		final String password = arguments[1];//"saikrishnam1989";
		String toEmail = "saikrishna4u2003@gmail.com";//arguments[2];
		List<DBObject> listOfUsers;
		DBObject eachUser = null;
		Date date = new Date();
		String birthDate = "";
		String userName = "";
		int dayOfBirth = 0;		
		int monthOfBirth = 0;
		String emailSentYr = "";
		String[] arr = null;
		String currentYear = date.toString().substring(date.toString().length()-4, date.toString().length());//date.getYear();
		int todaysDate = date.getDate();
		int currentMonth = date.getMonth();
		System.out.println("Todays Date --  "+todaysDate + " --- Month  -- " +(currentMonth+1)+" -- Year --"+currentYear+"--"+date.toString());		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", "smtp.imaginea.com");
		props.put("mail.smtp.port", "587");
		listOfUsers = EstablishDataBaseConnections();
		System.out.println("Test Newlines");
		int a=4;
		System.out.println("a"+a);
		for(int i = 0 ; i < listOfUsers.size() ; i++ ){
			eachUser = listOfUsers.get(i);
			birthDate = eachUser.getDateOfBirth();
			arr = birthDate.split("-");
			System.out.println("dayOfBirth"+dayOfBirth);
			System.out.println("monthOfBirth"+monthOfBirth);
			System.out.println("emailSentYr"+emailSentYr);	
			dayOfBirth = Integer.parseInt(arr[2]);
			monthOfBirth = Integer.parseInt(arr[1]);
			emailSentYr = eachUser.getEmailSentYear();
			System.out.println("dayOfBirth"+dayOfBirth);
			System.out.println("monthOfBirth"+monthOfBirth);
			System.out.println("emailSentYr"+emailSentYr);			
			if( currentMonth == monthOfBirth && dayOfBirth == todaysDate && emailSentYr.equals("")){
				userName = eachUser.getUserName();
				toEmail = eachUser.getToEmailId();//++"bigmachines-ps@imaginea.com";
				try {
					Session session = Session.getInstance(props,
							new javax.mail.Authenticator() {
								protected PasswordAuthentication getPasswordAuthentication() {
										return new PasswordAuthentication(username, password);
								}
							});
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(toEmail));
					
					message.setSubject("Hi "+userName);
					message.setText("Dear "+userName+","
						+ "\n\n Wish You Many Many Happy Returns Of The Day!");
		 
					Transport.send(message);
		 
					System.out.println("Done");
					System.out.println("a"+a);
					int fd = 6;
					int gh = 8;
					int c = fd+gh;
					System.out.println(c);
	 
				} //End Try Block
				
				catch (MessagingException e) {
					throw new RuntimeException(e);
					
				}//End Of Catch
				
			}//if today is the users Birthday Block End 
		}//for loop
	}//End Main	
}//End Class
