package novo.isutc.pfc.dao;

import novo.isutc.pfc.bean.Juri;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JuriDaoImpl extends AbstractDaoImpl<Juri, Integer> implements JuriDao {

    protected JuriDaoImpl() {
        super(Juri.class);
    }

    @Override
    public void saveJuri(Juri juri) {
        saveOrUpdate(juri);
    }

    @Override
    public List<Juri> findJuris(Integer numero) {
        return findByCriteria(Restrictions.eq("numero", numero));
    }

	@Override
	public List<Juri> findJurisPosicao(String posicao) {
		return findByCriteria(Restrictions.eq("posicao", posicao));
	}

	@Override
	public List<Juri> findJurisCurso(String codigo) {
		return findByCriteria(Restrictions.eq("codigocurso", codigo));
	}   
}
