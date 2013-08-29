package novo.isutc.pfc.manager;



import java.util.List;

import novo.isutc.pfc.bean.Pessoa;


public interface PessoaManager {

	Pessoa findByCodigoPessoa(Integer numeropessoa);
    void savePessoa(Pessoa pessoa);
    void deletePessoa(Integer numeropessoa);
    List<Pessoa> findPessoas(Integer numeropessoa);
}