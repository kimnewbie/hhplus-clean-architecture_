package hhplus.clean.architecture.domain.lecture;

import hhplus.clean.architecture.infra.lecture.LectureEntity;

import java.time.LocalDateTime;

public record Lecture(
        long id,
        String title,
        String instructor,
        LocalDateTime lectureDate,
        int applicantCnt
) {
    public static final int LIMIT_STUDENT = 30;

    public static Lecture fromEntity(LectureEntity entity) {
        if (entity == null) {
            throw new IllegalStateException("정보를 찾을 수 없습니다.");
        }
        return new Lecture(entity.getId(), entity.getTitle(), entity.getInstructor(), entity.getLectureDate(), entity.getApplicantCnt());
    }

    public int availableSeat() {
        return LIMIT_STUDENT - applicantCnt;
    }

    // 신청 가능한지 확인
    public void isAvailableLecture(LocalDateTime date) {
        if (lectureDate.isBefore(date)) {
            throw new IllegalStateException("날짜를 입력해주세요");
        }
        if (availableSeat() <= 0) {
            throw new IllegalStateException("수강인원이 초과되었습니다.");
        }
    }

    // 신청자수 추가
    public Lecture addApplicant() {
        return new Lecture(id, title, instructor, lectureDate, applicantCnt + 1);
    }
}
