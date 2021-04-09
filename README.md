# spring-non-web-datafix

Execute as gradle boot run
./gradlew bootRun

## Execute Datafix, execution order and control which Data fix to run

Set the respective Datafix property true in application.yml that is intended to be run. For Example

```shell
myDataFix1: false
myDataFix2: true
myDataFix3: true
myDataFix4: true

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