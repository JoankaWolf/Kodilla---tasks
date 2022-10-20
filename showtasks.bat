call runcrud.bat
if %ERRORLEVEL% == 0 goto startchrome
echo.
echo Runcrud has errors
goto fail

:startchrome
start chrome http://localhost:8080/crud/v1/task/tasks
if %ERRORLEVEL% == 0 goto end
echo Chorme has errors
goto fail

:fail
echo.
echo There where errors

:end
echo.
echo Work is finished.
