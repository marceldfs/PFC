package novo.isutc.pfc.manager;



import novo.isutc.pfc.bean.Curso;
import novo.isutc.pfc.dao.CursoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cursoManager")
@Transactional(readOnly = true)
public class CursoManagerImpl implements CursoManager {

    @Autowired
    private CursoDao cursoDao;

    @Override
    public Curso findByCodigoCurso(String codigocurso) {
        return cursoDao.findById(codigocurso);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveCurso(Curso curso) {
        cursoDao.saveCurso(curso);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCurso(String codigocurso) {
        Curso curso = cursoDao.findById(codigocurso);
        if (curso != null) {
            cursoDao.delete(curso);
        }
    }

    @Override
    public List<Curso> findCursos(String codigocurso) {
         List<Curso> cursos = cursoDao.findCursos(codigocurso);
         return cursos;
    }

	public void setCursoDao(CursoDao cursoDao) {
		this.cursoDao = cursoDao;
	}
	
	public List<Curso> findAll()
	{
		List<Curso> cursos = cursoDao.findAll();
		return cursos;
	}
	
    
    @Override
    public Curso findByCodigoParcial(String codigocurso) {
    	List<Curso> cursos = findAll();
        for(Curso curso: cursos )
        {
        	if(curso.getCodigoCurso().contains(codigocurso))
        	{
        		return curso;
        	}
        }
        return null;
    }
    
    
}
