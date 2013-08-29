package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Docente;

public interface DocenteDao extends AbstractDao<Docente, Integer> {
	void saveDocente(Docente docente);

	Docente findDocente(Integer numeropessoa);
	
	List<Docente> findAll();
}
