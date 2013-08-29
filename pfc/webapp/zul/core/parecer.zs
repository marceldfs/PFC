import isutc.pfc.bean.Document;
import isutc.pfc.bean.Pfc;

pfc = new Pfc();

recordTextPath()
{
	
	successMsg.value = "";
	if(descriptionBox.value != null && !descriptionBox.value.equals(""))
	{
		pfc.textStream = event.media.streamData;    //a unica maneira segura de pegar os dados se forem grandes
		pfc.format = event.media.contentType; 		// formato a ser inserido na base de dados.
		pfc.description = descriptionBox.value;
	}else{successMsg.setStyle("color:red"); successMsg.value = "Nao preecheu a descriçao, é obrigatório";}
}

save()
{	
	alert(pfc);
	if(pfc.format.endsWith("pdf"))
	{
		alert(userLoggedIn);
		submiter = pfcManager.submitPfc(userLoggedIn,pfc);	
		if(submiter != null)
		{
			successMsg.setStyle("color:blue");
			successMsg.value = "O PFC foi submetido com sucesso";
		}else {successMsg.setStyle("color:red"); successMsg.value = "Submissão mal sucedida, o estudante já possue um PFC submetido ou Tema não Aprovado";}
	}else{ successMsg.setStyle("color:red"); successMsg.value = "Submissão mal sucedida, o formato do documento deve ser PDF";}
}