#protoc -I=./ --java_out=../ ./remoteLogger.idl
#cp -f *.idl ../../resources/webroot/assets/idl/

./flatc --java -o ../java/com/clue/model/fbs/ remoteLogger.fbs
