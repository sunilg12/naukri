package com.naukri.database_api.repositories;

import com.naukri.database_api.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
