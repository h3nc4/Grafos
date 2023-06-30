javac -g -d bin/ src/*.java
jar cfm release/grafos.jar docs/MANIFEST.MF -C bin/ . README.md LICENSE
rm -rf bin/
java -jar release/grafos.jar
