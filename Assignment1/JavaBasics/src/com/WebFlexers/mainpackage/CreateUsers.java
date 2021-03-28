package com.WebFlexers.mainpackage;

import java.io.IOException;
import java.util.Scanner;

public class CreateUsers {

    public static void main(String[] args) {
    	
    	Admin admin_user = new Admin("admin", "1234567890LT", "25670GTA!", "LEFTERIS", "KONTOURIS");
    	System.out.println("Admin: " + admin_user.getUsername() + ", "+ admin_user.getPassword() + ", " + admin_user.getSuperuserPassword() + ", " + admin_user.getName() + " " + admin_user.getSurname());
    	System.out.println("***********Admin created***********\n");   	  	
    	
    	Doctor doctor_user = new Doctor("kostas2001", "26483dgdun", "KOSTAS", "KALOGEROPOULOS");
    	System.out.println("Doctor: " + doctor_user.getUsername() + ", "+ doctor_user.getPassword() + ", " + doctor_user.getName() + " " + doctor_user.getSurname());
    	System.out.println("***********Doctor created***********\n");   
    	
    	//4.6
    	Scanner scan = new Scanner(System.in);
    	
    	String username;
    	String password;
    	String name;
    	String surname;
    	
    	System.out.println("-------Main Menu-------");
    	System.out.println("1. Admin");
    	System.out.println("2. Doctor");
    	System.out.println("3. Patient");
    	
    	int selection;
    	do
    	{   
    		System.out.print("Choose entity to add: ");
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
        	}
        	catch (Exception e)
        	{
        		System.out.println("Invalid username. Try again");
        	}
    		break;
    	}  	
    	
    	while(true)
    	{
    		try
        	{
    			System.out.print("Password: ");
    			password = scan.nextLine();
        	}
        	catch (Exception e)
        	{
        		System.out.println("Invalid password. Try again");
        	}
    		break;
    	}
    	
    	while(true)
    	{
    		try
        	{
    			System.out.print("Name: ");
    			name = scan.nextLine();
        	}
        	catch (Exception e)
        	{
        		System.out.println("Invalid name. Try again");
        	}
    		break;
    	}
    	    	
    	while(true)
    	{
    		try
        	{
    			System.out.print("Surname: ");
    			surname = scan.nextLine();
        	}
        	catch (Exception e)
        	{
        		System.out.println("Invalid surname. Try again");
        	}
    		break;
    	}  
    	
    	if(selection == 1)
    	{
    		System.out.println("Admin created!");
    	}
    	else if(selection == 2)
    	{
    		System.out.println("Doctor created!");
    	}
    	else
    	{
    		System.out.println("Patient created!");
    	}
    }
}

