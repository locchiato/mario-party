package bd;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entities.Usuario;


public class Hibernate {

	private Configuration cfg;
	public SessionFactory factory;
	private Session session;
	
	public Hibernate(String conexion) {
		
		this.cfg = new Configuration();
		System.out.println("LA CONCHA DE LA LORA 1");
		this.cfg.configure(conexion);
		System.out.println("LA CONCHA DE LA LORA 2");
		this.factory = cfg.buildSessionFactory();
		System.out.println("LA CONCHA DE LA LORA 3");
		this.session = factory.openSession();
	}
	
	public static void main(String[] args) {
		Hibernate bd = new Hibernate("hibernateMIO.cfg.xml");
		Usuario usuario = new Usuario("mauro","123456");
		Usuario usuario2 = new Usuario("mauro66","123456");
		Usuario usuario3 = new Usuario("mauropp","123456");
		
		usuario2.setContracenia("vida");
		
		//bd.agregarUsuario(usuario2);
		
		if(bd.usuarioValidar(usuario)) {
			System.out.println("logeo correcto");
		}
		else {
			System.out.println("nombre o contracenia incorrecto");
		}
		
		if(bd.usuarioValidar(usuario2)) {
			System.out.println("logeo correcto");
		}
		else {
			System.out.println("nombre o contracenia incorrecto");
		}
		
		if(bd.usuarioValidar(usuario3)) {
			System.out.println("logeo correcto");
		}
		else {
			System.out.println("nombre o contracenia incorrecto");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public int agregarUsuario(Usuario usuario) {
		this.session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			
			String query = "SELECT u FROM Usuario u WHERE nombre='"+usuario.getNombre()+"'";
			Usuario usuarioBD = (Usuario) session.createQuery(query).uniqueResult();
//			Query q = session.getNamedQuery("NombreUsuarios");
//			List<String> listaNombres = q.getResultList();
//			if(listaNombres.contains(usuario.getNombre())) {
//				return -1;//Usuario ya existe
//			}
			if(usuarioBD != null) {
				return -1;//Usuario ya existe
			}
			else {
					session.save(usuario);
			}
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return 0;//Fallo Transaccion
		} 
        finally
        {
            session.close();
        }
			return 1;//Exito
	}
	
	public boolean usuarioValidar(Usuario usuario) {
		this.session = factory.openSession();
		
		try{
			String query = "SELECT u FROM Usuario u WHERE nombre='"+usuario.getNombre()+"'";
			Usuario usuarioBD = (Usuario) session.createQuery(query).uniqueResult();
			
			return usuarioBD != null && usuario.getContracenia().equals(usuarioBD.getContracenia()) ;

		}
		catch (HibernateException e) {
			e.printStackTrace();
			return false;//Fallo 
		} 
        finally
        {
            session.close();
        }
	}
	

}
