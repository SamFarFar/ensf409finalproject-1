# Furniture Inventory Checker
## project created by
#### -> Sam Farzamfar 30090257
#### -> Matteo Morrone 30085892
#### -> Sandip Mishra 30087956

# Video
-> The link to the video where the project is demonstrated
```bash
https://www.youtube.com/watch?v=r1truejvybk
```

# Installation

In order to successfully run this program, simply ensure that you have a copy 
of the inventory database (see the URL string below), and a username and password 
ready for MySQL.  Also ensure that you have the proper MySQL connector, and compile it using the commands for your operating system listed below. PLEASE NOTE that your SQL connector may have a different name and/or may not be stored in a "lib" folder depending on the structure of your personal machine.  From this point simply follow the prompts for a username, password, and requested items, and you will see a console output, as well as the creation of an order form text file, when applicable.

#### Windows:
```bash
javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/*.java
java -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Input
```
#### Mac/Linux:
```bash
javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/*.java
java -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Input
```

# JUnit Testing

-> In console type the following:

#### Mac/Linux:
```bash
javac edu/ucalgary/ensf409/*.java 
javac -cp .:lib/mysql-connector-java-8.0.23.jar:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar JUnit/FPTest.java
java -cp .:lib/mysql-connector-java-8.0.23.jar:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore JUnit.FPTest
```
#### Windows:
```bash
javac edu/ucalgary/ensf409/*.java 
javac -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar JUnit/FPTest.java
java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore JUnit.FPTest
```