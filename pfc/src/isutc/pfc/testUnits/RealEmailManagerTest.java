package isutc.pfc.testUnits;

import isutc.pfc.manager.RealEmailManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RealEmailManagerTest 
{
	public static void main(String[] args) 
	{
		ApplicationContext context = new FileSystemXmlApplicationContext("/webapp/WEB-INF/conf/spring-manager-pfc.xml");
			 
		RealEmailManager mailService = (RealEmailManager) context.getBean("emailManager");
			         
			        mailService.sendMail("stelio.zacarias@gmail.com", "stelio.zacarias@isutc.transcom.co.mz", "Testing123", "Testing only \n Hello Spring Email Sender");
			         
			      //  mailService.sendAlertMail("Exception occurred");
	}
}
