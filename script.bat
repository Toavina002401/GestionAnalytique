@echo off

rem Récuperation des arguments
set nameProject=%~1

rem Chemin vers repertoire de temp
set temp=temp
set tempsrc=tempsrc

rem Vérifie si le dossier existe deja
if exist "%temp%" (
    rd /S /Q "%temp%"
    echo Le dossier %temp% et son contenu ont ete supprimes avec succes.
)

if exist "%tempsrc%" (
    rd /S /Q "%tempsrc%"
    echo Le dossier %tempsrc% et son contenu ont ete supprimes avec succes.
)

mkdir "%temp%"
echo Le nouveau dossier %temp% a ete cree avec succes.

mkdir "%tempsrc%"
echo Le nouveau dossier %tempsrc% a ete cree avec succes.

rem Les dossiers necessaires
set librairie=lib
set xml=web.xml
set dispatche=dispatcher-servlet.xml
set web=web
set src=src

rem Le destination de chaque dossier
set deslibrairie=%temp%/WEB-INF/lib/
set desxml=%temp%/WEB-INF/
set desweb=%temp%/WEB-INF/

rem Copie le dossier librairie dans le dossier de destination
xcopy "%librairie%" "%deslibrairie%" /E /I /Y
echo Le copie du dossier %librairie% a ete effectuez avec succes dans %deslibrairie%.

rem Copie les fichier .xml vers le dossier de destination
copy "%xml%" "%desxml%"
copy "%dispatche%" "%desxml%"

echo Le fichier %xml% et %dispatche% a ete copie dans %desxml%.

rem Copie le dossier web dans le dossier de destination
xcopy "%web%" "%desweb%" /E /I /Y
echo Le copie du dossier %web% a ete effectuez avec succes dans %desweb%.

rem Copie les sources dans le tempsrc
rem Copie tous les fichiers .java du répertoire "src" vers "out"
for /r "src" %%f in (*.java) do copy "%%f" "tempsrc"

rem Compile tous les fichiers Java dans "out" en spécifiant le classpath
cd "tempsrc" 
javac -cp "..\lib\*" -d "..\temp\WEB-INF\classes" *.java
cd ..

echo Compilation des fichiers Java dans %tempsrc% terminee. Les fichiers .class sont stockes dans %temp%/WEB-INF/classes/.

set projet=%nameProject%.war

rem Crée le fichier WAR à partir du dossier source
jar -cvf "%projet%" -C "%temp%" .

echo Le fichier WAR a ete cree : %projet%

rem Déplace le fichier WAR vers le nouveau dossier
move "%projet%" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\"

echo Deploiment effectuer avec succes

pause
