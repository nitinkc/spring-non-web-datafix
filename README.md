# spring-non-web-datafix

### Execute as gradle boot run
`./gradlew bootRun`

### Execute Jar
```shell
gradle clean build --x test --refresh-dependencies

# Pass spring arguments with -D 
java -jar -DmyDataFix2=true -DmyDataFix3=true -DmyDataFix3=true build/libs/non-web-spring-datafix-0.0.1-SNAPSHOT.jar 

# Pass spring arguments with -D and commandline arguments after jar file. same arguments are shared by each class that 
# extents CommandLineRunner & ApplicationRunner
java -jar -DmyDataFix2=true -DmyDataFix3=true -DmyDataFix3=true build/libs/non-web-spring-datafix-0.0.1-SNAPSHOT.jar args1 args2 args3 a b c d     
```
## Execute Datafix, execution order and control which Data fix to run

Set the respective Datafix property true in application.yml that is intended to be run. For Example

```shell
myDataFix1: false
myDataFix2: false 
myDataFix3: false 
myDataFix4: false 

````

On the respective Runner Class, use the following two Annotations

```
@ConditionalOnExpression("${myDataFix2:true}")
@Order(value = 1)
```

`@ConditionalOnExpression` is set to false by default to prevent any accidental runs.
When the property value us set to true in the app yml file, then only the application runner will execute

`@Order` takes care of the order of execution. Lower number indicated higher priority.

If the ConditionalOnExpression receives a false value, then the order does not participate and the next
sequence od order comes into play.