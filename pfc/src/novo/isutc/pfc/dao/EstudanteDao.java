package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Estudante;

public interface EstudanteDao extends AbstractDao<Estudante, Integer> {
    void saveEstudante(Estudante curso);
    List<Estudante> findEstudantes(Integer numeropessoa);
}
