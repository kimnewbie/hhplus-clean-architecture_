package hhplus.clean.architecture.infra.lecture;

import java.util.List;

import hhplus.clean.architecture.domain.lectureRegistration.LectureRegistrationRepository;
import hhplus.clean.architecture.infra.lectureRegistration.LectureRegistrationEntity;
import hhplus.clean.architecture.infra.lectureRegistration.LectureRegistrationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRegistrationRepository {

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
