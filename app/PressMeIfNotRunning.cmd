REM @ECHO OFF
cd %CD%
java -Djava.library.path=".;lib" -classpath Medical_Chat_Bot.jar;lib\jpl.jar -jar Medical_Chat_Bot.jar
pause