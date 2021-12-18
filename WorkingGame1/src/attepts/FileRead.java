package attepts;

import java.io.FileReader;
import java.util.Scanner;

public class FileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("Base.txt"); 
            Scanner scan = new Scanner(fr);
            for (int i =0; i<6;i++) {
            	System.out.println(scan.nextLine());
            }
            scan.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}
		
	}

}
