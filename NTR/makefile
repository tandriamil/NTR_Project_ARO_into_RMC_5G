# Make file for the NTR project

build:
	javac -cp bin -d bin src/*.java

run:
	cp -b --suffix=`date +%j%H%I%S` *.csv backup/.
	java -cp bin Executable

clean: mrproper

mrproper:
	rm bin/*.class