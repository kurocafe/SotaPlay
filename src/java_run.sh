classpath=".:/home/vstone/lib/*\
:/home/vstone/vstonemagic/*\
:/usr/local/share/OpenCV/java/*\
:/home/pi/SotaPlay/lib/*\
"

OPTION="-Dfile.encoding=UTF8 -Djava.library.path=/usr/local/share/OpenCV/java/"

echo "java -classpath $classpath $OPTION $1"
java -classpath "$classpath" $OPTION $1