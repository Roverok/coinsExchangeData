package com.coinsExchangeData.model.maping;

public class User{
   	private String gender;
   	private Name name;
   	private byte[] userImage;
   	private boolean verified;
   	
   	public static class Name {
   	   	private String first;
   	   	private String last;
   	   	private int age;

   	 	public String getFirst(){
   			return this.first;
   		}
   		public void setFirst(String first){
   			this.first = first;
   		}
   	 	public String getLast(){
   			return this.last;
   		}
   		public void setLast(String last){
   			this.last = last;
   		}   		
   	}
   	
	public String getGender(){
		return this.gender;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
 	public Name getName(){
		return this.name;
	}
	public void setName(Name name){
		this.name = name;
	}
 	public byte[] getUserImage(){
		return this.userImage;
	}
	public void setUserImage(byte[] userImage){
		this.userImage = userImage;
	}
 	public boolean isVerified(){
		return this.verified;
	}
	public void setVerified(boolean verified){
		this.verified = verified;
	}
}
