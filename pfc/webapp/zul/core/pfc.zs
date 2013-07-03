import isutc.pfc.renderer.ThemeRenderer;
import isutc.pfc.renderer.IteratorDataSource;
import isutc.pfc.cons.Constant;
import isutc.pfc.bean.Document;
import isutc.pfc.bean.Tema;
import isutc.pfc.bean.Pfc;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

userLoggedIn = sessionScope.get(Constant.USER_LOGGED_IN);

//Para facilitar na defesa, mas para a APP real será finalistas e docentes
isStudant = userLoggedIn.hasRole("ESTUDANTES"); //TODO: Deve ser finalista, ainda não está a aceitar a consulta no LDAP
isTeacher = userLoggedIn.hasRole("PROFESSORES");

//TODO: Criar uma classe dessas constantes (cursos e turmas)
dps = new HashMap();
dps.put("LEIT","Informatica");
dps.put("LECT","Civil");
dps.put("LEIT","Mecânica");
dps.put("LGF","Gestão");
dps.put("ALL","Todos!!!");

showReport()
{	

    //garantir que os PDFs saiem com formato portugues
	Locale.setDefault(new Locale("pt"));

	reportDir = desktop.webApp.getRealPath("/WEB-INF/report");
	format = "application/pdf";	
	
	relatorios = pfcManager.listAllPfc();
	
	Map paramsMap = new HashMap();  //Para conter os parametros a passas ao report
	for(relatorio : relatorios)
	{	
		/*
		 * Alguns parâmetros não pegam, verificar porquê
		 * 
		 */
		paramsMap.put("titulo", relatorio.titulo);
		paramsMap.put("data_inicio", relatorio.data_inicio); //Não pega
		paramsMap.put("data_fim", relatorio.data_fim);//Não pega
		paramsMap.put("nota", relatorio.nota);
		paramsMap.put("situation", relatorio.situation);
		paramsMap.put("supervisor_name", relatorio.supervisorName); //Não pega
		paramsMap.put("fullName", relatorio.fullName);
		paramsMap.put("description", relatorio.description);
	}
	
	//JR data source, popular com collection
	JRDataSource dataSource = new JRBeanCollectionDataSource( relatorios );
	
	//gerar o relatorio
    reportData = JasperRunManager.runReportToPdf(reportDir + "/pfc.jasper", paramsMap, dataSource);
        
    //agora mandar ao browser
    Filedownload.save(reportData, format, "PFC's.pdf");
    
    return reportData;
	
} 	   

count = 0;
curso = sessionScope.get("dep") ;
_pfcs = null;
 
loadPfc()
{
}
	if(curso.equals("ALL"))
	{	
		_pfcs = pfcManager.listAllPfc();
	}
	else
	{
		//temas = temaManager.listThemes(curso);
	}

 
listByCriteria(criteria, value)
{
	//temas = temaManager.listThemeByCriteria(criteria,value);
	//TODO: Reload dos temas(conteúdo do listbox) =>themelist;
	//count = 0;
	//ListModel themes = new SimpleListModel(temas);

	//pfcList.setModel(themes);
	
}

dinamicSearch()
{
	//TODO: Ao escrever, vai sugerindo a palavra/frase
}

//Abrir o documento
openPFC()
{
	pfc = pfcList.selectedItem.value;
	doc = pfc.text;
	format = "application/pdf";					 
	Filedownload.save(doc, format, pfc.getFullName()+"_pfc.pdf");
}

//Apagar o PFC selecioado
deletePfc()
{	
	if(pfcList.selectedItem != null)
	{
		pfc = pfcList.selectedItem.value;
		
		
	//	alert("Botao: "+Messagebox.show("Tem certeza que pretende remover o PFC?", "Apagar PFC",Messagebox.YES | Messagebox.NO,Messagebox.QUESTION));
		/*if (Messagebox.show("Tem certeza que pretende remover o PFC?", "Apagar PFC",
	            Messagebox.YES | Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
		{ */
			pfcManager.deletePfc(pfc);
			count = 0;
			
			allPfc = pfcManager.listAllPfc();
			ListModel pfcs = new SimpleListModel(allPfc);
	
			pfcList.setModel(pfcs);
		//}
	}else Messagebox.show("Não selecionou o PFC pretedido");
}

updatePfc()
{
	sessionScope.remove("pfc");	
	if(pfcList.selectedItem != null)
	{
		pfc = pfcList.selectedItem.value;
		sessionScope.put(Constant.PFC,pfc);		
		showPage("core/cmp_pfc");	
	}else Messagebox.show("Não selecionou o PFC pretedido");
	
}