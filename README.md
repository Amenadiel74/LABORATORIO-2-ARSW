# BarrierSynch - Barrier Synchronization

**Author:** Stiven Esneider Pardo Gutierrez

## Description

A Java project demonstrating thread synchronization using a barrier mechanism (`CountDownLatch`).
N threads are spawned that perform the same task at different speeds, and at the end, the average execution time of all threads is calculated.

## Original Problem

In the original version, the main thread (`main`) started N threads with `start()` but **did not wait for them to finish** before reading the results of each one. Since the threads were still running, `getResultado()` returned `0`, resulting in an incorrect average.

## Applied Solution: Barrier Synchronization

`java.util.concurrent.CountDownLatch` was used as a barrier:

1. The `main` thread creates a `CountDownLatch(N)`.
2. Each `HiloProc` invokes `latch.countDown()` upon finishing its execution in `run()`.
3. The `main` thread calls `latch.await()` after starting the threads, which blocks its execution until the last thread finishes (when the count reaches 0).
4. Only then is the average execution time calculated.

### Modified Files

- **`src/edu/eci/arsw/samples/HiloProc.java`**: Added static field `CountDownLatch`, method `setLatch()`, and the call to `latch.countDown()` at the end of `run()`.
- **`src/edu/eci/arsw/samples/Main.java`**: Added creation of the `CountDownLatch`, passed it to threads via `setLatch()`, and added `latch.await()` before calculating the average.

## Requirements

- Java 6 or higher
- javac and java on PATH

## Compilation and Execution

### From Terminal

```bash
cd LABORATORIO-2-ARSW
javac -d bin src/edu/eci/arsw/samples/*.java
java -cp bin edu.eci.arsw.samples.Main
```

### From Eclipse

1. File > Import > General > Existing Projects into Workspace
2. Select the `LABORATORIO-2-ARSW` folder
3. Run `Main.java` with Run As > Java Application

## Expected Result

The program prints the progress of each thread and finally shows a message like:

```
El tiempo promedio de la ejecucion fue de: 12345
```

The value must be a realistic positive number (different from 0), reflecting the actual time the threads took to complete their task.
