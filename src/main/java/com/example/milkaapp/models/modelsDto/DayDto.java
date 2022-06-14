package com.example.milkaapp.models.modelsDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DayDto {
    private LocalDate date;
    private float hourStartDay;
    private float hourEndDay;
    private String note;
    private List<LocalTime> hoursSet;
}
