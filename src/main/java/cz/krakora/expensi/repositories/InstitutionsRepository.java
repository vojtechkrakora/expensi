package cz.krakora.expensi.repositories;

import cz.krakora.expensi.models.RegularExpensesInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionsRepository extends JpaRepository<RegularExpensesInstitution,Long> {
}
