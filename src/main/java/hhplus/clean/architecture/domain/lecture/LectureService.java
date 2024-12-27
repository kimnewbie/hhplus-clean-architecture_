package hhplus.clean.architecture.domain.lecture;

import hhplus.clean.architecture.api.lecture.CommonCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional(readOnly = true)
    public List<LectureInfo> findAllLecturesAvailable(
            CommonCommand.LectureCommand.Search searchCommand
    ) {
        List<Lecture> availableLectures = lectureRepository.findAllLecturesAvailable(
                Lecture.LIMIT_STUDENT, searchCommand.toStartOfDay(), searchCommand.toEndOfDay()
        );

        return availableLectures.stream().map(LectureInfo::fromDomain).toList();
    }
}
