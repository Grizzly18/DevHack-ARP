package attepts;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FileWrite {

	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("BaseAttept.txt");
		FileWriter fw = new FileWriter("BaseAttept.txt");
		Scanner scan = new Scanner(fr);
		for (int i = 0; i < 5; i++) {
			fw.write("\nGGGG HHHH \njjj KKK");
		}
		while (scan.hasNextLine() == true) {
			System.out.println(scan.nextLine());
		}
		scan.close();
		fw.close();
	}

}
