package Entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Patient {
  public String name;
  public String cpf;
  public ArrayList<Appointments> appointments;

  public Patient(String name, String cpf){
    this.name = name;
    this.cpf = cpf;
    this.appointments = new ArrayList<Appointments>();
  }

  // Quais são todos os médicos que um determinado paciente já consultou ou tem
  // consulta agendada?
  public ArrayList<Doctor> getDoctorsByPatient(){

    ArrayList<Doctor> doctorsByPatient = new ArrayList<Doctor>();

    for(Appointments a: appointments){
      if(a.patient == this){
        doctorsByPatient.add(a.doctor);
      }
    }

    return doctorsByPatient;
  }


  /* 
    Quais são todas as consultas que um determinado paciente realizou com
    determinado médico? (Somente consultas realizadas em um tempo passado são
    consideradas.)
  */
  public ArrayList<Appointments> getAppointmentsByDoctor(Doctor doctor){
    ArrayList<Appointments> appointmentsByDoctor = new ArrayList<Appointments>();

    for(Appointments a: appointments){
      if(a.doctor == doctor && (a.date.isBefore(LocalDate.now()) || (a.date.isEqual(LocalDate.now()) && a.time.isBefore(LocalTime.now())))){
        appointmentsByDoctor.add(a);
      }
    }
    return appointmentsByDoctor;
  }

  public ArrayList<Appointments> getFutureAppointment(){
    ArrayList<Appointments> futureAppointments = new ArrayList<Appointments>();
    for(Appointments a: appointments){
      if(a.date.isAfter(LocalDate.now()) || (a.date.isEqual(LocalDate.now()) && a.time.isAfter(LocalTime.now()))){
        futureAppointments.add(a);
      }      
    }

    return futureAppointments;
    
  }

  public void addApointment(Appointments appointment){
    appointments.add(appointment);
  }

 

}
