package Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
  public LocalDate date;
  public LocalTime time;
  public Doctor doctor;
  public Patient patient;

  public Appointment(LocalDate date, LocalTime time, Doctor doctor, Patient patient) {
    this.date = date;
    this.time = time;
    this.doctor = doctor;
    this.patient = patient;

    patient.addApointment(this);
    if (!doctor.hasPatient(patient)) {
      doctor.addPatient(this.patient);
    }
  }

}
