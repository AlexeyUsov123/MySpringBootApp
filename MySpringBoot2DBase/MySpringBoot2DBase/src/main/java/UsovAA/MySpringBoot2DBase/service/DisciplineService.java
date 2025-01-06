package UsovAA.MySpringBoot2DBase.service;

import org.springframework.stereotype.Service;
import UsovAA.MySpringBoot2DBase.entity.Discipline;
import java.util.List;

@Service
public interface DisciplineService {

    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);
}
