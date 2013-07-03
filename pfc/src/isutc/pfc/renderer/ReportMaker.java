package isutc.pfc.renderer;

import java.io.InputStream;
import java.util.HashMap;

import isutc.pfc.base.DbSpringManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportMaker extends DbSpringManager
{
	public static JasperPrint loadReport(String file) throws JRException
	{
		InputStream is = ReportMaker.class.getResourceAsStream("/webapp/WEB-INF/report/" + file + ".jrxml");
		JasperReport report = JasperCompileManager.compileReport(is);
		JasperPrint print = JasperFillManager.fillReport(is, new HashMap());
		
		return print;
	}
}
