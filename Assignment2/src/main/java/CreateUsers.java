import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class CreateUsers {

	/**
	 * Handles user input
	 */
	public static class UserInput {

		/**
		 * Prints out the displayMessage and infinitely loops until the user gives a valid int value
		 * that is between the provided bounds. If the input format is invalid the errorMessage is displayed
		 * @param lowBound The lowest value that can be returned
		 * @param upperBound The highest value that can be returned
		 * @param displayMessage The message that will be printed at the start
		 * @param errorMessage The message that will be printed in case of invalid input
		 * @return The user input
		 */
		public static int getIntInput(int lowBound, int upperBound, String displayMessage, String errorMessage) {
			int selection = 0;
			do
			{
				Scanner scanner = new Scanner(System.in);
				System.out.print(displayMessage);
				try {
					selection = scanner.nextInt();
					if (selection < lowBound || selection > upperBound) {
						System.out.println("The given value is out of bounds. Type an integer between "
											+ lowBound + " and " + upperBound);
					}
 				} catch (InputMismatchException e) {
					System.out.println(errorMessage);
				} catch (Exception e) {
					System.out.println("An unusual error has occurred. Try again");
				}
			} while (selection < lowBound || selection > upperBound);
			return selection;
		}

		/**
		 * Prints out the displayMessage and infinitely loops until the user gives a valid input.
		 * If the input format is invalid the errorMessage is displayed
		 * @param displayMessage The message that will be printed at the start
		 * @param errorMessage The message that will be printed in case of invalid input
		 * @return The user input
		 */
		public static String getStringInput(String displayMessage, String errorMessage) {
			while(true) {
				Scanner scanner = new Scanner(System.in);
				try {
					System.out.print(displayMessage);
					return scanner.nextLine();
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					System.out.println(errorMessage);
				} catch (Exception e) {
					System.out.println("An unusual error has occurred. Try again");
				}
			}
		}
	}

    public static void main(String[] args) {

		// Create sample objects of each class
    	Admin admin_user = new Admin("admin", "1234567890LT", "25670GTA!", "LEFTERIS", "KONTOURIS");
    	System.out.println("Admin: " + admin_user.getUsername() + ", "+ admin_user.getPassword() + ", " + admin_user.getSuperuserPassword()
							+ ", " + admin_user.getName() + " " + admin_user.getSurname());
    	System.out.println("Admin created\n");   	  	
    	
    	Doctor doctor_user = new Doctor("kostas2001", "26483dgdun", "KOSTAS", "KALOGEROPOULOS", Doctor.Specialty.internist);
    	System.out.println("Doctor: " + doctor_user.getUsername() + ", "+ doctor_user.getPassword() + ", " + doctor_user.getName() + " "
							+ doctor_user.getSurname() + " " + doctor_user.getSpecialty().toFirstLetterUppercase());
    	System.out.println("Doctor created\n");
    	
    	admin_user.InsertDoctor(doctor_user);
    	admin_user.DeleteDoctor(doctor_user);
    	System.out.print("\n");
    	
    	Patient patient_user = new Patient("MichaelX26", "89054809HDHJ", "MIXALIS", "STYLIANIDIS", "280501014523");
    	System.out.println("Patient: " + patient_user.getUsername() + ", "+ patient_user.getPassword() + ", " + patient_user.getName()
							+ " " + patient_user.getSurname() + ", " + patient_user.getAmka());
    	System.out.println("Patient created\n");

    	// Print a menu for choosing what type of user to add
    	System.out.println("-------Main Menu-------");
    	System.out.println("1. Doctor");
    	System.out.println("2. Patient");

    	// Select type
    	int entitySelection = UserInput.getIntInput(1, 3, "Choose entity to add (enter number): ", "Invalid input. Only integers are acceptable");

    	// Get input for the common attributes
		String username = UserInput.getStringInput("Username: ", "Invalid username. Try again");
		String password = UserInput.getStringInput("Password: ", "Invalid password. Try again");
		String name = UserInput.getStringInput("Name: ","Invalid name. Try again");
		String surname = UserInput.getStringInput("Surname: " ,"Invalid surname. Try again");

    	if (entitySelection == 1)
    	{
    		// Create doctor
			System.out.println("Select doctor specialty according to the following menu: ");

			// Print all the possible specialties of the Specialty enum
			var allDoctorSpecialties = Doctor.Specialty.values();
			for (int i = 0; i < allDoctorSpecialties.length; i++) {
				System.out.println(i+1 + ". " + allDoctorSpecialties[i]);
			}

			// Ask the user to select a specialty
    		int selectedSpecialtyIndex = UserInput.getIntInput(1, allDoctorSpecialties.length, "Selection: ", "Invalid input. Only integers are acceptable");
			selectedSpecialtyIndex--;

			// Create a doctor object and use it's methods
			Doctor doctor = new Doctor(username, password, name, surname, allDoctorSpecialties[selectedSpecialtyIndex]);
    		System.out.println(doctor.getSpecialty().toFirstLetterUppercase() + " created!");

			doctor.insertDateAvailability("24/7");
    		doctor.viewAppointmentAvailability();
    		// doctor.cancelAppointment(anAppointment);
    	}
    	else
    	{
    		// Create patient
    		String amka = UserInput.getStringInput("AMKA: ", "Invalid AMKA. Try again");
    		Patient patient = new Patient(username, password, name, surname, amka);
    		System.out.println("Patient created!");

    		// Patient methods. Some of them have been commented out,
			// because they require parameters that can't be provided at the moment
			patient.registerUser();
    		//patient.getAvailableAppointments(aDoctor);
    		//patient.addAppointment(aNewAppointment);
    		//patient.cancelAppointment(theAppointmentToBeCancelled);
    		//patient.replaceAppointment(theOldAppointment, theNewAppointment);
			//patient.setScheduledAppointments(aListOfAppointments);
			patient.viewAppointmentHistory();
    	}

    	// Read a patient from txt
		File file = new File(System.getProperty("user.dir") + "\\patient.txt");
		BufferedReader bufferedReader;

    	try {
    		bufferedReader = new BufferedReader(new FileReader(file));

			String str;
			while ((str = bufferedReader.readLine()) != null)
			{
				String[] fields = str.split("\\s+");
				Patient patient = new Patient(fields[0], fields[1], fields[2], fields[3], fields[4]);
				System.out.println("New patient created from text file:");
				System.out.println("Patient: " + patient.getUsername() + ", "+ patient.getPassword() + ", " + patient.getName()
						+ " " + patient.getSurname() + ", " + patient.getAmka());
			}
		} catch (FileNotFoundException e) {
    		System.out.println("The specified file was not found");
		} catch (Exception e) {
			System.out.println("An unusual error has occurred while reading the file");
		}

    }
}