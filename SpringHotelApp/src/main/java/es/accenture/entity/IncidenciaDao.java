package es.accenture.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.accenture.entity.Habitacion;
import es.accenture.entity.Incidencia;
import es.accenture.interfaces.IIncidenciaDao;

@Repository // Anotación para decirle a Spring que esta clase es un DAO para acceder a bbdd, spring crea objeto automáticamente
public class IncidenciaDao implements IIncidenciaDao { //devuelve la List de tipo Incidencia, ahí se guardan

    @Autowired //spring inyecta el SessionFactory, así no hay que hacer SessionFactory mySessionFactory=new SessionFactory porque se crea solo el objeto SessionFactory
    private SessionFactory mySessionFactory; //se crea una variable mySessionFactory de tipo SessionFactory para luego usarla dentro del try with resources, es como el pool de conexiones, se fabrican dentro las sessions, así solo se crea una vez
    
 // método para obtener detalles de todas las incidencias, devuelve una List donde se almacenan
	@Override // Anotación que dice que es un método de la interfaz IIncidenciaDao y se sobreescribe
	public List<Incidencia> obtenerDetallesTodasIncidencias() {
		// TODO Auto-generated method stub
		
		List<Incidencia>incidencias=null; //se crea la lista vacía donde se van a guardar las que se traigan de bbdd
		
	    Session miSession = null; //se crea vacía la sesión
		//se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una
		Transaction tx=null;

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx

			// consulta de incidenciass a bbdd, se crea una query dentro de miSession en hql (como el sql pero con objetos y clases), de la entity Incidencia lo que me traigas van a ser objetos tipo Incidencia y devuelve List<Incidencia>
			incidencias = miSession.createQuery("from Incidencia",Incidencia.class).getResultList();

			tx.commit(); 			// commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción

		} catch (Exception e) {
			
				if(tx!=null) { // si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
			
					tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo, es como coger bollos, ponértelos todos en la mano y llevarlos a la boca y si no te los puedes comer porque no te entran todos o se te caen se quita uno la mano de la boca
					
				}
			
			System.out.println("error en la consulta de incidencias");
			
		}finally {
				
			miSession.close(); // se cierra la sesión

		}

		return incidencias; // se devuelve la lista de las incidencias

	}
	
	// método para hacer el alta de una incidencia
	@Override // Anotación que dice que es un método de la interfaz IHabitaciónDao y se sobreescribe
	public void altaIncidencia(Incidencia incidencia) {
		// TODO Auto-generated method stub
		
		
		//se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una
		Transaction tx=null;
		
		Session miSession = null; //se crea vacía la sesión

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx

			miSession.save(incidencia); // guardar la incidencia en bbdd, hibernate hace un insert pero no se ve

			tx.commit(); // commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
				
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al guardar la incidencia");

		}finally {
		
			miSession.close(); // se cierra la sesión
		}
		
	}

	// método para hacer modificación de una incidencia
	@Override // Anotación que dice que es un método de la interfaz IIncidenciaDao y se sobreescribe
	public void modificarIncidencia(Incidencia incidencia) { //void no devuelve nada solo modifica
		// TODO Auto-generated method stub
		
		Session miSession = null; //se crea vacía la sesión
		
		//se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una
		Transaction tx=null;

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx

			miSession.update(incidencia); // actualizar la incidencia en bbdd, hibernate hace un update y cambia los datos

			tx.commit(); // commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
			
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al actualizar la incidencia");

		}finally {
		
			miSession.close(); // se cierra la sesión

		}
			
	}	
	
	// método para eliminar una incidencia
	@Override // Anotación que dice que es un método de la interfaz IIncidenciaDao y se sobreescribe
	public void eliminarIncidencia(int idIncidencia) { //void que no devuelve nada, solo borra
		// TODO Auto-generated method stub
		
		Session miSession = null; //se crea vacía la sesión
		
		Transaction tx=null; //se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			//comenzar la transación, aquí es donde todo queda dentro de tx
			tx=miSession.beginTransaction();
			
			//primero hay que buscar la habitación en bbdd por Id
			Incidencia incidencia=miSession.get(Incidencia.class,idIncidencia);

			//se comprueba que exista por si acaso con un if
			if(incidencia!=null) {
			
				//y se borra de bbdd porque hibernate hace el delete y elimina la incidencia
				miSession.delete(incidencia);
			}

			// commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción
			tx.commit();

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
				
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al borrar la incidencia");

		}finally {
		
			miSession.close(); // se cierra la sesión

		}
		
	}		

	 // método para obtener las incidencias de una habitación por su Id //es lo mismo que el de habitacionDao pero busca por el id de habitacion y no por el de incidencia
	@Override  // Anotación que dice que es un método de la interfaz IIncidenciaDao y se sobreescribe
	public List<Incidencia> obtenerIncidenciasPorIdHabitacion(int idHabitacion) { //devuelve una lista de tipo Incidencia
		// TODO Auto-generated method stub
		
		Session miSession = null; //se crea vacía la sesión
		
		Transaction tx=null; //se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx
			
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion); //primero hay que buscar la habitación en bbdd por Id

			tx.commit(); // commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción
			
			return habitacion.getIncidencias(); //después del commit devolver la lista de incidencias de la habitación

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
				
			
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al buscar las incidencias de esa habitación");
		
		}finally {
			
			miSession.close(); // se cierra la sesión

	}
		
		return null; //se pone fuera del catch por si no hay habitación y no entra en la excepcion tiene que devolver algo
		
	}

	// método para obtener las incidencias por su Id //es lo mismo que el de habitacionDao
	@Override // Anotación que dice que es un método de la interfaz IIncidenciaDao y se sobreescribe
	public Incidencia obtenerIncidenciaPorId(int idIncidencia) {

		Session miSession = null; //se crea vacía la sesión
		
		Transaction tx=null; //se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx
			
			Incidencia incidencia=miSession.get(Incidencia.class,idIncidencia); //primero hay que buscar la incidencia en bbdd por Id

			tx.commit(); // commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción
			
			return incidencia; //después del commit devolver la incidencia

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
				
			
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al buscar la incidencia");
		
		}finally {
			
			miSession.close(); // se cierra la sesión

	}
		
		return null; //se pone fuera del catch por si no hay incidencia y no entra en la excepcion tiene que devolver algo
		
	}
	
}
