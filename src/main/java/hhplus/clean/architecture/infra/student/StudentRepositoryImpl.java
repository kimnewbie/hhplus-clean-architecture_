package hhplus.clean.architecture.infra.student;

import hhplus.clean.architecture.domain.student.StudentInfo;
import hhplus.clean.architecture.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final StudentJpaRepository repository;

    @Override
    public StudentInfo findById(long id) {
        StudentEntity student = repository.findOneById(id);
        return student.toInfo();
    }
}
