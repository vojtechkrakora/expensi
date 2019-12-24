package cz.krakora.expensi.controllers;

import cz.krakora.expensi.dto.ComplexInfoDto;
import cz.krakora.expensi.services.ComplexInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
public class InfoRestController {
    @Autowired
    ComplexInfoService complexInfoService;
    private Logger logger = LoggerFactory.getLogger(FinancialRecordRestController.class);

    @GetMapping(value = "/info", produces = "application/json")
    public @ResponseBody ResponseEntity<ComplexInfoDto> getInfo(@RequestParam(required = false) Map<String, String> params) {
        ComplexInfoDto result;
        try {
            result = complexInfoService.getInfo(params);
        } catch (ParseException e) {
            logger.error("Can not parse a date from /info request: " + e.getStackTrace());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
