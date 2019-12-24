package cz.krakora.expensi.converters;

import cz.krakora.expensi.dto.RegularExpensesInstitutionDto;
import cz.krakora.expensi.models.RegularExpensesInstitution;

public class RegularExpensesInstitutionConverter extends CommonConverter<RegularExpensesInstitutionDto,RegularExpensesInstitution> {
    public RegularExpensesInstitutionConverter() {
    }

    @Override
    public  RegularExpensesInstitutionDto toDto(RegularExpensesInstitution data) {
        if (data == null) {
            return null;
        }

        RegularExpensesInstitutionDto dto = new RegularExpensesInstitutionDto();
        dto.setId(data.getId());
        dto.setExpectedNoteExpr(data.getExpectedNoteExpr());
        dto.setInstitutionName(data.getInstitutionName());

        return dto;
    }

    @Override
    public  RegularExpensesInstitution toData(RegularExpensesInstitutionDto dto) {
        if (dto == null) {
            return null;
        }

        RegularExpensesInstitution data = new RegularExpensesInstitution();
        data.setInstitutionName(dto.getInstitutionName());
        data.setExpectedNoteExpr(dto.getExpectedNoteExpr());

        return data;
    }
}
