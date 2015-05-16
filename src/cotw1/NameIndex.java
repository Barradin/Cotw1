/****************************************
 * Adam Tracy                           *
 * Countries of the World Assignment 1  *
 * Name Index                           *
 ***************************************/
package cotw1;

import java.io.*;

public class NameIndex {

	//declare variables
	private final int MAX_ARRAY_SIZE = 300;
	private String[] cnKeyArr = new String[MAX_ARRAY_SIZE];
	private int[] idDRPArr = new int[MAX_ARRAY_SIZE];
	private boolean append = true;
	private String[] temp;
	private int highLoc = 0;

	//***********************************************************
	/**
	 * method to insert the country name
	 * @param c
	 * @param loc
	 */
	public void insertKey(String c, int loc) {
		// TODO Auto-generated method stub
		cnKeyArr[loc] = c;

	}

	/**
	 * method to get the information out of DRP
	 * @param i
	 * @return
	 */
	public int getDRP(int i) {
		return idDRPArr[i];
	}

	/**
	 * method to insert the record pointer
	 * @param id
	 * @param loc
	 */
	public void insertDRP(int id, int loc) {
		// TODO Auto-generated method stub
		idDRPArr[loc] = id;

	}

	//*******************************************************
	/**
	 * writes name index array to another backup file
	 * easier to read from seperate file
	 * @throws IOException
	 */
	public void writeNIToBackup() throws IOException {
		File file = new File("NIBackup.txt");
		FileWriter write = new FileWriter(file, !append);
		PrintWriter p = new PrintWriter(write);
		for (int i = 0; i < cnKeyArr.length; i++) {
			if (cnKeyArr[i] != null) {
				p.printf("%d,%s%n", idDRPArr[i], cnKeyArr[i]);
			}
		}
		p.close();
	}

	/**
	 * inserts all the data into a new array (for userapp)
	 * high loc cuts off excess garbage in array
	 * @param line
	 */
	public void insertAll(String line) {
		temp = line.split(",");
		int loc = Integer.parseInt(temp[0]);
		idDRPArr[loc] = Integer.parseInt(temp[0]);
		cnKeyArr[loc] = temp[1];
		if (loc > highLoc) {
			highLoc = loc;
		}
	}

	/**
	 * insertion sort on the names to keep them tidy
	 * clear nulls removes nulls to allow comparison
	 * also shifts the id pointer with the name
	 */
	public void insertionSort() {
		clearNulls();//removes nulls from array
		int i, j;
		String key;
		int key2;
		for (j = 1; j < cnKeyArr.length; j++) {
			key = cnKeyArr[j];
			key2 = idDRPArr[j];
			i = j - 1;
			while (i >= 0) {
				if (key.compareTo(cnKeyArr[i]) > 0) {
					break;
				}
				cnKeyArr[i + 1] = cnKeyArr[i];
				idDRPArr[i + 1] = idDRPArr[i];
				i--;
			}
			cnKeyArr[i + 1] = key;
			idDRPArr[i + 1] = key2;
		}
	}

	/**
	 * clear nulls converts the nulls in string array to something comparable
	 * zzzzz will naturally appear at the bottom
	 */
	public void clearNulls() {
		for (int i = 0; i < cnKeyArr.length; i++) {
			if (cnKeyArr[i] == null) {
				cnKeyArr[i] = "zzzzz";
			}
		}
	}

	/**
	 * saerches the name index without reaching the garbage section of the array
	 * @param otherTran
	 * @return
	 */
	public int searchNI(String otherTran) {
		// TODO Auto-generated method stub
		int i = 0;
		boolean done = false;
		int targetDRP = -1;
		while (done == false && i < getHighLoc()) {
			if (cnKeyArr[i] != null) {
				if (cnKeyArr[i].equalsIgnoreCase(otherTran)) {
					targetDRP = idDRPArr[i];
					done = true;
				}
			}
			i++;
		}

		return targetDRP;
	}
	
	/**
	 * method to for the insert command from userApp
	 * inserts new data into the name index array
	 * @param otherTran
	 */
	public void insertTrans(String otherTran) {
		// TODO Auto-generated method stub
		temp = otherTran.split(",");
		boolean done = false;
		int loc = 0;
		while (done == false) {
			if (cnKeyArr[loc].equalsIgnoreCase("zzzzz")) {
				cnKeyArr[loc] = temp[2].replace("'", "");
				idDRPArr[loc] = Integer.parseInt(temp[1]);
				done = true;
			} else {
				loc++;
			}
		}

	}
	
	/**
	 * method to get the high location of usable array data
	 * @return
	 */
	public int getHighLoc() {
		return highLoc;
	}
}
