package novo.isutc.pfc.manager;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class RealEmailManager 
{
	private static Logger logger = Logger.getLogger(RealEmailManager.class);
	
	private JavaMailSender mailSender;
	private SimpleMailMessage message;
	private String secEmail;
	private String gpfcEmail;
	private String adminEmail;
	
    public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public void setSecEmail(String secEmail) {
		this.secEmail = secEmail;
	}

	public void setGpfcEmail(String gpfcEmail) {
		this.gpfcEmail = gpfcEmail;
	}

	public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }
	
	public void notifySec(String subject, String body) 
    {
    	new MailThread(adminEmail,secEmail,subject,body).run();
    }
	
	public void notifyGpfc(String subject, String body) 
    {
    	new MailThread(adminEmail,gpfcEmail,subject,body).run();
    }
	
	public void notifyStudentJuri(String to, String subject, String body, String fileName, byte[] attachment) 
    {
		MailAttachmentThread mailThread = new MailAttachmentThread(adminEmail,to,subject,body,fileName,attachment);
    	mailThread.run();
    }
    
    public class MailThread extends Thread {

    	private String from;
    	private String to;
    	private String subject;
    	private String body;
    	
    	public MailThread(String from, String to, String subject, String body) {
			this.from = from;
			this.to = to;
			this.subject = subject;
			this.body = body;
		}
    	    	
        public void run() {
    		try
    		{
    			message = new SimpleMailMessage(); 
	          
    			message.setFrom(from);
    			message.setTo(to);
    			message.setSubject(subject);
    			message.setText(body);
    			message.setSentDate(new Date());
    			logger.info("Before send"+message.getText());
    			mailSender.send(message);
    			logger.info("Sent");
    			this.stop();
        	
    		} catch (Exception ex) 
    		{
    			logger.info("ERROR1: " + ex.getMessage());
        	
    		}
        }
    }
    
    public class MailAttachmentThread extends Thread {

    	private String from;
    	private String to;
    	private String subject;
    	private String body;
    	private String fileName;
    	private byte[] attachment;
    	
    	public MailAttachmentThread(String from, String to, String subject, String body, String fileName, byte[] attachment) {
			this.from = from;
			this.to = to;
			this.subject = subject;
			this.body = body;
			this.fileName = fileName;
			this.attachment = attachment;
		}
    	    	
        public void run() {
    		try
    		{
    			JavaMailSenderImpl sender = new JavaMailSenderImpl();
    	    	sender.setHost(((JavaMailSenderImpl) mailSender).getHost());

    	    	MimeMessage mimeMessage = mailSender.createMimeMessage();

    	    	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    	    	
    	    	helper.setFrom(adminEmail);
    	    	helper.setTo(to);
    	    	helper.setSubject(subject);
    	    	helper.setText(body);

    	    	//usar o source para byteArrays, que vai ser o nosso ficheiro PDF
    	    	ByteArrayResource file = new ByteArrayResource(attachment);
    	    	
    	    	//anexar o ficheiro attach a messagem e enviar
    	    	helper.addAttachment(fileName,  file);

    	    	mailSender.send(mimeMessage);
    	    	logger.info("Sent");
    	    	this.stop();
        	
    		} catch (Exception ex) 
    		{
    			logger.info("ERROR1: " + ex.getMessage());
        	
    		}
        }
    }

}