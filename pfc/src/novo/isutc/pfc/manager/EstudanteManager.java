package novo.isutc.pfc.manager;



import java.util.List;

import novo.isutc.pfc.bean.Estudante;


public interface EstudanteManager {

	Estudante findByCodigoEstudante(Integer numeropessoa);
    void saveEstudante(Estudante estudante);
    void deleteEstudante(Integer numeropessoa);
    List<Estudante> findEstudantes(Integer numeropessoa);
}