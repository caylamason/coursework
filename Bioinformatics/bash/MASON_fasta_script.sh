#!/bin/bash


### Run this script from within the RAW_DATA folder which should be in your home directory (~/RAW_DATA)
### Inside this directory should be this script AND the fasta file bigdata.fna

### NOTE THAT SECTIONS (1) and (2) HAVE ALREADY BEEN COMPLETED. 

### (1) Ask the user for a fasta file for analysis. The following command, read, reads in user input from the command line.
###     For this exercise, use the file bigdata.fna which should be in your file ~/RAW_DATA

read -p "Enter fasta filename: " fn

### (2) Check to see if the file exists. If not, the function exits. I modified this if/then statement from the internet.

if [[ ! -f ./$fn ]]; 
then
   echo "The file does not exist. Exiting..."
   exit 1
fi

echo $fn


### (3) Split up the bigdata.fna into separate smaller .fna files of 50,000 sequences each.
###
###     I got the following line of code below from the internet. You will need to modify this awk command.
###     'awk' is a programming language for shell scripting for working with files.
###
###     Currently, the awk command splits a fasta file into smaller files of just 1000 sequence each. We want 50000.
###
###     The other problem is that it works on the wrong file. Change it so that it works with the user input file (see echo above).
###     Change these things and you should get 3 to 4 output files starting with 'myseq'

#
awk 'BEGIN {n_seq=0;} /^>/ {if(n_seq%50000==0){file=sprintf("myseq%d.fna",n_seq);} print >> file; n_seq++; next;} { print > file; }' < $fn


### (4) Use grep to check how many fasta sequences are in all of the .fna files and redirect this to a file in RAW_DATA called 'log.txt'
###     Hints on grep: -c counts and you can grep multiple files at once using the *. 
grep -c '>' *.fna > log.txt


### (5) Dump the output of log.txt to the terminal 
cat ./log.txt


### (6) The for loop below cycles though every file in the current directory and prints them.
###     The awk script below removes all the line breaks from fasta files.
###     TASK: Change the for loop so that it runs the awk command on all of the my*fna files and
###           outputs a new file with a .txt extension.
###     HINT: put the awk inside the do loop; also you can add extensions by $f'.txt' - this added a .txt to every $f file.

### More information on for loops can be found at: https://www.cyberciti.biz/faq/bash-loop-over-file/

for f in my*fna
do
 	echo $f
	awk 'BEGIN{RS=">"}{gsub("\n","",$0); print ">"$0}' $f > $f.txt
done




### (7) Use a for loop to count all the instances of the following string in all of the .fna.txt files:
###     'CACCCTCTCAGGTCGGCTACGCATCGTCGCC'
###     Also, like in (4) have the grep results for all files appended to log.txt (DON'T OVERWRITE IT)
###       then show the contents of log.txt in the terminal

for fn in my*fna.txt
do
	grep -c 'CACCCTCTCAGGTCGGCTACGCATCGTCGCC' $fn >> log.txt
	echo $fn >> log.txt
done

cat ./log.txt

### (8) Move all the .fna.txt files to the directory ~/P_DATA
mv ./*.fna.txt ~/P_DATA


### (9) Make a tar of the files in P_DATA - call it pdata.tar
### (10) Compress pdata.tar
tar -cvzf pdata.tar.gz ~/P_DATA




