package novo.isutc.pfc.manager;

import java.util.List;

import novo.isutc.pfc.bean.Docente;

public interface DocenteManager {
	Docente findByNumero(Integer numero);
    void saveDocente(Docente docente);
    void deleteDocente(Integer numeropessoa);
    List<Docente> findAll();
    int numberDocentes();
}
