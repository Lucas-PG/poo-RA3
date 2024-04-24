package UserInterface;

import java.util.ArrayList;

import Entities.Doctor;
import Entities.Patient;

public class Messages {
  public static String getChoicesMessage() {
    String message = "Escolha uma opção:\n";
    message += "1 - Obter os pacientes de um determinado médico\n";
    message += "2 - Obter as consultas para um determindado médico em um dado intervalo de tempo\n";
    message += "3 - Obter os médicos que um determinado paciente já consultou ou tem consultas agendadas\n";
    message += "4 - Obter as consultas que um determinado paciente realizou com determinado médico\n";
    message += "5 - Obter as consultas agendadas de um determinado paciente\n";
    message += "6 - Obter os pacientes que um determinado médico não consulta em um dado período.\n";
    message += "7 - Sair\n";

    return message;
  }

  public static ArrayList<Integer> getPossibleChoicesList() {
    return new ArrayList<Integer>() {
      {
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
        add(7);
      }
    };
  }

  public static String getDoctorChoiceMessage(ArrayList<Doctor> doctors) {
    String message = "Escolha um médico (Digite o respectivo código):\n";
    for (Doctor doctor : doctors) {
      message += doctor.code + " - " + doctor.name + "\n";
    }

    return message;
  }

  public static String getPatientChoiceMessage(ArrayList<Patient> patients) {
    String message = "Escolha um médico (Digite o respectivo código):\n";
    for (Patient patient : patients) {
      message += patient.cpf + " - " + patient.name + "\n";
    }

    return message;
  }
}
