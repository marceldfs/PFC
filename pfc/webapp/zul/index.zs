import novo.isutc.pfc.bean.Exemplar;
import novo.isutc.pfc.bean.Pfc;
pfcs = pfcManager.publishedPfcs();
user = securityUtil.getUserLoggedIn();

openPFC()
{
	Filedownload.save("/zul/TurayLivrateka.pdf", null);
}

login()
{
	Executions.getCurrent().sendRedirect("/zul/login.zul");
}

load()
{
	pfcs=pfcManager.publishedPfcs();
}

showPfc(pfcList)
{
	pfc = pfcList.selectedItem.value;
	exemplar = exemplarManager.findByNumeroPfcLast(pfc.getNumeropfc());				 
    doc = documentUtil.decompressDocument(exemplar.getDocument(),exemplar.getFormato());
    format = "application/pdf";					 
    Filedownload.save(doc, format, pfc.getTema()+"_pfc.pdf");
}