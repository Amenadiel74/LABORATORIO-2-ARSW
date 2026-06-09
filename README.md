# BarrierSynch - Sincronización por Barrera

**Autor:** Stiven Esneider Pardo Gutierrez

## Descripción

Proyecto Java de ejemplo sobre sincronización de hilos usando un mecanismo de barrera (`CountDownLatch`).
Se lanzan N hilos que realizan una misma tarea a diferentes velocidades, y al final se calcula el
promedio del tiempo de ejecución de todos ellos.

## Problema Original

En la versión original, el hilo principal (`main`) iniciaba los N hilos con `start()` pero **no
esperaba a que terminaran** antes de leer el resultado de cada uno. Como los hilos aún se estaban
ejecutando, `getResultado()` devolvía `0`, dando un promedio incorrecto.

## Solución Aplicada: Sincronización por Barrera

Se utilizó `java.util.concurrent.CountDownLatch` como barrera:

1. El `main` crea un `CountDownLatch(N)`.
2. Cada `HiloProc`, al terminar su ejecución en `run()`, invoca `latch.countDown()`.
3. El `main` llama a `latch.await()` después de iniciar los hilos, lo que bloquea su ejecución hasta
   que el último hilo termine (cuando el contador llegue a 0).
4. Solo entonces se calcula el promedio de los tiempos de ejecución.

### Archivos modificados

- **`src/edu/eci/arsw/samples/HiloProc.java`**: Se agregó campo estático `CountDownLatch`, método
  `setLatch()` y llamada a `latch.countDown()` al final de `run()`.
- **`src/edu/eci/arsw/samples/Main.java`**: Se agregó creación del `CountDownLatch`, se pasa a los
  hilos mediante `setLatch()`, y se añadió `latch.await()` antes del cálculo del promedio.

## Requisitos

- Java 6 o superior
- javac y java en PATH

## Compilación y Ejecución

### Desde la terminal

```bash
cd BarrierSynch
javac -d bin src/edu/eci/arsw/samples/*.java
java -cp bin edu.eci.arsw.samples.Main
```

### Desde Eclipse

1. File > Import > General > Existing Projects into Workspace
2. Seleccionar la carpeta `BarrierSynch`
3. Ejecutar `Main.java` con Run As > Java Application

## Resultado Esperado

El programa imprime el progreso de cada hilo y al final muestra un mensaje como:

```
El tiempo promedio de la ejecucion fue de: 12345
```

El valor debe ser un número positivo y realista (diferente de 0), reflejando el tiempo real
que tomaron los hilos en completar su tarea.
