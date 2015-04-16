package me.Fornax96;

/**
 * @author Fornax
 */
import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Fornax
 */
@ClientEndpoint
public class Socket {

	Session userSession = null;

	public Socket(URI endpointURI) {
		
		
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		} catch (DeploymentException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("opening websocket");
		this.userSession = userSession;
	}
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("closing websocket");
		this.userSession = null;
	}
	
	WallpaperManager mgr = new WallpaperManager();
	@OnMessage
	public void onMessage(String message) {
		System.out.println(message);
		Object obj = JSONValue.parse(message);
		JSONObject json = (JSONObject) obj;
		
		if(json == null){return;}
		if(!json.containsKey("payload")){return;}
		
		JSONObject payloadjson = (JSONObject) json.get("payload");
		
		if(payloadjson == null){return;}
		if(!payloadjson.containsKey("seconds_left")){return;}
		
		
		double time = (double) payloadjson.get("seconds_left");
		mgr.update(time);
	}
}
