import java.util.ArrayList;
import Entities.Doctor;

public class Interface {
  public Doctor getDoctor(ArrayList<Doctor> doctors) {
    String message = Messages.getDoctorChoiceMessage(doctors);
    System.out.print(message);

    return doctors.get(0);
  }
}
