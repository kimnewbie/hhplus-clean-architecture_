package hhplus.clean.architecture.domain.lectureRegistration;

import hhplus.clean.architecture.domain.lecture.LectureInfo;
import hhplus.clean.architecture.domain.student.StudentInfo;

public record RegistrationCompleted(
        StudentInfo studentInfo,
        LectureInfo lectureInfo
) {
}
