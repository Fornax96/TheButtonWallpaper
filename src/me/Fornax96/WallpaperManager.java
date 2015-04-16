package me.Fornax96;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Fornax
 */
public class WallpaperManager {
	private final ArrayList<String> backdrops = new ArrayList();
	
	public WallpaperManager(){
		try {
			ProcessBuilder pb = new ProcessBuilder(
				"xfconf-query",
				"-c", "xfce4-desktop",
				"-p", "/backdrop", 
				"-l");

			Process p = pb.start();
			p.waitFor();

			BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = bri.readLine();
			while(line != null) {
				backdrops.add(line);
				
				line = bri.readLine();
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace(System.err);
		}
	}
	

	public void update(Double time) {
		Integer number = time.intValue();
		
		try {
			for(String line : backdrops) {
				if(line.contains("image-path") || line.contains("last-image")){
					new ProcessBuilder("xfconf-query",
						"-c", "xfce4-desktop",
						"-p", line,
						"-s", ButtonMonitor.getBgLocation() + "bg-" + number + ".png")
						.start();
				}
			}

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
}
