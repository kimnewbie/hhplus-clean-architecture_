package hhplus.clean.architecture.api.lecture;

import java.time.LocalDateTime;

public record CommonLectureResponse(
        long id,
        String title,
        String instructor,
        LocalDateTime lectureDate
) {
}
