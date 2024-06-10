package App;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Patient;
import Reader.Reader;
import UserInterface.Interface;
import java.io.*;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import java.awt.*;

public class App {
  public static void main(String args[]) {
    Reader reader = new Reader("medicos.csv", "pacientes.csv", "consultas.csv");
    ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    ArrayList<Patient> patients = new ArrayList<Patient>();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    reader.read(doctors, patients, appointments);

    try {
      Patient.save("data/pacientes/all.ser", patients);
    } catch (IOException e) {
    }

    try {
      Doctor.save("data/medicos/all.ser", doctors);
    } catch (IOException e) {
    }

    try {
      Appointment.save("data/consultas/all.ser", appointments);
    } catch (IOException e) {

    }

    SwingUtilities.invokeLater(() -> new Interface(doctors, patients));
  }
}
