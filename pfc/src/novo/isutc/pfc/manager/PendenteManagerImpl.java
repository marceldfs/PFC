package novo.isutc.pfc.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novo.isutc.pfc.bean.Pendente;
import novo.isutc.pfc.dao.PendenteDao;

@Service("pendenteManager")
@Transactional
public class PendenteManagerImpl implements PendenteManager {

	private PendenteDao pendenteDao;
	
	@Override
	public Pendente findByNumeroExemplar(Integer estudante) {
		Pendente pendente = pendenteDao.findByNumeroExemplar(estudante);
		return pendente;
	}

	@Override
	public void savePendente(Pendente pendente) {
		pendenteDao.savePendente(pendente);
	}

	@Override
	public void deletePendente(Integer numero) {
		Pendente pendente = pendenteDao.findById(numero);
        if (pendente != null) {
        	pendenteDao.delete(pendente);
        }     
	}
	
	public List<Pendente> findAll()
	{
		List<Pendente> pendentes = pendenteDao.findAll();
		return pendentes;
	}

	public void setPendenteDao(PendenteDao pendenteDao) {
		this.pendenteDao = pendenteDao;
	}
	
	
}
