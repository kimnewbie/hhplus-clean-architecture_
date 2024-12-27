package hhplus.clean.architecture.domain.lecture;


import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllLecturesAvailable(int maxCount, LocalDateTime start, LocalDateTime to);

    List<Lecture> findAllByIds(List<Long> ids);

    Lecture findOneByIdWithLock(long lectureId);

    int updateLectureAppliedCount(long id, int applied);
}
