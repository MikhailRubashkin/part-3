package com.example.part3.repos;

import com.example.part3.domain.HistoryMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HistoryMessageRepo extends CrudRepository<HistoryMessage, Date> {
    List<HistoryMessage> findByLocalDate( Date localDate);
}
