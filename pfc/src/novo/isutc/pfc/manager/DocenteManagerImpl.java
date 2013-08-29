package novo.isutc.pfc.manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novo.isutc.pfc.bean.Curso;
import novo.isutc.pfc.bean.Docente;
import novo.isutc.pfc.dao.DocenteDao;

@Service("docenteManager")
@Transactional(readOnly = false)
public class DocenteManagerImpl implements DocenteManager {

	private DocenteDao docenteDao;
	
	@Override
	public Docente findByNumero(Integer numero) {
		return docenteDao.findById(numero);
	}

	@Override
	public void saveDocente(Docente docente) {
		docenteDao.saveDocente(docente);
	}

	@Override
	public void deleteDocente(Integer numeropessoa) {
		Docente docente = docenteDao.findById(numeropessoa);
        if (docente != null) {
        	docenteDao.delete(docente);
        }
      
	}

	@Override
	public List<Docente> findAll() {
		return docenteDao.findAll();
	}
	
	public int numberDocentes()
	{
		return docenteDao.findAll().size();
	}

	public void setDocenteDao(DocenteDao docenteDao) {
		this.docenteDao = docenteDao;
	}
	
	

}
