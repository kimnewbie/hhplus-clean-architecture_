package hhplus.clean.architecture.domain.lectureRegistration;

import hhplus.clean.architecture.domain.lecture.LectureInfo;
import hhplus.clean.architecture.domain.student.StudentInfo;

import java.util.List;

public record LecturesRegisteredByStudent(
        StudentInfo studentInfo,
        List<LectureInfo> lectureInfoList
) {
}
