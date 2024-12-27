package hhplus.clean.architecture.domain.lectureRegistration;

import hhplus.clean.architecture.api.lecture.CommonCommand;
import hhplus.clean.architecture.domain.lecture.Lecture;
import hhplus.clean.architecture.domain.lecture.LectureInfo;
import hhplus.clean.architecture.domain.lecture.LectureRepository;
import hhplus.clean.architecture.domain.student.StudentInfo;
import hhplus.clean.architecture.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureRegistrationService {

    private final LectureRegistrationRepository registrationRepository;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public LecturesRegisteredByStudent findAllRegisteredLectures(CommonCommand.LectureRegistrationCommand.Search command) {
        StudentInfo studentInfo = studentRepository.findById(command.studentId());

        List<Long> lectureIds = registrationRepository.findAllLectureIdsByStudentId(studentInfo.id());

        List<Lecture> lectures = lectureRepository.findAllByIds(lectureIds);
        List<LectureInfo> lectureInfos = lectures.stream().map(LectureInfo::fromDomain).toList();

        return new LecturesRegisteredByStudent(studentInfo, lectureInfos);
    }

    @Transactional
    public RegistrationCompleted regist(CommonCommand.LectureRegistrationCommand.Regist command) {
        // 이미 신청한 특강인지 조회
        int count = registrationRepository.countLectureRegistration(command.lectureId(), command.studentId());
        if (count > 0) {
            throw new IllegalStateException("이미 신청한 특강입니다.");
        }

        StudentInfo studentInfo = studentRepository.findById(command.studentId());
        Lecture lecture = lectureRepository.findOneByIdWithLock(command.lectureId());

        // 신청 가능한 상태인지 체크
        lecture.isAvailableLecture(LocalDateTime.now());

        // 신청 테이블 inset
        registrationRepository.insertOne(command.lectureId(), command.studentId());

        // 신청자수 update
        Lecture updatedLecture = lecture.addApplicant();
        lectureRepository.updateLectureAppliedCount(updatedLecture.id(),
                updatedLecture.applicantCnt());

        return new RegistrationCompleted(studentInfo, LectureInfo.fromDomain(updatedLecture));
    }
}
