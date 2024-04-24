package Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;

public class Reader {
  private String doctorsFile;
  private String patientsFile;
  private String appointmentsFile;

  public Reader(String doctorsFile, String patientsFile, String appointmentsFile) {
    this.doctorsFile = doctorsFile;
    this.patientsFile = patientsFile;
    this.appointmentsFile = appointmentsFile;
  }

  private void readDoctors(ArrayList<Doctor> doctors) {
    try {
      FileReader file = new FileReader(this.doctorsFile);
      BufferedReader buffer = new BufferedReader(file);
      String header = buffer.readLine();

      while (buffer.ready()) {
        String linha = buffer.readLine();
        String[] tokens = linha.split(",");

        Doctor doctor = new Doctor(tokens[0], Integer.parseInt(tokens[1]));
        doctors.add(doctor);
      }

    } catch (Exception e) {
      System.out.println("Ocorreu um erro ao ler o arquivo!");
      System.out.println(e.getMessage());
    }
  }

  private void readPatients(ArrayList<Patient> patients) {
    try {
      FileReader file = new FileReader(this.patientsFile);
      BufferedReader buffer = new BufferedReader(file);
      String header = buffer.readLine();

      while (buffer.ready()) {
        String linha = buffer.readLine();
        String[] tokens = linha.split(",");

        Patient patient = new Patient(tokens[0], tokens[1]);
        patients.add(patient);
      }

    } catch (Exception e) {
      System.out.println("Ocorreu um erro ao ler o arquivo!");
      System.out.println(e.getMessage());
    }
  }

  private void readAppointments(ArrayList<Appointment> appointments, ArrayList<Patient> patients,
      ArrayList<Doctor> doctors) {
    try {
      FileReader file = new FileReader(this.appointmentsFile);
      BufferedReader buffer = new BufferedReader(file);
      String header = buffer.readLine();

      while (buffer.ready()) {
        String linha = buffer.readLine();
        String[] tokens = linha.split(",");

        int doctorCode = Integer.parseInt(tokens[2]);
        String patientCpf = tokens[3];

        Patient patient = Patient.getPatientByCpf(patientCpf, patients);
        Doctor doctor = Doctor.getDoctorByCode(doctorCode, doctors);

        Appointment appointment = new Appointment(LocalDate.parse(tokens[0]), LocalTime.parse(tokens[1]), doctor,
            patient);
        appointments.add(appointment);
      }

    } catch (Exception e) {
      System.out.println("Ocorreu um erro ao ler o arquivo!");
      System.out.println(e.getMessage());
    }
  }

  public void read(ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<Appointment> appointments) {
    this.readDoctors(doctors);
    this.readPatients(patients);
    readAppointments(appointments, patients, doctors);
  }
}
