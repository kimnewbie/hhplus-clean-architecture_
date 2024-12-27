package hhplus.clean.architecture.infra.lecture;

import java.time.LocalDateTime;
import java.util.List;

import hhplus.clean.architecture.domain.lecture.Lecture;
import hhplus.clean.architecture.domain.lecture.LectureRepository;
import hhplus.clean.architecture.domain.lectureRegistration.LectureRegistrationRepository;
import hhplus.clean.architecture.infra.lectureRegistration.LectureRegistrationEntity;
import hhplus.clean.architecture.infra.lectureRegistration.LectureRegistrationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository repository;
    
    @Override
    public List<Lecture> findAllLecturesAvailable(int maxCount, LocalDateTime from,
                                                  LocalDateTime end) {
        List<LectureEntity> availableLectureEntities = repository
                .findAllByAppliedCntLessThanAndStartAtBetween(maxCount, from, end);
        return availableLectureEntities.stream().map(Lecture::fromEntity).toList();
    }

    @Override
    public List<Lecture> findAllByIds(List<Long> ids) {
        List<LectureEntity> entities = repository.findAllByIdIn(ids);
        return entities.stream().map(Lecture::fromEntity).toList();
    }

    @Override
    public Lecture findOneByIdWithLock(long lectureId) {
        LectureEntity entity = repository.findOneByIdWithLock(lectureId);
        return Lecture.fromEntity(entity);
    }

    @Override
    public int updateLectureAppliedCount(long id, int applied) {
        return repository.updateAppliedCount(id, applied);
    }
}
