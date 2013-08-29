package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Pfc;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class PfcDaoImpl extends AbstractDaoImpl<Pfc, Integer> implements PfcDao {

    protected PfcDaoImpl() {
        super(Pfc.class);
    }

    @Override
    public void savePfc(Pfc pfc) {
        saveOrUpdate(pfc);
    }

    @Override
    public Pfc findEstudante(Integer estudante) {
        List<Pfc> pfcs = this.findAll();
        for(Pfc pfc: pfcs)
        {
        	if(pfc.getEstudante().getNumeropessoa().equals(estudante))
        	{
        		return pfc;
        	}
        }
        return null;
    }  
    
    public List<Pfc> findAll()
    {
    	return  getCurrentSession().createCriteria(Pfc.class).list();
    }
    
    
}
