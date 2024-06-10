package Entities;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Patient implements Serializable {
  public String name;
  public String cpf;
  public ArrayList<Appointment> appointments;
  private static ArrayList<Patient> allPatients = new ArrayList<>();

  public Patient(String name, String cpf) {
    this.name = name;
    this.cpf = cpf;
    this.appointments = new ArrayList<Appointment>();
    allPatients.add(this);
  }

  public static ArrayList<String> getAvailableCpfs(ArrayList<Patient> patients) {
    ArrayList<String> cpfList = new ArrayList<String>();

    for (Patient patient : patients) {
      cpfList.add(patient.cpf);
    }

    return cpfList;
  };

  public static Patient getPatientByCpf(String cpf, ArrayList<Patient> patients) {
    Patient patientToReturn = new Patient("", ""); // PREENCHER COM DADOS PLACEHOLDER (preciso do construtor)

    for (Patient patient : patients) {
      if (patient.cpf.equals(cpf)) {
        patientToReturn = patient;
      }
    }

    return patientToReturn;
  }

  // Quais são todos os médicos que um determinado paciente já consultou ou tem
  // consulta agendada?
  public ArrayList<Doctor> getDoctorsByPatient() {

    ArrayList<Doctor> doctorsByPatient = new ArrayList<Doctor>();

    for (Appointment a : appointments) {
      if (a.patient == this) {
        if (!doctorsByPatient.contains(a.doctor)) {
          doctorsByPatient.add(a.doctor);
        }
      }
    }

    return doctorsByPatient;
  }

  /*
   * Quais são todas as consultas que um determinado paciente realizou com
   * determinado médico? (Somente consultas realizadas em um tempo passado são
   * consideradas.)
   */
  public ArrayList<Appointment> getAppointmentsByDoctor(Doctor doctor) {
    ArrayList<Appointment> appointmentsByDoctor = new ArrayList<Appointment>();

    for (Appointment a : appointments) {
      if (a.doctor == doctor
          && (a.date.isBefore(LocalDate.now())
              || (a.date.isEqual(LocalDate.now()) && a.time.isBefore(LocalTime.now())))) {
        appointmentsByDoctor.add(a);
      }
    }
    return appointmentsByDoctor;
  }

  public ArrayList<Appointment> getFutureAppointments() {
    ArrayList<Appointment> futureAppointments = new ArrayList<Appointment>();
    for (Appointment a : appointments) {
      if (a.date.isAfter(LocalDate.now())
          || (a.date.isEqual(LocalDate.now()) && a.time.isAfter(LocalTime.now()))) {
        futureAppointments.add(a);
      }
    }

    return futureAppointments;
  }

  public void addAppointment(Appointment appointment) {
    appointments.add(appointment);
  }

  public static void save(String file, ArrayList<Patient> pacientes) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(pacientes);
    }
  }

  public void saveAppointments(String file) throws IOException {
    FileOutputStream arquivo = new FileOutputStream(file);
    ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
    gravador.writeObject(appointments);
    gravador.close();
    arquivo.close();
  }

  public static ArrayList<Patient> loadAll(String file) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (ArrayList<Patient>) ois.readObject();
    }
  }
}
