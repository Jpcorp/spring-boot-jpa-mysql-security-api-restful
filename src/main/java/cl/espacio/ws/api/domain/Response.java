package cl.espacio.ws.api.domain;

public class Response {
	
	private Object mensaje;
	
	public Response() {
		super();
	}

	public Response(Object mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public Object getMensaje() {
		return mensaje;
	}

	public void setMensaje(Object mensaje) {
		this.mensaje = mensaje;
	}

	
}
