@echo on
set JAVA_HOME="D:\Program Files\Java\jdk-21"

REM ��������� �����
set SRC_DIR=./src/main/java
set TEST_DIR=./src/test/java
set BUILD_DIR=./build
set JAR_NAME=task111.jar
set DOC_DIR=./doc

REM �������� ������ ������
rd /s /q "%BUILD_DIR%"
md "%BUILD_DIR%"

REM Сборка проектов
"%JAVA_HOME%"\bin\javac -cp "./lib/*" -d "%BUILD_DIR%" %SRC_DIR%/ru/syspro/*.java %TEST_DIR%/ru/syspro/*.java

REM Генерация документаций Javadocs
"%JAVA_HOME%"\bin\javadoc -sourcepath "%SRC_DIR%" -subpackages ru -d "%DOC_DIR%"

REM Создание архива .jar
"%JAVA_HOME%"\bin\jar cvfe "%JAR_NAME%" ru.syspro.Main -C "%BUILD_DIR%" .

REM Запуск приложения непосредственно через созданный jar-файл
java -jar "%JAR_NAME%"