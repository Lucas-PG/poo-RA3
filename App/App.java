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

    for (Doctor d : doctors) {
      System.out.println(d.name);
    }

    // for (Appointment p : appointments) {
    // System.out.println("APPOINTMENT DATE:" + p.date + "PATIENT" + p.patient.name
    // + "CPF" + p.patient.cpf);
    // }

    // for (Patient p : patients) {
    // System.out.println(p.name);
    // }

    // for (Appointment a : Patient.getPatientByCpf("00000000000",
    // patients).appointments) {
    // System.out.println("ANDRE:" + a.date);
    // }

    // System.out.println("NOME: " + Patient.getPatientByCpf("00000000000",
    // patients).name);

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
