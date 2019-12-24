package cz.krakora.expensi.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegularExpensesInstitutionDto {
    private long id;
    private String institutionName;
    private String expectedNoteExpr;
    private List<FinancialRecordDto> financialRecordDtoList;
}
