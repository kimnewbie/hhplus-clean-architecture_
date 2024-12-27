package hhplus.clean.architecture.api.lecture;

import java.time.LocalDateTime;

/**
 * 특강 정보를 저장하고 전달하는 간단한 데이터 객체
 *
 * @param id
 * @param title
 * @param instructor
 * @param lectureDate
 */
public record CommonLectureResponse(
        long id,
        String title,
        String instructor,
        LocalDateTime lectureDate
) {
}
