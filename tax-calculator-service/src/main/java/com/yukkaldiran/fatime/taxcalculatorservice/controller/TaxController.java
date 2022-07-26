package com.yukkaldiran.fatime.taxcalculatorservice.controller;

import com.yukkaldiran.fatime.taxcalculatorservice.dto.TaxCalculateRequestDto;
import com.yukkaldiran.fatime.taxcalculatorservice.dto.TaxProductResponseDto;
import com.yukkaldiran.fatime.taxcalculatorservice.service.TaxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tax-calculate")
public class TaxController {

   private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping()
    public ResponseEntity<TaxProductResponseDto> calculateProductTax(@RequestBody TaxCalculateRequestDto calculateRequest){
        TaxProductResponseDto result =  taxService.getTaxOfProduct(calculateRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
