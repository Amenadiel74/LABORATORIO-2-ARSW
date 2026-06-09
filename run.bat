@echo off
echo ==========================================
echo Compiling and Running Laboratorio 2: Barrier
echo ==========================================
if not exist bin mkdir bin
javac -d bin src/edu/eci/arsw/samples/*.java
java -cp bin edu.eci.arsw.samples.Main
pause
