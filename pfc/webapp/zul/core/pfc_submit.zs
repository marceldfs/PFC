
import novo.isutc.pfc.bean.Pessoa;
import novo.isutc.pfc.bean.Estudante;
import novo.isutc.pfc.bean.Pfc;
import novo.isutc.pfc.bean.Exemplar;
import novo.isutc.pfc.bean.Docente;
import novo.isutc.pfc.bean.Juri;
import novo.isutc.pfc.bean.Pendente;

versao = sessionScope.get("versao");
tituloMsg = "Submeter "+versao;
titulo.label = "Submeter "+versao;
user = securityUtil.getUserLoggedIn();
pfc = null;

inicial()
{
	titulo.label = "Submeter "+versao;
	if(versao.equals("Marco-teorico"))
	{
		title.disabled=true;
		nome.disabled=true;
		email.disabled=true;
		pfc = pfcManager.findByNumeroPessoa(user.getStudentNumber());
		supervisor=docenteManager.findByNumero(pfc.getSupervisor().getNumeropessoa());
		title.value=pfc.getTema();
		nome.value=supervisor.getNome();
		email.value=supervisor.getEmail();
	}
	if(versao.equals("Projecto final"))
	{
		title.disabled=true;
		nome.disabled=true;
		email.disabled=true;
		pfc = pfcManager.findByNumeroPessoa(user.getStudentNumber());
		supervisor=docenteManager.findByNumero(pfc.getSupervisor().getNumeropessoa());
		title.value=pfc.getTema();
		nome.value=supervisor.getNome();
		email.value=supervisor.getEmail();
	}

}

formato = null;
document = null;

recordTextPath()
{
	
	document = event.media.streamData;    //a unica maneira segura de pegar os dados se forem grandes
	formato = event.media.contentType; 		// formato a ser inserido na base de dados.
}

save()
{	
	if(formato.endsWith("pdf"))
	{
		emailSubject ="";
		emailBody = "";
		if(versao.equals("Pre-projecto"))
		{
			curso = cursoManager.findByCodigoParcial(user.getClassId().substring(0, 1));
			pessoa = new Pessoa(user.getStudentNumber() , user.getFullName() ,user.getEmail());
			pessoaManager.savePessoa(pessoa);
			
			estudante = new Estudante(curso,pessoa);
			estudanteManager.saveEstudante(estudante);
			
			docente = new Docente(nome.value,email.value);
			docenteManager.saveDocente(docente);
			
			juri = new Juri("supervisor",curso,docente);
			juriManager.saveJuri(juri);
			
			tema = title.value;
			pfc = new Pfc(tema,estudante,docente);
			pfcManager.savePfc(pfc);
			
			versaoDB = versaoManager.findByNumeroversao(new Integer(1));
			
			compressData = documentUtil.compressDocument(document,formato);
			exemplar = new Exemplar(pfc,versaoDB,compressData,formato);
			exemplarManager.saveExemplar(exemplar);
			
			pendente = new Pendente(exemplar);
			pendente.setRegularizada(true);
			pendenteManager.savePendente(pendente);
			
			emailSubject = versaoDB.getNome()+" submetido";
			emailBody = "Caro membro da secretária o estudante "+user.getFullName()+" submeteu o pre-projecto do seu projecto final de curso com tema: "+pfc.getTema();
			
		}
		
		if(versao.equals("Marco-teorico"))
		{
			pfc = pfcManager.findByNumeroPessoa(user.getStudentNumber());
			
			versaoDB = versaoManager.findByNumeroversao(new Integer(2));
			
			compressData = documentUtil.compressDocument(document,formato);
			
			exemplar = new Exemplar(pfc,versaoDB,compressData,formato);
			exemplarManager.saveExemplar(exemplar);
			
			pendente = new Pendente(exemplar);
			pendente.setRegularizada(true);
			pendenteManager.savePendente(pendente);
			
			emailSubject = versaoDB.getNome()+" submetido";
			emailBody = "Caro membro da secretária o estudante "+user.getFullName()+" submeteu o marco-teorico do seu projecto final de curso com tema: "+pfc.getTema();
		}
		
		if(versao.equals("Projecto final"))
		{
			pfc = pfcManager.findByNumeroPessoa(user.getStudentNumber());
			
			versaoDB = versaoManager.findByNumeroversao(new Integer(3));
			
			compressData = documentUtil.compressDocument(document,formato);
			
			exemplar = new Exemplar(pfc,versaoDB,compressData,formato);
			exemplarManager.saveExemplar(exemplar);
			
			pendente = new Pendente(exemplar);
			pendente.setRegularizada(true);
			pendenteManager.savePendente(pendente);
			
			emailSubject = versaoDB.getNome()+" submetido";
			emailBody = "Caro membro da secretária o estudante "+user.getFullName()+" submeteu o projecto final do seu projecto final de curso com tema: "+pfc.getTema();
		}
		emailManager.notifySec(emailSubject,emailBody);
		alert("Versao do Pfc submetida com sucesso");
			
	}
	else
	{ 
		successMsg.setStyle("color:red"); successMsg.value = "Submissao mal sucedida, o formato do documento deve ser PDF";
	}
}