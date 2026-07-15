<h1 align="center" style="
background: linear-gradient(135deg, #7b2ff7, #f107a3);
padding: 18px;
border-radius: 12px;
color: white;
font-size: 48px;
box-shadow: 0 0 15px rgba(241, 7, 163, 0.6);
">
🏨 SpringHotelApp
</h1>

## 📌 Descripción:
Aplicación web desarrollada con Spring MVC y Hibernate para la gestión de un hotel. Permite administrar habitaciones, huéspedes, reservas e incidencias, así como autenticación de usuarios con distintos roles (recepcionista y supervisor).

El sistema cubre operaciones CRUD completas y simula un entorno real de gestión hotelera con control de acceso según perfil.

---

## ⚙️ Requisitos previos:

- Java JDK 8+
- Apache Tomcat 9+
- MySQL 8.0
- Maven
- IDE (Eclipse)

---

## 🚀 Instalación y despliegue:

1. Clonar el repositorio:

```
git clone https://github.com/DansHZLabs/Proyecto-Hotel-Fundaula.git
```

2. Importar el proyecto en el IDE (Eclipse como Maven project)

3. Configurar la conexión a la base de datos en:  

   `src/main/resources/application.properties`
   

4. Ejecutar los scripts SQL en MySQL Workbench

5. Cambia usuario y contraseña según tu MySQL en application.properties según tu configuración local

6. Desplegar en Tomcat

7. Acceder en el navegador:

```
http://localhost:8080/SpringHotelApp
```

---

## 🗄️ Creación de la BBDD:

ORDEN CORRECTO PARA EJECUTAR LOS SCRIPTS (se encuentran en la carpeta /sql del proyecto):


1. hoteldb_CREATE DATABASE.sql
2. hoteldb_usuarios.sql
3. hoteldb_huespedes.sql
4. hoteldb_habitaciones.sql
5. hoteldb_reservas.sql
6. hoteldb_incidencias.sql
7. hoteldb_datos_usuarios.sql
8. hoterdb_datos_huespedes.sql
9. hoteldb_datos_habitaciones.sql
10. hoteldb_datos_incidencias.sql
11. hoteldb_datos_reservas.sql 
 

<h2 align="center">Tablas de la BBDD</h2>

<p align="center">
<img width="499" height="215" alt="image" src="https://github.com/user-attachments/assets/cfd0209e-7c7c-4d67-9651-51c5ccd89bb4" />
  <br>
  <em>tabla usuarios</em>
</p>

<br>

<p align="center">
  <img width="496" height="213" alt="image" src="https://github.com/user-attachments/assets/15fe7756-f374-4b15-95b6-f284983e4a6f" />
  <br>
  <em>tabla habitaciones</em>
</p>

<br><br>

<p align="center">
  <img width="496" height="213" alt="image" src="https://github.com/user-attachments/assets/fbb26278-ca84-4334-81c8-89433bea0a72" />
  <br>
  <em>tabla huespedes</em>
</p>

<br><br>

<p align="center"><img width="494" height="215" alt="image" src="https://github.com/user-attachments/assets/be0ca565-2f5d-4ee9-998d-8ffda8fdf9b2" />
  <br>
  <em>tabla incidencias</em>
</p>

<br>

<p align="center">
  <img width="499" height="239" alt="image" src="https://github.com/user-attachments/assets/34f89899-4a6c-485e-a8c1-b525842fe9f4" />
  <br>
  <em>tabla reservas</em>
</p>
<br>

<h2 align="center">Relaciones de las tablas</h2>

<p align="center">
  <img width="496" height="173" alt="image" src="https://github.com/user-attachments/assets/3dd937ee-e53f-412a-b844-e91e9a38a811" />
  <br>
  <em>FK de incidencias</em>
</p>

<br>

<p align="center">
 <img width="498" height="175" alt="image" src="https://github.com/user-attachments/assets/422ebf04-219e-4198-a7f2-d11a45f4342f" />
  <br>
  <em>FK de reservas</em>
</p>

---

## Visualización del proyecto:

<p align="center">
  <img width="1240" height="622" alt="Screenshot_15-7-2026_2016_localhost" src="https://github.com/user-attachments/assets/afa58e48-38be-4a2c-8c49-426fcf77e2c7" />
  <br>
  <em>Pantalla Login</em>
</p>

<p align="center">
 <img width="1240" height="622" alt="Screenshot_15-7-2026_20141_localhost" src="https://github.com/user-attachments/assets/a95adbd1-b4a4-4397-a25c-dd10c8bfd586" />
  <br>
  <em>Pantalla Inicio</em>
</p>

<p align="center">
 <img width="1240" height="622" alt="Screenshot_15-7-2026_20153_localhost" src="https://github.com/user-attachments/assets/b04707ac-baef-4c6c-8ed2-62c7f07beeb0" />
  <br>
  <em>Pantalla Habitaciones</em>
</p>

<p align="center">
<img width="1240" height="622" alt="Screenshot_15-7-2026_2028_localhost" src="https://github.com/user-attachments/assets/804c7d3e-08d0-4aef-87b7-eefedae23366" />
  <em>Pantalla Detalle Habitacion</em>
</p>

<p align="center">
 <img width="1240" height="749" alt="Screenshot_15-7-2026_20221_localhost" src="https://github.com/user-attachments/assets/7168bede-0369-4feb-88b0-bd2808a60b1f" />
  <br>
  <em>Pantalla Formulario editar Habitacion</em>
</p>

<p align="center">
<img width="1240" height="749" alt="Screenshot_15-7-2026_20233_localhost" src="https://github.com/user-attachments/assets/71f7b4f6-b09f-4d61-86a8-14f324ce74b8" />
  <br>
  <em>Pantalla nueva Habitacion</em>
</p>

<p align="center">
<img width="1240" height="622" alt="Screenshot_15-7-2026_20245_localhost" src="https://github.com/user-attachments/assets/6c6fda08-375c-4a2e-a1e1-50e91b8d82dc" />
  <br>
  <em>Pantalla Huespedes</em>
</p>

<p align="center">
<img width="1240" height="622" alt="Screenshot_15-7-2026_20320_localhost" src="https://github.com/user-attachments/assets/75ef9113-6ed6-4323-80d9-f5dc3102466a" />
  <br>
  <em>Pantalla Incidencias</em>
</p>

<p align="center">
<img width="1240" height="622" alt="Screenshot_15-7-2026_20331_localhost" src="https://github.com/user-attachments/assets/d2d80523-d848-47d5-a316-b3accdfa8f8a" />
<br>
  <em>Pantalla Reservas</em>
</p>

---

## 🛠️ Tecnologías utilizadas

### 🔧 Backend
- ☕ **Java 11**  
- 🌱 **Spring MVC**  
- 🗄️ **Hibernate (ORM)**
- 🎁 **Maven**

### 💾 Base de datos
- 🐬 **MySQL 8**

### 🎨 Frontend
- 🖥️ **JSP + JSTL**
- 🏷️ **Spring Form Tags**
- 🖌️ **CSS**

### 🚀 Servidor
- 🌐 **Apache Tomcat 9**

---

## 🧱 Estructura del proyecto:

Arquitectura en capas:

```
es.accenture.config       → configuración
es.accenture.controller   → gestión de peticiones HTTP
es.accenture.dao          → es el acceso a BBDD, lo usa Services por medio de Controller
es.accenture.entity       → entidades de la bbdd
es.accenture.exceptions   → excepciones personalizadas
es.accenture.interfaces   → son las interfaces que definen los contratos de métodos que se deben cumplir
es.accenture.services     → es la lógica, se manda a DAO para las consultas a BBDD
```
<p align="center">
  <img width="295" height="214" alt="screenshot" src="https://github.com/user-attachments/assets/b33fa337-b574-445d-8973-146517417c70" />
  <br>
  <em>imagen de los paquetes del proyecto</em>
</p>

### PAQUETE CONFIG: configuración de Spring, Beans, etc

```
AppConfig         → configura Spring (escaneo de componentes y beans generales)
AppInitializer    → arranque de la app (sustituye al web.xml)
WebConfig         → configura Spring MVC (las vistas, las rutas, el ViewResolver)
HibernateConfig   → configura conexión a base de datos + ORM + transacciones
```

### PAQUETE CONTROLLER: recibe las peticiones HTTP, llama al service y da la respuesta

```
LoginController → gestiona el login, el logout y la sesión de usuario
HabitacionController → gestiona las peticiones del CRUD de habitaciones
HuespedController → gestiona las peticiones del CRUD de huéspedes
IncidenciaController → gestiona las peticiones del CRUYD de incidencias
ReservasController → gestiona las peticiones del CRUD de reservas
```

### PAQUETE DAO: es el acceso a BBDD, lo usa Services para el acceso a datos

```
HabitacionDao → establece la lógica del acceso a los datos de Habitacion
HuespedDao → establece la lógica del acceso a los datos de Huesped
IncidenciaDao → establece la lógica del acceso a los datos de Incidencia
ReservasDao → establece la lógica del acceso a los datos de Reservas
UsuarioDao → establece la lógica del acceso a los datos de Usuario
```

### PAQUETE ENTITIES: entidades de la bbdd

```
Habitacion → es representación de la tabla habitaciones
Huesped → es representación de la tabla huéspedes
Reserva → es representación de la tabla reservas
Incidencia → es representación de la tabla incidencias
Usuario → es representación de la tabla usuarios (login y roles)
```

### PAQUETE EXCEPTIONS: son las excepciones personalizadas

```
ActualizarException → error de actualización en base de datos
BuscarException → error de acceso en base de datos
EliminarException → error de eliminación en base de datos
GuardarException → error de guardado en base de datos
HuespedException → error de creacion de huespedes en base de datos
UsarioException → error de autentificación
```

### PAQUETE INTERFACES: son las interfaces que definen los contratos de métodos que se deben cumplir

```
IHabitacionDao
IHabitacionService
IHuespedDao
IHuespedService
IIncidenciaDao
IIncidenciaService
IReservasDao
IReservasService
IUsuarioDao
IUsuarioService

```

### PAQUETE SERVICES: es la lógica, se manda a DAO para las consultas a BBDD

```
HabitacionService → se encarga de la lógica de Habitacion
HuespedService → se encarga de la lógica de Huesped
IncidenciaService → se encarga de la lógica de Incidencia
ReservasService → se encarga de la lógica de Reservas
UsuarioService → se encarga de la lógica de Usuario
```

### VISTAS: (van en WebInf, Vistas; y muestran la pantalla con los datos html y css correspondientes a cada sección)

```
DetalleHabitacion.jsp
DetalleHuesped.jsp
DetalleIncidencia.jsp
DetalleReserva.jsp
FormularioHabitacion.jsp
FormularioHuesped.jsp
FormularioIncidencia.jsp
FormularioReserva.jsp
Habitaciones.jsp
Huespedes.jsp
Incidencias.jsp
Principal.jsp
Reservas.jsp
```

(en SRC, Main, Resources)

```
application.properties → contiene configuración de conexión a base de datos
```

carpeta sql/ en la raiz con los scrips de BBDD y su orden de ejecución

Se realizó la separación de responsabilidades para seguir buenas prácticas

Arquitectura en capas (Controller → Service → DAO → DB)


---

## 🌿 Estructura de ramas:

```
main        → versión final estable
develop     → integración
```

---

## 🔄 Flujo de trabajo:

Commits  
Revisión por compañero  
Merge final a main  

---

## ✅ Funcionalidades:

- Login con roles (recepcionista / supervisor)
- CRUD habitaciones
- CRUD huéspedes
- CRUD incidencias
- CRUD reservas
- Control de acceso por rol

---

# 🔄 Flujo completo de la App

## Vista (JSP)
- El usuario interactúa (formulario, botón…)
- Hace una petición HTTP

## Controller
- Recibe la petición (@Controller)
- Llama al service

## Service
- Valida datos básicos
- Lógica de negocio
- Llama al DAO

## DAO
- Interfaz + implementación
- Acceso a base de datos (Hibernate/JPA)

## Entity
- Clases mapeadas a tablas (@Entity)
- Representan los datos

## Base de datos (MySQL)
- Devuelve los datos

## Flujo de datos
BD → DAO → Service → Controller → JSP (vista con los datos)

---

## Ejemplo (Habitaciones)

Habitaciones.jsp → click “ver listado”  
HabitacionController → obtenerHabitaciones()  
Service valida datos  
HabitacionDAO → query Hibernate  
Habitacion (Entity) ← datos  
Controller mete datos en Model  
JSP muestra listado

---

## 🔒 Validaciones de las reglas de negocio (a revisar tema historiales)

### 🏨 Eliminación de habitaciones

**Incidencias**
- abierta → ❌ No
- en_proceso → ❌ No
- cerrada → ❌ No

**Reservas**
- pendiente → ❌ No
- confirmada → ❌ No
- cancelada → ❌ No
- finalizada → ❌ No

**Disponibilidad**
- disponible → ✔️ Sí
- ocupada → ❌ No

---

### 👤 Eliminación de huéspedes

- pendiente → ❌ No
- confirmada → ❌ No
- cancelada → ✔️ Sí
- finalizada → ❌ No

---

### ⚠️ Eliminación de incidencias

- abierta → ❌ No
- en_proceso → ❌ No
- cerrada → ❌ No

---

## 🔑 Credenciales de prueba:

```
recepcionista / recep123
supervisor / super123
```

---

## Documentación:

Ver carpeta /docs:

Proyecto (PDF)

- [proyecto (PDF)](SpringHotelApp/docs/proyecto.pdf)


## 👥 Autores:

- Javier Roldán Pomareta
- Daniel Herraiz Bastida
<br>
<br>
