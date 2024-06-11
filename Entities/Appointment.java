package Entities;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;

import App.App;

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
  }

  public int getMonthDiffFromCurrentDate() {
    LocalDate currentDate = LocalDate.now();

    Period period = Period.between(date, currentDate);
    int distanceMonths = period.getYears() * 12 + period.getMonths();

    return distanceMonths;
  }

  public static void save(String file, ArrayList<Appointment> consultas) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(consultas);
      // for (Appointment a : consultas) {
      // System.out.println(a.date);
      // }
    }
  }

  public static ArrayList<Appointment> loadAll(String file)
      throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (ArrayList<Appointment>) ois.readObject();
    }
  }
}
