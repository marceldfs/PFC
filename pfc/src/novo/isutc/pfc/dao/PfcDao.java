package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Pfc;

public interface PfcDao extends AbstractDao<Pfc, Integer> {
	void savePfc(Pfc pfc);

	Pfc findEstudante(Integer numeropessoa);
	
	List<Pfc> findAll();
}
