package App;

import java.io.IOException;
import java.util.ArrayList;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import Reader.Reader;
import UserInterface.Interface;

public class SecondProgram {
  public static void main(String args[]) {
    Reader reader = new Reader("medicos.csv", "pacientes.csv", "consultas.csv");
    ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    ArrayList<Patient> patients = new ArrayList<Patient>();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    try {
      doctors = reader.readDoctorsSer(doctors, false);
      patients = reader.readPatientsSer(patients, false);
      appointments = reader.readAppointmentsSer(appointments, patients, doctors, false);
    } catch (IOException | ClassNotFoundException e) {

    }

    Interface userInterface = new Interface(doctors, patients, appointments);
    userInterface.start();
  }
}
