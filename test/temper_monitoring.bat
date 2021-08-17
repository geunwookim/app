echo off

:1

set YEAR=%date:~0,4%
set MONTH=%date:~5,2%
set DAY=%date:~8,2%
set HOUR=%time:~0,2%
set MINUTE=%time:~3,2%
set SECOND=%time:~6,2%

echo %YEAR%-%MONTH%-%DAY% %HOUR%:%MINUTE%:%SECOND% >> C:\Users\computer_name\Desktop\monitor.txt

netstat -ano | findstr 6000 >> C:\Users\computer_name\Desktop\monitor.txt

echo | set /p TEMP:="TEMP: " >> C:\Users\computer_name\Desktop\monitor.txt
python C:\Users\dlogixs\Desktop\test\test.py >> C:\Users\computer_name\Desktop\monitor.txt

echo. >> C:\Users\computer_name\Desktop\monitor.txt

timeout -t 5 /nobreak

goto 1