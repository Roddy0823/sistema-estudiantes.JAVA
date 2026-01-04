@echo off
echo ========================================
echo   Compilando Sistema de Estudiantes
echo ========================================
echo.

REM Crear directorio de salida si no existe
if not exist "out" mkdir out

REM Compilar todos los archivos Java
echo Compilando archivos Java...
javac -cp "lib/*" -d out -sourcepath src src/com/sena/estudiantes/app/Main.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo [OK] Compilacion exitosa!
    echo.
    echo Para ejecutar use: ejecutar.bat
) else (
    echo.
    echo [ERROR] Error en la compilacion.
    echo Asegurese de tener Java JDK instalado y en el PATH.
)

pause
