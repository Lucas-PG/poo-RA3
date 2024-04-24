package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import Reader.Reader;
import UserInterface.Interface;

public class App {
  public static void main(String args[]) {
    Reader reader = new Reader("medicos.csv", "pacientes.csv", "consultas.csv");
    Interface interface1 = new Interface();
    ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    ArrayList<Patient> patients = new ArrayList<Patient>();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    reader.read(doctors, patients, appointments);

    interface1.startUserInteraction(doctors, patients);
  }
}
