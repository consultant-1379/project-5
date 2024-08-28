package com.bodyguards.security.repository;

import com.bodyguards.security.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportsRepository extends MongoRepository<Report, Integer> {
}
