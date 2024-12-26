package hhplus.clean.architecture.domain.lectureRegistration;

import java.util.List;

public interface LectureRegistrationRepository {

    List<Long> findAllLectureIdsByStudentId(long studentId);

    void insert(long lectureId, long studentId);

    int countLectureRegistration(long lectureId, long studentId);
}
