package App;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import Reader.Reader;
import UserInterface.Interface;
import java.io.*;
import java.util.ArrayList;

public class App {
  public static void main(String args[]) {
    Reader reader = new Reader("medicos.csv", "pacientes.csv", "consultas.csv");
    ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    ArrayList<Patient> patients = new ArrayList<Patient>();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    reader.read(doctors, patients, appointments);
    try {
      Patient.save("data/pacientes/all.yml", patients);
    } catch (IOException e) {
    }

    try {
      Doctor.save("data/medicos/all.yml", doctors);
    } catch (IOException e) {
    }

    Interface userInterface = new Interface(doctors, patients);
    userInterface.start();
  }
}
