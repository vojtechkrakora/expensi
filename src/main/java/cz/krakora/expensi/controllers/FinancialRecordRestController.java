package cz.krakora.expensi.controllers;

import cz.krakora.expensi.dto.FinancialRecordDto;
import cz.krakora.expensi.services.FinancialRecordService;
import cz.krakora.expensi.utils.CsvUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FinancialRecordRestController {
    @Autowired
    private FinancialRecordService financialRecordService;
    private Logger logger = LoggerFactory.getLogger(FinancialRecordRestController.class);

    @GetMapping(value = "/records", produces = "application/json")
    public List<FinancialRecordDto> getRecords(){
        return financialRecordService.getAll();
    }

    @GetMapping(value = "/records/{notePrefix}", produces = "application/json")
    public List<FinancialRecordDto> getRecordsByNote(@PathVariable("notePrefix") String notePrefix) {
        return financialRecordService.getAllByNote(notePrefix);
    }

    @GetMapping(value = "records/max/{note}", produces = "application/json")
    public Map<String, Double> getMaxByNote(@PathVariable("note") String note) {
        Map<String, Double> result = new HashMap<>();
        result.put("maxByNote", financialRecordService.getMaxByNote(note));

        return result;
    }

    @GetMapping(value = "records/min/{note}", produces = "application/json")
    public Map<String, Double> getMinByNote(@PathVariable("note") String note) {
        Map<String, Double> result = new HashMap<>();
        result.put("minByNote", financialRecordService.getMinByNote(note));

        return result;
    }

    @PostMapping(value = "/records", consumes = "application/json", produces = "application/json")
    public @ResponseBody  ResponseEntity<String> addRecords(@RequestBody List<FinancialRecordDto> dataList) {
        financialRecordService.addFinancialRecordList(dataList);
        return new ResponseEntity<>("Accepted",HttpStatus.OK);
    }

    @GetMapping(value = "/records/income/max", produces = "application/json")
    public FinancialRecordDto getMaxIncome(@RequestParam(required = false) Map<String, String> params) {
        return financialRecordService.getMaxIncome(params);
    }

    @GetMapping(value = "/records/outcome/max", produces = "application/json")
    public FinancialRecordDto getMaxOutcome(@RequestParam(required = false) Map<String, String> params) {
        return financialRecordService.getMaxOutcome(params);
    }

    @GetMapping(value = "/records/income/avg", produces = "application/json")
    public Map<String, Double> getAvgIncome(@RequestParam(required = false) Map<String, String> params) {
        Map<String, Double> result = new HashMap<>();
        try {
            result.put("IncomeAvg",financialRecordService.getAvgIncome(params));
        } catch (ParseException e) {
            logger.error("Can not parse a date from /info request: " + e.getStackTrace());
            return null;
        }
        return result;
    }

    @GetMapping(value = "/records/outcome/avg", produces = "application/json")
    public Map<String, Double> getAvgOutcome(@RequestParam(required = false) Map<String, String> params) {
        Map<String, Double> result = new HashMap<>();
        try {
            result.put("OutcomeAvg",financialRecordService.getAvgOutcome(params));
        } catch (ParseException e) {
            logger.error("Can not parse a date from /info request: " + e.getStackTrace());
            return null;
        }
        return result;
    }

    @GetMapping(value = "/records/income/median", produces = "application/json")
    public Map<String, Double> getMedianIncome(@RequestParam(required = false) Map<String, String> params) {
        Map<String, Double> result = new HashMap<>();
        try {
            result.put("IncomeMedian",financialRecordService.getMedianIncome(params));
        } catch (ParseException e) {
            logger.error("Can not parse a date from /info request: " + e.getStackTrace());
            return null;
        }
        return result;
    }

    @GetMapping(value = "/records/outcome/median", produces = "application/json")
    public Map<String, Double> getMedianOutcome(@RequestParam(required = false) Map<String, String> params) {
        Map<String, Double> result = new HashMap<>();
        try {
            result.put("OutcomeMedian",financialRecordService.getMedianOutcome(params));
        } catch (ParseException e) {
            logger.error("Can not parse a date from /info request: " + e.getStackTrace());
            return null;
        }
        return result;
    }

    @PostMapping(value = "/records/upload", consumes = "multipart/form-data")
    public void uploadRecords(@RequestParam("file")MultipartFile file){
        try {
            financialRecordService.addFinancialRecordList(CsvUtils.read(FinancialRecordDto.class, file.getInputStream()));
        } catch (IOException e) {
            logger.error("Can not accept input file as financial records: " + e.toString());
        }
    }
}
