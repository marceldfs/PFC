import isutc.pfc.renderer.ThemeRenderer;
import isutc.pfc.cons.Constant;
import isutc.pfc.bean.Tema;
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



count = 0;
curso = sessionScope.get("dep") ;
temas = null;
 
loadTema()
{
}
	if(curso.equals("ALL"))
	{	
		temas = temaManager.listAllThemes();
	}
	else
	{
		temas = temaManager.listThemes(curso);
	}

 
listByCriteria(criteria, value)
{
	temas = temaManager.listThemeByCriteria(criteria,value);
	//TODO: Reload dos temas(conteúdo do listbox) =>themelist;
	count = 0;
	ListModel themes = new SimpleListModel(temas);

	themelist.setModel(themes);
	
}

dinamicSearch()
{
	//TODO: Ao escrever, vai sugerindo a palavra/frase
}

//Apagar o tema selecioado
deleteTheme()
{	
	if(themelist.selectedItem != null)
	{
		theme = themelist.selectedItem.value;
	/*if (Messagebox.show("Tem certeza que pretende remover o Tema?", "Apagar Tema",
            Messagebox.YES | Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES)
	{ */
		temaManager.deleteTheme(theme);
		
		temas = temaManager.listAllThemes();
		ListModel themes = new SimpleListModel(temas);

		themelist.setModel(themes);
	//}
	}else Messagebox.show("Não selecionou o tema pretedido");
}

updateTheme()
{
	sessionScope.remove("theme");	
	if(themelist.selectedItem != null)
	{
		theme = themelist.selectedItem.value;
		sessionScope.put(Constant.TEMA,theme);		
		showPage("core/cmp_tema");	
	}else Messagebox.show("Não selecionou o tema pretedido");
	
}

showReport()
{	

    //garantir que os PDFs saiem com formato portugues
	Locale.setDefault(new Locale("pt"));

	reportDir = desktop.webApp.getRealPath("/WEB-INF/report");
	format = "application/pdf";	
	
	relatorios = temaManager.listAllThemes();
	
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
    reportData = JasperRunManager.runReportToPdf(reportDir + "/temas.jasper", paramsMap, dataSource);
        
    //agora mandar ao browser
    Filedownload.save(reportData, format, "Tema's.pdf");
    
    return reportData;
	
} 