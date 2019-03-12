package email;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;


public class Email {

	public static Boolean sendEmail(String mailId, String subject, String content, File fileAttachment,HttpServletRequest req , HttpSession session,String name)
            throws AddressException, MessagingException, Exception {

 	  session=req.getSession();
 	  
 	  /*final String username="javamustak@gmail.com";
 	   final String password="Mustak123";*/
 	/* final String username = (String)session.getAttribute("usern");
 	final String password = (String)session.getAttribute("upass");*/
 	  
 	 final String username = req.getParameter("userId");
  	final String password = req.getParameter("userPassword");
		/*String username = req.getParameter("userName");
	
		String password = req.getParameter("password");
	*/
		
 	   //String fileAttachment = "C:\\Users\\Irshad\\Desktop\\file.txt";
 	   String host ="smtp.gmail.com" ;
 	   String port = "587";
 	   String mailFrom = username;
 	   Properties properties = System.getProperties();
 	   properties.put("mail.smtp.host", host);
 	   properties.put("mail.smtp.port", port);
 	   properties.put("mail.smtp.auth", "true");
 	   properties.put("mail.smtp.starttls.enable", "true");
 	   properties.put("mail.user", mailFrom);
 	   properties.put("mail.password", password);

 	      Session sess = Session.getInstance(properties,
 	    	         new javax.mail.Authenticator() {
 	    	            protected PasswordAuthentication getPasswordAuthentication() {
 	    	               return new PasswordAuthentication(username, password);
 	    	            }
 	    	         });
        // creates a new e-mail message
        Message msg = new MimeMessage(sess);

        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddresses = { new InternetAddress(mailId) };
    
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        
        String message1 = "<html><head><body><div style=\"font-size: 14px;font-family: arial;color: black;\">Hi "+name+",<br>"+content+"<br><br>" +
        		"</div></body></html>";
        
        
        //String htmlText = message1;
        //msg.setContent(htmlText, "text/html");
        
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = message1;
        messageBodyPart.setContent(htmlText, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
                    /* MimeBodyPart attachPart = new MimeBodyPart();

                try {
                attachPart.attachFile(fileAttachment);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);*/
        
//        if (fileAttachment != null && fileAttachment.length > 0) {
//            for (File filePath : fileAttachment) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(fileAttachment);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
//            }
//        }
                msg.setContent(multipart);

        Transport.send(msg);
        
        return true;

            }
	
	
	

}
