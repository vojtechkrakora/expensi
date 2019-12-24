package cz.krakora.expensi.services.impl;

import cz.krakora.expensi.constants.ExpensiConstants;
import cz.krakora.expensi.dto.ComplexInfoDto;
import cz.krakora.expensi.repositories.RecordRepository;
import cz.krakora.expensi.services.ComplexInfoService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class ComplexInfoServiceImpl implements ComplexInfoService {
    private final RecordRepository recordRepository;

    public ComplexInfoServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public ComplexInfoDto getInfo(Map<String, String> params) throws ParseException {
        Date fromDate = null;
        Date toDate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy");
        if (params.get(ExpensiConstants.FROM_PARAM) != null) {
            fromDate = format.parse(params.get(ExpensiConstants.FROM_PARAM));
        }
        if (params.get(ExpensiConstants.TO_PARAM) != null) {
            toDate = format.parse(params.get(ExpensiConstants.TO_PARAM));
        }
        ComplexInfoDto infoDto = new ComplexInfoDto();
        infoDto.setAvgIncome(recordRepository.getAverage(ExpensiConstants.INCOME, fromDate, toDate));
        infoDto.setAvgOutcome(recordRepository.getAverage(ExpensiConstants.OUTCOME, fromDate, toDate));
        infoDto.setMaxIncome(recordRepository.getMax(ExpensiConstants.INCOME, fromDate, toDate).getAmount());
        infoDto.setMaxOutcome(recordRepository.getMax(ExpensiConstants.OUTCOME, fromDate, toDate).getAmount());
        infoDto.setMedianIncome(recordRepository.getMedianAmount(ExpensiConstants.INCOME, fromDate, toDate));
        infoDto.setMedianOutcome(recordRepository.getMedianAmount(ExpensiConstants.OUTCOME, fromDate, toDate));

        return infoDto;
    }
}
