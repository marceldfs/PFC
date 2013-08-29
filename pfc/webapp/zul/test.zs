import novo.isutc.pfc.bean.Curso;

save()
{
	t = new Curso();
	t.setCodigoCurso(codigoTxt.value);
	t.setDesignacao(designacaoTxt.value);
	cursoManager.saveCurso(t);
}