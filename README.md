The main program determines whether two DFAs are equivalent or not i.e. whether they recognise the same language, using symmetric difference with Emptiness Testing (see [3.3.1](https://scholarworks.rit.edu/cgi/viewcontent.cgi?referer=https://www.google.com/&httpsredir=1&article=7944&context=theses)). It can also output the following information of DFA(s) , as the intermediate steps of equivalence checking:

* complementation 
* intersection
* symmetric difference
* non-emptyness

### Usage

The compiled jar files take two args from command line: ```file1.txt [file2.txt]```, where each file denotes the encoding of a DFA.

### Sample Input

For sample encoding, See MD7 & MD8.txt.