package controllers;

import play.Logger;
import play.libs.F.Promise;
import play.mvc.WebSocketController;
import play.mvc.Http.WebSocketEvent;
import play.mvc.Http.WebSocketFrame;

public class WsStatus extends WebSocketController {

	public static void echo() throws InterruptedException {
		while (inbound.isOpen()) {

			// get event but no wait
			Promise<WebSocketEvent> p = inbound.nextEvent();
			WebSocketEvent e = p.getOrNull();

			if (e instanceof WebSocketFrame) {
				WebSocketFrame frame = (WebSocketFrame) e;

				if (!frame.isBinary) {

					// get original message, Uppercase it and send it back
					//
					outbound.send("Echo: %s", frame.textData.toUpperCase());
					Logger.info("Echo: %s", frame.textData);

					// if message is QUIT, disconnect WS
					if (frame.textData.contains("quit")) {
						disconnect();
						Logger.info("Goodbye!");
					}

				}

			} else {
				outbound.send("Time flies ... " + System.currentTimeMillis());
			}

			Thread.sleep(1000);
		}
	}

}
