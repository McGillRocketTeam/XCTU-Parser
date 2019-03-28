#!/bin/bash

if [[ $1 == "-h" ]]; then
	echo "Usage is ./run_decoder <root_directory>"
	echo "It will run on all .log.txt files under the parent directory."
	exit
fi

DIR=$1
for file in $(find $DIR/ -maxdepth 2 -iname '*.log.txt')
do
	echo $file
	java XCTU_RSSI_Parser $file
done