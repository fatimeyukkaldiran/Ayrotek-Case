package com.yukkaldiran.fatime.taxcalculatorservice.repository;

import com.yukkaldiran.fatime.taxcalculatorservice.entity.TaxCalculationLogger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaxCalculationLoggerRepository extends MongoRepository<TaxCalculationLogger, Long> {
}
