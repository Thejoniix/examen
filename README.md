# Buscaminas en Consola

Proyecto desarrollado en Java para la asignatura de Programación Orientada a Objetos.  
El programa implementa una versión en consola del juego Buscaminas, aplicando POO, MVC, excepciones, persistencia de datos y pruebas unitarias.

## Integrantes

- Juan Xavier Peralta
- Jonathan Pulupa
- 
- 

## Descripción

El juego consiste en descubrir todas las casillas seguras de un tablero de 10x10 sin seleccionar una mina.  
El tablero contiene 10 minas ubicadas de forma aleatoria. Las casillas descubiertas muestran el número de minas cercanas o el símbolo `.` cuando no hay minas alrededor.
## Diagrama de clases 
<img width="1539" height="1463" alt="DiargamaClaseExamen" src="https://github.com/user-attachments/assets/904fbb42-4318-47f3-ad8c-530608c3abb3" />

## Funcionalidades

- Tablero de 10x10.
- Colocación aleatoria de 10 minas.
- Descubrimiento de casillas.
- Revelado automático de casillas vacías.
- Marcado y desmarcado de posibles minas.
- Detección de victoria y derrota.
- Guardado y carga de partida mediante archivo binario.
- Manejo de excepciones personalizadas.
- Pruebas unitarias con JUnit 5.

## Estructura del proyecto

```text
src/
└── buscaminas/
    ├── Main.java
    ├── acciones/
    │   ├── AccionJuego.java
    │   ├── AccionDescubrir.java
    │   └── AccionMarcar.java
    ├── controlador/
    │   └── ControladorJuego.java
    ├── excepciones/
    │   ├── CasillaMarcadaException.java
    │   └── CasillaYaDescubiertaException.java
    ├── modelo/
    │   ├── Casilla.java
    │   ├── Tablero.java
    │   └── Juego.java
    ├── persistenciaDatos/
    │   ├── GestorPartida.java
    │   └── GestorPartidaArchivo.java
    └── vista/
        └── VistaConsola.java
```
## Pruebas unitarias
```\
src/
└── buscaminas/
    ├── modelo/
    │   ├── CasillaTest.java
    │   ├── TableroTest.java
    │   └── JuegoTest.java
    └── persistenciaDatos/
        └── GestorPartidaArchivoTest.java
```

** Requisitos
- Java JDK 17 o superior.
- Eclipse IDE.
- JUnit 5 para ejecutar las pruebas unitarias.

## Ejecución del programa

### Desde Eclipse
Clorar Repositorio
git clone https://github.com/Thejoniix/examen
1. Importar el proyecto.
2. Abrir la clase `Main.java`.
3. Ejecutar con:

```text
Run As → Java Application
```

### Desde consola

Ubicarse en la carpeta `src` del proyecto y ejecutar:

```bash
javac buscaminas/Main.java
java buscaminas.Main
```

## Uso del juego

Al iniciar el programa se muestra un menú con las siguientes opciones:

```text
1. Descubrir
2. Marcar
3. Guardar
4. Cargar
5. Salir
```

Las coordenadas se ingresan usando una letra y un número. Por ejemplo:

```text
B2
E6
H9
```

## Símbolos del tablero

```text
?   = Casilla cubierta
M   = Casilla marcada
V   = Casilla vacía
X   = Mina
1-8 = Cantidad de minas cercanas
```

## Ejecución de pruebas

Para ejecutar las pruebas unitarias en Eclipse:

1. Abrir una clase de prueba, por ejemplo `CasillaTest.java`.
2. Hacer clic derecho sobre el archivo.
3. Seleccionar:

```text
Run As → JUnit Test
```

También se puede ejecutar todo el paquete de pruebas usando JUnit 5.

## Patrón aplicado

El proyecto aplica el patrón MVC:

```text
Modelo      = contiene la lógica del juego.
Vista       = muestra el tablero y los mensajes en consola.
Controlador = conecta la vista con el modelo y gestiona las acciones del jugador.
```

## Persistencia

La partida se guarda en un archivo binario llamado:


```text
partida_buscaminas.dat
```

Esto permite cargar una partida guardada y continuar el juego.
