package hhplus.clean.architecture.domain.lecture;

import hhplus.clean.architecture.api.lecture.CommonLectureResponse;

import java.time.LocalDateTime;

public record LectureInfo(
        long id,
        String title,
        String instructor,
        LocalDateTime lectureDate,
        int availableSeat
) {
    public static LectureInfo fromDomain(Lecture lecture) {
        return new LectureInfo(lecture.id(), lecture.title(), lecture.instructor(),
                lecture.lectureDate(), lecture.availableSeat());
    }

    public CommonLectureResponse toCommonResponse() {
        return new CommonLectureResponse(id, title, instructor, lectureDate);
    }

}
