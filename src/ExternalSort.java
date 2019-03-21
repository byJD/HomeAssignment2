import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;


public class ExternalSort {

    private int fileCount = 1;
    private int zeroCount = 0;

    public void createChuncks(String inputFile, int n, int k){

        int chunkCount = 0;
        double[] array = new double[k];
        double[] shortArray;

        try {
            File arrayInput = new File(inputFile);
            Scanner in = new Scanner(arrayInput);

            while(in.hasNext()) {
                String s = in.next();
                array[chunkCount++] = Double.valueOf(s);

                //keep track of chuncksize
                if (chunkCount == k){

                    //sort
                    sort(array);

                    //write
                    System.out.println(Arrays.toString(array));

                    writeToTempFile(array);

                    //reset
                    chunkCount = 0;
                    zeroCount = 0;
                    array = new double[k];
                }
            }
            in.close();

            //if array not completely filled
            //create an array smaller than the chunk

            if (n%k != 0) {
                shortArray = new double[n % k];

                for (int i = 0; i < n % k; i++) {
                    shortArray[i] = array[i];

                }
                sort(shortArray);
                System.out.println(Arrays.toString(shortArray));
                writeToTempFile(shortArray);
            }
        }
        catch (FileNotFoundException e) {
            System.exit(1);
        }
    }


    public void externalSort(String inputFile, String outputFile, int n, int k){

       //
       createChuncks(inputFile, n, k);

        System.out.println(fileCount);;

        /*

       int fileTracker = 1;
       int numberOfFiles = fileCount-1;

       //write data from all files to one file


        try {



            double [] numbers = new double[numberOfFiles];
            BufferedReader[] brs = new BufferedReader[numberOfFiles];

            for (int i = 0; i < numberOfFiles; i++){

                brs[i] = new BufferedReader(new FileReader(String.format("src/tempFile_%s", fileTracker++)));

                //System.out.println(Arrays.toString(brs));

                //String input  = brs[i].readLine().replaceAll("\\[", "").replaceAll("\\]", "");


                Double value = Double.parseDouble(brs[i].readLine().replaceAll("\\[", "").replaceAll("\\]", ""));

                //Double value = Double.valueOf();

                System.out.println(value);

            }






        } catch (IOException e) {
            e.printStackTrace();
        }

        */


    }

    public void writeToTempFile(double array[]){


        String fileName = String.format("src/tempFile_%s", fileCount++);

        try{
            FileWriter fr = new FileWriter(fileName);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter out = new PrintWriter(br);

            for (int i = 0; i < array.length; i++){
                out.write(String.format(Double.toString(array[i])+'\n'));

            }

            //out.write(Arrays.toString(array));

            out.close();
        }

        catch(IOException e){
            System.out.println(e);
        }
    }


    public void writeToResult(String fileName){

    }



    public void readTempFiles(){

        String filename;

        for (int i = 1; i <= fileCount; i++){

            filename = String.format("src/tempFile_%s", i);

            try{

                File file = new File(filename);
                Scanner input = new Scanner(file).useDelimiter(", ");



            }
            catch (FileNotFoundException e) {
                System.exit(1);
            }

        }
    }

    public void mergeTempFiles(String file1, int k /*, String fil2, int n*/){

        double[] array = new double[k];
        int index = 0;

        double d1;
        double d2;

        //there are never more items in the file than the size of the chunck, so won't go out of bound

        try {
            File arrayInput = new File(file1);
            Scanner masterFile = new Scanner(arrayInput).useDelimiter(", ");

            while(masterFile.hasNext()) {
                String s = masterFile.next();
                array[index++] = Double.valueOf(s.replaceAll("\\[", "").replaceAll("\\]",""));
            }
            masterFile.close();


        }
        catch (FileNotFoundException e) {
            System.exit(1);
        }

        System.out.println(Arrays.toString(array));
    }




    public void sort(double[] a) {

        int n = a.length;
        for (int i=1; i<n; ++i) {
            double key = a[i];
            int j = i-1;


            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && a[j] > key) {
                a[j+1] = a[j];
                j = j-1;
            }
            a[j+1] = key;
        }

    }


    public static void main(String[] args) {

        ExternalSort sort = new ExternalSort();

        sort.externalSort("src/test.txt", "src/result.txt", 7, 3);

        //sort.mergeTempFiles("src/tempFile_1", 3);

    }


    }
