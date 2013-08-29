package novo.isutc.pfc.manager;



import java.util.List;

import novo.isutc.pfc.bean.Juri;


public interface JuriManager {

	Juri findByNumero(Integer numero);
    void saveJuri(Juri juri);
    void deleteJuri(Integer numero);
    List<Juri> findJuris(Integer numero);
    List<Juri> findJurisPosicao(String posicao);
    List<Juri> findJurisCurso(String codigo);
	
}