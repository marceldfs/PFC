package novo.isutc.pfc.manager;



import java.util.List;

import novo.isutc.pfc.bean.Curso;


public interface CursoManager {

	Curso findByCodigoCurso(String codigocurso);
    void saveCurso(Curso curso);
    void deleteCurso(String codigocurso);
    List<Curso> findCursos(String codigocurso);
	Curso findByCodigoParcial(String codigocurso);
	List<Curso> findAll();
}