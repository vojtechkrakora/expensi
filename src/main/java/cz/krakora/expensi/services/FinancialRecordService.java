package cz.krakora.expensi.services;

import cz.krakora.expensi.dto.FinancialRecordDto;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface FinancialRecordService {
    List<FinancialRecordDto> getAll();
    List<FinancialRecordDto> getAllByNote(String note);
    double getMaxByNote(String note);
    double getMinByNote(String note);
    void addFinancialRecordList(List<FinancialRecordDto> dataList);
    FinancialRecordDto getMaxIncome(Map<String, String> params);
    FinancialRecordDto getMaxOutcome(Map<String, String> params);
    double getAvgIncome(Map<String, String> params) throws ParseException;
    double getAvgOutcome(Map<String, String> params) throws ParseException;
    double getMedianIncome(Map<String, String> params) throws ParseException;
    double getMedianOutcome(Map<String, String> params) throws ParseException;
}
