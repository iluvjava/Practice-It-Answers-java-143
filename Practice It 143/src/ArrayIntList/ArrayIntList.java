package ArrayIntList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Class ArrayIntList can be used to store a list of integers.
public class ArrayIntList implements Iterable<Integer> {
 private int[] elementData;  // list of integers
 private int size = 0;       // current number of elements in the list
 
 public static final int DEFAULT_CAPACITY = 10;
 
 // post: constructs an empty list of default capacity
 public ArrayIntList() {
     this(DEFAULT_CAPACITY);
 }
 
 // pre : capacity >= 0
 // post: constructs an empty list with the given capacity
 private ArrayIntList(int capacity) {
     if (capacity < 0) {
         throw new IllegalArgumentException("Capacity must be at least 0: " + capacity);
     }
     elementData = new int[capacity];
 }
 
 // for creating test cases more easily
 public ArrayIntList(int... elements) {
     this(Math.max(DEFAULT_CAPACITY, elements.length * 2));
     for (int n : elements) {
         add(n);
     }
 }
 
 // for creating test cases more easily (a dupe of the above constructor)
 public static ArrayIntList withValues(int... elements) {
     ArrayIntList list = new ArrayIntList(Math.max(DEFAULT_CAPACITY, elements.length * 2));
     for (int n : elements) {
         list.add(n);
     }
     return list;
 }
 
 // for creating test cases more easily
 public ArrayIntList(int element, boolean notCapacity) {
     this();
     add(element);
 }
 
 // for creating test cases more easily
 public ArrayIntList(Collection<Integer> elements) {
     this(Math.max(DEFAULT_CAPACITY, elements.size() * 2));
     for (int n : elements) {
         add(n);
     }
 }
 
 // copy constructor; for creating test cases more easily
 public ArrayIntList(ArrayIntList list) {
     this(Math.max(DEFAULT_CAPACITY, list.size() * 2));
     addAll(list);
 }
 
 // pre : size() < capacity (elementData.length)
 // post: appends the given value to the end of the list
 public void add(int value) {
     add(size, value);
 }
 
 // pre: size() < capacity (elementData.length) && 0 <= index <= size()
 // post: inserts the given value at the given index, shifting subsequent
 //     values right
 public void add(int index, int value) {
     checkIndex(index, 0, size);
     ensureCapacity(size + 1);
     
     for (int i = size; i > index; i--) {
         elementData[i] = elementData[i - 1];
     }
     elementData[index] = value;
     size++;
 }
 
 // post: appends all values in the given list to the end of this list
 public void addAll(ArrayIntList other) {
     for (int i = 0; i < other.size; i++) {
         add(other.elementData[i]);
     }
 }
 
 // post: list is empty
 public void clear() {
     size = 0;
 } 
 
 // post: returns true if the given value is contained in the list, false otherwise
 public boolean contains(int value) {
     return indexOf(value) != -1;
 } 
 
 // post: ensures that the underlying array has the given capacity; if not,
 // the size is doubled (or more if given capacity is even larger)
 public void ensureCapacity(int capacity) {
     if (capacity > elementData.length) {
         int newCapacity = elementData.length * 2 + 1;
         if (capacity > newCapacity) {
             newCapacity = capacity;
         }
         int[] newList = new int[newCapacity];
         for (int i = 0; i < size; i++) {
             newList[i] = elementData[i];
         }
         elementData = newList;
     }
 }
 
 // returns true if o is an ArrayIntList with the same size and elements as this one
 public boolean equals(Object o) {
     if (!(o instanceof ArrayIntList)) {
         return false;
     }

     ArrayIntList other = (ArrayIntList) o;
     if (this.size != other.size) {
         return false;
     }
     
     for (int i = 0; i < size; i++) {
         if (elementData[i] != other.elementData[i]) {
             return false;
         }
     }
     
     return true;
 }
 
 // pre : 0 <= index < size()
 // post: returns the integer at the given index in the list
 public int get(int index) {
     checkIndex(index);
     return elementData[index];
 }
 
 // post: returns capacity of this list's underlying array
 public int getCapacity() {
     return elementData.length;
 }
 
