package novo.isutc.pfc.dao;

import novo.isutc.pfc.bean.Docente;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DocenteDaoImpl extends AbstractDaoImpl<Docente, Integer> implements DocenteDao {

    protected DocenteDaoImpl() {
        super(Docente.class);
    }

    @Override
    public void saveDocente(Docente docente) {
        saveOrUpdate(docente);
    }

    @Override
    public Docente findDocente(Integer numeropessoa) {
        return (Docente) findByCriteria(Restrictions.eq("numeropessoa", numeropessoa)).get(0);
    }    
}
