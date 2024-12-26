package hhplus.clean.architecture.infra.lecture;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
    List<LectureEntity> findAllByAppliedCntLessThanAndStartAtBetween(
            int maxCount, LocalDateTime from, LocalDateTime end
    );

    List<LectureEntity> findAllByIdIn(List<Long> ids);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from LectureEntity l where l.id = :id")
    LectureEntity findOneByIdWithLock(@Param("id") long id);

    @Modifying
    @Query("update LectureEntity l set l.applicantCnt = :appliedCount where l.id = :id")
    int updateAppliedCount(@Param("id") long id, @Param("appliedCount") int appliedCount);
}
