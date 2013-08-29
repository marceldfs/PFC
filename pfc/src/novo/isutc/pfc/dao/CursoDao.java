package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Curso;

public interface CursoDao extends AbstractDao<Curso, String> {
    void saveCurso(Curso curso);
    List<Curso> findCursos(String codigocurso);
}
