package presentation.patient;

import java.util.Scanner;

import persistence.patient.dao.RegistrationDAO;
import persistence.patient.daoImpl.RegistrationDAOImpl;
import persistence.patient.model.Patient;
import persistence.patient.util.RegistrationUtil;
import persistence.patient.utilImpl.RegistrationUtilImpl;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.daoImpl.UserLoginDAOImpl;
import persistence.startup.util.UserLoginUtil;
import persistence.startup.utilImpl.UserLoginUtilImpl;
import presentation.CommonConstants;
import presentation.ScreenFields;
import presentation.ScreenTitles;
import presentation.startup.UserLogin;

public class RegisterPatientOutput {
	
	public boolean RegisterPatient(){
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		System.out.println(CommonConstants.titleSpace+ScreenTitles.signUp+CommonConstants.titleSpace);
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		Scanner sc = new Scanner(System.in);
		int sel;
		System.out.print(ScreenFields.emailInput);
        String userId = sc.next();
        System.out.print(ScreenFields.passwordInput);
        String password = sc.next();
        int length= password.length();
        String hidden = "";
        for(int i=0;i<length;i++){
        	hidden+="*";
        }
        if(userId.endsWith("healthifyyou.com")) {
    		System.out.println("You are not allowed to Register !");
    		return false;
    	}
        Patient p= new Patient();
        p.setPassword(password);
        p.setPatientEmail(userId);
        p.setPatientType("P");
        System.out.println();
        System.out.println(ScreenFields.emailIdOutput+"= "+userId);
        System.out.println(ScreenFields.passwordOutput+"= "+hidden);
        System.out.println();
        System.out.println();
		while(true){
			System.out.println("1. " +ScreenFields.proceed);
			System.out.println("2. Login");
			System.out.println("3. " +ScreenFields.exit);
		 	System.out.println(ScreenFields.getInput+":");
			sel = sc.nextInt();
			switch(sel){
			case 1:
				 RegistrationUtil util= new RegistrationUtilImpl();
				 
				 if(util.ValidateEmail(userId)!=null){
					 System.out.println(util.ValidateEmail(userId));
					 break;
				 }
				 else 
				 if(util.ValidatePassword(password)!=null){
					 System.out.println(util.ValidatePassword(password));
					 break;
				 }
				 else{
					    for(int i=0; i<100; i++)
							System.out.print(CommonConstants.headingChar);
						System.out.println();
						System.out.println(CommonConstants.titleSpace+ScreenTitles.signUp+CommonConstants.titleSpace);
						for(int i=0; i<100; i++)
							System.out.print(CommonConstants.headingChar);
						System.out.println();
					 	System.out.println(CommonConstants.mediumSpace+CommonConstants.subheading+ScreenTitles.registration+CommonConstants.subheading+CommonConstants.titleSpace);
					    System.out.println();
					    System.out.println(ScreenFields.getInput);
					    System.out.println(ScreenFields.firstNameInput);
				    	String fname= sc.next();		
				    	System.out.println(ScreenFields.lastNameInput);
				    	String lname= sc.next();
				    	System.out.println(ScreenFields.birthDateInput);
				    	String DOB= sc.next();
				    	p.setPatientDob(DOB);
				    	System.out.println(ScreenFields.contactInput);
				    	Long contact= sc.nextLong();
				    	p.setPatientContact(contact);
				    	System.out.println(ScreenFields.cityInput);
				    	String city= sc.next();
				    	p.setPatientAddress(city);
				    	System.out.println(ScreenFields.gender);
				    	String gender= sc.next();
				    	p.setPatientGender(gender);
                        p.setPatientType("P");
                        String name= fname+" "+lname;
	    	        	p.setPatientName(name);
				    	System.out.println(ScreenFields.selection);
						System.out.println("1. Confirm to proceed to register");
						System.out.println("2." +ScreenFields.goBack);
						System.out.println();
					 	System.out.println(ScreenFields.selection+": ");
				    	int sel2= sc.nextInt();
				    	
				    	switch(sel2){
				    	case 1: if(util.ValidateDate(DOB)!=null){
				    				System.out.println(util.ValidateDate(DOB));	
				    				break;
				    			}
				    	        else 
				    	        if(util.ValidateContact(contact)!=null){
				    	        	System.out.println(util.ValidateContact(contact));
				    	        	break;
				    	        }
				    	        else
				    	        if(util.ValidateNames(fname)!=null && util.ValidateNames(lname)!=null){
				    	        	System.out.println(util.ValidateNames(lname));
				    	        	break;
				    	        }
				    	        else
				    	        if(util.ValidateCity(city)!=null){
				    	        	System.out.println(util.ValidateCity(city));
				    	        }
				    	        else{
				    	        	RegistrationDAO dao= new RegistrationDAOImpl();
				    	        	System.out.println(dao.addPatientDetails(p));
				    	        
				    	        	break;
				    	        }
				    	case 2:System.out.println(ScreenFields.goBack);
				    		break;
				    	}
					    break;
				     }
				
			case 2:
			    UserLogin obj= new UserLogin();
			    obj.LoginUser();
				break;
		
			case 3:
				System.out.println(ScreenFields.exit);
				return false;
			
	    	}
		 
	  }

	}
}
