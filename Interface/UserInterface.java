import java.util.ArrayList;
import java.util.Scanner;

import Entities.Doctor;
import Entities.Patient;

public class UserInterface {
  UserScanner userScanner;

  public UserInterface() {
    this.userScanner = new UserScanner();
  }

  // COLOCAR ESSE MÉTODO COMO MÉTODO ESTÁTICO DA CLASSE DOCTOR
  public ArrayList<Integer> getAvailableDoctorCodes(ArrayList<Doctor> doctors) {
    ArrayList<Integer> codesList = new ArrayList<Integer>();

    for (Doctor doctor : doctors) {
      codesList.add(doctor.code);
    }

    return codesList;
  };

  // COLOCAR ESSE MÉTODO COMO MÉTODO ESTÁTICO DA CLASSE PATIENT
  public ArrayList<String> getAvailableCpfs(ArrayList<Patient> patients) {
    ArrayList<String> cpfList = new ArrayList<String>();

    for (Patient patient : patients) {
      cpfList.add(patient.cpf);
    }

    return cpfList;
  };

  // ESSE TAMBÉM, ESTÁTICO DA CLASSE DOCTOR
  public Doctor getDoctorByCode(int code, ArrayList<Doctor> doctors) {
    Doctor doctorToReturn = new Doctor(); // PREENCHER COM DADOS PLACEHOLDER (preciso do construtor)

    for (Doctor doctor : doctors) {
      if (doctor.code == code) {
        doctorToReturn = doctor;
      }
    }

    return doctorToReturn;
  }

  // ESSE AQUI, ESTÁTICO DA CLASSE PATIENT
  public Patient getPatientByCpf(String cpf, ArrayList<Patient> patients) {
    Patient patientToReturn = new Patient(); // PREENCHER COM DADOS PLACEHOLDER (preciso do construtor)

    for (Patient patient : patients) {
      if (patient.cpf == cpf) {
        patientToReturn = patient;
      }
    }

    return patientToReturn;
  }

  private Doctor getDoctor(ArrayList<Doctor> doctors) {
    String message = Messages.getDoctorChoiceMessage(doctors);
    int chosenOption = -1;
    ArrayList<Integer> availableCodes = getAvailableDoctorCodes(doctors);
    // AQUI SERÁ Doctor.getAvailableDoctorCodes... , já que é estático

    System.out.print(message);
    chosenOption = this.userScanner.getInt("Digite o código do médico");

    while (!availableCodes.contains(chosenOption)) {
      System.out.println("Digite um código válido");
      chosenOption = this.userScanner.getInt("Digite o código do médico");
    }

    return getDoctorByCode(chosenOption, doctors); // AQUI SERÁ Doctor.getDoctorByCode... , já que é estático
  }

  private Patient getPatient(ArrayList<Patient> patients) {
    String message = Messages.getPatientChoiceMessage(patients);
    String chosenCpf = "";
    ArrayList<String> availableCpfs = getAvailableCpfs(patients);
    // AQUI SERÁ Patient.getAvailableCpfs... , já que é estático

    System.out.print(message);
    chosenCpf = this.userScanner.getCpf("Digite o cpf do paciente");

    while (!availableCpfs.contains(chosenCpf)) {
      System.out.println("Digite um cpf válido");
      chosenCpf = this.userScanner.getCpf("Digite o cpf do paciente");
    }

    return getPatientByCpf(chosenCpf, patients); // AQUI SERÁ Patient.getPatientByCpf... , já que é estático
  }

  private int getMenuChosenOption() {
    String message = Messages.getChoicesMessage();
    ArrayList<Integer> possibleChoices = Messages.getPossibleChoicesList();
    int chosenOption = -1;

    System.out.println(message);
    chosenOption = this.userScanner.getInt("Digite o número da opção escolhida");

    while (!possibleChoices.contains(chosenOption)) {
      System.out.println("Digite uma opção válida");
      chosenOption = this.userScanner.getInt("Digite o número da opção escolhida");
    }

    return chosenOption;
  }

  // Depois melhoramos os nomes dessas variáveis, deixa essas por enquanto
  public void startUserInteraction(ArrayList<Doctor> doctors, ArrayList<Patient> patients) {
    int chosenOption = -1;

    do {
      chosenOption = getMenuChosenOption();

      switch (chosenOption) {
        case 1:
          Doctor doctorToGetPatients = this.getDoctor(doctors);
          // Doctor.getPatients(doctorToGetPatients);
          break;
        case 2:
          Doctor doctorToGetByPeriod = this.getDoctor(doctors);
          String startDate = this.userScanner.getDate("Digite a data de início");
          String endDate = this.userScanner.getDate("Digite a data de fim");
          // Appointments.getAppointmentsByPeriod(doctorToGetByPeriod, startDate,
          // endDate);
          break;
        case 3:
          Patient patientToGetDoctors = this.getPatient(patients);
          // getDoctorsByP
          break;
        case 4:
          Patient patientToGetAppointmentsByDoctor = this.getPatient(patients);
          Doctor doctorToGetAppointmentsByDoctor = this.getDoctor(doctors);
          // getAppByD
          break;
        case 5:
          Patient patientToGetAppointments = this.getPatient(patients);
          // getaAppByP
          break;
        case 6:

          // getMissingP
          break;
        case 7:
          // sair
          break;

      }
    } while (chosenOption != 7);
  }
}
