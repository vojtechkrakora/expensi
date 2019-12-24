package cz.krakora.expensi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialRecordDto {
    private long id;
    @JsonProperty("Datum")
    private String date;
    @JsonProperty("Objem")
    private String amount;
    @JsonProperty("Měna")
    private String currency;
    @JsonProperty("Číslo účtu")
    private String bankAccountNumber;
    @JsonProperty("Protiúčet")
    private String contraAccountNumber;
    @JsonProperty("Kód banky")
    private int contraBankCode;
    @JsonProperty("Zpráva pro příjemce")
    private String messageForRecipient;
    @JsonProperty("Poznámka")
    private String note;
    private RegularExpensesInstitutionDto regularExpensesInstitutionDto;
}
