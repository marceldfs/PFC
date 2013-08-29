package novo.isutc.pfc.manager;



import novo.isutc.pfc.bean.Exemplar;
import novo.isutc.pfc.dao.ExemplarDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("exemplarManager")
@Transactional(readOnly = true)
public class ExemplarManagerImpl implements ExemplarManager {

    @Autowired
    private ExemplarDao exemplarDao;

    @Override
    public Exemplar findByNumero(Integer numero) {
        return exemplarDao.findById(numero);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveExemplar(Exemplar exemplar) {
        exemplarDao.saveExemplar(exemplar);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteExemplar(Integer numero) {
        Exemplar exemplar = exemplarDao.findById(numero);
        if (exemplar != null) {
            exemplarDao.delete(exemplar);
        }
    }

    @Override
    public List<Exemplar> findExemplares(Integer numero) {
         List<Exemplar> exemplares = exemplarDao.findExemplares(numero);
         return exemplares;
    }

	public void setExemplarDao(ExemplarDao exemplarDao) {
		this.exemplarDao = exemplarDao;
	}
	
    public Exemplar findByNumeroPfc(Integer numeropfc)
    {
    	List<Exemplar> exemplares = exemplarDao.findAll();
    	for(Exemplar exemplar:exemplares )
    	{
    		if(exemplar.getPfc().getNumeropfc().equals(numeropfc))
    		{
    			return exemplar;
    		}
    	}
    	return null;
    }
    
    public Exemplar findByNumeroPfcLast(Integer numeropfc)
    {
    	List<Exemplar> exemplares = exemplarDao.findAll();
    	for(Exemplar exemplar:exemplares )
    	{
    		if(exemplar.getPfc().getNumeropfc().equals(numeropfc) &&
    				exemplar.getVersao().getNumeroversao()==3)
    		{
    			return exemplar;
    		}
    	}
    	return null;
    }
    
    public List<Exemplar> findExemplares(boolean estado) {
        List<Exemplar> exemplares = exemplarDao.findAll();
        List<Exemplar> exemplaresState = new ArrayList();
        for(Exemplar exemplar: exemplares)
        {
        	if(!estado && !exemplar.isEstado() && exemplar.getVersao().getNumeroversao()==2)
        	{
        		exemplaresState.add(exemplar);
        	}
        	
        	if(estado && !exemplar.isEstado() && exemplar.getVersao().getNumeroversao()==3)
        	{
        		exemplaresState.add(exemplar);
        	}
        }
        return exemplaresState;
   }
    
}
