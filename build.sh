javac -g -d bin/ src/*.java
jar cfm release/grafos.jar docs/MANIFEST.MF -C bin/ .
rm -rf bin/
java -jar release/grafos.jar
