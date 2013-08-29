package novo.isutc.pfc.dao;

import novo.isutc.pfc.bean.Versao;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VersaoDaoImpl extends AbstractDaoImpl<Versao, Integer> implements VersaoDao {

    protected VersaoDaoImpl() {
        super(Versao.class);
    }

    @Override
    public void saveVersao(Versao versao) {
        saveOrUpdate(versao);
    }

    @Override
    public List<Versao> findVersaos(Integer numeroversao) {
        return findByCriteria(Restrictions.eq("numeroversao", numeroversao));
    }
    
    
    
}
