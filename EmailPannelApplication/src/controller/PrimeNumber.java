package controller;

import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		int temp,i;
	  
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter any number:");
		int num=scan.nextInt();
        for(i=2; i<=num/2;i++){
        	temp=num%i;
        	if(temp==0){
        		System.out.println("this is prime number");
        	}
        	else{
        		System.out.println("this is not 5prime number");
        	}
        }
	
	}
}
