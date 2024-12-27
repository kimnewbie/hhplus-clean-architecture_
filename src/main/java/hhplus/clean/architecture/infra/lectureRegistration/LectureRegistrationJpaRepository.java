package hhplus.clean.architecture.infra.lectureRegistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRegistrationJpaRepository extends JpaRepository<LectureRegistrationEntity, Long> {

    @Query("select lr.lectureId from LectureRegistrationEntity lr where lr.studentId = :studentId")
    List<Long> findAllLectureIdsByStudentId(@Param("studentId") long studentId);

    int countByLectureIdAndStudentId(long lectureId, long studentId);
}
