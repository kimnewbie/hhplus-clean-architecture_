package hhplus.clean.architecture.infra.lectureRegistration;

import hhplus.clean.architecture.domain.student.StudentInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lecture_registration")
public class LectureRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long lectureId;
    private long studentId;
}
