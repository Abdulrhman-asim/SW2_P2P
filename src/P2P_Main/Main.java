package P2P_Main;

import java.io.IOException;
import java.util.Scanner;


public class Main {
	
	static boolean flag = true;
	public static void main(String[] args) throws IOException {

		Scanner cin = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		
		Peer a = new Peer(cin.nextLine());
		QueuingModule regulator = new QueuingModule();
				
		
		// Peer a connects to the group
		a.join();
		
		// This Thread checks if another peer sends anything 
		
		Thread thread1 = new Thread() {
			public void run() {
				while (true) {
					try {
						if(flag)
						{
							regulator.addToQue(a.receive());
						}
						else
						{
							System.out.println("** Network is flooded!!, Can't receive anything now");
							a.notify(a.getUsername() + " is Overwhelmed pausing for a couple of secs...");
							sleep(5000);
							flag = true;
						}
						
							
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		
		
		// This Thread Sends randomly generated text
		
		Thread thread2 = new Thread() {
			public void run() {
				while (true) {
					try {
							
							a.sendGenerated(Generator.generate());							
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		
		// This Thread Regulates Traffic 
		 
		Thread thread3 = new Thread() {
			public void run() {
				while (true) {
					try {
						if(!regulator.trafficCheck())
						{
							flag = false;
							
						}
						
						regulator.storeData();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		
		
		thread1.start();
		thread2.start();
		thread3.start();
		

	}

}
