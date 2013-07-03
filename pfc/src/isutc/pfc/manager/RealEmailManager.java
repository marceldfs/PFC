package isutc.pfc.manager;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class RealEmailManager 
{
	private static Logger logger = Logger.getLogger(UserManager.class);
	
	private JavaMailSender mailSender;
	private SimpleMailMessage templateMessage;
	private SimpleMailMessage message;
	
    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }
    
    public void setTemplateMessage(SimpleMailMessage mailTemplate) 
    {
        this.templateMessage = mailTemplate;
    }
    
    public void sendMail(String from, String to, String subject, String body) 
    {
    	     
    		        
        try
        {
        	message = new SimpleMailMessage(this.templateMessage); 
	          
        	message.setFrom(from);
        	message.setTo(to);
        	message.setSubject(subject);
        	message.setText(body);
        	message.setSentDate(new Date());
        	logger.info("Before send"+message.getText());
	        mailSender.send(message);
	        logger.info("Sent");
        	
        } catch (Exception ex) 
        {
        	logger.info("ERROR1: " + ex.getMessage());
        	
            this.templateMessage.setText("ERROR: " + ex.getMessage());
            
            this.mailSender.send(templateMessage);
            logger.info("ERROR2: " + ex.getMessage());
        }
    } 

}
