package me.Fornax96;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Fornax
 */
public class WallpaperManager {

	public void update(Double time) {
		Integer number = time.intValue();
		
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
				if(line.contains("image-path") || line.contains("last-image")){
					new ProcessBuilder("xfconf-query",
						"-c", "xfce4-desktop",
						"-p", line,
						"-s", ButtonMonitor.getBgLocation() + "bg-" + number + ".png")
						.start();
				}
				
				line = bri.readLine();
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace(System.err);
		}
	}
}
