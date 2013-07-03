package isutc.pfc.renderer;

import isutc.pfc.bean.Tema;

import org.zkoss.zul.Grid;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Listbox;

public class ThemeRenderer implements ListitemRenderer
{

	public void render(Listitem listItem, Object data) throws Exception 
	{
		final Tema tema = (Tema) data;
		listItem.setValue(tema);
		int cont = 1;
		
		Listbox testAPI = new Listbox();
		testAPI.setItemRenderer("");
		Grid gridAPI = new Grid();
		gridAPI.setRowRenderer("");
		
		new Listcell(""+cont++).setParent(listItem); //contador
		new Listcell(tema.getTitulo()).setParent(listItem);//titulo
		new Listcell(""+tema.getNota()).setParent(listItem);//nota
		new Listcell(tema.getData_inicio().toString()).setParent(listItem); //inicio
		new Listcell(tema.getData_fim().toString()).setParent(listItem);//fim
		new Listcell(tema.getDescription()).setParent(listItem);//desc
	}
	
}
