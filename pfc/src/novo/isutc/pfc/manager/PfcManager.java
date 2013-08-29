package novo.isutc.pfc.manager;

import java.util.List;

import novo.isutc.pfc.bean.Pfc;

public interface PfcManager {
	Pfc findByNumeroPessoa(Integer numero);
    void savePfc(Pfc pfc);
    void deletePfc(Integer numero);
    List<Pfc> publishedPfcs();
    
}
