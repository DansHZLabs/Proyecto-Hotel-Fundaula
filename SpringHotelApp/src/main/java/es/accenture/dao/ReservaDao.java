package es.accenture.dao;                     //esto lo hace cualquiera entero A o B //copiar y pegar habitación y cambiar nombres

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Reserva;
import es.accenture.interfaces.IReservaDao;

@Transactional //para que Spring gestione automáticamente las transacciones
@Repository // Anotación para decirle a Spring que esta clase es un DAO para acceder a bbdd, spring crea objeto automáticamente
public class ReservaDao implements IReservaDao{
	
	 private SessionFactory mySessionFactory; //se crea una variable mySessionFactory de tipo SessionFactory

	 @Autowired //inyección por constructor
	    public ReservaDao(SessionFactory mySessionFactory) {
	        this.mySessionFactory = mySessionFactory;
	        
	    }
	 
	// método para obtener detalles de todas las reservas, devuelve una List donde se almacenan
	@Override // Anotación que dice que es un método de la interfaz IReservaDao y se sobreescribe
	public List<Reserva> buscarReservas() {
		// TODO Auto-generated method stub
		
		List<Reserva>reservas=null; //se crea la lista vacía donde se van a guardar las que se traigan de bbdd
		
		Session miSession=mySessionFactory.getCurrentSession(); //se obtiene la sesión activa de la conexión a bbdd
		
		// consulta de reservas a bbdd, se crea una query dentro de miSession en hql (como el sql pero con objetos y clases), de la entity Reserva lo que me traigas van a ser objetos tipo Reserva y devuelve List<Reserva>
		reservas=miSession.createQuery("from Reserva",Reserva.class).getResultList();
		
		return reservas; // se devuelve la lista de las reservas
		
	}

	// método para hacer el alta de una reserva
	@Override // Anotación que dice que es un método de la interfaz IReservaDao y se sobreescribe
	public void guardarReserva(Reserva reserva) { //void que no devuelve nada, solo borra
		// TODO Auto-generated method stub
	
		Session miSession=mySessionFactory.getCurrentSession(); //se obtiene la sesión activa de la conexión a bbdd
		
		miSession.save(reserva); // guardar la reserva en bbdd, hibernate hace un insert pero no se ve
		
	}

	// método para hacer modificación de una reserva
	@Override // Anotación que dice que es un método de la interfaz IReservaDao y se sobreescribe
	public void actualizarReserva(Reserva reserva) { //void que no devuelve nada, solo borra
		// TODO Auto-generated method stub
		
		Session miSession=mySessionFactory.getCurrentSession(); //se obtiene la sesión activa de la conexión a bbdd
		
		miSession.update(reserva); // actualizar la reserva en bbdd, hibernate hace un update y cambia los datos
		
	}

	// método para eliminar una reserva
	@Override // Anotación que dice que es un método de la interfaz IReservaDao y se sobreescribe
	public void eliminarReserva(int idReserva) { //void que no devuelve nada, solo borra
		// TODO Auto-generated method stub
		
		Session miSession=mySessionFactory.getCurrentSession(); //se obtiene la sesión activa de la conexión a bbdd
		
		//primero hay que buscar la reserva en bbdd por Id
		Reserva reserva=miSession.get(Reserva.class,idReserva);
		
		//se comprueba que exista por si acaso con un if
		if(reserva!=null) {
		
			//y se borra de bbdd porque hibernate hace el delete y elimina la reserva
			miSession.delete(reserva);
			
		}
	}

	// método para obtener una reserva por su Id //es lo mismo que el de borrar pero sin el delete y que lo devuelva
	@Override // Anotación que dice que es un método de la interfaz IReservaDao y se sobreescribe
	public Reserva buscarReservaPorId(int idReserva) {
		// TODO Auto-generated method stub
		
		Session miSession=mySessionFactory.getCurrentSession(); //se obtiene la sesión activa de la conexión a bbdd
		
		Reserva reserva=miSession.get(Reserva.class,idReserva); //primero hay que buscar la reserva en bbdd por Id
		
		return reserva; //después del commit devolver la reserva
		
	}

}
