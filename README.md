# Reflection with Graalvm

Native GraalVM image involve some limitation. More precisely on reflection feature. It's possible to use It according to some configuration.

#### Environment:
```[baptiste@KEYWER aot-reflection]$ $GRAALVM_HOME/bin/java -version
openjdk version "11.0.6" 2020-01-14
OpenJDK Runtime Environment GraalVM CE 20.0.0 (build 11.0.6+9-jvmci-20.0-b02)
OpenJDK 64-Bit Server VM GraalVM CE 20.0.0 (build 11.0.6+9-jvmci-20.0-b02, mixed mode, sharing)
```

#### Dynamic Class Loading:
Class classe = Class.forName(USER_CLASS);
This kind of call is dynamically replace in native compilation if classes is declared in the image. 

#### Reflection:
You have to list class which need to be reflected in a json file. And precise the path during the native compilation:   
```$GRAALVM_HOME/bin/native-image -H:ReflectionConfigurationFiles=reflection.json -jar target/aot-reflection-1.0-SNAPSHOT.jar```    
Example:   
```[
     {
       "name" : "com.keywer.aot.User",
       "allDeclaredFields": true,
       "allDeclaredMethods" : true,
       "fields": [
         {"name" : "age", "allowWrite" : true }
       ],
       "methods": [
         { "name" : "sayHello", "parameterTypes" : [] },
         { "name" : "sayHelloForUser", "parameterTypes" : ["java.lang.String"] }
       ]
     }
   ]
```

#### Dynamic proxy 
Dynamic proxy are supported without any configuration.   


#### Documentations: 
https://github.com/oracle/graal/blob/master/substratevm/LIMITATIONS.md
