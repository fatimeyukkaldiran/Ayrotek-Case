package com.yukkaldiran.fatime.taxcalculatorservice.service;

import com.yukkaldiran.fatime.taxcalculatorservice.dto.TaxCalculateRequestDto;
import com.yukkaldiran.fatime.taxcalculatorservice.dto.TaxProductResponseDto;
import com.yukkaldiran.fatime.taxcalculatorservice.entity.TaxCalculationLogger;
import com.yukkaldiran.fatime.taxcalculatorservice.repository.TaxCalculationLoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TaxService {
        private final TaxCalculationLoggerRepository loggerRepository;

    public TaxProductResponseDto getTaxOfProduct(TaxCalculateRequestDto calculateRequest) {
        TaxProductResponseDto taxProductResponseDto = new TaxProductResponseDto(
                calculateRequest.getPrice(),
                calculateRequest.getTaxRate(),
                calculateTax(calculateRequest.getPrice(), calculateRequest.getTaxRate())
        );

        return taxProductResponseDto;
    }

    private BigDecimal calculateTax(BigDecimal price, BigDecimal taxRate){
        BigDecimal result = taxRate.multiply(price);
        saveTaxCalculationToDatabase(price,taxRate,result);
        return  result;
    }

    private void saveTaxCalculationToDatabase(BigDecimal price, BigDecimal taxRate, BigDecimal tax){
        TaxCalculationLogger logger = new TaxCalculationLogger();

        logger.setPrice(price);
        logger.setTaxRate(taxRate);
        logger.setTaxOfProduct(tax);

      loggerRepository.save(logger);

    }
}
