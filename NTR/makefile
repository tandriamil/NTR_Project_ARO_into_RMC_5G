# Make file for the NTR project

build:
	javac -cp bin -d bin src/*.java

run:
	touch tmp.csv
	mv -b --suffix=`date +%j%H%I%S` *.csv backup/.
	java -cp bin Executable

clean: mrproper

mrproper:
	touch tmp.csv
	mv -b --suffix=`date +%j%H%I%S` *.csv backup/.
	rm bin/*.class
