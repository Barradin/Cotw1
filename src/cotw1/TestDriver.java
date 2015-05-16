/****************************************
 * Adam Tracy                           
 * Countries of the World Assignment 1  *
 * Test Driver                          *
 ***************************************/


package cotw1;

import java.io.File;
import java.io.IOException;

public class TestDriver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//data arrays
		String [] fileNameSuffix = new String[] {"Sample", ""};
		String [] transDataSuffix = new String[] {"1","2","3","4"};
		
		//check for files and delete them
		File file1 = new File("Backup.txt");
		File file2 = new File("Log.txt");
		File file3 = new File("NIBackup.txt");
		
		if(file1.exists()){
			file1.delete();
		}
		if(file2.exists()){
			file2.delete();
		}
		if(file3.exists()){
			file3.delete();
		}
		
		//run program according to specs
		Setup.main(new String[]{fileNameSuffix[0]});
		PrettyPrintUtility.main(args);
		for(int i = 0; i < 3; i++){
		UserApp.main(new String[]{transDataSuffix[i]});
		}
		PrettyPrintUtility.main(args);
		
		Setup.main(new String[]{fileNameSuffix[1]});
		UserApp.main(new String[]{transDataSuffix[3]});

	}

}
