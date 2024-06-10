package Reader;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Reader {
  private String doctorsFile;
  private String patientsFile;
  private String appointmentsFile;

  public Reader(String doctorsFile, String patientsFile, String appointmentsFile) {
    this.doctorsFile = doctorsFile;
    this.patientsFile = patientsFile;
    this.appointmentsFile = appointmentsFile;
  }

  private void readDoctors(ArrayList<Doctor> doctors) throws IOException, ClassNotFoundException {

    File doctorSer = new File("data/medicos/all.ser");

    if (doctorSer.exists()) {

      doctors.addAll(Doctor.loadAll("data/medicos/all.ser"));
      // try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(doctorSer))) {
      //   ArrayList<Doctor> serDoctors = (ArrayList<Doctor>) ois.readObject();
      //   doctors.addAll(serDoctors);
      // } catch (IOException | ClassNotFoundException e) {
      //   e.printStackTrace();
      // }
    } else {

      try {
        FileReader file = new FileReader(this.doctorsFile);
        BufferedReader buffer = new BufferedReader(file);
        buffer.readLine();

        while (buffer.ready()) {
          String linha = buffer.readLine();
          String[] tokens = linha.split(",");

          Doctor doctor = new Doctor(tokens[0], Integer.parseInt(tokens[1]));
          doctors.add(doctor);
        }

        buffer.close();

      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler o arquivo dos médicos!");
        System.out.println(e.getMessage());
      }
    }
  }

  private void readPatients(ArrayList<Patient> patients)
      throws IOException, ClassNotFoundException {
    File patientsSer = new File("data/pacientes/all.ser");
    String patientsSerStr = "data/pacientes/all.ser";

    if (patientsSer.exists()) {
      patients.addAll(Patient.loadAll(patientsSerStr));
      System.out.println(patients);
    } else {
      try {
        FileReader file = new FileReader(this.patientsFile);
        BufferedReader buffer = new BufferedReader(file);
        buffer.readLine();

        while (buffer.ready()) {
          String linha = buffer.readLine();
          String[] tokens = linha.split(",");

          Patient patient = new Patient(tokens[0], tokens[1]);
          patients.add(patient);
        }

        buffer.close();

      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler o arquivo dos pacientes!");
        System.out.println(e.getMessage());
      }
    }
  }

  private void readAppointments(
      ArrayList<Appointment> appointments, ArrayList<Patient> patients, ArrayList<Doctor> doctors)
      throws IOException, ClassNotFoundException {
    // TODO: Achar um jeito melhor
    File appointmentsSer = new File("data/consultas/all.ser");

    // TODO: Melhorar a lógica
    if (appointmentsSer.exists()) {
      System.out.println("File existe");

      appointments = Appointment.loadAll("data/consultas/all.ser");
      // for (Patient patient : patients) {
      //   String patientFileName = "data/consultas/" + patient.name + ".ser";
      //   File patientFile = new File(patientFileName);
      //
      //   if (patientFile.exists()) {
      //     System.out.println("File exists for patient: " + patient.name);
      //     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(patientFile))) {
      //       ArrayList<Appointment> patientAppointments = (ArrayList<Appointment>)
      // ois.readObject();
      //       appointments.addAll(patientAppointments);
      //     } catch (EOFException eof) {
      //     } catch (IOException | ClassNotFoundException e) {
      //       e.printStackTrace();
      //     }
      //   }
      // }
    } else {
      try {
        FileReader file = new FileReader(this.appointmentsFile);
        BufferedReader buffer = new BufferedReader(file);
        buffer.readLine();

        while (buffer.ready()) {
          String linha = buffer.readLine();
          String[] tokens = linha.split(",");

          int doctorCode = Integer.parseInt(tokens[2]);
          String patientCpf = tokens[3];

          Patient patient = Patient.getPatientByCpf(patientCpf, patients);
          Doctor doctor = Doctor.getDoctorByCode(doctorCode, doctors);

          Appointment appointment =
              new Appointment(
                  LocalDate.parse(tokens[0]), LocalTime.parse(tokens[1]), doctor, patient);
          appointments.add(appointment);
        }

        buffer.close();

      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler o arquivo das consultas!");
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public void read(
      ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<Appointment> appointments) {
    try {

      this.readDoctors(doctors);
    } catch (IOException | ClassNotFoundException e) {

    }
    try {

      this.readPatients(patients);
    } catch (IOException | ClassNotFoundException e) {

    }
    try {
      readAppointments(appointments, patients, doctors);
    } catch (IOException | ClassNotFoundException e) {
    }
  }
}
