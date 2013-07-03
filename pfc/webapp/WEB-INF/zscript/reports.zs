import org.zkoss.util.media.AMedia;
import net.sf.jasperreports.engine.JasperRunManager;
import java.util.Locale;

//garantir que os PDFs saiem com formato portugues
Locale.setDefault(new Locale("pt"));

reportDir = desktop.webApp.getRealPath("/WEB-INF/report");
format = "application/pdf";

//gerar o relatorio em PDF e botar no session e devolver os dados binarios dele (aceitando dataSource)
 showReport(report, title, params, dataSource)
 {         
     //gerar o relatorio
     reportData = JasperRunManager.runReportToPdf(reportDir + "/" + report + ".jasper", params, dataSource);
         
     //agora mandar ao browser - como depende do formato do relatorio
     Filedownload.save(reportData, format, title + ".pdf");
     
     return reportData;
 }