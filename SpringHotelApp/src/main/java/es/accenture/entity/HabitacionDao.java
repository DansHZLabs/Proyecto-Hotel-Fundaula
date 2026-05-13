package es.accenture.entity; //esto lo hace el A  //copiar sintaxis conexión sessionFactoy y session a partir del minuto 10 del video de hibernate de clase pero hay que cambiarlo
                                               //cambiar los try with resources mañana porque que están mal, revisar lo del rollback, la excepción solo aplica a la transacción hay que meterlo como condición
import java.util.List;                         //en entity Habitación el tipo tiene que ser BigDecimal y no double que es el que se usa para dinero porque el otro no da cálculos exactos //falta transformar las excepciones en personalizadas

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.accenture.entity.Habitacion;
import es.accenture.interfaces.IHabitacionDao;

@Repository // Anotación para decirle a Spring que esta clase es un DAO para acceder a bbdd, spring crea objeto automáticamente
public class HabitacionDao implements IHabitacionDao {

    @Autowired //spring inyecta el SessionFactory, así no hay que hacer SessionFactory mySessionFactory=new SessionFactory porque se crea solo el objeto SessionFactory
    private SessionFactory mySessionFactory; //se crea una variable mySessionFactory de tipo SessionFactory para luego usarla dentro del try with resources, es como el pool de conexiones, se fabrican dentro las sessions, así solo se crea una vez
	
	// método para obtener detalles de todas las habitaciones, devuelve una List donde se almacenan
	@Override // Anotación que dice que es un método de la interfaz IHabitaciónDao y se sobreescribe
	public List<Habitacion> obtenerDetallesTodasHabitaciones() { //devuelve la List de tipo Habitacion, ahí se guardan
		// TODO Auto-generated method stub
		
		List<Habitacion>habitaciones=null; //se crea la lista vacía donde se van a guardar las que se traigan de bbdd
		
	    Session miSession = null; //se crea vacía la sesión
		//se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una
		Transaction tx=null;

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx

			// consulta de habitaciones a bbdd, se crea una query dentro de miSession en hql (como el sql pero con objetos y clases), de la entity Habitación lo que me traigas van a ser objetos tipo Habitacion y devuelve List<Habitacion>
			habitaciones = miSession.createQuery("from Habitacion",Habitacion.class).getResultList();

			tx.commit(); 			// commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción

		} catch (Exception e) {
			
				if(tx!=null) { // si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
			
					tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo, es como coger bollos, ponértelos todos en la mano y llevarlos a la boca y si no te los puedes comer porque no te entran todos o se te caen se quita uno la mano de la boca
					
				}
			
			System.out.println("error en la consulta de habitaciones");
			
		}finally {
				
			miSession.close(); // se cierra la sesión

		}

		return habitaciones; // se devuelve la lista de las habitaciones

	}

	// método para hacer el alta de una habitación
	@Override // Anotación que dice que es un método de la interfaz IHabitaciónDao y se sobreescribe
	public void altaHabitacion(Habitacion habitacion) { //void no devuelve nada, solo lo manda a la bbdd
		// TODO Auto-generated method stub

		//se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una
		Transaction tx=null;
		
		Session miSession = null; //se crea vacía la sesión

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx

			miSession.save(habitacion); // guardar la habitacion en bbdd, hibernate hace un insert pero no se ve

			tx.commit(); // commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
				
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al guardar la habitación");

		}finally {
		
			miSession.close(); // se cierra la sesión

	}
		
	}
		
		//voy por aquí


	// método para hacer modificación de una habitación
	@Override // Anotación que dice que es un método de la interfaz IHabitaciónDao y se sobreescribe
	public void modificarHabitacion(Habitacion habitacion) { //void no devuelve nada solo modifica
		// TODO Auto-generated method stub

		Session miSession = null; //se crea vacía la sesión
		
		//se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una
		Transaction tx=null;

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx

			miSession.update(habitacion); // actualizar la habitacion en bbdd, hibernate hace un update y cambia los datos

			tx.commit(); // commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
			
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al actualizar la habitación");

		}finally {
		
			miSession.close(); // se cierra la sesión

	}
			
		}
		
			

	// método para eliminar una habitación
	@Override // Anotación que dice que es un método de la interfaz IHabitaciónDao y se sobreescribe
	public void eliminarHabitacion(int idHabitacion) { //void que no devuelve nada, solo borra
		// TODO Auto-generated method stub

		Session miSession = null; //se crea vacía la sesión
		
		Transaction tx=null; //se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			//comenzar la transación, aquí es donde todo queda dentro de tx
			tx=miSession.beginTransaction();
			
			//primero hay que buscar la habitación en bbdd por Id
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion);

			//se comprueba que exista por si acaso con un if
			if(habitacion!=null) {
			
				//y se borra de bbdd porque hibernate hace el delete y elimina la habitación
				miSession.delete(habitacion);
			}

			// commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción
			tx.commit();

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
				
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al borrar la habitación");

		}finally {
		
			miSession.close(); // se cierra la sesión

	}
		
	}		


	// método para obtener una habitación por su Id //es lo mismo que el de borrar pero sin el delete y que lo devuelva
	@Override // Anotación que dice que es un método de la interfaz IHabitaciónDao y se sobreescribe
	public Habitacion obtenerHabitacionPorId(int idHabitacion) { //devuelve objeto de tipo Habitacion
		// TODO Auto-generated method stub

		Session miSession = null; //se crea vacía la sesión
		
		Transaction tx=null; //se crea la transacción vacía donde se va a acumular todo lo que se quiere tramitar a bbdd para enviarlo de una

		// try with resources para que se cierre solo no se puede hacer porque no llega al rollback porque cierra sesión antes y si se mete el rollback en otro try solo se controlaría la excepción y el rollback sería mentira
		try {
			
			miSession=mySessionFactory.openSession(); //se abre la sesión manualmente
			
			tx=miSession.beginTransaction(); //comenzar la transación, aquí es donde todo queda dentro de tx
			
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion); //primero hay que buscar la habitación en bbdd por Id

			tx.commit(); // commit para los cambios de la transacción en bbdd, confirma la transacción, aquí se acaba oficialmente la transacción
			
			return habitacion; //después del commit devolver la habitación

		} catch (Exception e) {
			
			if(tx!=null) { //si es distinto de null es que la transacción se inicio pero aún así pudo fallar y si no está completa o falla algo se hace el rollback y echa todo para atrás
				
			
				tx.rollback(); // rollback se asegura de que si no está completo o hay algún error antes del commit se deshaga todo
			
			}
			
			System.out.println("error al buscar la habitación");
		
		}finally {
			
			miSession.close(); // se cierra la sesión

	}
		
		return null; //se pone fuera del catch por si no hay habitación y no entra en la excepcion tiene que devolver algo
		
	}

}
