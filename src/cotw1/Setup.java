/****************************************
 * Adam Tracy                           *
 * Countries of the World Assignment 1  *
 * Setup                                *
 ***************************************/
package cotw1;

import java.io.*;
import java.util.Scanner;


public class Setup {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//declare variables
		String fileNameSuffix = args[0];
		RawData rd = new RawData(); // object to take in country data
		DataTable dt = new DataTable();
		NameIndex ni = new NameIndex();
		File file = new File("A1RawData" + fileNameSuffix +".csv");
		Scanner cFile = new Scanner(file);
		int n = 0;

		dt.writeStatus("STATUS > Setup Started.");
		dt.writeStatus("STATUS > RAW DATA file opened. A1RawData" + fileNameSuffix + ".csv");
		
		//****************************************************************************
		
		//read the data file and insert into arrays
		while (cFile.hasNext()) {
			String line = cFile.nextLine();
			if (line.startsWith("INSERT INTO")) {
				rd = rd.Read1File(line, rd);
				dt.insertCode(rd.getCode(),rd.getID());
				dt.insertID(rd.getID(), rd.getID());
				dt.insertCountryName(rd.getCountryName(),rd.getID());
				dt.insertContinent(rd.getContinent(),rd.getID());
				dt.insertArea(rd.getArea(),rd.getID());
				dt.insertPopulation(rd.getPopulation(),rd.getID());
				dt.insertLifeExp(rd.getLifeExp(), rd.getID());	
				
				ni.insertKey(rd.getCountryName(), rd.getID());
				ni.insertDRP(rd.getID(), rd.getID());
				n++;
			}
			
		}
		
		//*****************************************************************************
		
		//write backup files and end messages
		dt.writeToBackup();
		ni.writeNIToBackup();
		rd.finishUp(cFile);
		dt.writeStatus("STATUS > RAW DATA file closed");
		dt.writeStatus("STATUS > Setup Finished", n);

	}

}
