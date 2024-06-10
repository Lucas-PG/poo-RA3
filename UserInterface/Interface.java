package UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;

import Entities.Doctor;
import Entities.Patient;
import Exceptions.IllegalOption;
import Exceptions.IncorrectType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Interface {
  private ArrayList<Doctor> doctors;
  private ArrayList<Patient> patients;

  public Interface(ArrayList<Doctor> doctors, ArrayList<Patient> patients) {
    this.doctors = doctors;
    this.patients = patients;
  }

  private Doctor getDoctor(ArrayList<Doctor> doctors) throws IncorrectType, IllegalOption {
    String message = Messages.getDoctorChoiceMessage(doctors);
    int chosenOption = -1;
    ArrayList<Integer> availableCodes = Doctor.getAvailableDoctorCodes(doctors);

    try {
      chosenOption = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o código do médico" + message));
    } catch (Exception e) {
      throw new IncorrectType("Tipo inválido");
    }

    while (!availableCodes.contains(chosenOption)) {
      throw new IllegalOption("Código Inválido");
    }

    return Doctor.getDoctorByCode(chosenOption, doctors);
  }

  private Patient getPatient(ArrayList<Patient> patients) throws IllegalOption {
    String message = Messages.getPatientChoiceMessage(patients);
    String chosenCpf = "";
    ArrayList<String> availableCpfs = Patient.getAvailableCpfs(patients);

    chosenCpf = JOptionPane.showInputDialog(null, "Digite o cpf do paciente" + message);

    while (!availableCpfs.contains(chosenCpf)) {
      throw new IllegalOption("CPF Inválido");
    }

    return Patient.getPatientByCpf(chosenCpf, patients);
  }

  private int getMenuChosenOption() throws IllegalOption, IncorrectType {
    int chosenOption = -1;

    try {
      chosenOption = Integer.parseInt(JOptionPane.showInputDialog(null, Messages.getChoicesMessage()));
    } catch (Exception e) {
      throw new IncorrectType("Tipo inválido");
    }

    if (!Messages.getPossibleChoicesList().contains(chosenOption)) {
      throw new IllegalOption("Opção Inválida!");
    }

    return chosenOption;
  }

  private void firstOption(ArrayList<Doctor> doctors) {
    Doctor doctor = new Doctor("", -1);

    while (doctor.name == "") {
      try {
        doctor = this.getDoctor(doctors);
      } catch (IllegalOption e) {
        e.show();
      } catch (IncorrectType e) {
        e.show();
      }
    }

    JOptionPane.showMessageDialog(null, "Aqui está a lista de pacientes de " + doctor.name + ":\n\n"
        + Messages.getPatientsListMessage(doctor.getPatients()));
  }

  private void secondOption(ArrayList<Doctor> doctors) {
    Doctor doctor = new Doctor("", -1);

    while (doctor.name == "") {
      try {
        doctor = this.getDoctor(doctors);
      } catch (IllegalOption e) {
        e.show();
      } catch (IncorrectType e) {
        e.show();
      }
    }
    String startDate = JOptionPane.showInputDialog(null, "Digite a data de início");
    String endDate = JOptionPane.showInputDialog(null, "Digite a data de fim");

    JOptionPane.showMessageDialog(null,
        "Aqui está a lista de consultas do doutor " + doctor.name + "\n" + "No período entre " + startDate + " e "
            + endDate + ":\n"
            + Messages.getAppointmentsListMessage(
                doctor.getAppointmentsByPeriod(LocalDate.parse(startDate),
                    LocalDate.parse(endDate))));
  }

  private void thirdOption(ArrayList<Patient> patients) {
    Patient patient = new Patient("", "");

    while (patient.name == "") {
      try {
        patient = this.getPatient(patients);
      } catch (IllegalOption e) {
        e.show();
      }
    }

    JOptionPane.showMessageDialog(null, "Aqui está a lista de médicos que " + patient.name + " já consultou:\n\n"
        + Messages.getDoctorsListMessage(patient.getDoctorsByPatient()));
  }

  private void fourthOption(ArrayList<Patient> patients, ArrayList<Doctor> doctors) {
    Patient patient = new Patient("", "");

    while (patient.name == "") {
      try {
        patient = this.getPatient(patients);
      } catch (IllegalOption e) {
        e.show();
      }
    }
    Doctor doctor = new Doctor("", -1);

    while (doctor.name == "") {
      try {
        doctor = this.getDoctor(doctors);
      } catch (IllegalOption e) {
        e.show();
      } catch (IncorrectType e) {
        e.show();
      }
    }

    JOptionPane.showMessageDialog(null,
        "Aqui está a lista de consultas de " + patient.name + " com o médico(a) " + doctor.name + ":\n\n"
            + Messages.getAppointmentsListMessage(patient.getAppointmentsByDoctor(doctor)));
  }

  private void fitfhOption(ArrayList<Patient> patients) {
    Patient patient = new Patient("", "");

    while (patient.name == "") {
      try {
        patient = this.getPatient(patients);
      } catch (IllegalOption e) {
        e.show();
      }
    }

    JOptionPane.showMessageDialog(null,
        "Aqui está a lista de consultas marcadas de " + patient.name + ":\n\n"
            + Messages.getAppointmentsListMessage(patient.getFutureAppointments()));
  }

  private void sixthOption(ArrayList<Doctor> doctors) {
    Doctor doctor = new Doctor("", -1);

    while (doctor.name == "") {
      try {
        doctor = this.getDoctor(doctors);
      } catch (IllegalOption e) {
        e.show();
      } catch (IncorrectType e) {
        e.show();
      }
    }
    int months = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de meses para filtrar"));

    JOptionPane.showMessageDialog(null,
        "Aqui estão os pacientes que não se consultam com " + doctor.name + " há " + months + " meses:\n\n"
            + Messages.getPatientsListMessage(doctor.getMissingPatients(months)));
  }

  public void start() {
    int chosenOption = -1;

    do {
      while (!Messages.getPossibleChoicesList().contains(chosenOption)) {
        try {
          chosenOption = getMenuChosenOption();
        } catch (IllegalOption e) {
          e.show();
        } catch (IncorrectType e) {
          e.show();
        }
      }

      switch (chosenOption) {
        case 1:
          this.firstOption(doctors);
          break;
        case 2:
          this.secondOption(doctors);
          break;
        case 3:
          this.thirdOption(patients);
          break;
        case 4:
          this.fourthOption(patients, doctors);
          break;
        case 5:
          this.fitfhOption(patients);
          break;
        case 6:
          this.sixthOption(doctors);
          break;
        case 7:
          JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!");
          break;
      }

      if (chosenOption != 7) {
        chosenOption = -1;
      }
    } while (chosenOption != 7);
  }
}
