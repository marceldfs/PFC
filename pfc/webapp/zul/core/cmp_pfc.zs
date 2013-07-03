import isutc.pfc.cons.Constant;
import isutc.pfc.bean.Supervisor;
import isutc.pfc.bean.Pfc;

pfc = sessionScope.get(Constant.PFC);
docentes = userManager.getDocentsDetails();
userLoggedIn = sessionScope.get(Constant.USER_LOGGED_IN);

save()
{
	
	//pfc.setTitulo(title_txt.value);
	pfc.setDescription(description_txt.value);
	pfc.setNota(nota_txt.value);
	pfc.setSupervisorName(combo_sup.value);
	pfc.setOponente(combo_oponent.value);
	pfc.setPresidente(combo_president.value);	
	pfc.setSituation(situation_list.selectedItem.value);
	pfc.setFase(fase_list.selectedItem.value);
	pfc.setPlagium(plagio_txt.value);
	
	if(isValidJuri(pfc))
	{
		test = pfcManager.updatePfc(pfc);
		if(test != null)
		{
			successMsg.setStyle("color:blue"); successMsg.value = "PFC actualizado com sucesso!";
		}else{ successMsg.setStyle("color:red"); successMsg.value = "PFC, não actualizado";}
	}else{
		successMsg.setStyle("color:red");
		successMsg.value = "Actualização interrompida, reveja o juri!";
	  }
}

private boolean isValidJuri(pfc)
{
	return pfc.getOponente().equalsIgnoreCase(pfc.getSupervisorName()) ||
		   pfc.getOponente().equalsIgnoreCase(pfc.getPresidente())		||
		   pfc.getOponente().equalsIgnoreCase(pfc.getSupervisorName()) ? false : true;
}