package Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointments {
  private LocalDate date;
  private LocalTime time;
  private Doctor doctor;

  public Appointments(LocalDate date, LocalTime time, Doctor doctor){
    this.date = date;
    this.time = time;
    this.doctor = doctor;
  }
}
