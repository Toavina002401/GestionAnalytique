@echo off

rem Crée le répertoire "out" pour copier les fichiers .java
mkdir "tempsrc"

rem Copie tous les fichiers .java du répertoire "src" vers "out"
for /r "src" %%f in (*.java) do copy "%%f" "tempsrc"

rem Compile tous les fichiers Java dans "out" en spécifiant le classpath
cd "tempsrc" 
javac -cp "..\lib\*" -d "..\temp\WEB-INF\classes" *.java
cd ..

rem Crée le fichier JAR après compilation
jar cfe "lib\front-controller.jar" mg.MainClass -C "temp\WEB-INF\classes" .

rem Nettoie les répertoires temporaires
if exist "tempsrc" (
    rmdir /s /q "tempsrc"
)
if exist "mg" (
    rmdir /s /q "mg"
)
