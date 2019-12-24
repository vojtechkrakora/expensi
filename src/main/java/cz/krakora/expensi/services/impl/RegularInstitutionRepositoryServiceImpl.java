package cz.krakora.expensi.services.impl;

import cz.krakora.expensi.converters.RegularExpensesInstitutionConverter;
import cz.krakora.expensi.dto.RegularExpensesInstitutionDto;
import cz.krakora.expensi.repositories.InstitutionsRepository;
import cz.krakora.expensi.services.RegularInstitutionRepositoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularInstitutionRepositoryServiceImpl implements RegularInstitutionRepositoryService {
    private final InstitutionsRepository institutionsRepository;
    private RegularExpensesInstitutionConverter regularExpensesInstitutionConverter = new RegularExpensesInstitutionConverter();

    public RegularInstitutionRepositoryServiceImpl(InstitutionsRepository institutionsRepository) {
        this.institutionsRepository = institutionsRepository;
    }

    @Override
    public List<RegularExpensesInstitutionDto> findAll() {
        return regularExpensesInstitutionConverter.allToDto(institutionsRepository.findAll());
    }

    @Override
    public void addInstitutionsList(List<RegularExpensesInstitutionDto> dataList) {
        institutionsRepository.saveAll(regularExpensesInstitutionConverter.allToData(dataList));
    }
}
