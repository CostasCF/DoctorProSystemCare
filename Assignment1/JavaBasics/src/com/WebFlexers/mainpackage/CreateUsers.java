package com.WebFlexers.mainpackage;

import java.io.IOException;
import java.util.Scanner;

public class CreateUsers {

    public static void main(String[] args) {
    	
    	Admin admin_user = new Admin("admin", "1234567890LT", "25670GTA!", "LEFTERIS", "KONTOURIS");
    	System.out.println("Admin: " + admin_user.getUsername() + ", "+ admin_user.getPassword() + ", " + admin_user.getSuperuserPassword() + ", " + admin_user.getName() + " " + admin_user.getSurname());
    	System.out.println("Admin created\n");   	  	
    	
    	Doctor doctor_user = new Doctor("kostas2001", "26483dgdun", "KOSTAS", "KALOGEROPOULOS");
    	System.out.println("Doctor: " + doctor_user.getUsername() + ", "+ doctor_user.getPassword() + ", " + doctor_user.getName() + " " + doctor_user.getSurname());
    	System.out.println("Doctor created\n");
    	
    	admin_user.InsertDoctor(doctor_user);
    	admin_user.DeleteDoctor(doctor_user);
    	System.out.print("\n");
    	
    	Patient patient_user = new Patient("MichaelX26", "89054809HDHJ", "MIXALIS", "STYLIANIDIS", "280501014523");
    	System.out.println("Patient: " + patient_user.getUsername() + ", "+ patient_user.getPassword() + ", " + patient_user.getName() + " " + patient_user.getSurname() + ", " + patient_user.getAmka());
    	System.out.println("Patient created");
    	
    	//4.6
    	Scanner scan = new Scanner(System.in);
    	
    	String username;
    	String password;
    	String name;
    	String surname;
    	
    	System.out.println("-------Main Menu-------");
    	System.out.println("1. Doctor");
    	System.out.println("2. Patient");
    	
    	int selection;
    	do
    	{   
    		System.out.print("Choose entity to add (enter number): ");
        	selection = scan.nextInt(); 
        	scan.nextLine();
    	}
    	while(selection < 1 || selection > 3);
    	
    	while(true)
    	{
    		try
        	{
    			System.out.print("Username: ");
        		username = scan.nextLine();
				break;
        	}
        	catch (Exception e)
        	{
				System.out.println(e.getMessage());
        		System.out.println("Invalid username. Try again");
        	}
    	}  	
    	
    	while(true)
    	{
    		try
        	{
    			System.out.print("Password: ");
    			password = scan.nextLine();
				break;
        	}
        	catch (Exception e)
        	{
        		System.out.println("Invalid password. Try again");
        	}
    	}
    	
    	while(true)
    	{
    		try
        	{
    			System.out.print("Name: ");
    			name = scan.nextLine();
				break;
        	}
        	catch (Exception e)
        	{
        		System.out.println("Invalid name. Try again");
        	}
    	}
    	    	
    	while(true)
    	{
    		try
        	{
    			System.out.print("Surname: ");
    			surname = scan.nextLine();
				break;
        	}
        	catch (Exception e)
        	{
        		System.out.println("Invalid surname. Try again");
        	}
    	}  
    	
    	if(selection == 1)
    	{
    		System.out.println("Doctor created!");
    	}
    	else
    	{
    		System.out.println("Patient created!");
    	}
    }
}

