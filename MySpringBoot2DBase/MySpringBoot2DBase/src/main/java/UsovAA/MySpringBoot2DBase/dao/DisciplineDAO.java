package UsovAA.MySpringBoot2DBase.dao;

import org.springframework.stereotype.Repository;
import UsovAA.MySpringBoot2DBase.entity.Discipline;

import java.util.List;

@Repository
public interface DisciplineDAO {

    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);

}
