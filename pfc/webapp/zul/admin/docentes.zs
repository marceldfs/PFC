import isutc.pfc.bean.Supervisor;
import isutc.pfc.renderer.ThemeRenderer;

count = 0;

docentes = userManager.getDocentsDetails();
alert(docentes);

listar()
{
	//Listar Docentes
	docentes = userManager.getDocentsDetails();
	
	ListModel teachers = new SimpleListModel(docentes);
	docenteList.setModel(teachers);
}