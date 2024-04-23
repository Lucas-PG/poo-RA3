package Entities;

import java.util.ArrayList;

public class Doctor {
  private String name;
  private int code;
  ArrayList<Patient> patients;

  public Doctor(String name, int code, ArrayList<Patient> patients){
    this.name = name;
    this.code = code;
    this.patients = patients;
  }

  

}