/****************************************
 * Adam Tracy                           *
 * Countries of the World Assignment 1  *
 * Pretty Print                         *
 ***************************************/
package cotw1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PrettyPrintUtility {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//declare variables
		boolean append = true;
		final int MAX_ARRAY_SIZE = 300;
		String[] codeArr = new String[MAX_ARRAY_SIZE];
		int[] idArr = new int[MAX_ARRAY_SIZE];
		String[] counNameArr = new String[MAX_ARRAY_SIZE];
		String[] contArr = new String[MAX_ARRAY_SIZE];
		float[] areaArr = new float[MAX_ARRAY_SIZE];
		long[] popArr = new long[MAX_ARRAY_SIZE];
		float[] lifeExpArr = new float[MAX_ARRAY_SIZE];
	    String[] cnKeyArr = new String[MAX_ARRAY_SIZE];
		int[] idDRPArr = new int[MAX_ARRAY_SIZE];
		String temp[];
		String line;
		int highLoc = 0;
		File file = new File("Log.txt");
		FileWriter write = new FileWriter(file, append);
		PrintWriter p = new PrintWriter(write);
		File file2 = new File("Backup.txt");
		File backupFile = new File("NIBackup.txt");
		Scanner b2File = new Scanner(backupFile);
		Scanner bFile = new Scanner(file2);
		
		//********************************************************

		//read from the backup file into arrays
		while(bFile.hasNext()){
		 line = bFile.nextLine();
		 temp = line.split(",");
		 int loc = Integer.parseInt(temp[1]);
			codeArr[loc] = temp[0];
			idArr[loc] = Integer.parseInt(temp[1]);
			counNameArr[loc] = temp[2];
			contArr[loc] = temp[3];
			areaArr[loc] = Float.parseFloat(temp[4]);
			popArr[loc] = Long.parseLong(temp[5]);
			lifeExpArr[loc] = Float.parseFloat(temp[6]);
			if(loc > highLoc){
				highLoc = loc;
			}
		}
		bFile.close();		
		
		//print data to log file if within highest location
		p.printf("N: " + highLoc + "%n");	
		p.printf("CDE -ID Name------------------------------------------Continent--------------Area--- ---Population Life%n");
		for(int i = 0; i < highLoc; i++){
			if(codeArr[i] != null){
				p.printf("%s %03d %-45s %-17s %,12.1f %,13d %3.1f %n",
						codeArr[i], idArr[i], counNameArr[i], contArr[i],
						areaArr[i], popArr[i], lifeExpArr[i]);
			}
			else{
				p.printf("Location Empty%n");
			}
		}
		
		p.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");
		
		
		
		
		
		//****************************************************************************************************
		//same thing for name index reads name index back and prints within highest location
		p.printf("-ID Name-------------------------------------------------------------%n");
		
		while(b2File.hasNext()){
			line = b2File.nextLine();
			temp = line.split(",");
			int loc = Integer.parseInt(temp[0]);
			idDRPArr[loc] = Integer.parseInt(temp[0]);
			cnKeyArr[loc] = temp[1];			
		}
		b2File.close();
		
		for(int i = 0; i < highLoc; i++){
			if(cnKeyArr[i] != null){
			p.printf("%03d %-45s%n", idDRPArr[i], cnKeyArr[i]);
			}
			else{
				p.printf("Location Empty%n");
			}
		}

		p.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");		
		p.close();
	
	}

}
