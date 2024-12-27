package hhplus.clean.architecture.api.lecture;

import hhplus.clean.architecture.domain.lecture.LectureInfo;
import hhplus.clean.architecture.domain.lecture.LectureService;
import hhplus.clean.architecture.domain.lectureRegistration.LectureRegistrationService;
import hhplus.clean.architecture.domain.lectureRegistration.LecturesRegisteredByStudent;
import hhplus.clean.architecture.domain.lectureRegistration.RegistrationCompleted;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final LectureRegistrationService lectureRegistrationService;

    /**
     * 신청 가능한 특강 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<CommonDto.LectureSearchDto.Response>> availableLectureList(
            @RequestBody CommonDto.LectureSearchDto.Request request
    ) {
        List<LectureInfo> lectureInfos = lectureService.findAllLecturesAvailable(
                request.toCommand());

        List<CommonDto.LectureSearchDto.Response> responses = lectureInfos.stream()
                .map(CommonDto.LectureSearchDto.Response::fromInfo).toList();

        return ResponseEntity.ok(responses);
    }

    /**
     * 학생이 신청완료한 특강 목록 조회
     */
    @GetMapping("/list/{studentId}")
    public ResponseEntity<CommonDto.LectureRegisteredDto.Response> studentLecturesList(
            @PathVariable("studentId") long studentId
    ) {
        CommonDto.LectureRegisteredDto.Request request = new CommonDto.LectureRegisteredDto.Request(studentId);
        LecturesRegisteredByStudent registionInfo = lectureRegistrationService.findAllRegisteredLectures(
                request.toCommand());

        return ResponseEntity.ok(CommonDto.LectureRegisteredDto.Response.fromInfo(registionInfo));
    }

    /**
     * 특강 신청
     */
    @PostMapping("/regist")
    public ResponseEntity<CommonDto.LectureRegistDto.Response> regist(
            @RequestBody CommonDto.LectureRegistDto.Request request
    ) {
        RegistrationCompleted completed = lectureRegistrationService.regist(request.toCommand());

        return ResponseEntity.ok(CommonDto.LectureRegistDto.Response.fromInfo(completed));
    }
}
