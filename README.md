# College-DataBase
Data Structures &amp; Algorithms Course, IIT Delhi
# Problem Statement:
https://drive.google.com/file/d/1s1fQbRilUpPwBQ79F7xQl6YZ3xL0zGNo/view?usp=sharing
# Report for my Implementation :
Pair class:
It takes two arguments of generic types K,T and stores them as key and value, Basically this data structure represents a tupple.
__________________________________________________________________________________________________________________________
DOUBLE HASHING:

(I) Implementation of Double Hashing:
Double Hashng class (Double_hash) has Node_DH nested class in it.

(1)Node_DH: It has two fields Pair<K,T> which is pair of Key and Value, and another field- boolean status :
status=true represents that the index is currently not empty whereas status=false implies that the index is recently emptied that is there was some node but it was deleted.
So basically we can categorize every index of array of hashtable(named hashmap) into three categories:(I) which has hashmap[index]==null that is no node/data has never been inserted into this index, (II)which has status == true that is it currently stores some data, (III) which has status==false that is there was some data/node was present but was later deleted.

(2) Insertion: index=(h1+i*h2)%T where i starts from 0 and in case of collision i increases by 1 and the insertion will terminate when a node with status = false is achieved or index is again equal to index0(h1 (that is in (h1+i*h2) and i=0))

(3) Delete, Update, Address, Get, Contains : All have similar implementation : (h1+i*h2)%T where i starts from 0 and if the key matches with the given key then found is set true else i increases by 1 and further checks for equality of keys.
Termination: there are three conditions for termination if either of them is true loop while terminate (I) if the element is found that is found==true (II) index is again equal to index0(h1 (that is in (h1+i*h2) and i=0)) (III) pfound==true, here pfound will be true if we find an index which is null that implies that no data was inserted for given h1, h2 after this index.
In deletion if key is found, to delete set its status=false that signifies that it is a recently deleted node.

(II) Time Complexities:
Theoretical:
Insertion: Worst Case:O(n) where n is the no. of elements in the table in case hash table is almost full or every key has 						 same value of hash codes.
		   Best Case:in one step that is the case if there is no collison at all hence constant time O(1)
		   Expected: O(1)
Deletion:Worst Case:O(n) where n is the no. of elements in the table in case hash table is almost full or every key has 						 same value of hash codes and in case key is not present.
		   Best Case:in one step if the key to be deleted is found at the very first calculated index hence constant time 			O(1)
		   Expected: O(1)
All others Update Address Get Contains have the similar time complexities.

Experimental:
In a test case with number of Objects to be inserted as 48 and Table size 73. Except a few cases all the insertions take only one step, same goes for all other opeartions as well. The max number of steps required in insertion was 5 which  still O(1).And as the table size increases more number of insertions happen in 1 step.


__________________________________________________________________________________________________________________________
SEPARATE CHAINING BINARY SEARCH TREE APPROACH:
Scbst<K extends Comparable<K>,T>
(I) Implementation:
(1) The hashtable array(named chain) is of type Sentinel, Sentinel is nested class within Scbst class, Basically its is nothing more than a pointer it has one only attribute of type Node, Basically Sentinel points to the root of the BST structure at every index.

(2) Node<K,T>(nested class): It has three fields Data of type Pair<K,T> and Node left,right that stores the left and right child resepectively.

(3) Insertion: Insertion is carried out w.r.t. to the first name of the Student but since it is generic it can't be done directly, So in order to carry out this operation K is made Comparable and it compareTo is overriden in the class key(described below).We start from root if compareTo gives less than equal to zero then we move to left else we move to right till we reach the exact place.
Termination: there are two conditions for termintion if either of it is true we terminate: (I)if while traversing the same key(fname+lname) is found then we terminate and return -1 hence printing "E" in the output (II)if the appropriate poisition for insertion is found and node has been inserted.

(4) Update, Address, Get, Contains: All have similar implementation. We start from root and by using compareTo continue traversing to the required position.
Termination:(I)if the key is found , (II) if the node at which key must be present is null : If either of two is true we terminate.

(5)Delete:Initial implementation of delete is same as update get address and contains till the key is found.
After that if(key is not found) then exit else if key is found then:
For the replacement of the node I have chosen the max node of the left subtree that from the node to be deleted go left once, after that traverses in right the right direction till the next right is not null. And then this node replaces the node to be deleted. This node is greater than the whole of the left subtree of the the node to be deleted as it is the largest of left subtree and this node is smaller than all of the right subtree. Hence it is a valid replacement.

(II) Time Complexities:
Theoretical:
Any insertion,deletion,or other query in BST: Worst Case is O(n) when all the nodes are in inputted in sorted order
											  Average Case is O(logn)
											  In general for height H of tree Time Complexity is is O(H).

Insert,Delete,Update,Get,Address,Contains: All have same time complexities:
										   Best Case : O(1) in case of no collison
										   Worst Case: O(n) where n is number of elements, in case if all the elements have same index and are inputted in sorted order.
										   Expected: In separate chaining Expected no. of nodes in a slot is lambda = n/N n is number of keys/elements and N is no. of slots.
										   And worst case for a given index is O(no. of nodes) hence expected complexity is O(lambda)=O(1) for n<N or n~O(N).

Experimental:
In a input with 48 objects and table size 1 the maximum no. of steps for insertion and queries are 10 which is O(logn) as log48 is 5.58~6
In a test case with same inputs but table size 55 the maximum no. of steps for insertion and queries are 3 which is O(1) here lambda<1 as n<N.
And as the table size increases more number of insertions happen in 1 step. 

__________________________________________________________________________________________________________________________
Class Key<K extends Comparable<K>>:
It is a class with two fields(first and last) of generic type K.
This class overrides toString() and compareTo()
toString(): returns conacatenation of first.toString and last.toString().
compareTo(): compares first.toString of two keys.
__________________________________________________________________________________________________________________________
Main Class(=assignment3.java)
Finally parameters K and T in MyHashTable_ are parameterized with Key<String> and Student respectively.
__________________________________________________________________________________________________________________________

NOTE:(1) I AM ASSUMING THAT ALL THE INSERTIONS HAVE UNIQUE KEY. HENCE IF A DUPLICATE KEY IS INSERTED NO "E" WILL BE PRINTED. IT IS DONE IN ACCORDANCE WITH THE ANSWER OF AN INSTRUCTOR ON PIAZZA.
(2) ALL THE TIME COMPLEXITY ANALYSIS ASSUMES THAT HASH CODE GENERATION IS O(1) OTHERWISE TIME COMPLEXITIES WILL CHANGE ACCORDINGLY.
(3) SINCE ON THE ASSIGNMENT PAGE FOR DOUBLE HASHING IT IS GIVEN THAT T=1.5N~2N I AM ASSUMING THAT THERE WILL ALWAYS BE SPACE FOR A NEW ELEMENT TO BE INSERTED. THAT IS TABLE NEVER FILLED COMPLETELY.
