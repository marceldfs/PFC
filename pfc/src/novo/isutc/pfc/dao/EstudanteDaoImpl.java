package novo.isutc.pfc.dao;

import novo.isutc.pfc.bean.Estudante;
import novo.isutc.pfc.bean.Estudante;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstudanteDaoImpl extends AbstractDaoImpl<Estudante, Integer> implements EstudanteDao {

    protected EstudanteDaoImpl() {
        super(Estudante.class);
    }

    @Override
    public void saveEstudante(Estudante estudante) {
        saveOrUpdate(estudante);
    }

    @Override
    public List<Estudante> findEstudantes(Integer numeropessoa) {
        return findByCriteria(Restrictions.eq("numeropessoa", numeropessoa));
    }
    
}
