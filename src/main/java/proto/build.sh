protoc -I=./ --java_out=../ ./remoteLogger.proto
cp -f *.proto ../../resources/webroot/assets/idl/
