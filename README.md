# 🎮 Aplicación de Minijuegos – Proyecto Final

Para este proyecto final hemos decidido crear una aplicación que propone una selección de los **minijuegos más populares y apreciados en Internet**.

## Estructura general de la aplicación

La aplicación consta de:

- Una **página inicial** que permite iniciar sesión o crear una cuenta en caso de no tener una.  
- Una **página de inicio** donde podemos elegir el juego que queremos jugar de entre la selección disponible.  
- Una **página “Sobre nosotros”** con información y referencias del proyecto.  
- Una **página de cierre de sesión (Logout)**.

---

## Manual explicativo

Al abrir la aplicación nos encontramos con la **página de inicio de sesión** con el login de **Firebase**.  
En ella hay dos opciones: **registro** o **login**, representadas con botones.  
El usuario debe registrarse con un correo electrónico (no necesariamente real) y una contraseña de mínimo 6 caracteres.

Una vez iniciada la sesión, la aplicación lleva al **menú de juegos**, donde se puede elegir entre:

- Sudoku  
- Snake  
- Tres en raya  
- Solitario  

Para acceder a un juego basta con hacer clic sobre su imagen, que actúa como botón, y la app abrirá el juego en Internet.  
En la parte inferior aparecen los botones para acceder a las páginas **Sobre nosotros** y **Logout**.

---

### Página "Sobre nosotros"

En esta sección se muestra información sobre los creadores de la aplicación y las referencias utilizadas durante el desarrollo del proyecto.

### Página "Logout"

Permite cerrar la sesión actual. Una vez cerrada, la aplicación redirige de nuevo a la pantalla de inicio de sesión.

---

## Descripción algorítmica

A continuación se detalla la relación de clases e interfaces diseñadas, junto con sus aspectos más importantes e interacción entre las mismas.

---

### MainActivity.java

La clase `MainActivity` es la **actividad principal** de la aplicación.  
Extiende `AppCompatActivity` y se encarga de mostrar la interfaz de usuario y gestionar las interacciones.  
Incluye los métodos `onCreate` y `showHome`, además de los oyentes de eventos de los botones y vistas.

**Aspectos importantes:**

- **Interfaz `Animation`**: define la funcionalidad para las animaciones vistas en la app.  
- **Clase `ImageView`**: muestra la imagen de fondo (`bgapp`).  
- **Clase `LinearLayout`**: organiza los elementos en forma vertical u horizontal (bienvenida, inicio, menú de juegos).  
- **Clase `FloatingActionButton`**: representa los botones flotantes del menú.  
- **Clase `Intent`**: abre actividades o enlaces web.  
- **Clase `FirebaseAuth`**: maneja la autenticación con Firebase.  
- **Clase `Handler`**: programa tareas o retrasos en animaciones.

**Archivo XML: `activity_main.xml`**

Define la interfaz principal.  
Utiliza varios `LinearLayout` para estructurar la bienvenida, el texto principal, los iconos de los juegos (`ImageView`) y un fondo de pantalla (`ImageView`).

---

### LOGIN – Firebase

**Clase `AuthActivity`**

Gestiona la **autenticación de usuarios** mediante Firebase.  
Permite registrarse e iniciar sesión con correo y contraseña.

**Elementos importantes:**

- `EditText` (user, pass) y `Button` (buttonReg, buttonLog) inicializados en `onCreate`.  
- Método `setup`: define los listeners de clic.  
- Registro: verifica campos y usa `FirebaseAuth.createUserWithEmailAndPassword()`.  
- Login: usa `FirebaseAuth.signInWithEmailAndPassword()`.  
- Métodos `showAlert` y `showRegister` muestran mensajes al usuario.  
- Método `showHome`: redirige al usuario a `HomeActivity`.

---

### Clase `HomeActivity`

Representa la pantalla para **cerrar sesión**.  
Muestra el correo del usuario y ofrece el botón de “Cerrar sesión”.

**Aspectos importantes:**

- Usa `FirebaseAuth` para cerrar sesión.  
- Al hacerlo, redirige al usuario a `AuthActivity`.  
- Muestra el correo electrónico del usuario actual mediante `Intent extras`.

---

### Clase `ProviderType`

Enum que define los tipos de proveedor de autenticación.  
Actualmente contiene solo una opción: `BASIC` (correo y contraseña).

---

### AboutUsActivity.java

Muestra la página **"Acerca de nosotros"**.

**Características:**

- Subclase de `AppCompatActivity`.  
- Usa `setContentView(R.layout.about_us_activity)`.  
- Muestra información sobre el proyecto y referencias mediante un `TextView` (`textViewAboutUs`).

**Archivo XML: `about_us_activity.xml`**

- Estructura principal con `ConstraintLayout` y fondo semitransparente (`alpha: 0.7`).  
- Incluye `Toolbar` con título y `ImageButton` para volver atrás.  
- Contiene un `ScrollView` con información sobre los miembros del equipo, avatares (`ImageView`) y un `ListView` con las referencias utilizadas.

---

### Configuración

**Clase `ConfigActivity`**

Subclase de `AppCompatActivity` que muestra la pantalla **Configuración**.  
Incluye información sobre cómo configurar la app.

**Archivo XML: `config_activity.xml`**

- Estructura con `ConstraintLayout` y fondo semitransparente.  
- `Toolbar` con título y botón de regreso.  
- `LinearLayout` con un `TextView` indicando que se está en la página de configuración.

---

### Menú flotante

Presente en varias actividades:  
`AboutUsActivity.java`, `ConfigActivity.java`, `MainActivity.java`.

**Código Java:**

- Usa `setOnClickListener` y `onClick(View)` para manejar eventos de botones.  
- Contiene un botón principal que muestra otros tres botones al ser pulsado.  
- Permite navegar a diferentes actividades.

**Código XML:**

- Define cuatro `FloatingActionButton`, tres de ellos con `visibility: gone` hasta que se activa el botón principal.

---

## Interacción entre clases

1. La aplicación inicia en `AuthActivity`, donde el usuario puede **registrarse o iniciar sesión**.  
2. Tras un registro exitoso, se muestra un diálogo de confirmación y se vuelve a `AuthActivity` para iniciar sesión.  
3. Al iniciar sesión correctamente, se redirige a `MainActivity`.  
4. Desde `MainActivity`, se puede acceder a los juegos, la página “Sobre nosotros” o cerrar sesión.  
5. Al cerrar sesión desde `HomeActivity`, el usuario vuelve a `AuthActivity`.  
6. `FirebaseAuth` gestiona todo el proceso de autenticación y cierre de sesión.

---

## Tecnologías utilizadas

- **Java (Android Studio)**  
- **Firebase Authentication**  
- **XML (layouts e interfaces)**  
- **Android UI Components** (`ConstraintLayout`, `LinearLayout`, `ImageView`, `FloatingActionButton`)

---

## Autores

Proyecto desarrollado por el equipo de estudiantes de **Aplicaciones y Usabilidad**.  
Incluye referencias y recursos mencionados en la sección “Sobre nosotros”.

---

