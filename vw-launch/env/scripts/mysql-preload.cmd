@echo off

title mysql
mysql --user=vwserver --password=vwserver < mysql-preload.sql
@if errorlevel 1 goto errormark

@goto endmark
:errormark
	echo   
    pause
:endmark
@ENDLOCAL

