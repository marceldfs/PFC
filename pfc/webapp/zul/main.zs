import isutc.pfc.cons.Constant;
import isutc.pfc.security.SecurityHelper;

// current user logged in
sessionScope.put(Constant.USER_LOGGED_IN, SecurityHelper.getUser());
userLoggedIn = sessionScope.get(Constant.USER_LOGGED_IN);

//Para facilitar na defesa, mas para a APP real será finalistas e docentes
isStudant = userLoggedIn.hasRole("ESTUDANTES"); //TODO: Deve ser finalista, ainda não está a aceitar a consulta no LDAP
isTeacher = userLoggedIn.hasRole("PROFESSORES");//TODO: Deve ser Docente DPG, ainda não está a aceitar a consulta no LDAP

showPage(tela)
{
	rootPage = "/zul/";
	body = desktopScope.get("body");
	body.children.clear();
	novaTela = new Include(); 
	novaTela.setSrc(rootPage+""+tela+".zul");
	body.appendChild(novaTela);
}

dps = new HashMap();
dps.put("LEIT","Departamento de Engenheria Informatica e de Telecomunicações");
dps.put("LECT","Civil");
dps.put("LEMT","Mecânica");
dps.put("LGF","Gestão");


logout() 
{
  session.invalidate();
  Executions.getCurrent().sendRedirect("/");
 }