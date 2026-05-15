CREATE TABLE reservas (
  id_reserva INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fecha_entrada DATE NOT NULL,
  fecha_salida DATE NOT NULL,
  id_huesped INT NOT NULL,
  id_habitacion INT NOT NULL,
  tipo_pension ENUM('ALOJAMIENTO','MEDIA','COMPLETA'),
  estado_reserva ENUM('PENDIENTE','CONFIRMADA','CANCELADA'),
  numero_huespedes INT NOT NULL,
  observaciones TEXT,
  CONSTRAINT fk_reserva_huesped
    FOREIGN KEY (id_huesped)
    REFERENCES huespedes(id_huesped),
  CONSTRAINT fk_reserva_habitacion
    FOREIGN KEY (id_habitacion)
    REFERENCES habitaciones(id_habitacion)  
) ENGINE=InnoDB 
DEFAULT CHARSET=utf8mb4 
COLLATE=utf8mb4_0900_ai_ci;
