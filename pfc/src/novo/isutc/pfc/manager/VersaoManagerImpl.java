package novo.isutc.pfc.manager;



import novo.isutc.pfc.bean.Versao;
import novo.isutc.pfc.dao.VersaoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("versaoManager")
@Transactional(readOnly = true)
public class VersaoManagerImpl implements VersaoManager {

    @Autowired
    private VersaoDao versaoDao;

    @Override
    public Versao findByNumeroversao(Integer numeroversao) {
        return versaoDao.findById(numeroversao);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveVersao(Versao versao) {
        versaoDao.saveVersao(versao);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteVersao(Integer numeroversao) {
        Versao versao = versaoDao.findById(numeroversao);
        if (versao != null) {
            versaoDao.delete(versao);
        }
    }

    @Override
    public List<Versao> findVersaos(Integer numeroversao) {
         List<Versao> versaos = versaoDao.findVersaos(numeroversao);
         return versaos;
    }

	public void setVersaoDao(VersaoDao versaoDao) {
		this.versaoDao = versaoDao;
	}
	
	public List<Versao> findAll()
	{
		List<Versao> versaos = versaoDao.findAll();
		return versaos;
	}
    
    
}
