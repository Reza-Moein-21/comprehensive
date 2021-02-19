#!/bin/bash
echo "Install shabnam.jar into m2 directory";
echo "shabnam.jar path: $1";
mvn install:install-file -Dfile=$1 -DgroupId=ir.comprehensive -DartifactId=jasper-shabnam -Dversion=1.0 -Dpackaging=jar