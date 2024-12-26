package hhplus.clean.architecture.api.lecture;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CommonCommand {
    public class LecturCommand {
        public record Search(LocalDate date) {
            // 날짜의 시작 시간 (00:00:00)
            public LocalDateTime toStartOfDay() {
                return LocalDateTime.of(date, LocalTime.MIN);  // date의 00:00:00 시각
            }

            // 날짜의 끝 시간 (23:59:59.999999999)
            public LocalDateTime toEndOfDay() {
                return LocalDateTime.of(date, LocalTime.MAX);  // date의 23:59:59.999999999 시각
            }
        }
    }

    public class LectureRegistrationCommand {
        public record Search(long studentId) {

        }

        public record Regist(long lectureId, long studentId) {

        }
    }
}
