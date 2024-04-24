package UserInterface;

import java.util.ArrayList;

import Entities.Doctor;
import Entities.Patient;

public class Interface {
  UserScanner userScanner;

  public Interface() {
    this.userScanner = new UserScanner();
  }

  private Doctor getDoctor(ArrayList<Doctor> doctors) {
    String message = Messages.getDoctorChoiceMessage(doctors);
    int chosenOption = -1;
    ArrayList<Integer> availableCodes = Doctor.getAvailableDoctorCodes(doctors);

    System.out.print(message);
    chosenOption = this.userScanner.getInt("Digite o código do médico");

    while (!availableCodes.contains(chosenOption)) {
      System.out.println("Digite um código válido");
      chosenOption = this.userScanner.getInt("Digite o código do médico");
    }

    return Doctor.getDoctorByCode(chosenOption, doctors);
  }

  private Patient getPatient(ArrayList<Patient> patients) {
    String message = Messages.getPatientChoiceMessage(patients);
    String chosenCpf = "";
    ArrayList<String> availableCpfs = Patient.getAvailableCpfs(patients);

    System.out.print(message);
    chosenCpf = this.userScanner.getCpf("Digite o cpf do paciente");

    while (!availableCpfs.contains(chosenCpf)) {
      System.out.println("Digite um cpf válido");
      chosenCpf = this.userScanner.getCpf("Digite o cpf do paciente");
    }

    return Patient.getPatientByCpf(chosenCpf, patients);
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

          System.out.println("Aqui está a lista de pacientes de " + doctorToGetPatients.name + ":");
          System.out.println(Messages.getPatientsListMessage(doctorToGetPatients.getPatients()));
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
          Doctor doctorToGetMissingPatients = this.getDoctor(doctors);
          int months = this.userScanner.getInt("Digite a quantidade de meses para filtrar");
          // getMissingP
          break;
        case 7:
          System.out.println("Obrigado por utilizar nosso sistema!");
          System.out.println("Saindo...");
          break;

      }
    } while (chosenOption != 7);
  }
}
