package UserInterface;

import java.time.LocalDate;
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

  private void firstOption(ArrayList<Doctor> doctors) {
    Doctor doctor = this.getDoctor(doctors);

    System.out.println("Aqui está a lista de pacientes de " + doctor.name + ":");
    System.out.println(Messages.getPatientsListMessage(doctor.getPatients()));
  }

  private void secondOption(ArrayList<Doctor> doctors) {
    Doctor doctor = this.getDoctor(doctors);
    String startDate = this.userScanner.getDate("Digite a data de início");
    String endDate = this.userScanner.getDate("Digite a data de fim");
    
    System.out.println("Aqui está a lista de consultas do doutor " + doctor.name + "\n");
    System.out.println("No período entre " + startDate + " e " + endDate + ":\n");
    System.out.println(Messages.getAppointmentsListMessage(doctor.getAppointmentsByPeriod(LocalDate.parse(startDate), LocalDate.parse(endDate))));
  }

  private void thirdOption(ArrayList<Patient> patients) {
    Patient patient = this.getPatient(patients);

    System.out.println("Aqui está a lista de médicos que " + patient.name + " já consultou:");
    System.out.println(Messages.getDoctorsListMessage(patient.getDoctorsByPatient()));
  }

  private void fourthOption(ArrayList<Patient> patients, ArrayList<Doctor> doctors) {
    Patient patient = this.getPatient(patients);
    Doctor doctor = this.getDoctor(doctors);

    System.out.println("Aqui está a lista de consultas de " + patient.name + " com o médico(a) " + doctor.name + ":");
    System.out.println(Messages.getAppointmentsListMessage(patient.getAppointmentsByDoctor(doctor)));
  }

  private void fitfhOption(ArrayList<Patient> patients) {
    Patient patient = this.getPatient(patients);

    System.out.println("Aqui está a lista de consultas marcadas de " + patient.name + ":");
    System.out.println(Messages.getAppointmentsListMessage(patient.getFutureAppointments()));
  }

  private void sixthOption(ArrayList<Doctor> doctors) {
    Doctor doctor = this.getDoctor(doctors);
    int months = this.userScanner.getInt("Digite a quantidade de meses para filtrar");

    System.out.println("Aqui estão os pacientes que não se consultam com " + doctor.name + " há " + months + " meses:");
    System.out.println(Messages.getPatientsListMessage(doctor.getMissingPatients(months)));
  }

  // Depois melhoramos os nomes dessas variáveis, deixa essas por enquanto
  public void startUserInteraction(ArrayList<Doctor> doctors, ArrayList<Patient> patients) {
    int chosenOption = -1;

    do {
      chosenOption = getMenuChosenOption();

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
          System.out.println("Obrigado por utilizar nosso sistema!");
          System.out.println("Saindo...");
          break;

      }
    } while (chosenOption != 7);
  }
}
