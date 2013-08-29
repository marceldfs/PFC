package novo.isutc.pfc.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novo.isutc.pfc.bean.Pfc;
import novo.isutc.pfc.dao.PfcDao;

@Service("pfcManager")
@Transactional
public class PfcManagerImpl implements PfcManager {

	private PfcDao pfcDao;
	
	@Override
	public Pfc findByNumeroPessoa(Integer estudante) {
		Pfc pfc = pfcDao.findEstudante(estudante);
		return pfc;
	}

	@Override
	public void savePfc(Pfc pfc) {
		pfcDao.savePfc(pfc);
	}

	@Override
	public void deletePfc(Integer numeropessoa) {
		Pfc pfc = pfcDao.findById(numeropessoa);
        if (pfc != null) {
        	pfcDao.delete(pfc);
        }
      
	}

	public void setPfcDao(PfcDao pfcDao) {
		this.pfcDao = pfcDao;
	}

	@Override
	public List<Pfc> publishedPfcs() {
		List<Pfc> pfcs =  pfcDao.findAll();
		List<Pfc> publishedPfcs = new ArrayList();
		for(Pfc pfc: pfcs)
		{
			if(pfc.isPublicado())
			{
				publishedPfcs.add(pfc);
			}
		}
		return publishedPfcs;
	}

	
	
}
