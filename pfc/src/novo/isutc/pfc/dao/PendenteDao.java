package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Pendente;

public interface PendenteDao extends AbstractDao<Pendente, Integer> {
	void savePendente(Pendente pendente);

	Pendente findByNumeroExemplar(Integer numeroexemplar);
	
	List<Pendente> findAll();
}
