package togo.plannumerotation;

/**
 * 
 * @author aristide mendoo.aristide@gmail.com
 * j2me/android developper
 * 
 *  apply the numerotation instruction of TOGO
 *
 */
public class PhoneNumberUpdater {
	String prefix=null;
	
	// code du pays = 228
	public PhoneNumberUpdater() {
	}
	
	//update a phone number
	public String Update(String phoneNumber){		
		return regularNumber(removeSpace(phoneNumber))?applyNumerotationPlan(baseNumber(phoneNumber)):phoneNumber;
	}
	
	public boolean requireUpdate(String phoneNumber){
		return true;
	}
	
	// test if a phone number need to be update 
	public boolean regularNumber(String phoneNumber){
		
		if(phoneNumber.startsWith("00228")){
			
			 if(phoneNumber.substring(5, phoneNumber.length()).length()==7){				  
				 return true;
			 }else {				  
				 return false;
			 }			
						
		}else if(phoneNumber.startsWith("+228")){
						
			 if(phoneNumber.substring(4, phoneNumber.length()).length()==7){				  
				 return true;
			 }else {				  
				 return false;
			 }	
		}else if(phoneNumber.length()==7){			 
			return true;
		}else return false;	
		
	}
	
	// retrieve prefix number 
	public String baseNumber(String phoneNumber){
	    String phone=removeSpace(phoneNumber).trim();	    
	    prefix="";
	    if(phone.startsWith("00228")){
	    	prefix=phone.substring(0,5);
	    	phone=phone.substring(5, phone.length());
	    }else if(phone.startsWith("+228")){
	    	prefix=phone.substring(0,4);
	    	phone=phone.substring(4, phone.length());
	    }	    
	    return phone;	    
	}
	
	// retrieve specials caracteres 
	public String removeSpace(String phoneNumber){		
		String[] cell=null;
		
		if(phoneNumber.trim().contains(" ")) 
			cell=phoneNumber.split(" ");
		else if(phoneNumber.trim().contains("-")){
			cell=phoneNumber.split("-");}
		
		String result=null;
		if(cell!=null){
			result="";
			for(int i=0; i< cell.length;i++){
			  result +=cell[i];
			}		
		}		
	    return (result==null)?phoneNumber:result;	
	}
	
	// add corresponding code to the phone number
    public String applyNumerotationPlan(String phoneNumber){
    
    	if(phoneNumber.length()==7){
    		String new_number="";
    		switch(getCategory(Integer.parseInt(phoneNumber.substring(0, 2)))){
    			case 0:  new_number="2"+phoneNumber;break;
    			case 1:  new_number="90"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 2:  new_number="91"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 3:  new_number="91"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 4:  new_number="92"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 5:  new_number="98"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 6:  new_number="98"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 7:  new_number="98"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 8:  new_number="99"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 9:  new_number="99"+phoneNumber.substring(1,phoneNumber.length());break;
    			case 10: new_number="99"+phoneNumber.substring(1,phoneNumber.length());break;
      }  
    		return prefix+new_number;
    }else{
    	return phoneNumber;
    }
       	
    }
    
    // look for corresponding numerotation code 
    public int getCategory(int deb){
    	
     	    if(deb==22 || deb==23|| deb==24 || deb==25 || deb==26 || deb==27 || deb==32 || deb==33 || deb==44 || deb==55 || deb==66 || deb==77){
     	    	return 0;
     	    }else if(deb==90||deb==91||deb==92||deb==93||deb==97||deb==98||deb==99){
     	    	return 1;
     	    }else if(deb==4||deb==6||deb==7||deb==8 ||deb==9){
     	    	return 2;
     	    }else if(deb==72||deb==73||deb==74||deb==75||deb==76){
     	    	return 3;
     	    }else if(deb==81||deb==82||deb==83||deb==85){
     	    	return 4;    	    	
     	    }else if(deb==1||deb==2||deb==3||deb==5){
     	    	return 5;
     	    }else if(deb==60){
     	    	return 6;
     	    }else if(deb==84||deb==86||deb==87){
     	    	return 7;
     	    }else if(deb==52||deb==53){
     	    	return 8;
     	    }else if(deb==70||deb==71){
     	    	return 9;
     	    }else if(deb==94||deb==95||deb==96){
     	    	return 10;
     	    }else{
     	    	return 11;
     	    }        	    	
    }
}
