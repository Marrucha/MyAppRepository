call runcrud.bat
if %ERRORLEVEL% =="0" goto runpage

:runpage
start chrome "http://localhost:8080/crud/v1/task/getTasks"
