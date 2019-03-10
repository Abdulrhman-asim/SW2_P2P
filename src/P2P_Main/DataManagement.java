package P2P_Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Formatter;

public class DataManagement {

	Formatter receiver;
	
	public DataManagement() throws FileNotFoundException
	{
		receiver = new Formatter("Log.txt");
	}
	
	
	public void writeToFile(String data) throws FileNotFoundException
	{	
		
		
		receiver.format("%s",data);
		receiver.format("%s",System.lineSeparator());
		receiver.flush();
		
	}
	
	
	
}
