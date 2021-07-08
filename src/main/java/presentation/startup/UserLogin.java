package presentation.startup;
import java.util.Scanner;
import persistence.startup.dao.UserLoginDAO;
import persistence.startup.daoImpl.UserLoginDAOImpl;
import persistence.startup.model.Login;
import persistence.startup.util.UserLoginUtil;
import persistence.startup.utilImpl.UserLoginUtilImpl;
import presentation.CommonConstants;
import presentation.ScreenTitles;
import presentation.patient.RegisterPatientOutput;


public class UserLogin{
	public boolean LoginUser(){
	
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		System.out.println(CommonConstants.titleSpace+ScreenTitles.login+CommonConstants.titleSpace);
		for(int i=0; i<100; i++)
			System.out.print(CommonConstants.headingChar);
		System.out.println();
		System.out.print("Enter email : ");
        Scanner sc = new Scanner(System.in);
        String userId = sc.next();
        System.out.print("Enter password : ");
        String password = sc.next();
        int length= password.length();
        String hidden = "";
        for(int i=0;i<length;i++){
        	hidden+="*";
        }
        Login l = new Login();
        l.setUserPassword(password);
        l.setUserEmail(userId);
        
        System.out.println();
        System.out.println("Email : "+userId);
        System.out.println("Password : "+hidden);
        System.out.println();
        System.out.println();
		int sel;
		while(true){
			System.out.println("1. Continue");
			System.out.println("2. Patient Sign Up");
			System.out.println("3. Exit");
		
			System.out.println("Please enter your selection below:");
			sel = sc.nextInt();
			switch(sel){
			case 1:
				 UserLoginUtil util= new UserLoginUtilImpl();
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
					 UserLoginDAO dao= new UserLoginDAOImpl();
					 System.out.println(dao.GetuserDetails(l));
					 break;
					 }
				
			case 2:
			    RegisterPatientOutput obj= new RegisterPatientOutput();
			    obj.RegisterPatient(); 
				break;
			case 3:
				System.out.print("EXIT!");
				return false;
			
	    	}
		 
	  }
	  
	    
	    
	}
	
}