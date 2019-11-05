package servidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

public class HiloDeCliente extends Thread{

	// SOCKET PARA COMUNICARSE CON EL CLIENTE QUE SE CONECTO
	private Socket clientSocket;
	// CLASES PARA ESCRIBIR Y LEER, NECESARIAS PARA LA COMINICACION
	private ObjectInputStream in;
	private ObjectOutputStream out;
	//ARRAYLIST DE LOS USUARIOS
	private ArrayList<Socket> usuarios;
	
	private ServidorFrame frame;
	
	private String ipCliente;
	
	private int idUsuario;
	
	public HiloDeCliente( Socket s, ArrayList<Socket> u, ServidorFrame frame){
		clientSocket = s;
		this.frame = frame;
		setUsuarios(u);
		try {
			// VINCULAMOS LOS INPUT Y OUTPUT CON LOS DEL CLIENTE( ESTOS LOS TIENE EL SOCKET )
			in = new ObjectInputStream( clientSocket.getInputStream() );
			out = new ObjectOutputStream( clientSocket.getOutputStream() );
			ipCliente = clientSocket.getInetAddress().getHostAddress();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// ESTA FUNCION SE EJECUTA UNA VEZ QUE EL SERVIDOR LE MANDA EL START AL HILO
	// ESTA SE ENCARGA DE ESCUCHAR CONSTANTEMENTE AL SOCKET DE CLIENTE
	@Override
	public void run(){
		try {
			String respuesta;
			while(true){
				//RECIBIMOS EL OBJETO
				Object peticion = in.readObject();
				System.out.println(" Se recibio un objeto " + peticion.getClass().getName());
				
				// REEMPLAZAR POR UN SWITCH
				/*if( peticion instanceof IngresarPartida ){
					//actualizarEspera((IngresarPartida)peticion);
					buscarYagregar( (IngresarPartida)peticion );
					//partida.agregarJugador( new Jugador(clientSocket,out,in));
					frame.mostrarMensajeFrame("SE AGREGO EL JUGADOR " + clientSocket.getInetAddress());
					//this.finalize();
					// PERO SERIA UNA FORMA DE DETENER ESTE HILO PARA QUE LO
					
				}else if( peticion instanceof LoginBean ){
					if(loguear((LoginBean) peticion)){
						frame.mostrarMensajeFrame(ipCliente+">> Login");
						((LoginBean)peticion).setIdUsuario(idUsuario);
						out.writeObject(peticion);
					}else
						out.writeObject("ERROR LOGIN");
				}else if( peticion instanceof Peticion ){
					frame.mostrarMensajeFrame(ipCliente+">> Peticion");
					out.writeObject("INGRESADO");
				}else if( peticion instanceof RegistrarBean ){
					if(agregarUsuario((RegistrarBean) peticion)){
						frame.mostrarMensajeFrame(ipCliente+">> Registro");
						out.writeObject("REGISTRO");
					}else
						out.writeObject("ERROR REGISTRO");
				}else if( peticion instanceof DesconexionBean ){
					desconectarUsuario();
					frame.mostrarMensajeFrame(ipCliente+">> Logout");
					out.writeObject("DESCONECTADO");
				}else if( peticion.equals("KILL THREAD") ){
					frame.mostrarMensajeFrame(ipCliente+">> Eliminando hilo de cliente");					
					out.writeObject("HILO ELIMINADO");
					break;//eliminamos bucle infinito
				}else if( peticion instanceof RecuperarBean ){
					frame.mostrarMensajeFrame(ipCliente+">> Solicitud de pregunta secreta.");
					respuesta = devolverPreguntaSecreta((RecuperarBean) peticion);
					if(respuesta == null)
						out.writeObject("NICK INVALIDO");
					else
						out.writeObject(respuesta);
				}else if( peticion instanceof DatosUsuarioBean ){
					frame.mostrarMensajeFrame(ipCliente+">> Solicitud de datos del cliente.");
					devolverDatosCliente((DatosUsuarioBean) peticion);
					out.writeObject(peticion);
				}else if( peticion instanceof ValidarRespuestaBean ){
					frame.mostrarMensajeFrame(ipCliente+">> Solicitud de validar respuesta");					
					respuesta = validarRespuesta((ValidarRespuestaBean) peticion); 
					if(respuesta == null)
						out.writeObject("RESPUESTA INVALIDA");
					else
						out.writeObject(respuesta);
				}else if( peticion instanceof ExisteUsuarioBean ){
					frame.mostrarMensajeFrame(ipCliente+">> Solicitud de existencia de usuario.");					
					respuesta = existeUsuario((ExisteUsuarioBean) peticion); 
					out.writeObject(respuesta);
				}else if( peticion instanceof ActualizarDatosBean ){
					frame.mostrarMensajeFrame(ipCliente+">> Solicitud de actualizacion de datos.");					
					actualizarDatos((ActualizarDatosBean) peticion); 
					out.writeObject("DATOS ACTUALIZADOS");
				}else if( peticion instanceof estoyListoBean ){
					System.out.println(((estoyListoBean) peticion).getId()-1);
					partidas.get(((estoyListoBean)peticion).getIdPartida()).getJugadores().get( ((estoyListoBean) peticion).getId()-1).setEstoyListo(true);
				}else if( peticion instanceof DireccionBean ){
						partidas.get(((DireccionBean)peticion).getIdPartida()).getJugadores().get( ((DireccionBean) peticion).getId()-1).setDireccion(((DireccionBean) peticion).getDireccion());
						partidas.get(((DireccionBean)peticion).getIdPartida()).getJugadores().get( ((DireccionBean) peticion).getId()-1).setEnvieDireccion(true);
				}else if( peticion instanceof RankingBean ){
					frame.mostrarMensajeFrame(ipCliente+">> Solicitud de ranking.");
					out.writeObject(retornarRanking((RankingBean)peticion));
				}else if( peticion instanceof EstadisticasBean ){
					frame.mostrarMensajeFrame(ipCliente+">> Solicitud de estadisticas.");
					out.writeObject(retornarEstadisticas((EstadisticasBean)peticion));
				}else if( peticion instanceof ActualizarPartidasBean ){
						enviarPartidas((ActualizarPartidasBean)peticion);
						System.out.println("Se envio la actualizacion");
				}else{
					frame.mostrarMensajeFrame("no se reconoce al objeto.");
				}*/
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	//Lo dejo para que nos guiemos como ejemplo
	/*private void enviarPartidas( ActualizarPartidasBean peticion ) throws IOException{
		// DEBERIAMOS HACER ESTO PARA CADA PARTIDA
		for (Partida partida : partidas) {
			
			peticion.getPartidas().add(partida.getNombre() + " " +
										partida.getCantJugadoresEnCurso() + " " +
										partida.getCantJugadoresMax() + " " +
										partida.isEstado() );
		}
		out.writeObject(peticion);
	
	}*/

	private void desconectarUsuario() {
		//QUITAMOS AL USURIO DE LA LISTA DE USUARIOS CONECTADOS Y DEJAMOS QUE EL CLIENTE CIERRE LA
		//LA CONEXION
		usuarios.remove(clientSocket);		
	}

	private boolean loguear() {
		return true;
	}

	public boolean agregarUsuario() {
		return true;
	}

	public ArrayList<Socket> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Socket> usuarios) {
		this.usuarios = usuarios;
	}
	
}
