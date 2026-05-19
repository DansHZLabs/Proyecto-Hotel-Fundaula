
init
=======
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
git clone https://github.com/PrimeraEdicionFlexible/ProyectoFinalEquipoL.git
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

ORDEN CORRECTO PARA EJECUTAR LOS SCRIPTS:

1. hoteldb_CREATE DATABASE.sql  
2. hoteldb_usuarios.sql  
3. hoteldb_huespedes.sql  
4. hoteldb_habitaciones.sql  
5. hoteldb_reservas.sql  
6. hoteldb_incidencias.sql  

<p align="center">
  <img src="docs/images/diagrama ER BBDD.png" style="max-width: 100%; height: auto;" />
  <br>
  <em>diagrama ER</em>
</p>

Los scripts SQL se encuentran en la carpeta /sql del proyecto.

<h2 align="center">Tablas de la BBDD</h2>

<p align="center">
  <img src="docs/images/tabla usuarios.png" width="500" />
  <br>
  <em>tabla usuarios</em>
</p>

<br>

<p align="center">
  <img src="docs/images/tabla habitaciones.png" width="500" />
  <br>
  <em>tabla habitaciones</em>
</p>

<br><br>

<p align="center">
  <img src="docs/images/tabla huespedes.png" width="500" />
  <br>
  <em>tabla huespedes</em>
</p>

<br><br>

<p align="center">
  <img src="docs/images/tabla incidencias.png" width="500" />
  <br>
  <em>tabla incidencias</em>
</p>

<br>

<p align="center">
  <img src="docs/images/tabla reservas.png" width="500" />
  <br>
  <em>tabla reservas</em>
</p>
<br>

<h2 align="center">Relaciones de las tablas</h2>

<p align="center">
  <img src="docs/images/fk de incidencias.png" width="500" />
  <br>
  <em>FK de incidencias</em>
</p>

<br>

<p align="center">
  <img src="docs/images/fk de reservas.png" width="500" />
  <br>
  <em>FK de reservas</em>
</p>

---

## 🛠️ Tecnologías utilizadas

### 🔧 Backend
- ☕ **Java 11**  
- 🌱 **Spring MVC**  
- 🗄️ **Hibernate (ORM)**  

### 💾 Base de datos
- 🐬 **MySQL 8**

### 🎨 Frontend
- 🖥️ **JSP + JSTL**

### 🚀 Servidor
- 🌐 **Apache Tomcat 9**

### 🔐 Seguridad y logging
- 📜 **Log4j** (logging)  
- 🔒 **BCrypt** (seguridad)  

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
es.accenture.services     → PAQUETE SERVICES: es la lógica, se manda a DAO para las consultas a BBDD
es.accenture.utils        → Tareas adicionales
```
<p align="center">
  <img src="docs/images/estructura de paquetes.png" style="width: 50%; max-width: 900px; height: auto;" />
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
nombreController → gestiona el login, el logout y la sesión de usuario
nombreController → gestiona las peticiones del CRUD de habitaciones
nombreController → gestiona las peticiones del CRUD de huéspedes
nombreController → gestiona las peticiones del CRUD de reservas
```

### PAQUETE DAO: es el acceso a BBDD, lo usa Services para el acceso a datos

```
nombreDao → establece la lógica del acceso a los datos de...
nombreDao → establece la lógica del acceso a los datos de...
nombreDao → establece la lógica del acceso a los datos de...
nombreDao → establece la lógica del acceso a los datos de...
nombreDao → establece la lógica del acceso a los datos de...
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
nombreException → error de acceso a base de datos
nombreException → error de autentificación
nombreException → no encontrado
nombreException → el error que sea
```

### PAQUETE INTERFACES: son las interfaces que definen los contratos de métodos que se deben cumplir

```
Inombre → interfaz que hace...
Inombre → interfaz que hace...
Inombre → interfaz que hace...
Inombre → interfaz que hace...
Inombre → interfaz que hace...
Inombre → interfaz que hace...
Inombre → interfaz que hace...
```

### PAQUETE SERVICES: es la lógica, se manda a DAO para las consultas a BBDD

```
nombreService → servicio del que se encarga
nombreService → servicio del que se encarga
nombreService → servicio del que se encarga
nombreService → servicio del que se encarga
```

### PAQUETE UTILS: tareas adicionales

```
nombreUtils → 
nombreUtils → 
nombreUtils → 
nombreUtils → 
```

### VISTAS: (van en WebInf, Vistas)

```
nombre.jsp → vista de la pantalla de...
nombre.jsp → vista de la pantalla de...
nombre.jsp → vista de la pantalla de...
nombre.jsp → vista de la pantalla de...
nombre.jsp → vista de la pantalla de...
nombre.jsp → vista de la pantalla de...
```

(en SRC, Main, Resources)

```
application.properties → contiene configuración de conexión a base de datos
```

carpeta sql/ en la raiz con los scrips de BBDD y su orden de ejecución

Separación de responsabilidades para seguir buenas prácticas

Arquitectura en capas (Controller → Service → DAO → DB)


---

## 🌿 Estructura de ramas:

```
main        → versión final estable
develop     → integración
feature/*   → desarrollo por funcionalidades
```

Ejemplos:

```
feature/setup
feature/sql
feature/habitaciones
feature/incidencias
feature/login
feature/huespedes
feature/reservas
```

