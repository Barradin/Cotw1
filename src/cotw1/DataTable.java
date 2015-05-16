/****************************************
 * Adam Tracy                           *
 * Countries of the World Assignment 1  *
 * Data Table                           *
 ***************************************/
package cotw1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataTable {

	//declare some variables
	private final int MAX_ARRAY_SIZE = 300;
	private String[] codeArr = new String[MAX_ARRAY_SIZE];
	private int[] idArr = new int[MAX_ARRAY_SIZE];
	private String[] counNameArr = new String[MAX_ARRAY_SIZE];
	private String[] contArr = new String[MAX_ARRAY_SIZE];
	private float[] areaArr = new float[MAX_ARRAY_SIZE];
	private long[] popArr = new long[MAX_ARRAY_SIZE];
	private float[] lifeExpArr = new float[MAX_ARRAY_SIZE];
	private boolean append = true;
	private boolean success;
	private String[] temp;
	
	/**
	 * constructor for data table
	 * @throws IOException
	 */
	public DataTable() throws IOException {
	}

	//******************************************************************
	/**
	 * are you feeling successful!?
	 * @return
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * inserts code data
	 * @param s
	 * @param loc
	 */
	public void insertCode(String s, int loc) {
		// TODO Auto-generated method stub
		codeArr[loc] = s;

	}

	/**
	 * inserts id data
	 * @param i
	 * @param loc
	 */
	public void insertID(int i, int loc) {
		// TODO Auto-generated method stub
		idArr[loc] = i;
	}

	/**
	 * inserts country name data
	 * @param s
	 * @param loc
	 */
	public void insertCountryName(String s, int loc) {
		// TODO Auto-generated method stub
		counNameArr[loc] = s;
	}

	/**
	 * inserts continent data
	 * @param s
	 * @param loc
	 */
	public void insertContinent(String s, int loc) {
		contArr[loc] = s;
	}

	/**
	 * inserts area data
	 * @param f
	 * @param loc
	 */
	public void insertArea(float f, int loc) {
		areaArr[loc] = f;

	}

	/**
	 * inserts population data
	 * @param l
	 * @param loc
	 */
	public void insertPopulation(long l, int loc) {
		popArr[loc] = l;
	}

	/**
	 * inserts life expectancy data
	 * @param f
	 * @param loc
	 */
	public void insertLifeExp(float f, int loc) {
		lifeExpArr[loc] = f;

	}

	/**
	 * method to write the data table stuff to backup file
	 * @throws IOException
	 */
	public void writeToBackup() throws IOException {
		File file = new File("Backup.txt");
		FileWriter write = new FileWriter(file, !append);
		PrintWriter p = new PrintWriter(write);
		for (int i = 0; i < codeArr.length; i++) {
			if (codeArr[i] != null) {
				p.printf("%s,%d,%s,%s,%f,%d,%f,%n", codeArr[i], idArr[i],
						counNameArr[i], contArr[i], areaArr[i], popArr[i],
						lifeExpArr[i]);
			}
		}
		p.close();
	}

	/**
	 * insert ALL the things! (into new arrays)
	 * @param line
	 */
	public void insertAll(String line) {
		// TODO Auto-generated method stub
		temp = line.split(",");
		int loc = Integer.parseInt(temp[1]);
		codeArr[loc] = temp[0];
		idArr[loc] = Integer.parseInt(temp[1]);
		counNameArr[loc] = temp[2];
		contArr[loc] = temp[3];
		areaArr[loc] = Float.parseFloat(temp[4]);
		popArr[loc] = Long.parseLong(temp[5]);
		lifeExpArr[loc] = Float.parseFloat(temp[6]);

	}

	/**
	 * insert stuff when given an IN command from userApp
	 * @param otherTran
	 */
	public void insertTrans(String otherTran) {
		// TODO Auto-generated method stub
		success = false;
		temp = otherTran.split(",");
		int loc = Integer.parseInt(temp[1]);
		if (idArr[loc] == 0) {
			codeArr[loc] = temp[0].substring(31, 34);
			idArr[loc] = Integer.parseInt(temp[1]);
			counNameArr[loc] = temp[2].replace("'", "");
			contArr[loc] = temp[3].replace("'", "");
			areaArr[loc] = Float.parseFloat(temp[5]);
			popArr[loc] = Long.parseLong(temp[7]);
			lifeExpArr[loc] = Float.parseFloat(temp[8]);
			success = true;
		}
	}

	/**
	 * write single data to the log
	 * @param i
	 * @throws IOException
	 */
	public void writeToLog(int i) throws IOException {
		File file = new File("Log.txt");
		FileWriter write = new FileWriter(file, append);
		PrintWriter p = new PrintWriter(write);

		if(i < MAX_ARRAY_SIZE && counNameArr[i] != null){
		p.printf("%s %03d %-45s %-17s %,12.1f %,13d %3.1f %n",
				codeArr[i], idArr[i], counNameArr[i], contArr[i],
				areaArr[i], popArr[i], lifeExpArr[i]);
		}
		else{
			p.printf("Error. Bad ID.%n");
		}
		p.close();
	}

	/**
	 * write stuff to log file in a pretty way when not given an int variable
	 * @throws IOException
	 */
	public void writeAllToLog() throws IOException {
		File file = new File("Log.txt");
		FileWriter write = new FileWriter(file, append);
		PrintWriter p = new PrintWriter(write);
		for (int i = 0; i < codeArr.length; i++) {
			if (codeArr[i] != null) {
				p.printf("%s %03d %-45s %-17s %,12.1f %,13d %3.1f %n",
						codeArr[i], idArr[i], counNameArr[i], contArr[i],
						areaArr[i], popArr[i], lifeExpArr[i]);
			}
		}
		p.close();
	}

	/**
	 * write ALL the stuff to a log file when given an int
	 * @param i
	 * @throws IOException
	 */
	public void writeAllToLog(int i) throws IOException {
		File file = new File("Log.txt");
		FileWriter write = new FileWriter(file, append);
		PrintWriter p = new PrintWriter(write);
		if(codeArr[i] != null){
				p.printf("%s %03d %-45s %-17s %,12.1f %,13d %3.1f %n",
						codeArr[i], idArr[i], counNameArr[i], contArr[i],
						areaArr[i], popArr[i], lifeExpArr[i]);
		}
		p.close();
	}

	/**
	 * writing stuff to the log file when given two strings
	 * @param tranCode
	 * @param otherTran
	 * @throws IOException
	 */
	public void writeToLog(String tranCode, String otherTran) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("Log.txt");
		FileWriter write = new FileWriter(file, append);
		PrintWriter p = new PrintWriter(write);
		temp = otherTran.split(",");
		
		p.printf("%s %s,%03d,%s,%s,%.1f,%d,%.1f %n", tranCode,
				temp[0].substring(31,34), Integer.parseInt(temp[1]), temp[2].replace("'",""), temp[3].replace("'",""),
				Float.parseFloat(temp[5]), Long.parseLong(temp[7]), Float.parseFloat(temp[8]));
		
		p.close();
	}
	
	/**
	 * for the various status messages throughout the program
	 * @param s
	 * @param n
	 * @throws IOException
	 */
	public void writeStatus(String s, int n) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("Log.txt");
		FileWriter write = new FileWriter(file, append);
		PrintWriter p = new PrintWriter(write);
		if(n == 0){
		p.printf(s + "%n");
		}
		else{
			p.printf(s + " " +  n + " countries processed.%n");
		}
		p.close();
	}

	/**
	 * more status messages
	 * @param s
	 * @throws IOException
	 */
	public void writeStatus(String s) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("Log.txt");
		FileWriter write = new FileWriter(file, append);
		PrintWriter p = new PrintWriter(write);
		p.printf(s + "%n");		
		p.close();
		
	}

}
