@echo off
echo ========================================
echo   Sistema de Gestion de Estudiantes
echo ========================================
echo.

REM Verificar que existe el directorio de clases compiladas
if not exist "out" (
    echo [ERROR] No se encontraron clases compiladas.
    echo Ejecute primero: compilar.bat
    pause
    exit /b 1
)

REM Ejecutar la aplicacion
java -cp "out;lib/*" com.sena.estudiantes.app.Main

pause
