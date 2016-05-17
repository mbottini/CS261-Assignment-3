all:
	javac *.java
	jar -cmvf manifest.txt Assignment3.jar *.java *.class library/*.java\
        library/*.class
	rm *.class
	rm library/*.class
	
