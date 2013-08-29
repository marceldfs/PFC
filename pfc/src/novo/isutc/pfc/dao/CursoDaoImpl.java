package novo.isutc.pfc.dao;

import novo.isutc.pfc.bean.Curso;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CursoDaoImpl extends AbstractDaoImpl<Curso, String> implements CursoDao {

    protected CursoDaoImpl() {
        super(Curso.class);
    }

    @Override
    public void saveCurso(Curso curso) {
        saveOrUpdate(curso);
    }

    @Override
    public List<Curso> findCursos(String codigocurso) {
        return findByCriteria(Restrictions.like("codigocurso", codigocurso));
    }
    
    
    
}
