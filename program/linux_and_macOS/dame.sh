#!/bin/sh

# Retrieve dame directory
PROGRAM=`readlink "$0"`
if [ "$PROGRAM" = "" ]; then
  PROGRAM=$0
fi
PROGRAM_DIR=`dirname "$PROGRAM"`

# Run Dame
exec java -jar "$PROGRAM_DIR"/dame.jar
