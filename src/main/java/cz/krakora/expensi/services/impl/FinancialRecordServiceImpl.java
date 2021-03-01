package cz.krakora.expensi.services.impl;

import cz.krakora.expensi.constants.ExpensiConstants;
import cz.krakora.expensi.converters.FinancialRecordConverter;
import cz.krakora.expensi.dto.FinancialRecordDto;
import cz.krakora.expensi.models.FinancialRecord;
import cz.krakora.expensi.repositories.RecordRepository;
import cz.krakora.expensi.services.FinancialRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FinancialRecordServiceImpl implements FinancialRecordService {
    private final RecordRepository recordRepository;
    private final FinancialRecordConverter financialRecordConverter = new FinancialRecordConverter();
    ModelMapper modelMapper = new ModelMapper();

    public FinancialRecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<FinancialRecordDto> getAll() {
        return financialRecordConverter.allToDto(recordRepository.findAll());
    }

    @Override
    public List<FinancialRecordDto> getAllByNote(String note) {
        return financialRecordConverter.allToDto(recordRepository.findAllByNoteContains(note));
    }

    @Override
    public double getMaxByNote(String note) {
        FinancialRecord record = recordRepository.findTop1ByNoteContainsOrderByAmountDesc(note);
        String amountStr;

        if (record == null) {
            return 0.0;
        }

        amountStr = financialRecordConverter.toDto(record).getAmount();

        return Double.parseDouble(amountStr);
    }

    @Override
    public double getMinByNote(String note) {
        FinancialRecord record = recordRepository.findTop1ByNoteContainsOrderByAmountAsc(note);
        String amountStr;

        if (record == null) {
            return 0.0;
        }

        amountStr = financialRecordConverter.toDto(record).getAmount();

        return Double.parseDouble(amountStr);
    }

    @Override
    public void addFinancialRecordList(List<FinancialRecordDto> dataList) {
        recordRepository.saveAll(financialRecordConverter.allToData(dataList));
    }

    @Override
    public FinancialRecordDto getMaxIncome(Map<String, String> params){
        return modelMapper.map(recordRepository.getMax(ExpensiConstants.INCOME),FinancialRecordDto.class);
    }

    @Override
    public FinancialRecordDto getMaxOutcome(Map<String, String> params) {
        return modelMapper.map(recordRepository.getMax(ExpensiConstants.OUTCOME), FinancialRecordDto.class);
    }

    @Override
    public double getAvgIncome(Map<String, String> params) throws ParseException {
        Date from = validateAndGetDate(params.get(ExpensiConstants.FROM_PARAM));
        Date to = validateAndGetDate(params.get(ExpensiConstants.TO_PARAM));
        return recordRepository.getAverage(ExpensiConstants.INCOME, from, to);
    }

    @Override
    public double getAvgOutcome(Map<String, String> params) throws ParseException {
        Date from = validateAndGetDate(params.get(ExpensiConstants.FROM_PARAM));
        Date to = validateAndGetDate(params.get(ExpensiConstants.TO_PARAM));
        return recordRepository.getAverage(ExpensiConstants.OUTCOME, from, to);
    }

    @Override
    public double getMedianIncome(Map<String, String> params) throws ParseException {
        Date from = validateAndGetDate(params.get(ExpensiConstants.FROM_PARAM));
        Date to = validateAndGetDate(params.get(ExpensiConstants.TO_PARAM));
        return recordRepository.getMedianAmount(ExpensiConstants.INCOME, from, to);
    }

    @Override
    public double getMedianOutcome(Map<String, String> params) throws ParseException {
        Date from = validateAndGetDate(params.get(ExpensiConstants.FROM_PARAM));
        Date to = validateAndGetDate(params.get(ExpensiConstants.TO_PARAM));
        return recordRepository.getMedianAmount(ExpensiConstants.OUTCOME, from, to);
    }

    @Override
    public double getAmountByNote(String note) {
        Double result = recordRepository.getAmountByNote(note);

        if (result == null) {
            return 0.0;
        }

        return recordRepository.getAmountByNote(note);
    }

    /**
     * Validates that input string is date format or even exists, returns Date instead of String.
     * @param input String which should contain String to convert to a Date.
     * @return Date or null
     * @throws ParseException If input String is not empty but, date is not in expected date pattern.
     */
    private Date validateAndGetDate(String input) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(ExpensiConstants.DATE_PATTERN);

        if (input == null) {
            return null;
        }

        return format.parse(input);
    }
}
