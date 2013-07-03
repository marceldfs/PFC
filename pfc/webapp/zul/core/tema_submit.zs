validate()
{
	
}

correct()
{
	
}

confirm()
{
	
}

cancel()
{
	
}



confirmUpdate(title,description,supervisor)
{
	//	TODO:Deve pegar o User logado
	studant = userLoggedIn;
	 if((supervisor == null) || supervisor.trim().equals("") )
	 {
		 propostaSup = "-";
	 }else propostaSup = supervisor;
	
	
	if(studant!=null)
	{
		tema = temaManager.submitTheme(studant,title,description,propostaSup);
		if(tema != null)
		{	
			successMsg.setStyle("color:blue");
			successMsg.value = "Tema submetido com sucesso"; 
			emailManager.sendMail("stelio.zacarias@gmail.com", "stelio.zacarias@isutc.transcom.co.mz;stelio.zacarias@sapo.mz;stelio.zacarias@movitel.co.mz","Submissão de Tema", 
					              "Tema submetido com sucesso \n Aluno: "+studant.fullName
					              +"\n Titulo: "+title
					              +"\n Proposta de Supervisor: "+propostaSup
					              +"\n Descrição: "+description					              
					             );
		}else 
		 { 
			successMsg.setStyle("color:red"); 
			successMsg.value = "O estudante já submeteu um tema";
		 }
	}
}
