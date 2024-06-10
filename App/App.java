package App;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import Reader.Reader;
import UserInterface.Interface;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class App {
  public static void main(String args[]) {
    Reader reader = new Reader("medicos.csv", "pacientes.csv", "consultas.csv");
    ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    ArrayList<Patient> patients = new ArrayList<Patient>();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    reader.read(doctors, patients, appointments);

    Interface userInterface = new Interface(doctors, patients);
    userInterface.start();
  }
}
