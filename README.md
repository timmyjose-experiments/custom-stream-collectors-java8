[![Build Status](https://travis-ci.org/timmyjose/custom-stream-collectors-java8.svg?branch=master)](https://travis-ci.org/timmyjose/custom-stream-collectors-java8)

This is a small collection of examples of collecting streams of custom types or built-in types into 
a custom object type for Java streams (Java 8 and above). 

This was prompted by the fact that there were no
examples to be found in the official docs on how to collect a stream into a custom object type. My project, 
[Functional Nim](https://github.com/timmyjose/functional-nim) required precisely such functionality, and I 
determined to create some custom collectors and demos of collecting into arbitrary types so that it might be useful 
for others.

Please check the `com.z0ltan.custom.collectors.collectors` package for custom implementations of the `Collector` interface
which is required in order to be able to collect intermediate results into a final result. For more details on exactly how 
the custom collectors work, please consult my blog: [Talking with a Lisp](https://z0ltan.wordpress.com/2017/07/11/creating-custom-java-8-stream-collectors/).

Perusing the tests in the `src/test/java` folder is also recommended to get a better feel of how to implement and use your
own custom collector(s).


## Usage

To run the sample tests:

```
	$ mvn clean && mvn compiler
	$ mvn test
```

To compile and run the sample examples:

```
	$ mvn clean && mvn compile
	$ mvn exec:java -Dexec.mainClass=com.z0ltan.custom.collectors.Main
```

Alternatively,

```
	$ mvn clean && mvn package
	$ java -jar java -jar target/custom-collectors-1.0-SNAPSHOT.jar
```


## Build

```
	$ mvn clean && mvn compiler
```

or

```
	$ mvn clean && mvn package 
```


## Demo

Sample run:

```

Macushla:custom-collectors z0ltan$ java -jar target/custom-collectors-1.0-SNAPSHOT.jar
[wrapperDemo] original wrapper = Wrapper { values = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] }

[wrapperDemo] modified wrapper = Wrapper { values = [2, 2, 6, 4, 10, 6, 14, 8, 18, 10] }



[splitterDemo] original wrappers = [Wrapper { values = [1, 2, 3, 4, 5] } , Wrapper { values = [6, 7, 8, 9, 10] } , Wrapper { values = [11, 12, 13, 14, 15] } ]

[splitterDemo] splitValue = Splitter { firstHalf = [1, 2, 3, 4, 5, 6, 7], secondHalf = [8, 9, 10, 11, 12, 13, 14, 15]}



[fooToBarDemo] original foos = [Foo { id = 1, name = Hello}, Foo { id = 6, name = Again}, Foo { id = 2, name = World}, Foo { id = 3, name = We}, Foo { id = 4, name = We}, Foo { id = 5, name = Meet}]

[fooToBarDemo] bar = Bar { ids = [1, 2, 3, 4, 5, 6], names = [Meet, Hello, Again, World, We]}



[fooFilterBarDemo] foos = [Foo { id = 1, name = Hello}, Foo { id = 5, name = Again}, Foo { id = 2, name = World}, Foo { id = 3, name = We}, Foo { id = 4, name = Meet}]

[fooFilterBarDemo] bar = Bar { ids = [1, 3, 5], names = [Hello, Again, We]}



[fooFilterMapBarDemo] foos = [Foo { id = 1, name = Hello}, Foo { id = 5, name = Again}, Foo { id = 2, name = World}, Foo { id = 3, name = We}, Foo { id = 4, name = Meet}]

[fooFilterMapBarDemo] bar = Bar { ids = [2, 4], names = [MEET, WORLD]}

```
