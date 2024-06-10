package Entities;

import java.io.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class Appointment implements Serializable {
  public LocalDate date;
  public LocalTime time;
  public Doctor doctor;
  public Patient patient;

  public Appointment(LocalDate date, LocalTime time, Doctor doctor, Patient patient) {
    this.date = date;
    this.time = time;
    this.doctor = doctor;
    this.patient = patient;

    patient.addAppointment(this);
    doctor.addAppointment(this);
    if (!doctor.hasPatient(patient)) {
      doctor.addPatient(this.patient);
    }

    try {
      save("consultas.ser");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int getMonthDiffFromCurrentDate() {
    LocalDate currentDate = LocalDate.now();

    Period period = Period.between(date, currentDate);
    int distanceMonths = period.getYears() * 12 + period.getMonths();

    return distanceMonths;
  }

  public void save(String file) throws IOException {
    FileOutputStream arquivo = new FileOutputStream(file);
    ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
    gravador.writeObject(this);
    gravador.close();
    arquivo.close();
  }
}
