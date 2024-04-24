package Entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor {
  public String name;
  public int code;
  private ArrayList<Patient> patients = new ArrayList<Patient>();
  // private ArrayList<Appointments> appointments = new ArrayList<Appointments>();

  public Doctor(String name, int code) {
    this.name = name;
    this.code = code;
  }

  public static ArrayList<Integer> getAvailableDoctorCodes(ArrayList<Doctor> doctors) {
    ArrayList<Integer> codesList = new ArrayList<Integer>();

    for (Doctor doctor : doctors) {
      codesList.add(doctor.code);
    }

    return codesList;
  };

  public static Doctor getDoctorByCode(int code, ArrayList<Doctor> doctors) {
    Doctor doctorToReturn = new Doctor("", 0); // PREENCHER COM DADOS PLACEHOLDER (preciso do construtor)

    for (Doctor doctor : doctors) {
      if (doctor.code == code) {
        doctorToReturn = doctor;
      }
    }

    return doctorToReturn;
  }

  public void addPatient(Patient patient) {
    patients.add(patient);
  }

  public boolean hasPatient(Patient patient) {
    return this.patients.contains(patient);
  }

  // public void addApointment(Appointments appointment){
  // appointments.add(appointment);
  // }

  // Quais são todos os pacientes de um determinado médico?
  public ArrayList<Patient> getPatients() {
    return patients;
  }

  /*
   * Quais são os pacientes de um determinado médico que não o consulta há mais
   * que um determinado tempo (em meses)?
   */
  public ArrayList<Patient> getMissingPatients(int months) {
    ArrayList<Patient> missingPatients = new ArrayList<Patient>();
    LocalDate currentDate = LocalDate.now();

    for (Patient p : patients) {
      if (currentDate.getMonthValue() - p.appointments.get(p.appointments.size() - 1).date.getMonthValue() > months) {
        missingPatients.add(p);
      }
    }

    return missingPatients;
  }

  /*
   * Quais são todas as consultas agendadas para um determinado médico em
   * determinado período (definido por uma data inicial e uma data final), na
   * ordem
   * crescente dos horários? (O período pode cobrir tanto o tempo passado como o
   * tempo futuro.)
   */
  public ArrayList<Appointment> getAppointmentsByPeriod(LocalDate startDate, LocalDate endDate) {
    ArrayList<Appointment> appointmentsByPeriod = new ArrayList<Appointment>();

    // for(Appointments a: appointments){
    // if(startDate.isBefore(a.date) && endDate.isAfter(a.date)){
    // appointmentsByPeriod.add(a);
    // }
    // }
    return appointmentsByPeriod;
  }

}