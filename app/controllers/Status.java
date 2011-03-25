package controllers;

import java.util.concurrent.ExecutionException;

import play.Logger;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.WebSocketController;
import play.mvc.Http.WebSocketEvent;

public class Status extends Controller {
	public static void echo() {
		render();
	}

	public static void show() {
		render();
	}

	public static class WS extends WebSocketController {

		public static void get() throws InterruptedException, ExecutionException {
			Long i = 0L;

			// Loop while the socket is open
			while (inbound.isOpen()) {
				i++;

				outbound.sendJson(new StatusVO(i, "Hello World!"));

				try {
					Thread.sleep(1000);

				} catch (InterruptedException ex) {
					Logger.error("", ex);
				}

			}

		}

	}

	public static class StatusVO {
		public Long id;
		public String name;

		public StatusVO(Long id, String name) {
			this.id = id;
			this.name = name;
		}
	}

}
