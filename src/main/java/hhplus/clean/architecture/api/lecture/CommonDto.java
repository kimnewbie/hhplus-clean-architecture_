package hhplus.clean.architecture.api.lecture;

import hhplus.clean.architecture.domain.lecture.LectureInfo;
import hhplus.clean.architecture.domain.lectureRegistration.LecturesRegisteredByStudent;
import hhplus.clean.architecture.domain.lectureRegistration.RegistrationCompleted;
import hhplus.clean.architecture.domain.student.StudentInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CommonDto {
    public static class LectureRegistDto {
        public record Request(
                long lectureId,
                long studentId
        ) {
            public CommonCommand.LectureRegistrationCommand.Regist toCommand() {
                return new CommonCommand.LectureRegistrationCommand.Regist(lectureId, studentId);
            }
        }

        public record Response(
                long studentId,
                CommonLectureResponse lecture
        ) {
            public static Response fromInfo(RegistrationCompleted completed) {
                LectureInfo lectureInfo = completed.lectureInfo();
                CommonLectureResponse lectureResponse = new CommonLectureResponse(
                        lectureInfo.id(), lectureInfo.title(), lectureInfo.instructor(),
                        lectureInfo.lectureDate());
                return new Response(completed.studentInfo().id(), lectureResponse);
            }
        }

    }

    public static class LectureRegisteredDto {

        public record Request(
                long studentId
        ) {
            public CommonCommand.LectureRegistrationCommand.Search toCommand() {
                return new CommonCommand.LectureRegistrationCommand.Search(studentId);
            }
        }

        public record Response(
                long studentId,
                List<CommonLectureResponse> lectures
        ) {
            public static Response fromInfo(LecturesRegisteredByStudent info) {

                StudentInfo studentInfo = info.studentInfo();
                List<CommonLectureResponse> lectures = info.lectureInfoList().stream()
                        .map(LectureInfo::toCommonResponse).toList();
                return new Response(studentInfo.id(), lectures);
            }
        }
    }

    public static class LectureSearchDto {
        public record Request(LocalDate date) {

            public CommonCommand.LectureCommand.Search toCommand() {
                return new CommonCommand.LectureCommand.Search(date);
            }
        }

        public record Response(
                long id,
                String title,
                String instructor,
                LocalDateTime lectureDate,
                int availableSeat // 남은 신청자 수
        ) {

            public static Response fromInfo(LectureInfo info) {
                return new Response(info.id(), info.title(), info.instructor(), info.lectureDate(), info.availableSeat());
            }
        }

    }
}