 // post : returns the position of the first occurence of the given
 //      value (-1 if not found)
 public int indexOf(int value) {
     for (int i = 0; i < size; i++) {
         if (elementData[i] == value) {
             return i;
         }
     }
     return -1;
 }
 
 // post: returns true if list is empty, false otherwise
 public boolean isEmpty() {
     return size == 0;
 }
 
 // post: returns an iterator for this list
 public Iterator<Integer> iterator() {
     return new ArrayIntListIterator(this);
 } 
 
 // pre : 0 <= index < size()
 // post: removes value at the given index, shifting subsequent values left
 public void remove(int index) {
     checkIndex(index);
     for (int i = index; i < size - 1; i++) {
         elementData[i] = elementData[i + 1];
     }
     size--;
 }
 
 // post: removes all occurrences of the values in the given list from this list
 public void removeAll(ArrayIntList other) {
     int newSize = 0;
     for (int i = 0; i < size; i++) {
         if (!other.contains(elementData[i])) {
             elementData[newSize] = elementData[i];
             newSize++;
         }
         size = newSize;
     }
 }
 
 // pre : 0 <= index < size()
 // post: replaces the integer at the given index with the given value
 public void set(int index, int value) {
     checkIndex(index);
     elementData[index] = value;
 } 
 
 // post: returns the current number of elements in the list
 public int size() {
     return size;
 }
 
 // post: returns an array version of the contents of this list
 public int[] toArray() {
     return Arrays.copyOf(elementData, size);
 }
 
 // post: creates a comma-separated, bracketed version of the list
 public String toString() {
     String result = "[";
     for (int i = 0; i < size; i++) {
         if (i > 0) {
             result += ", ";
         }
         if (i < elementData.length) {
             result += elementData[i];
         } else {
             // student's code is bogus; OOB
             result += "OOB!";
         }
     }
     result += "]";
     return result;
 }
 
 // helpers to make sure indexes do not fall out of bounds
 private void checkIndex(int index) {
     checkIndex(index, 0, size - 1);
 }
 
 private void checkIndex(int index, int min, int max) {
     if (!(min <= index && index <= max)) {
         throw new ArrayIndexOutOfBoundsException("Illegal index " + index + 
                 "; must be between " + min + " and " + max + "\n"
                 + "list : " + toString() + " (size=" + size + ")\n"
                 + "array: " + Arrays.toString(elementData) + " (capacity=" + elementData.length + ")");
     }
 }
 
 
 // Stuart Reges
 // 4/4/05
 //
 // The ArrayIntListIterator class provides a set of utilities for iterating
 // over an ArrayIntList and possibly removing values from the list.
 
 private static class ArrayIntListIterator implements Iterator<Integer> {
     private ArrayIntList list;    // list to iterate over
     private int position;          // current position within the list
     private boolean removeOK;      // whether it's okay to remove now
     
     // pre : list != null
     // post: constructs an iterator for the given list
     public ArrayIntListIterator(ArrayIntList list) {
         this.list = list;
         position = 0;
         removeOK = false;
     }
     
     // post: returns true if there are more elements left, false otherwise
     public boolean hasNext() {
         return position < list.size();
     }
     
     // pre : hasNext()
     // post: returns the next element in the iteration
     public Integer next() {
         if (!hasNext())
             throw new NoSuchElementException();
         int result = list.get(position);
         position++;
         removeOK = true;
         return result;
     }
     
     // pre : next() has been called without a call on remove (i.e., at most one
     //     call per call on next)
     // post: removes the last element returned by the iterator
     public void remove() {
         if (!removeOK)
             throw new IllegalStateException();
         list.remove(position - 1);
         position--;
         removeOK = false;
     }
 }    

//YOUR CODE GOES HERE
 
 
 /**
  * Removes a certain element in this list. 
  * @param arg
  */
 public void removeAll(int arg)
 {
	 for(Iterator<Integer> itr = this.iterator();itr.hasNext(); )
	 if(itr.next()==arg)itr.remove();
 }
 
 
 /**
  * for each possible pairs of number in the list, if the bigger one appears in front the 
  * smaller one, we are going to print them in a formatted way. 
  * <br>
  * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/printInversions">
  * Original Question</a>
  * @param arg
  */
 public void printInversions()
 {
	 for(
			 int first =0, second = 1;
			 first<size&&second<size;
			 first+= second>=size-1?1:0,
			 second=second<size -1 ?second+1:first+1
		)
		 if(this.elementData[first]>this.elementData[second])
		System.out.println("("+this.elementData[first]+", "+this.elementData[second]+")");
 }
 
