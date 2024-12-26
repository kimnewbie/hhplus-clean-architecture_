package hhplus.clean.architecture.domain.lecture;

import java.time.LocalDateTime;

public record LectureInfo(
        long id,
        String title,
        String instructor,
        LocalDateTime lectureDate
        int availableSeat
) {
}
