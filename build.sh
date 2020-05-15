mvn clean package
$GRAALVM_HOME/bin/native-image -H:ReflectionConfigurationFiles=reflection.json -jar target/aot-reflection-1.0-SNAPSHOT.jar