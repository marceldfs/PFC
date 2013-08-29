package novo.isutc.pfc.dao;

import novo.isutc.pfc.bean.Exemplar;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExemplarDaoImpl extends AbstractDaoImpl<Exemplar, Integer> implements ExemplarDao {

    protected ExemplarDaoImpl() {
        super(Exemplar.class);
    }

    @Override
    public void saveExemplar(Exemplar exemplar) {
        saveOrUpdate(exemplar);
    }

    @Override
    public List<Exemplar> findExemplares(Integer numero) {
        return findByCriteria(Restrictions.eq("numero", numero));
    }
    
    
    
}
