package cz.krakora.expensi.controllers;

import cz.krakora.expensi.dto.RegularExpensesInstitutionDto;
import cz.krakora.expensi.services.RegularInstitutionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegularExpensesInstitutionRestController {
    @Autowired
    private RegularInstitutionRepositoryService regularInstitutionRepositoryService;

    @GetMapping("/institutions")
    public List<RegularExpensesInstitutionDto> getInstitutions() {
        return regularInstitutionRepositoryService.findAll();
    }

    @PostMapping(value = "/institutions", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<String> addInstitutions(@RequestBody List<RegularExpensesInstitutionDto> data) {
        regularInstitutionRepositoryService.addInstitutionsList(data);
        return new ResponseEntity<>("Post Response", HttpStatus.OK);
    }
}
