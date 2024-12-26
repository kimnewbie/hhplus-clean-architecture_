package hhplus.clean.architecture.api.lecture;

public class CommonDto {
    public class LectureRegistDto {

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
                        lectureInfo.id(), lectureInfo.title(), lectureInfo.lecturerName(),
                        lectureInfo.startAt());
                return new Response(completed.studentInfo().id(), lectureResponse);
            }
        }

    }
}
