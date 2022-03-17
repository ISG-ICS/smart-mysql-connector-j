# A Smart PGJDBC

This repository hosts a **smart** version `mysql-connector-j` driver developed on top of the open-source [mysql-connector-j project](https://github.com/mysql/mysql-connector-j).

### Why is it `smart`? 
 - It intercepts `SELECT` SQL queries and sends them to a `VisBooster` server for query rewriting.
 - By applying customizable rewriting rules, the rewritten SQL queries returned by `VisBooster` are much more efficient than the original queries.

The [`VisBooster` project](https://github.com/ISG-ICS/VisBooster) aims to rewrite SQL queries to an equivelant but more efficient form.

For more information of original `mysql-connector-j` project, refer to the original [README file](README_mysql-connector-j.md).

## Deveop and Build
To build the project, follow the instructions from [4.3 Installing from Source](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing-source.html).

A shorter version is provided here:

1. Install Apache Ant version 1.10.6 or newer ([http://ant.apache.org/](http://ant.apache.org/)).
2. Install JDK 1.8.x ([https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)).
3. (Already downloaded and put then into a `lib` folder under the root of this repository.) See `The following third-party libraries` bullet on the above page.
4. Create a file `build.properties` under the root of this repository and fill in the absolute path of the installed JDK.
   ```bash
   com.mysql.cj.build.jdk=/Library/Java/JavaVirtualMachines/jdk1.8.0_321.jdk/Contents/Home
   ```
5. Build.
    ```bash
    $> ant dist
    ```
After build, the output jar file is at `build/mysql-connector-java-8.X.XX-SNAPSHOT/mysql-connector-java-8.X.XX-SNAPSHOT.jar`.


For more development information, refer to the original [CONTRIBUTING.md](CONTRIBUTING.md).