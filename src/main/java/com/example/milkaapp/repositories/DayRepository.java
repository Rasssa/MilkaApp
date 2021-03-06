package com.example.milkaapp.repositories;

import com.example.milkaapp.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
        Optional<Day> findByHourStartDay(LocalTime hourStart);
        Day findDayByDate (LocalDate date);
        Day findDayById (Long id);
        }
