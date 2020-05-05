#!/bin/bash
./build/trivia 5 | diff - goldenMasterSeed5.txt 
