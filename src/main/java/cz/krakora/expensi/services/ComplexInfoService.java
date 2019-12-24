package cz.krakora.expensi.services;

import cz.krakora.expensi.dto.ComplexInfoDto;

import java.text.ParseException;
import java.util.Map;

public interface ComplexInfoService {
    ComplexInfoDto getInfo(Map<String, String> params) throws ParseException;
}
