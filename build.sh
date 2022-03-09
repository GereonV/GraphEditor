javac -d build/classes src/*/*.java src/*/*/*.java
jar cfve build/GraphEditor.jar grapheditor.GraphFrame -C build/classes .
java -jar build/GraphEditor.jar
