CREATE TABLE `incidencias` (
  `id_incidencia` int NOT NULL AUTO_INCREMENT,
  `estado_incidencia` enum('abierta', 'en curso', 'cerrada') NOT NULL,
  `prioridad` enum('baja', 'media', 'alta') NOT NULL,
  `descripcion_incidencia` TEXT DEFAULT NULL,
  `fecha_apertura` DATE NOT NULL,
  `fecha_cierre` DATE NOT NULL,
  `id_habitacion` int NOT NULL,
  PRIMARY KEY (`id_incidencia`),
  CONSTRAINT `fk_incidencias_habitacion` 
    FOREIGN KEY (`id_habitacion`) 
    REFERENCES `habitaciones` (`id_habitacion`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
