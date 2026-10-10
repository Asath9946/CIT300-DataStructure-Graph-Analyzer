#!/usr/bin/env bash
# Compile and run the Data Structure & Graph Performance Analyzer
# Usage:  chmod +x run.sh  &&  ./run.sh

set -e

echo "============================================================"
echo " Compiling Data Structure & Graph Performance Analyzer..."
echo "============================================================"

mkdir -p build
javac -d build $(find src -name "*.java")

echo "Compilation successful."
echo

java -cp build com.sltc.dsa.Main
