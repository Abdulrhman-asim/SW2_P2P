package P2P_Main;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

public class QueuingModule {
	
	private Queue<String> que;
	private DataManagement file; 
	
	public QueuingModule() throws FileNotFoundException
	{
		file = new DataManagement();
		que =  new LinkedList<String>();
	}
	
	public void addToQue(String text)
	{
		if(!(text.charAt(0) == '~'))
		{
			que.add(text);
		}
	}
	
	public boolean trafficCheck()
	{
		if(que.size() > 100)
			return false;
		return true;
	}
	
	
	public void storeData() throws FileNotFoundException
	{
		if(!que.isEmpty())
		{
			file.writeToFile(que.poll());
		}
		else
		{
			return;
		}
	}

}
