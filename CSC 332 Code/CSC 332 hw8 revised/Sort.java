import javax.lang.model.type.ArrayType;

/**
 * A class that contains several sorting routines, implemented as static
 * methods. Arrays are rearranged with smallest item first, using compareTo.
 * 
 * @author Mark Allen Weiss
 */
public final class Sort {
	/**
	 * Simple insertion sort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(
			AnyType[] a) {
		int j;

		for (int p = 1; p < a.length; p++) {
			AnyType tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	/**
	 * Shellsort, using Shell's (poor) increments.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void shellsort(
			AnyType[] a) {
		int j;

		for (int gap = a.length / 2; gap > 0; gap /= 2)
			for (int i = gap; i < a.length; i++) {
				AnyType tmp = a[i];
				for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
					a[j] = a[j - gap];
				a[j] = tmp;
			}
	}

	/**
	 * Standard heapsort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void heapsort(
			AnyType[] a) {
		for (int i = a.length / 2; i >= 0; i--)
			/* buildHeap */
			percDown(a, i, a.length);
		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i); /* deleteMax */
			percDown(a, 0, i);
		}
	}

	/**
	 * Internal method for heapsort.
	 * 
	 * @param i
	 *            the index of an item in the heap.
	 * @return the index of the left child.
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Internal method for heapsort that is used in deleteMax and buildHeap.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @int i the position from which to percolate down.
	 * @int n the logical size of the binary heap.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void percDown(
			AnyType[] a, int i, int n) {
		int child;
		AnyType tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
				child++;
			if (tmp.compareTo(a[child]) < 0)
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}

	/**
	 * Mergesort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void mergeSort(
			AnyType[] a) {
		AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];

		mergeSort(a, tmpArray, 0, a.length - 1);
	}

	/**
	 * Internal method that makes recursive calls.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void mergeSort(
			AnyType[] a, AnyType[] tmpArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param leftPos
	 *            the left-most index of the subarray.
	 * @param rightPos
	 *            the index of the start of the second half.
	 * @param rightEnd
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void merge(
			AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos,
			int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos].compareTo(a[rightPos]) <= 0)
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos++];

		while (leftPos <= leftEnd)
			// Copy rest of first half
			tmpArray[tmpPos++] = a[leftPos++];

		while (rightPos <= rightEnd)
			// Copy rest of right half
			tmpArray[tmpPos++] = a[rightPos++];

		// Copy tmpArray back
		for (int i = 0; i < numElements; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}

	/**
	 * Quicksort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void quicksort(
			AnyType[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static final int CUTOFF = 3;

	/**
	 * Method to swap to elements in an array.
	 * 
	 * @param a
	 *            an array of objects.
	 * @param index1
	 *            the index of the first object.
	 * @param index2
	 *            the index of the second object.
	 */
	public static <AnyType> void swapReferences(AnyType[] a, int index1,
			int index2) {
		AnyType tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	/**
	 * Return median of left, center, and right. Order these and hide the pivot.
	 */
	private static <AnyType extends Comparable<? super AnyType>> AnyType median3(
			AnyType[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0)
			swapReferences(a, left, center);
		if (a[right].compareTo(a[left]) < 0)
			swapReferences(a, left, right);
		if (a[right].compareTo(a[center]) < 0)
			swapReferences(a, center, right);

		// Place pivot at position right - 1
		swapReferences(a, center, right - 1);
		return a[right - 1];
	}

	/**
	 * Internal quicksort method that makes recursive calls. Uses
	 * median-of-three partitioning and a cutoff of 10.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void quicksort(
			AnyType[] a, int left, int right) {
		if (left + CUTOFF <= right) {
			AnyType pivot = median3(a, left, right);

			// Begin partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}

			swapReferences(a, i, right - 1); // Restore pivot

			quicksort(a, left, i - 1); // Sort small elements
			quicksort(a, i + 1, right); // Sort large elements
		} else
			// Do an insertion sort on the subarray
			insertionSort(a, left, right);
	}

	/**
	 * Internal insertion sort routine for subarrays that is used by quicksort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void insertionSort(
			AnyType[] a, int left, int right) {
		for (int p = left + 1; p <= right; p++) {
			AnyType tmp = a[p];
			int j;

			for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	/**
	 * Quick selection algorithm. Places the kth smallest item in a[k-1].
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param k
	 *            the desired rank (1 is minimum) in the entire array.
	 */
	public static <AnyType extends Comparable<? super AnyType>> void quickSelect(
			AnyType[] a, int k) {
		quickSelect(a, 0, a.length - 1, k);
	}

	/**
	 * Internal selection method that makes recursive calls. Uses
	 * median-of-three partitioning and a cutoff of 10. Places the kth smallest
	 * item in a[k-1].
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 * @param k
	 *            the desired index (1 is minimum) in the entire array.
	 */
	private static <AnyType extends Comparable<? super AnyType>> void quickSelect(
			AnyType[] a, int left, int right, int k) {
		if (left + CUTOFF <= right) {
			AnyType pivot = median3(a, left, right);

			// Begin partitioning
			int i = left, j = right - 1;
			for (;;) {
				while (a[++i].compareTo(pivot) < 0) {
				}
				while (a[--j].compareTo(pivot) > 0) {
				}
				if (i < j)
					swapReferences(a, i, j);
				else
					break;
			}

			swapReferences(a, i, right - 1); // Restore pivot

			if (k <= i)
				quickSelect(a, left, i - 1, k);
			else if (k > i + 1)
				quickSelect(a, i + 1, right, k);
		} else
			// Do an insertion sort on the subarray
			insertionSort(a, left, right);
	}

	private static final int NUM_ITEMS = 1000;
	private static int theSeed = 1;

	private static void checkSort(Integer[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] < a[i - 1])
				System.out.println("Error at " + i);
		// System.out.println("Finished checksort");
	}

	/**
	 * Make a string of length 5 through 100 using lowercase letters.
	 * 
	 * @return A random string.
	 */
	public static String randomString() {
		int howLong = (int) (96 * Math.random()) + 5;
		String s = "";
		for (int x = 0; x < howLong; x++) {
			// random character between a and z
			char ch = (char) ((26 * Math.random()) + 'a');
			s += ch;
		}
		return s;
	}

	/**
	 * Creates an array of Strings and records the amount of time it takes to
	 * sort using different algorithms
	 * @author Alex Aziz
	 */

	public static void stringTest() {
		int start = 100;
		int end = 1000000;
		int numberOfTests = 10;
		for (int k = start; k <= end; k = k * 10) {
			System.out.println(k);
			System.out.println("Exp #" + (Math.log10(k) - 1));
			int numberOfElements = k;
			int increment = numberOfElements / numberOfTests;
			java.util.Random gen = new java.util.Random();
			for (int i = increment; i <= numberOfElements; i += increment) {
				String[] data = new String[i];
				for (int j = 0; j < i; j++) {
					data[j] = randomString();
				}// End For
				String[] sortableArray = new String[data.length];// Copies original data into a new array so each method sorts a copy not the original
				for(int l=0; i<data.length; l++){
					sortableArray[l]=data[l]; 
				}
				System.out.print(sortableArray.length+" ");
				
				long startTime = System.nanoTime();
				sortableArray = data;
				Sort.heapsort(sortableArray);
				long endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				if (k < 10000) {
					startTime = System.nanoTime();
					Sort.insertionSort(sortableArray);
					endTime = System.nanoTime();
					System.out.print((endTime - startTime) + " ");
				}// End If

				startTime = System.nanoTime();
				sortableArray = data;
				Sort.shellsort(sortableArray);
				endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				startTime = System.nanoTime();
				sortableArray = data;
				Sort.mergeSort(sortableArray);
				endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				startTime = System.nanoTime();
				sortableArray = data;
				Sort.quicksort(sortableArray);
				endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				System.out.println("");
			}// End For
			System.out.println();
		}// End For
	}// End stringTest

	/**
	 * Creates an array of Integers and records the amount of time it takes to
	 * sort using different algorithms
	 * @author Alex Aziz
	 */
	public static void integerTest() {
		int start = 100;
		int end = 1000000;
		int numberOfTests = 10;
		for (int k = start; k <= end; k = k * 10) {
			System.out.println(k);
			System.out.println("Exp #" + (Math.log10(k) - 1));
			int numberOfElements = k;
			int increment = numberOfElements / numberOfTests;
			java.util.Random gen = new java.util.Random();
			for (int i = increment; i <= numberOfElements; i += increment) {
				Integer[] data = new Integer[i];
				for (int j = 0; j < i; j++) {
					data[j] = gen.nextInt(2 * numberOfElements);
				}// End For
				Integer[] sortableArray = new Integer[data.length];// Copies original data into a new array so each method sorts a copy not the original
				for(int l=0; i<data.length; l++){
					sortableArray[l]=data[l]; 
				}// End for
				System.out.print(sortableArray.length+" ");
				
				long startTime = System.nanoTime();
				sortableArray = data;
				Sort.heapsort(sortableArray);
				checkSort(sortableArray);
				long endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				if (k < 10000) {
					startTime = System.nanoTime();
					Sort.insertionSort(sortableArray);
					checkSort(sortableArray);
					endTime = System.nanoTime();
					System.out.print((endTime - startTime) + " ");
				}// End If

				startTime = System.nanoTime();
				sortableArray = data;
				Sort.shellsort(sortableArray);
				checkSort(sortableArray);
				endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				startTime = System.nanoTime();
				sortableArray = data;
				Sort.mergeSort(sortableArray);
				checkSort(sortableArray);
				endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				startTime = System.nanoTime();
				sortableArray = data;
				Sort.quicksort(sortableArray);
				checkSort(sortableArray);
				endTime = System.nanoTime();
				System.out.print((endTime - startTime) + " ");

				System.out.println("");
			}// End For
			System.out.println();
		}// End For
	}// End integerTest

	public static void main(String[] args) {
		System.out.println("String Test");
		stringTest();
		System.out.println();
		System.out.println("Integer Test");
		integerTest();
	}// End main
}
