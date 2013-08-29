package novo.isutc.pfc.manager;



import novo.isutc.pfc.bean.Juri;
import novo.isutc.pfc.dao.JuriDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("juriManager")
@Transactional(readOnly = true)
public class JuriManagerImpl implements JuriManager {

    @Autowired
    private JuriDao juriDao;

    @Override
    public Juri findByNumero(Integer numero) {
        return juriDao.findById(numero);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveJuri(Juri juri) {
        juriDao.saveJuri(juri);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteJuri(Integer numero) {
        Juri juri = juriDao.findById(numero);
        if (juri != null) {
            juriDao.delete(juri);
        }
    }

    @Override
    public List<Juri> findJuris(Integer numero) {
         List<Juri> juris = juriDao.findJuris(numero);
         return juris;
    }

	public void setJuriDao(JuriDao juriDao) {
		this.juriDao = juriDao;
	}
	
	public List<Juri> findAll()
	{
		List<Juri> juris = juriDao.findAll();
		return juris;
	}

	@Override
	public List<Juri> findJurisPosicao(String posicao) {
		return juriDao.findJurisPosicao(posicao);
	}

	@Override
	public List<Juri> findJurisCurso(String codigo) {
		return juriDao.findJurisCurso(codigo);
	}
    
    
}
