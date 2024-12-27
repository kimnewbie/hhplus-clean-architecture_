package hhplus.clean.architecture.lectureRegistration;

import hhplus.clean.architecture.api.lecture.CommonCommand;
import hhplus.clean.architecture.domain.lecture.Lecture;
import hhplus.clean.architecture.domain.lecture.LectureRepository;
import hhplus.clean.architecture.domain.lectureRegistration.LectureRegistrationRepository;
import hhplus.clean.architecture.domain.lectureRegistration.LectureRegistrationService;
import hhplus.clean.architecture.domain.student.StudentInfo;
import hhplus.clean.architecture.domain.student.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class LectureRegistrationServiceTest {

    @InjectMocks
    private LectureRegistrationService lectureRegistrationService;

    @Mock
    private LectureRegistrationRepository registrationRepository;
    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Step3")
    void 수강신청_30명만_성공() {
        // given
        long lectureId = 1L;
        long studentId = 1L;
        int appliedCount = 30;  // 이미 30명이 신청되어 있어 수용인원 초과

        // when
        when(registrationRepository.countLectureRegistration(lectureId, studentId)).thenReturn(0);
        when(studentRepository.findById(studentId)).thenReturn(new StudentInfo(studentId, "학생1"));
        when(lectureRepository.findOneByIdWithLock(lectureId)).thenReturn(
                new Lecture(lectureId, "특강1", "강연자1", LocalDateTime.of(2024, 11, 10, 14, 0), appliedCount)
        );

        // 검증
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> lectureRegistrationService.regist(
                new CommonCommand.LectureRegistrationCommand.Regist(lectureId, studentId)));

        assertEquals("수강인원이 초과되었습니다.", exception.getMessage());
    }


    @Test
    @DisplayName("Step4")
    void 학생이_특강을_중복신청_한번만성공() {
        // given
        long lectureId = 1L;
        long studentId = 1L;

        // when
        when(registrationRepository.countLectureRegistration(lectureId, studentId)).thenReturn(1);

        // 검증
        IllegalStateException exception = assertThrows(
                IllegalStateException.class, () -> lectureRegistrationService.regist(
                        new CommonCommand.LectureRegistrationCommand.Regist(lectureId, studentId)));

        assertEquals("이미 신청한 특강입니다.", exception.getMessage()); // 메시지 검증
    }
}
