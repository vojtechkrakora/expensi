package cz.krakora.expensi.services;

import cz.krakora.expensi.dto.RegularExpensesInstitutionDto;

import java.util.List;

public interface RegularInstitutionRepositoryService {
    List<RegularExpensesInstitutionDto> findAll();
    void addInstitutionsList(List<RegularExpensesInstitutionDto> dataList);
}
