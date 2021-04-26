import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileCopy {
	
	private static void copyCharByChar(File source, File dest) throws IOException {
		long start = System.nanoTime();
		InputStream in = null;
	    OutputStream out = null;
	    
	    try {
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			int length = (int) source.length();
			byte[] buffer = new byte[length];
			while ((length = in.read(buffer)) > 0) {
			     out.write(buffer, 0, length);
			  }
		} finally {
			in.close();
			out.close();
			System.out.println("--File copied successfully! Time taken "+((System.nanoTime()-start)/1000)+" microseconds--");
		}
	}
	
	private static void copyLineByLine(String source,String dest) {
		long start = System.nanoTime();
		BufferedReader br = null;
	    PrintWriter pw = null;
	    
	    try {
			br = new BufferedReader(new FileReader( source ));
			pw =  new PrintWriter(new FileWriter( dest ));
			String line;
			
			while ((line = br.readLine()) != null) {
			  pw.println(line);
			}
			br.close();
			pw.close();
			System.out.println("--File copied successfully! Time taken "+((System.nanoTime()-start)/1000)+" microseconds--");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int again = 1;
		while (again != 0) {
			System.out.print("Enter the path of the source file : ");
			String source = sc.nextLine();
			System.out.print("Enter the path of the dest file : ");
			String dest = sc.nextLine();
			System.out.println("Do you want to :");
			System.out.println("1-Copy line by line");
			System.out.println("2-Copy character by character");
			System.out.print("-->");
			int option = sc2.nextInt();
			if (option == 1) {
				copyLineByLine(source, dest);
			} else {
				File src = new File(source);
				File dst = new File(dest);
				copyCharByChar(src, dst);
			}
			System.out.println("Do you want to copy another file ? 1 for yes,0 for no");
			System.out.print("-->");
			again = sc2.nextInt();
		}
		sc.close();
		sc2.close();
		System.out.println("Finished !");		
	}

}
