package com.example.part3.repos;

import com.example.part3.domain.Temperature;
import com.example.part3.domain.Temperature;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TemperatureRepo extends CrudRepository<Temperature, Date> {

    List<Temperature> findByLocalDate( Date localDate);

}
