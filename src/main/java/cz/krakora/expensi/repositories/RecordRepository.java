package cz.krakora.expensi.repositories;

import cz.krakora.expensi.models.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<FinancialRecord,Long>, RecordRepositoryCustom {
    List<FinancialRecord> findAllByNoteStartsWith(String notePrefix);
    List<FinancialRecord> findAllByNoteContains(String note);
    FinancialRecord findTop1ByNoteContainsOrderByAmountDesc(String note);
    FinancialRecord findTop1ByNoteContainsOrderByAmountAsc(String note);

    @Query(value = "SELECT sum(f.amount) from FinancialRecord f where f.note LIKE %:note%")
    Double getAmountByNote(@Param("note") String note);
}
