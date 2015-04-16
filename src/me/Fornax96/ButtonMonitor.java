package me.Fornax96;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Fornax
 */
public class ButtonMonitor {

	private static String bgLocation;

	public static void main(String[] args) throws InterruptedException {
		try {
			bgLocation = args[0];
		} catch (Exception e) {
			System.out.println("Wallpaper file location was not set");
		}

		try {
			Socket s = new Socket(new URI(getSocketHash()));
		} catch (URISyntaxException ex) {
			Logger.getLogger(ButtonMonitor.class.getName()).log(Level.SEVERE, null, ex);
		}

		while (true) {
			Thread.sleep(10000);
		}
	}

	private static String getSocketHash() {
		//Oh my god why am I going through so much trouble for an april fool's joke?
		try {
			URL url = new URL("https://www.reddit.com/r/thebutton/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			conn.setRequestProperty("User-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while(line != null) {
				sb.append(line);
				
				line = br.readLine();
			}
			in.close();
			
			
			String webpage = sb.toString();
			
			final Pattern pattern = Pattern.compile("(wss:\\/\\/wss\\.redditmedia\\.com\\/thebutton\\?h=[^\"]*)");
			final Matcher matcher = pattern.matcher(webpage);
			matcher.find();
			
			System.out.println("Found a websocket string! " + matcher.group(1));
			
			return matcher.group(1);
		} catch (MalformedURLException ex) {
			Logger.getLogger(ButtonMonitor.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ButtonMonitor.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}

	public static String getBgLocation() {
		return bgLocation;
	}

}
