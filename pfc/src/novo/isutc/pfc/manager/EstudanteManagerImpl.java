package novo.isutc.pfc.manager;



import novo.isutc.pfc.bean.Estudante;
import novo.isutc.pfc.dao.EstudanteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("estudanteManager")
@Transactional(readOnly = true)
public class EstudanteManagerImpl implements EstudanteManager {

    @Autowired
    private EstudanteDao estudanteDao;

    @Override
    public Estudante findByCodigoEstudante(Integer numeropessoa) {
        return estudanteDao.findById(numeropessoa);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveEstudante(Estudante estudante) {
        estudanteDao.saveEstudante(estudante);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteEstudante(Integer numeropessoa) {
        Estudante estudante = estudanteDao.findById(numeropessoa);
        if (estudante != null) {
            estudanteDao.delete(estudante);
        }
    }

    @Override
    public List<Estudante> findEstudantes(Integer numeropessoa) {
         List<Estudante> estudantes = estudanteDao.findEstudantes(numeropessoa);
         return estudantes;
    }

	public void setEstudanteDao(EstudanteDao estudanteDao) {
		this.estudanteDao = estudanteDao;
	}
    
    
}
