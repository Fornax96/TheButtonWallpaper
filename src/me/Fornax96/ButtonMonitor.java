package me.Fornax96;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fornax
 */
public class ButtonMonitor {
	private static String bgLocation;

	public static void main(String[] args) throws InterruptedException {
		try{
			bgLocation = args[0];
		}catch(Exception e){
			System.out.println("Wallpaper file location was not set");
		}
		
		try {
			Socket s = new Socket(new URI("wss://wss.redditmedia.com/thebutton?h=ef2175b479eba171f47fd00bf1127f3ff44cf83a&e=1429212736"));
		} catch (URISyntaxException ex) {
			Logger.getLogger(ButtonMonitor.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		while(true){
			Thread.sleep(10000);
		}
	}
	
	public static String getBgLocation(){
		return bgLocation;
	}

}
