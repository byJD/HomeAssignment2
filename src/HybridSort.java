import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class HybridSort {

    //private double [] sortedArray;



    public int quicksort (double [] arr, int left, int right){

        int pivotIndex = 0;

        if (left < right) {
            pivotIndex = partitionRandom(arr, left, right);
            quicksort(arr, left, pivotIndex-1);
            quicksort(arr, pivotIndex+1, right);
        }

        return pivotIndex;
    }

    int partitionRandom(double arr[], int left, int right){
        Random rand = new Random();

        int random = left + rand.nextInt(right - left);

        swap(arr, random, right);

        return partition(arr, left, right);

    }


    int partition (double[] arr, int left, int right) {
        double pivot = arr[right]; // pivot
        int i = (left - 1); // Index of smaller element

        for (int j = left; j <= right - 1; j++) {

            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {

                i++; // increment index of smaller element
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, right);
        return (i + 1);
    }

    //swaps item in array
    void swap(double []a, int i, int j){
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }


    public void quadraticsort (double [] arr, int left, int right){


        //selection sort
        //why?
        //https://johnderinger.wordpress.com/2012/12/28/quadratic-and-linearithmic-comparison-based-sorting-algorithms/
        //it writes less, writing is slower than reading IRL


        for (int i = left; i < right - 1; i++)
        {
            int index = i;

            for (int j = i + 1; j < right; j++)
                if (arr[j] < arr[index])
                    index = j;

            swap(arr, i, index);
        }
    }


    public void hybridsort (double [] arr, int left, int right){

        //(Cselection * n2) < (Cquick*(n log n))

        //if pivot is not not in the middle, algorithm is drastically slower

        if (arr.length < 30 || right - left <= 30){
            quadraticsort(arr, left, right);
        }
        else{
            quicksort(arr, left, right);
        }
    }



    public static void main(String[] args) {

        HybridSort sort = new HybridSort();



        double [] arr = {5, 2, 9, 12, 6, 8, 3, 7};
        System.out.println("unsorted array: \n" + Arrays.toString(arr));

        //double[] sortedArr = new double[arr.length];

        //sort.sortedArray = new double[arr.length];

        //System.out.print("Quicksort: output ");
        System.out.println("Quicksort: output " + sort.quicksort(arr, 0, arr.length-1));
        System.out.println(Arrays.toString(arr));
        //System.out.println(Arrays.toString(sort.sortedArray));

        double [] arr2 = {5, 2, 9, 12, 6, 8, 3, 7};

        System.out.println("Quadratic");
        sort.quadraticsort(arr2, 0, arr2.length);
        System.out.println(Arrays.toString(arr2));


        double [] arr3 = {5, 2, 9, 12, 6, 8, 3, 7};

        System.out.println("Hybrid");
        sort.quadraticsort(arr3, 0, arr3.length);
        System.out.println(Arrays.toString(arr3));


    }

}
