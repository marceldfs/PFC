package novo.isutc.pfc.manager;

import java.util.List;

import novo.isutc.pfc.bean.Pendente;

public interface PendenteManager {
	Pendente findByNumeroExemplar(Integer numero);
    void savePendente(Pendente pendente);
    void deletePendente(Integer numero);
    List<Pendente> findAll();
}
