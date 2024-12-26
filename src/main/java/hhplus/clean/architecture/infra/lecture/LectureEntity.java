package hhplus.clean.architecture.infra.lecture;

import hhplus.clean.architecture.domain.lecture.Lecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lecture")
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String instructor;
    private LocalDateTime lectureDate;
    private int applicantCnt;

    public Lecture toDomain() {
        return new Lecture(id, title, instructor, lectureDate, applicantCnt);
    }

}
