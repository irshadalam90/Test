package controller;

public class PatternProgrmao {

	public static void main(String[] args) {
	int n=5;
	int z=5;
	for(int i=n;i>=1;i--){
		for(int j=5;j>i;j--){
			System.out.print(" ");
		}
		
		for(int k=1;k<=z;k++)
		{
			System.out.print(k);
		}
		z--;
		System.out.println();
	}
	
	}

}