---

## 🔄 Flujo de trabajo:

Cada funcionalidad en rama feature/*  
Pull Request a develop  
Revisión por compañero  
Merge a develop  
Merge final a main  

<p align="center">
  <img src="docs/images/dibujo git.png" style="max-width: 100%; height: auto;" />
  <br>
  <em>imagen simplificada del flujo de trabajo</em>
</p>

---

## 👥 Reparto de tareas:

A Javi → (ej: Habitaciones + Incidencias)  
B Jorge → (ej: Huéspedes + Login)  

---

## ⚠️ Conflictos de merge: ------------------> apuntar los que surjan

Se resolvieron conflictos principalmente en:

- configuración de Spring ---------------> es un ejemplo, borrar luego  
- controladores compartidos -------------> es un ejemplo, borrar luego  
- ficheros JSP comunes ------------------> es un ejemplo, borrar luego  

Resolución manual combinando cambios de los dos desarrolladores.

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
- Llama al servicie

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

- [proyecto (PDF)](docs/proyecto.pdf)

---

## Uso de inteligencia artificial
### Herramientas utilizadas
**Alumno A — Javier Roldán Pomareta
- ChatGPT (versión: 5.5)

Usos:

**Alumno B — *(Nombre Apellido)*:**
- Claude (versión: ...)
- Otra: ...
(Cada alumno marca solo las que haya usado. Si alguno no usó ninguna, escribir «Ninguna»
en su bloque.)

---

### Áreas en las que se ha usado

<table>
<tr>
<th>Componente</th>
<th>Quién</th>
<th>¿Has usado IA?</th>
<th>¿Para qué?</th>
</tr>

<tr>
<td>POM</td>
<td>A</td>
<td>Sí / -</td>
<td>En el Eclipse de mis compañeros saltaba un error en el POM (no me acuerdo cuál era) pero a mi sí me funcionaba, faltaba la dependencia de plugging de Maven porque yo uso una versión diferente porque aunque tengo todas instaladas me gusta más, se copió y se pegó y se comprobó que funcionaba</br>
Tengo estas dependencias que he ido buscando en foros y copiando de otros proyectos para mi proyecto es un proyecto de Spring MVC que vamos a usar Hibernate, mysql (y no me acuerdo qué más le puse en el prompt) dime si me falta alguna y me dijo que no.
</td>
</tr>

<tr>
<td>Configuración de Spring (XML / Java Config)</td>
<td>A</td>
<td>Sí / -</td>
<td>He copiado estas clases de configuración de un proyecto para meterlas en el mío que es un proyecto Maven con configuración por clases e Hibernate, dime si están bien y si los comentarios que he puesto son correctos para explicar cada cosa o estoy poniendo alguna burrada</td>
</tr>

<tr>
<td>Excepciones personalizadas</td>
<td>Ambos</td>
<td>Sí / No</td>
<td>Ej: «plantilla de clase de excepción»</td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Habitaciones</td>
<td>A</td>
<td>Sí / No</td>
<td>...</td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Incidencias</td>
<td>A</td>
<td>Sí / No</td>
<td>...</td>
</tr>

<tr>
<td>Entidad Reserva + DAO de Reservas (común)</td>
<td>quien lo desarrolló</td>
<td>Sí / No</td>
<td>...</td>
</tr>

<tr>
<td>Reservas — listado / detalle / eliminación + JSPs</td>
<td>A</td>
<td>Sí / No</td>
<td>...</td>
</tr>

<tr>
<td>Entidad Usuario / DAO Usuario / LoginController</td>
<td>B</td>
<td>Sí / No</td>
<td>...</td>
</tr>

<tr>
<td>Entidad / DAO / Controller / vistas de Huéspedes</td>
<td>B</td>
<td>Sí / No</td>
<td>...</td>
</tr>

<tr>
<td>Reservas — alta / modificación + FormularioReserva.jsp</td>
<td>B</td>
<td>Sí / No</td>
<td>...</td>
</tr>

<tr>
<td>Documentación (Readme)</td>
<td>A / - / -</td>
<td>Sí / -</td>
<td>Dime cómo se ponen los títulos en grande en el readme</br>
Dime cómo se pone color morado de fondo en el título principal del readme y ponle un dibujito</br>
Dame el código para meter una imagen en un div en el readme para que quede responsive</br>
El profe me ha dado esta tabla para meter en el readme pero se ve un truño en el preview, dámela en h
tml</br>
Dame un icono para pegar en cada título que corresponda con los nombres de los apartados</br>
Dime como meter una línea al final de cada apartado del readme</br>
Dime una descripción buena para poner en este readme</br>
</td>
</tr>
</table>

<tr>
<td>Documentación (Javadoc)</td>
<td>A / B / Ambos</td>
<td>Sí / No</td>
<td>...</td>
</tr>
</table>

---

### Compromiso

Declaro que entiendo todo el código que entrego.

Puedo explicar qué hace cada fragmento marcado como IA, justificar por qué encaja en el proyecto y modificarlo durante la defensa si fuera necesario.

— Alumno A: Javier Roldán Pomareta

— Alumno B: *(Nombre del alumno)*

---

## Capturas de pantalla:

Acordarnos de poner aquí al final cuando acabemos todo unas capturas de pantalla de la app funcionando que no se tarda nada y queda muy bien.
