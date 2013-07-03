import isutc.pfc.cons.Constant;
import isutc.pfc.bean.Supervisor;
import isutc.pfc.bean.Tema;

theme = sessionScope.get(Constant.TEMA);
isStudent = USER_LOGGED_IN.hasRole("ESTUDANTES");

docentes = userManager.getDocentsDetails();

roles  = USER_LOGGED_IN.authorities;

save()
{	
	theme.setTitulo(title_txt.value);
	theme.setDescription(description_txt.value);
	theme.setNota(nota_txt.value);
	theme.setSupervisorName(combo_sup.value);
	theme.setSituation(situation_list.selectedItem.value);
	
	
	updatedTheme = temaManager.updateTheme(theme);
	
	if(updatedTheme != null) {	successMsg.setStyle("color:blue");successMsg.value = "Tema actualizado com sucesso"; }
	 else { successMsg.setStyle("color:red"); successMsg.value = "O Tema não foi actualizado";}
	// ....Fazer o que tem que fazer, depois tirar o tema da sessão
	//sessionScope.remove(Constant.TEMA);	
}

teste()
{
	
}