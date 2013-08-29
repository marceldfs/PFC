package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Exemplar;

public interface ExemplarDao extends AbstractDao<Exemplar, Integer> {
    void saveExemplar(Exemplar exemplar);
    List<Exemplar> findExemplares(Integer numero);
}
