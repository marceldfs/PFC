package novo.isutc.pfc.manager;



import java.util.List;

import novo.isutc.pfc.bean.Exemplar;


public interface ExemplarManager {

	Exemplar findByNumero(Integer numero);
    void saveExemplar(Exemplar exemplar);
    void deleteExemplar(Integer numero);
    List<Exemplar> findExemplares(Integer numero);
    Exemplar findByNumeroPfc(Integer numero);
    Exemplar findByNumeroPfcLast(Integer numero);
    List<Exemplar> findExemplares(boolean state);
}