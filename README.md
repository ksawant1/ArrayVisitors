# CSX42: Assignment 4
**Name:Krupa Sawant
**B-Number:B00 814013
** Use of 1 Slack Day

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.

Note: build.xml is present in (./arrayvisitors/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile arrayvisitors/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile arrayvisitors/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile src/build.xml run -Dinput="<path/to/inputfile>" -Dinput2="<path/to/inputfile2>" -Dcommonintsout="<path/to/commonintsoutfile>" -Dmissingintsout="<path/to/missingintsoutfile>" -Ddebug= debuglevel

```
Note: Arguments accept the absolute path of the files.


## Description:
## Assumptions
    1. Each line of input contains a two digit integer per line.
    2. The input line doesn't contain spaces.
    3. The input is two-digit positive integer from 00 to 99.(throws exception is special symbol or negative value occurs)
    4. For the debug part the debug levels are levels 
        debugvalue=0 for Driver class,             debugvalue=1 for Constructor,
        debugvalue=2 for Fileprocessor class  ,    debugvalue=3 for CommonintsVisitor class,
        debugvalue=4 for MissingIntsVisitor class , debugvalue=5 for PopulateMyArray class
        debugvalue=6 for Myarray class            ,   debugvalue=7 for Myarraylist class
        debugvalue=8 for Results class
     5. example of command line argument : ant -buildfile src/build.xml run -Dinput="input.txt" -Dinput2="input2.txt" -Dcommonintsout="commonintsout.txt" -Dmissingintsout="missingintsout.txt" -Ddebug=7
    
## Program FLow:
 1. Driver code accepts command line argument, creates instances of visitors, ADT, results and fileprocessor.
 2. The Element MyArray accepts PopulateMyArrayVisitor and the visitor populates the two instances of myarray.
 3. The Element MyArrayList accepts CommonIntsVisitor which finds common numbers between two instances of myarray and prints to Result. 
 4. The Element MyArray accepts MissingIntsVisitor which finds missing numbers compared with (0,99) to two instances of myarray and prints to Result. 
 
 ## Use of Sets for DataStructure
 1.Set stores only unique elements which makes it easier to write in File as it considers the integer only once.
 ## Use of Arrays for DataStructure
 1. Arrays are the simplest structure to store integers and easy operations like adding and deleting become easy
 2. The only disadvantage is the static defining of size of array.
 ## Use of ArrayList for DataStructure
 1.Major benefit of using arraylist is it is dynamic in size. 
 
 
  References: https://www.freecodecamp.org/news/binary-search-trees-bst-explained-with-examples/
  https://www.moreofless.co.uk/binary-search-tree-bst-java/
  
## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [7/24/2020]


