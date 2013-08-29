package novo.isutc.pfc.manager;



import novo.isutc.pfc.bean.Pessoa;
import novo.isutc.pfc.dao.PessoaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("pessoaManager")
@Transactional(readOnly = true)
public class PessoaManagerImpl implements PessoaManager {

    @Autowired
    private PessoaDao pessoaDao;

    @Override
    public Pessoa findByCodigoPessoa(Integer numeropessoa) {
        return pessoaDao.findById(numeropessoa);
    }

    @Override
    @Transactional(readOnly = false)
    public void savePessoa(Pessoa pessoa) {
        pessoaDao.savePessoa(pessoa);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePessoa(Integer numeropessoa) {
        Pessoa pessoa = pessoaDao.findById(numeropessoa);
        if (pessoa != null) {
            pessoaDao.delete(pessoa);
        }
    }

    @Override
    public List<Pessoa> findPessoas(Integer numeropessoa) {
         List<Pessoa> pessoas = pessoaDao.findPessoas(numeropessoa);
         return pessoas;
    }

	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}
	
    
}
