package cz.krakora.expensi.models;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REGULAR_EXPENSES_INSTITUION")
@Data
public class RegularExpensesInstitution {
    @Id
    @GeneratedValue
    private long id;
    private String institutionName;
    private String expectedNoteExpr;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "regularExpensesInstitution")
    private List<FinancialRecord> financialRecords = new ArrayList<>();
}