 /**
  * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/maxCount">
  * Original question</a>
  * @return
  * The maximum numbers of repeated elements in a sorted list. 
  */
 public int maxCount()
 {
	 if(this.size<=1)return this.size;
	 int result = 1; 
	 for(int first = 0, second = 1,count =1; second<this.size; first++, second++)
	 {
		 if(this.elementData[second]-this.elementData[first]==0)
		{count++;result = Math.max(count, result);continue;}
			 count =1;
	 }
	 return result; 
 }
 
 
 /**
  * In fact, the name of the method already suggests its functionality. <br>
  * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/mirror">Original question</a>
  */
 public void mirror()
 {
	 
	 int previoussize =this.size;
	 this.ensureCapacity(this.size*2);
	 this.size*=2;
	 for(int left = previoussize-1, right = previoussize;left>=0;left--, right++)
	 {
		 this.elementData[right]= this.elementData[left];
	 }
	 
 }
 
 
 /**
  * This method does so fancy stuff...<br>
  * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/stretch"> original question</a>
  * @param arg
  */
 public void stretch(int arg)
 {
	 if(arg<=0)this.size=0;
	 if(arg==1)return; 
	 this.ensureCapacity(this.size*arg);
	 int previoussize = this.size;
	 arg-=2;
	 for(int i = previoussize-1, repetation = 0; 
			 i>=0;
			 i=repetation == arg?i-1:i,
			 repetation = repetation<arg?repetation+1:0
		  )
	 {
		 this.add(i+1,this.get(i));
	 }
	 
 }
 
 
 /**
  * <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/longestSortedSequence">
  * Original Question</a>
  * @return
  * The length of the longest sorted sub sequence in this list. 
  */
 public int longestSortedSequence()
 {
	 if(this.size<=1)return this.size; 
	 int result =1; 
	 for(int i =0, j = 1, temp = 1; j<this.size;i++,j++)
	 {
		 if(this.elementData[j]-this.elementData[i]>=0)
		 {
			 temp++;
			 result = Math.max(temp, result);
			 continue;
		 }
		 temp=1;
	 }
	 return result; 
 }
 
 
 
 /**
  * This method takes the integral of the array<br>
  * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/runningTotal">Original question</a>
  * @return
  */
 public ArrayIntList runningTotal()
 {
	 
	 int[] lst= Arrays.copyOf(this.elementData, this.size);
	 
	 for(int i = 1; i<this.size; i++)
	 {
		 lst[i]= lst[i-1]+lst[i];
	 }
	 ArrayIntList arry = new ArrayIntList();
	 arry.elementData = lst;
	 arry.size = lst.length;
	 return arry;
 }
 
 
 
 /**
  * <a href = "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/isPairwiseSorted">Original question</a>
  * @return
  */
 public boolean isPairwiseSorted ()
 {
	 for(int i = 0 , j = 1; i<this.size&&j<this.size; i+=2, j+=2)
	 {
		 if(this.elementData[j]<this.elementData[i])return false;
	 }
	 return true; 
 }
 
 
 /**
  * Removes the first n elements in the list. We are going to swap element from
  * <br>
  * <a href= "https://practiceit.cs.washington.edu/problem/view/cs2/sections/arrayintlist/removeFront">Here</a>
  * @param
  * assume that the parameter passed is between zero and the size of the array inclusive. 
  */
 public void removeFront(int n)
 {
	 for(int left = 0, right = n; left<n; left++, right++)
	 {
		 this.elementData[left] = this.elementData[right];
	 }
	 this.size -= n;
 }
 
 
 

}

