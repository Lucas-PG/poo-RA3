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
      ArrayList<Doctor> serDoctors = new ArrayList<>();

      try (FileInputStream fileIn = new FileInputStream(doctorSer);
          ObjectInputStream in = new ObjectInputStream(fileIn)) {

        while (true) {
          try {
            Doctor doctor = (Doctor) in.readObject();
            serDoctors.add(doctor);
          } catch (EOFException e) {
            break; // End of file reached
          }
        }
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
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
    File patientSer = new File("data/pacientes/all.ser");

    if (patientSer.exists()) {
      ArrayList<Patient> serPatients = new ArrayList<>();

      try (FileInputStream fileIn = new FileInputStream(patientSer);
          ObjectInputStream in = new ObjectInputStream(fileIn)) {

        while (true) {
          try {
            Patient patient = (Patient) in.readObject();
            serPatients.add(patient);
          } catch (EOFException e) {
            break; // End of file reached
          }
        }
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }

      // Now you can use the list of deserialized patients
      for (Patient patient : serPatients) {
        System.out.println(patient);
      }
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
    File appointmentsSer = new File("data/consultas/Andre.ser");

    ArrayList<Appointment> appointmentsList = new ArrayList<>();

    // TODO: Melhorar a lógica
    if (appointmentsSer.exists()) {
      System.out.println("File existe");
      for (Patient patient : patients) {
        String patientFileName = "data/consultas/" + patient.name + ".ser";
        File patientFile = new File(patientFileName);

        if (patientFile.exists()) {
          System.out.println("File exists for patient: " + patient.name);
          try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(patientFile))) {
            ArrayList<Appointment> patientAppointments = (ArrayList<Appointment>) ois.readObject();
            appointments.addAll(patientAppointments);
          } catch (EOFException eof) {
          } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
          }
        }
      }
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
