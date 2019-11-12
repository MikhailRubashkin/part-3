package com.example.part3.repos;

import com.example.part3.domain.HistorySmoke;
import com.example.part3.domain.Smoke;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HistorySmokeRepo extends CrudRepository<HistorySmoke, Date> {
    List<HistorySmoke> findByLocalDate( Date localDate);
}
