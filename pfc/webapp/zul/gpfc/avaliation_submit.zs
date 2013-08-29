formato = null;
document = null;

recordTextPath()
{
	
	document = event.media.streamData;    //a unica maneira segura de pegar os dados se forem grandes
	formato = event.media.contentType; 		// formato a ser inserido na base de dados.
}

save()
{
	exemplar = exemplarlist.selectedItem.value;
	if(exemplar != null)
	{
		exemplar.setEstado(true);
		exemplarManager.saveExemplar(exemplar);
		
		emailSubject = "Parecer do DPG";
		emailBody = "Caro "+exemplar.getPfc().getEstudante().getPessoa().getNome()+" encontre em anexo o parecer do DPG sobre o seu marco-teorico.";
		compressData = documentUtil.compressDocument(document,formato);
		attachment = documentUtil.decompressDocument(compressData,formato);
		to = exemplar.getPfc().getEstudante().getPessoa().getEmail();
		emailManager.notifyStudentJuri(to,emailSubject,emailBody,"Parecer DPG.pdf", attachment);
	}
}