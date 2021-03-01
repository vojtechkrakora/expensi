package cz.krakora.expensi.converters;

import cz.krakora.expensi.dto.FinancialRecordDto;
import cz.krakora.expensi.dto.RegularExpensesInstitutionDto;
import cz.krakora.expensi.models.FinancialRecord;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FinancialRecordConverter implements CommonConverter<FinancialRecordDto, FinancialRecord> {
    private final RegularExpensesInstitutionConverter regularExpensesInstitutionConverter = new RegularExpensesInstitutionConverter();
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger = LoggerFactory.getLogger(FinancialRecordConverter.class);

    @Override
    public FinancialRecordDto toDto(FinancialRecord data) {
        if (data == null) {
            return null;
        }

        FinancialRecordDto dto = new FinancialRecordDto();
        dto.setAmount(Double.toString(data.getAmount()));
        dto.setBankAccountNumber(data.getBankAccountNumber());
        if (data.getContraAccountNumber() != null) {
            dto.setContraAccountNumber(data.getContraAccountNumber());
        }
        dto.setContraBankCode(data.getContraBankCode());
        dto.setCurrency(data.getCurrency());
        if (data.getDate() != null) {
            dto.setDate(data.getDate().toString());
        }
        dto.setId(data.getId());
        dto.setMessageForRecipient(data.getMessageForRecipient());
        dto.setNote(data.getNote());
        if (data.getRegularExpensesInstitution() != null) {
            dto.setRegularExpensesInstitutionDto(modelMapper.map(data.getRegularExpensesInstitution(), RegularExpensesInstitutionDto.class));
        }

        return dto;
    }

    @Override
    public FinancialRecord toData(FinancialRecordDto dto) {
        if (dto == null) {
            return null;
        }

        FinancialRecord data = new FinancialRecord();
        if (dto.getAmount() != null) {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            try {
                data.setAmount(format.parse(dto.getAmount()).doubleValue());
            } catch (ParseException e) {
                logger.error("Can not read records double value: " + e.toString());
            }
        }
        data.setBankAccountNumber(dto.getBankAccountNumber());
        if (dto.getContraAccountNumber() != null) {
            data.setContraAccountNumber(dto.getContraAccountNumber());
        }
        data.setContraBankCode(dto.getContraBankCode());
        data.setCurrency(dto.getCurrency());
        if (dto.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy");
            try {
                data.setDate(format.parse(dto.getDate()));
            } catch (ParseException e) {
                logger.error("Can not read records date: " + e.toString());
            }
        }
        data.setId(dto.getId());
        data.setMessageForRecipient(dto.getMessageForRecipient());
        data.setNote(dto.getNote());
        if (dto.getRegularExpensesInstitutionDto() != null) {
            data.setRegularExpensesInstitution(regularExpensesInstitutionConverter.toData(dto.getRegularExpensesInstitutionDto()));
        }

        return data;
    }
}
