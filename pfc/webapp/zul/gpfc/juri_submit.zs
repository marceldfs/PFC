import novo.isutc.pfc.bean.Docente;
import novo.isutc.pfc.bean.Juri;
import novo.isutc.pfc.bean.Curso;
import novo.isutc.pfc.bean.Curso;

carregar()
{
	juri.visible=true;
	exemplar = exemplarlist.selectedItem.value;
	if(exemplar != null)
	{
		supervisor= exemplar.getPfc().getSupervisor();
		supervisorNome.value = supervisor.getNome();
		supervisorNome.disabled = true;
		supervisorEmail.value = supervisor.getEmail();
		supervisorEmail.disabled = true;
	}
}

saveJuri()
{
	exemplar = exemplarlist.selectedItem.value;
	if(exemplar != null)
	{
	
		curso = exemplar.getPfc().getEstudante().getCurso();
	
		oponente = new Docente(oponenteNome.value,oponenteEmail.value);
		docenteManager.saveDocente(oponente);
	
		presidente = new Docente(presidenteNome.value,presidenteEmail.value);
		docenteManager.saveDocente(presidente);
	
		juriOponente = new Juri("oponente",curso,oponente);
		juriManager.saveJuri(juriOponente);
		
		juriPresidente = new Juri("presidente",curso,presidente);
		juriManager.saveJuri(juriPresidente);
		
		pfc = exemplar.getPfc();
		pfc.setOponente(oponente);
		pfc.setPresidente(presidente);
		pfcManager.savePfc(pfc);
		
		exemplar.setEstado(true);
		exemplarManager.saveExemplar(exemplar);
	}
}