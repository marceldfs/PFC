//context = desktop.execution.getContextPath();
back()
{
	session.invalidate();
	Executions.getCurrent().sendRedirect("/");//TODO: máximo de tentativas
}
		
	 