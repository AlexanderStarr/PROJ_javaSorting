import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;

public class sorting {
    
    private static int[] arr;
    private static int[] arrCopy;
    private static int[] arrCopyCopy;
    private static BufferedReader read;
    private static Random randomGenerator;
    
    private static int size;
    private static int random;
    
    private static int n;
    
    private static int randomNum(int lower, int upper) {
      // Returns a random number in the range of lower to upper, inclusive.
      int randNum = randomGenerator.nextInt(upper - lower + 1) + lower;
      return randNum;
    }
    
    private static void insertSort(int low, int high) {
      int si = low;  // Stands for sorted index.  Keeps track of how much is sorted.
      // Until the array is sorted from low to high, keep doing insertions.
      while (si < high) {
        int i = si + 1;    // i keeps track of where the 'hole' to insert is.
        int valToInsert = arr[i];    // Store the new value to insert.
        // Until the 'hole' is at the lowest index or valToInsert is greater than 
        // the value before the 'hole', shift the elements of the array right, moving the hole left.
        while (i > low && arr[i - 1] > valToInsert) {
          arr[i] = arr[i - 1];
          i--;
        }
        arr[i] = valToInsert;
        si++;
      }
    }
    
    private static boolean isSorted(int low, int high) {
      int i = low;    // Start at the lowest index.
      while (i < high) {
        // If at any position i, the element is greater than its successor, then it is not sorted.
        if (arr[i] > arr[i+1]) return false;
        i++;
      }
      // If all elements pass the test, the array is sorted.
      return true;
    }

    private static void printArray() {
      System.out.print("[" + arr[0]);
        for(int i=1; i<size; i++) {
            System.out.print(", " + arr[i]);
        }
        System.out.println("]");
    }
    
    public static void buildheap(){
        n=arr.length-1;
        for(int i=n/2;i>=0;i--){
            heapify(i);
        }
    }
    
    public static void heapify(int i){ 
        int largest;
        int left=2*i;
        int right=2*i+1;
        if(left <= n && arr[left] > arr[i]){
            largest=left;
        }
        else{
            largest=i;
        }
        
        if(right <= n && arr[right] > arr[largest]){
            largest=right;
        }
        if(largest!=i){
            exchange(i,largest);
            heapify(largest);
        }
    }
    
    public static void exchange(int i, int j){
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t; 
   }
    
    public static void heapsort(){
        buildheap();    
        for(int i=n;i>0;i--){
            exchange(0, i);
            n=n-1;
            heapify(0);
        }
    }
    
    private static void mergesort(int low, int high) {
        // Check if low is smaller then high, if not then the array is sorted
        if (low < high) {
          // Get the index of the element which is in the middle
          int middle = low + (high - low) / 2;
          // Sort the left side of the array
          mergesort(low, middle);
          // Sort the right side of the array
          mergesort(middle + 1, high);
          // Combine them both
          merge(low, middle, high);
        }
      }
    
    private static void mergesortA(int low, int high) {
        // Check if the array to be sorted is < 100.  If so, use insertSort on it.
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        // Otherwise, split it into smaller pieces.
        else {
          int middle = low + (high - low) / 2;
          mergesortA(low, middle);
          mergesortA(middle + 1, high);
          merge(low, middle, high);
        }
      }
    
    private static void mergesortB(int low, int high) {
        // Check if the array is already sorted, regardless of size.  Only call mergeSort if it's unsorted.
      if (!isSorted(low, high)) {
          int middle = low + (high - low) / 2;
          mergesort(low, middle);
          mergesort(middle + 1, high);
          merge(low, middle, high);
        }
      }
    
    private static void mergesortC(int low, int high) {
      // Check if the array is already sorted, regardless of size.  Only call mergeSort if it's unsorted.
      if (!isSorted(low, high)) {
        // If it's not sorted, but less than 100 elements, then use insertSort.
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        // Otherwise, divide it into smaller arrays.
        else {
          int middle = low + (high - low) / 2;
          mergesort(low, middle);
          mergesort(middle + 1, high);
          merge(low, middle, high);
        }
      }
    }

