package com.example.part3.repos;

import com.example.part3.domain.Smoke;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SmokeRepo extends CrudRepository<Smoke, Date> {

    List<Smoke> findByLocalDate( Date localDate);
}
