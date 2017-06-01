## Salesforce DataLoader - Data Provider

### Line commands
```
java -jar dataloader-provider.jar --spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver --spring.datasource.url=jdbc:oracle:thin:@192.168.x.x:1521:xxxxx --spring.datasource.username=xxxxxxxxxx --spring.datasource.password=xxxxxxxxxx --logging.file=daloader-provider.log --path.csv.data=C:\DataLoader\bin\data --path.csv.output=C:\DataLoader\bin\output --spring.mail.host=smtp.gmail.com --spring.mail.username=tecsupvirtual@tecsup.edu.pe --spring.mail.password=TECSUPVIRTUAL2015 --spring.mail.port=465 --spring.mail.properties.mail.smtp.ssl.enable=true --mail.to=jpalomino@tecsup.edu.pe
```

## Aditional parameter testing
```
-- test=[mail|lead-write|...]
```

### Documentations
* [Create JAR with IntelliJ IDEA](https://www.youtube.com/watch?v=3Xo6zSBgdgk)
* [Spring Batch Example](https://spring.io/guides/gs/batch-processing/)
* [Spring Batch older example](http://websystique.com/springbatch/spring-batch-read-from-mysql-database-and-write-to-a-csv-file/)

##### Jar to Exe
* http://launch4j.sourceforge.net/
* https://techblog.bozho.net/installing-java-application-as-a-windows-service/

