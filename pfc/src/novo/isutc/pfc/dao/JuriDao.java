package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Juri;

public interface JuriDao extends AbstractDao<Juri, Integer> {
    void saveJuri(Juri juri);
    List<Juri> findJuris(Integer numero);
    List<Juri> findJurisPosicao(String posicao);
    List<Juri> findJurisCurso(String codigo);
}
