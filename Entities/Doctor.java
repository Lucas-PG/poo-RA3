package Entities;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor implements Serializable {
  public String name;
  public int code;
  private ArrayList<Patient> patients = new ArrayList<Patient>();
  private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

  private static ArrayList<Doctor> allDoctors = new ArrayList<>();

  public Doctor(String name, int code) {
    this.name = name;
    this.code = code;

    allDoctors.add(this);
    // try {
    // save("data/medicos/all.ser");
    // } catch (IOException e) {
    // }
  }

  public static ArrayList<Integer> getAvailableDoctorCodes(ArrayList<Doctor> doctors) {
    ArrayList<Integer> codesList = new ArrayList<Integer>();

    for (Doctor doctor : doctors) {
      codesList.add(doctor.code);
    }

    return codesList;
  };

  public static Doctor getDoctorByCode(int code, ArrayList<Doctor> doctors) {
    Doctor doctorToReturn = new Doctor("", 0);

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

  public void addAppointment(Appointment appointment) {
    appointments.add(appointment);
  }

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

    for (Patient p : patients) {

      int smallerDistance = p.appointments.get(0).getMonthDiffFromCurrentDate();

      for (Appointment a : p.appointments) {
        int distance = a.getMonthDiffFromCurrentDate();

        if (distance < smallerDistance && distance > 0) {
          smallerDistance = distance;
        }
      }

      if (smallerDistance >= months) {
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

    for (Appointment a : appointments) {
      if (startDate.isBefore(a.date) && endDate.isAfter(a.date)) {
        appointmentsByPeriod.add(a);
      }
    }
    return appointmentsByPeriod;
  }

  public static void save(String file, ArrayList<Doctor> medicos) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(medicos);
    }
  }

  public static ArrayList<Doctor> loadAll(String file) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (ArrayList<Doctor>) ois.readObject();
    }
  }
}
