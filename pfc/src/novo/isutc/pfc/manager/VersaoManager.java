package novo.isutc.pfc.manager;



import java.util.List;

import novo.isutc.pfc.bean.Versao;


public interface VersaoManager {

	Versao findByNumeroversao(Integer numeroversao);
    void saveVersao(Versao versao);
    void deleteVersao(Integer numeroversao);
    List<Versao> findVersaos(Integer numeroversao);
	
}