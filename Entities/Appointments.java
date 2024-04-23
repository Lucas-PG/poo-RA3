package Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointments {
  public LocalDate date;
  public LocalTime time;
  public Doctor doctor;
  public Patient patient;

  public Appointments(LocalDate date, LocalTime time, Doctor doctor, Patient patient){
    this.date = date;
    this.time = time;
    this.doctor = doctor;
    this.patient = patient;

    patient.addApointment(this);
  }


}
