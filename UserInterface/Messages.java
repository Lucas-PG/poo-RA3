package UserInterface;

import java.util.ArrayList;

import Entities.Appointment;
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
    message += "7 - Marcar consulta.\n";
    message += "8 - Sair\n";

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
        add(8);
      }
    };
  }

  public static String getDoctorsListMessage(ArrayList<Doctor> doctors) {
    String message = "";

    if (doctors.size() == 0) {
      return "Nenhum doutor encontrado \n";
    }

    for (Doctor doctor : doctors) {
      message += "Código: " + doctor.code + " > " + doctor.name + "\n";
    }

    return message;
  }

  public static String getPatientsListMessage(ArrayList<Patient> patients) {
    String message = "";

    if (patients.size() == 0) {
      return "Nenhum paciente encontrado \n";
    }

    for (Patient patient : patients) {
      message += "CPF: " + patient.cpf + " > " + patient.name + "\n";
    }

    return message;
  }

  public static String getAppointmentsListMessage(ArrayList<Appointment> appointments) {
    String message = "";

    if (appointments.size() == 0) {
      return "Nenhuma consulta encontrada \n";
    }

    for (Appointment appointment : appointments) {
      message += "Data - hora: " + appointment.date + " - " + appointment.time + " > Médico:" + appointment.doctor.name
          + " > Paciente:" + appointment.patient.name + "\n";
    }

    return message;
  }

  public static String getDoctorChoiceMessage(ArrayList<Doctor> doctors) {
    String message = "\nEscolha um médico (Digite o respectivo código):\n";
    message += getDoctorsListMessage(doctors);
    return message;
  }

  public static String getPatientChoiceMessage(ArrayList<Patient> patients) {
    String message = "\nEscolha um paciente (Digite o respectivo cpf):\n";
    message += getPatientsListMessage(patients);
    return message;
  }
}