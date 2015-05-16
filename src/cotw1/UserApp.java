/****************************************
 * Adam Tracy                           *
 * Countries of the World Assignment 1  *
 * User App                             *
 ***************************************/
package cotw1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//declare some variables
		String transDataSuffix = args[0];
		String tranCode = null;
		String otherTran = null;
		int targetCode;
		int ref;
		int n = 0;
		
		//objects as well
		UI ui = new UI(transDataSuffix);
		DataTable dt = new DataTable();
		NameIndex ni = new NameIndex();
		dt.writeStatus("STATUS > UserApp Started", n);
		dt.writeStatus("STATUS > TransDataFile Opened. TransData " + transDataSuffix);

		//read the data from the backup files and insert into arrays
		File file = new File("Backup.txt");
		File backupFile = new File("NIBackup.txt");
		Scanner b2File = new Scanner(backupFile);
		Scanner bFile = new Scanner(file);
		while (bFile.hasNext()) {
			String line = bFile.nextLine();
			dt.insertAll(line);
		}
		while (b2File.hasNext()) {
			String line = b2File.nextLine();
			ni.insertAll(line);
		}

		//close files
		bFile.close();
		b2File.close();
		
		//********************************************************************************
		//loop till done. process the transdata information and switch as necessary
		while (!ui.isDone()) {
			tranCode = ui.processTrans();
			otherTran = ui.getRestOfLine();
			if (tranCode != null) {
				switch (tranCode) {

				case "IN": //insert new information into tables
					dt.writeToLog(tranCode, otherTran);
					otherTran.split(",");
					dt.insertTrans(otherTran);
					ni.insertTrans(otherTran);
					if (dt.getSuccess() == true) {
						ui.writeToLog("Ok, Country Inserted.");
					} else {
						ui.writeToLog("Error, Add command failed. Location likely in use.");
					}
					n++;
					break;

				case "DN": //delete by name -not working-
					ui.writeToLog(tranCode);
					ui.writeToLog("[SORRY! Delete by name module not yet working.]");
					n++;
					break;

				case "DI"://delete by id -not working-
					ui.writeToLog(tranCode);
					ui.writeToLog("[SORRY! Delete by ID module not yet working.]");
					n++;
					break;

				case "SN"://select a country by its name
					ui.writeToLog(tranCode, otherTran);
					targetCode = ni.searchNI(otherTran.replaceAll("\\s+$", ""));
					if (targetCode != -1) {
						dt.writeToLog(targetCode);
					} else {
						ui.writeToLog("Invalid identifier. Check spelling and try again.");
					}
					n++;
					break;

				case "SI"://select a country by its ID
					ui.writeToLog(tranCode, otherTran);
					dt.writeToLog(Integer.parseInt(otherTran));
					n++;
					break;

				case "AN"://print all the countries by name order
					ui.writeToLog(tranCode);
					ni.insertionSort();
					ui.writeToLog("CDE -ID Name------------------------------------------Continent--------------Area--- ---Population Life");
					for (int i = 0; i < ni.getHighLoc(); i++) {
						ref = ni.getDRP(i);
						dt.writeAllToLog(ref);
					}
					ui.writeToLog("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					n++;
					break;

				case "AI"://print all the countries by ID order
					ui.writeToLog(tranCode);
					ui.writeToLog("CDE -ID Name------------------------------------------Continent--------------Area--- ---Population Life");
					dt.writeAllToLog();
					ui.writeToLog("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					n++;
					break;

				default://if nothing else, make the user feel bad.
					ui.writeToLog("Invalid Code.%n");
					n++;
					break;

				}
			}
		}

		//wrap things up
		dt.writeStatus("STATUS > UserApp Finished", n);
		ui.finishUp();
		dt.writeStatus("STATUS > TransDataFile closed");

	}

}
