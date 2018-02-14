# pizza-order-sorting-cli
CLI application to read files from source folder, sort the content and write into destination folder

## Project Technology Description

  - Spring Boot (For simplicity)
  - Java 1.8
  - Apache Maven
  - Google Guava
  - Apache Common CLI
  - jUnit, Mockito (unit testing)
  
## Download, Build & Run this Application

* **Download:**
```
  $> git clone https://github.com/anz2050/pizza-order-sorting-cli.git
```

* **Build:**
```
  $> cd pizza-order-sorting-cli
  
  $> mvn clean package
```  

* **To check command line options:**
```
  $> java -jar target/pizza-order-sorting-cli-0.0.1-SNAPSHOT.jar -help
```

* **Run the application from command line**
```
  $> java -jar target/pizza-order-sorting-cli-0.0.1-SNAPSHOT.jar -srcDir C:\tmp\data-file -destDir C:\tmp\out
```

* **Run Unit Tests:**
```
   $> cd pizza-order-sorting-cli
  
  $> mvn test
```

## Assumptions

* Correct source and target folder provided
* Source folder have text file(s)
* File has two field each line with space as separator
* First line in file, has header
* User have permission to create folder/file on the drive
