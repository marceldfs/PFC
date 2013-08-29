package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Pessoa;

public interface PessoaDao extends AbstractDao<Pessoa, Integer> {
    void savePessoa(Pessoa pessoa);
    List<Pessoa> findPessoas(Integer numeropessoa);
}
