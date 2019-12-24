package cz.krakora.expensi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class FinancialRecord {
    @Id
    @GeneratedValue
    private long id;
    private Date date;
    private double amount;
    private String currency;
    private String bankAccountNumber;
    private String contraAccountNumber;
    private int contraBankCode;
    private String messageForRecipient;
    private String note;

    @ManyToOne
    @JoinColumn(name ="fk_regularExpensesInstitutionId")
    private RegularExpensesInstitution regularExpensesInstitution;
}
