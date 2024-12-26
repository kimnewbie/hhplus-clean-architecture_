package hhplus.clean.architecture.infra.lectureRegistration;

import hhplus.clean.architecture.domain.lectureRegistration.LectureRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRegistrationRepositoryImpl implements LectureRegistrationRepository {

    private final LectureRegistrationJpaRepository repository;

    @Override
    public List<Long> findAllLectureIdsByStudentId(long studentId) {
        return repository.findAllLectureIdsByStudentId(studentId);
    }

    @Override
    public void insert(long lectureId, long studentId) {
        LectureRegistrationEntity entity = LectureRegistrationEntity.builder()
                .lectureId(lectureId)
                .studentId(studentId)
                .build();

        repository.save(entity);
    }

    @Override
    public int countLectureRegistration(long lectureId, long studentId) {
        return repository.countByLectureIdAndStudentId(lectureId, studentId);
    }
}
