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

    try {
      doctors = reader.readDoctors(doctors);
      patients = reader.readPatients(patients);
      appointments = reader.readAppointments(appointments, patients, doctors);
    } catch (IOException | ClassNotFoundException e) {

    }

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

    Interface userInterface = new Interface(doctors, patients, appointments);
    userInterface.start();
  }
}
