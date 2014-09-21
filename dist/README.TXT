========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "KeywordIndex.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.

100282N
Kumarasinghe C.U.
CS2022 Programming Project 2011

How to run the program:
 
1. Open the file KeywordIndex.txt in 100282N_CS2022SC folder

2. Edit inputFile location in line 41 of the code to the Input.txt file location in your
   computer.

3. Edit outputFile location in line 45 of the code to the Output.txt file location in your
   computer.

   warning!! if file locations are incorrect program will terminate unexpectedly

4. Compile the program

  a. Open the command prompt

  b. Go to the file location where KeywordIndex.text by using cd function

  c. Compile by typing javac KeywordIndex.java
  
  d. If no errors go to step 5

  e. If error cheak keywordIndex.txt for syntax errors.

5. Run the Program by typing: java KeywordIndex

6. If error go to step1 and continue otherwise go to step 7

7. cheak the Output.txt file to see the output

Thank You

