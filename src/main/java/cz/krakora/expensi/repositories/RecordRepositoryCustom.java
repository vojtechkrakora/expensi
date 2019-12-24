package cz.krakora.expensi.repositories;

import cz.krakora.expensi.models.FinancialRecord;

import java.util.Date;

public interface RecordRepositoryCustom {
    FinancialRecord getMax(boolean isIncome);
    FinancialRecord getMax(boolean isIncome, Date from, Date to);
    double getAverage(boolean isIncome, Date from, Date to);
    double getMedianAmount(boolean isIncome, Date from, Date to);
}