      private static void merge(int low, int middle, int high) {

        // Copy both parts into the arrCopy array
        for (int i = low; i <= high; i++) {
          arrCopy[i] = arr[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
          if (arrCopy[i] <= arrCopy[j]) {
            arr[k] = arrCopy[i];
            i++;
          } else {
            arr[k] = arrCopy[j];
            j++;
          }
          k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
          arr[k] = arrCopy[i];
          k++;
          i++;
        }

      }
      
      private static void quicksort(int low, int high) {
          int i = low, j = high;
          // Get the pivot element from the middle of the list
          int pivot = arr[(high+low)/2];

          // Divide into two lists
          while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (arr[i] < pivot) {
              i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (arr[j] > pivot) {
              j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i < j) {
              exchange(i, j);
              i++;
              j--;
            } else if (i == j) { i++; j--; }
          }

          // Recursion
          if (low < j)
            quicksort(low, j);
          if (i < high)
            quicksort(i, high);
        }
      
      private static int medianOfThree(int a, int b, int c) {
        if ((a <= b && b <= c) || (c <= b && b <= a)) return b;
        else if ((b <= a && a <= c) || (c <= a && a <= b)) return a;
        else return c;
      }
      
      private static void quicksort4A(int low, int high) {
        int i = low, j = high;
        int pivot = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        while (i <= j) {
          while (arr[i] < pivot) {
            i++;
          }
          while (arr[j] > pivot) {
            j--;
          }
          if (i < j) {
            exchange(i, j);
            i++;
            j--;
          } else if (i == j) { i++; j--; }
        }
        if (low < j)
          quicksort(low, j);
        if (i < high)
          quicksort(i, high);
      }
      
      private static void quicksort4B(int low, int high) {
        int i = low, j = high;
        int m1 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        int m2 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        int m3 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
        int pivot = medianOfThree(m1, m2, m3);
        while (i <= j) {
          while (arr[i] < pivot) {
            i++;
          }
          while (arr[j] > pivot) {
            j--;
          }
          if (i < j) {
            exchange(i, j);
            i++;
            j--;
          } else if (i == j) { i++; j--; }
        }
        if (low < j)
          quicksort(low, j);
        if (i < high)
          quicksort(i, high);
      }
      
      private static void quicksort5A(int low, int high) {
      if ((high - low) < 100) {
        insertSort(low, high);
      }
      else {
          int i = low, j = high;
          int pivot = arr[(high+low)/2];
          while (i <= j) {
            while (arr[i] < pivot) {
              i++;
            }
            while (arr[j] > pivot) {
              j--;
            }
            if (i < j) {
              exchange(i, j);
              i++;
              j--;
            } else if (i == j) { i++; j--; }
          }
          if (low < j)
            quicksort(low, j);
          if (i < high)
            quicksort(i, high);
        }
      }
      
      private static void quicksort5B(int low, int high) {
      if (!isSorted(low, high)) {
          int i = low, j = high;
          int pivot = arr[(high+low)/2];
          while (i <= j) {
            while (arr[i] < pivot) {
              i++;
            }
            while (arr[j] > pivot) {
              j--;
            }
            if (i < j) {
              exchange(i, j);
              i++;
              j--;
            } else if (i == j) { i++; j--; }
          }
          if (low < j)
            quicksort(low, j);
          if (i < high)
            quicksort(i, high);
        }
      }
      
      private static void quicksort5E(int low, int high) {
      if (!isSorted(low, high)) {
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        else{
            int i = low, j = high;
            int pivot = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            while (i <= j) {
              while (arr[i] < pivot) {
                i++;
              }
              while (arr[j] > pivot) {
                j--;
              }
              if (i < j) {
                exchange(i, j);
                i++;
                j--;
              } else if (i == j) { i++; j--; }
            }
            if (low < j)
              quicksort(low, j);
            if (i < high)
              quicksort(i, high);
        }
      }
      }
      
      private static void quicksort5F(int low, int high) {
      if (!isSorted(low, high)) {
        if ((high - low) < 100) {
          insertSort(low, high);
        }
        else{
            int i = low, j = high;
            int m1 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            int m2 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            int m3 = medianOfThree(arr[randomNum(low, high)], arr[randomNum(low, high)], arr[randomNum(low, high)]);
            int pivot = medianOfThree(m1, m2, m3);
            while (i <= j) {
              while (arr[i] < pivot) {
                i++;
              }
              while (arr[j] > pivot) {
                j--;
              }
              if (i < j) {
                exchange(i, j);
                i++;
                j--;
              } else if (i == j) { i++; j--; }
            }
            if (low < j)
              quicksort(low, j);
            if (i < high)
              quicksort(i, high);
        }
      }
      }

    public static void main(String[] args) {
        
        read = new BufferedReader(new InputStreamReader(System.in));
        
        randomGenerator = new Random();
        
        try
        {
            /*System.out.print("Please enter array size : ");
            size = Integer.parseInt(read.readLine());
            
            System.out.print("Please enter the random range : ");
            random = Integer.parseInt(read.readLine());*/
            
            // The code for Problems 3 and 5
          // See results.txt for the output of this
          int n = 1000000;
          int range = 10000;
          while (n != 0) {
            // Initialize a total for each mergeSort variation.
            int mTot = 0;
            int mATot = 0;
            int mBTot = 0;
            int mCTot = 0;
            int qTot = 0;
            int q4ATot = 0;
            int q4BTot = 0;
            int q5ATot = 0;
            int q5BTot = 0;
            int q5ETot = 0;
            int q5FTot = 0;
            // Will do 10 tests at each array size.
            arr = new int[n];
              arrCopy = new int[n];
              arrCopyCopy = new int[n];
              for (int i=0; i<10; i++) {
                // Create the new array.
                for(int j=0; j<n; j++) {
                    arr[j] = arrCopy[j] = arrCopyCopy[j] = randomGenerator.nextInt(range);
                }
                
                // Then add the amount of time each sort took to its total.
                long start = System.currentTimeMillis();
                mergesort(0, n-1);
                long finish = System.currentTimeMillis();
                mTot += (finish-start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                mergesortA(0, n-1);
                finish = System.currentTimeMillis();
                mATot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                mergesortB(0, n-1);
                finish = System.currentTimeMillis();
                mBTot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                mergesortC(0, n-1);
                finish = System.currentTimeMillis();
                mCTot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopy[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                quicksort(0, n-1);
                finish = System.currentTimeMillis();
                qTot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                quicksort4A(0, n-1);
                finish = System.currentTimeMillis();
                q4ATot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                quicksort4B(0, n-1);
                finish = System.currentTimeMillis();
                q4BTot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                quicksort5A(0, n-1);
                finish = System.currentTimeMillis();
                q5ATot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                quicksort5B(0, n-1);
                finish = System.currentTimeMillis();
                q5BTot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                quicksort5E(0, n-1);
                finish = System.currentTimeMillis();
                q5ETot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopyCopy[j];
                
                start = System.currentTimeMillis();
                quicksort5F(0, n-1);
                finish = System.currentTimeMillis();
                q5FTot += (finish - start);
                for(int j=0; j<n; j++) arr[j] = arrCopyCopy[j];
              }
              // After 10 tests, calculate the average time taken for each one and print it.
              // Then increase n and do 10 more test.
              System.out.println("For an array size of " + n + ":");
              System.out.println("mergesort: " + (mTot/10) + " milliseconds");
              System.out.println("mergesortA: " + (mATot/10) + " milliseconds");
              System.out.println("mergesortB: " + (mBTot/10) + " milliseconds");
              System.out.println("mergesortC: " + (mCTot/10) + " milliseconds");
              System.out.println("quicksort: " + (qTot/10) + " milliseconds");
              System.out.println("quicksortA: " + (q5ATot/10) + " milliseconds");
              System.out.println("quicksortB: " + (q5BTot/10) + " milliseconds");
              System.out.println("quicksortC: " + (q4ATot/10) + " milliseconds");
              System.out.println("quicksortD: " + (q4BTot/10) + " milliseconds");
              System.out.println("quicksortE: " + (q5ETot/10) + " milliseconds");
              System.out.println("quicksortF: " + (q5FTot/10) + " milliseconds");
              if (n == 1000000) n = 10000000;
              else if (n == 10000000) n = 50000000;
              else if (n == 50000000) n = 0;
          }
            
            /*
            // create array
            arr = new int[size];
            arrCopy = new int[size];
            
            
            // fill array
            for(int i=0; i<size; i++) {
                arr[i] = arrCopy[i] = randomGenerator.nextInt(random);
            }
            if (size < 101) { 
              System.out.println("Initial array:");
              printArray();
            }
            
            long start = System.currentTimeMillis();
            Arrays.sort(arr);
            if (size < 101) printArray();
            long finish = System.currentTimeMillis();
            
            System.out.println("Arrays.sort: " + (finish-start) + " milliseconds.");
            System.out.println("arr is sorted = " + isSorted(0, size-1));
            arr = arrCopy;
            
            // Insertion sort     
            start = finish;
            for(int i=0; i<size; i++) arr[i] = arrCopy[i];
            insertSort(0, size-1);
            if (size < 101) printArray();
            finish = System.currentTimeMillis();
            System.out.println("insertsort: " + (finish-start) + " milliseconds.");
            
            // Heap sort      
            start = finish;
            for(int i=0; i<size; i++) arr[i] = arrCopy[i];
            heapsort();
            if (size < 101) printArray();
            finish = System.currentTimeMillis();
            System.out.println("heapsort: " + (finish-start) + " milliseconds.");
 
            // Quick sort
            start = finish;
            for(int i=0; i<size; i++) arr[i] = arrCopy[i];
            quicksort5A(0, size-1);
            if (size < 101) printArray();
            finish = System.currentTimeMillis();
            System.out.println("quicksort: " + (finish-start) + " milliseconds.");
            
            // Merge sort, which destroys arrCopy[].
            start = finish;
            for(int i=0; i<size; i++) arr[i] = arrCopy[i];
            mergesort(0, size-1);
            if (size < 101) printArray();
            finish = System.currentTimeMillis();
            System.out.println("mergesort: " + (finish-start) + " milliseconds.");*/
            
      
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
