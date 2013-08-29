package novo.isutc.pfc.dao;

import novo.isutc.pfc.bean.Pessoa;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaDaoImpl extends AbstractDaoImpl<Pessoa, Integer> implements PessoaDao {

    protected PessoaDaoImpl() {
        super(Pessoa.class);
    }

    @Override
    public void savePessoa(Pessoa pessoa) {
        saveOrUpdate(pessoa);
    }

    @Override
    public List<Pessoa> findPessoas(Integer numeropessoa) {
        return findByCriteria(Restrictions.like("numeropessoa", numeropessoa));
    }
    
    
    
}
