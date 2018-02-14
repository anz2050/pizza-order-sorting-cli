# pizza-order-sorting-cli
CLI application to read files from source folder, sort the content and write into destination folder

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

* **Run Application:**
```
  $> java -jar target/pizza-order-sorting-cli-0.0.1-SNAPSHOT.jar -srcDir <source folder> -destDir <destination folder>
```

* **Example:**
```
  $> java -jar target/pizza-order-sorting-cli-0.0.1-SNAPSHOT.jar -srcDir C:\tmp\data-file -destDir C:\tmp\out
```
