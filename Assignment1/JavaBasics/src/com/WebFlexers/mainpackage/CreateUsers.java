package com.WebFlexers.mainpackage;

import java.io.IOException;
import java.util.Scanner;

public class CreateUsers {

    public static void main(String[] args) {
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
    		System.out.print("Choose role: ");
        	selection = scan.nextInt(); 
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

