CREATE TABLE `habitaciones` (
  `id_habitacion` int NOT NULL AUTO_INCREMENT,
  `numero_habitacion` varchar(50) NOT NULL,
  `tipo_habitacion` enum('INDIVIDUAL','DOBLE','SUITE') NOT NULL,
  `precio_noche` decimal(7,2) NOT NULL,
  `disponibilidad_habitacion` enum('DISPONIBLE','OCUPADA','LIMPIEZA','MANTENIMIENTO') NOT NULL,
  `orientacion_habitacion` enum('INTERIOR','EXTERIOR') DEFAULT NULL,
  PRIMARY KEY (`id_habitacion`),
  UNIQUE KEY `numero_habitacion` (`numero_habitacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
