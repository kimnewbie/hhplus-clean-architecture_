package hhplus.clean.architecture.domain.student;

import hhplus.clean.architecture.infra.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository {

    StudentInfo findById(long id);
}
