package Entities;

import java.io.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;

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

    // try {
    //   patient.saveAppointments("data/consultas/" + patient.name + ".ser");
    // } catch (IOException e) {
    //   e.printStackTrace();
    // }
    System.out.println("Appointment criado");
  }

  public int getMonthDiffFromCurrentDate() {
    LocalDate currentDate = LocalDate.now();

    Period period = Period.between(date, currentDate);
    int distanceMonths = period.getYears() * 12 + period.getMonths();

    return distanceMonths;
  }

  public static void save(String file, ArrayList<Appointment> consultas) throws IOException {
    FileOutputStream arquivo = new FileOutputStream(file);
    ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
    gravador.writeObject(consultas);
    gravador.close();
    arquivo.close();
  }

  public static ArrayList<Appointment> loadAll(String file)
      throws IOException, ClassNotFoundException {
    try (FileInputStream arquivo = new FileInputStream(file);
        ObjectInputStream leitor = new ObjectInputStream(arquivo)) {
      return (ArrayList<Appointment>) leitor.readObject();
    }
  }
}
