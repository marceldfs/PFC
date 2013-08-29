package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Pendente;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class PendenteDaoImpl extends AbstractDaoImpl<Pendente, Integer> implements PendenteDao {

    protected PendenteDaoImpl() {
        super(Pendente.class);
    }

    @Override
    public void savePendente(Pendente pendente) {
        saveOrUpdate(pendente);
    }

    @Override
    public Pendente findByNumeroExemplar(Integer numeroexemplar) {
        List<Pendente> pendentes = this.findAll();
        for(Pendente pendente: pendentes)
        {
        	if(pendente.getNumero().equals(numeroexemplar))
        	{
        		return pendente;
        	}
        }
        return null;
    }  
    
    public List<Pendente> findAll()
    {
    	return  getCurrentSession().createCriteria(Pendente.class).list();
    }
    
    
}
