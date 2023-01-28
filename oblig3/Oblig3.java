import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Oblig3{
  public static void main(String[] args) throws IOException {

    int[] empty = new int[0];
    String inputFileName = args[0];
    int[] seq = input(inputFileName);
    String[] split = inputFileName.split("\\.");
    String fileName = split[0] + "_results.csv";

    //ENDRE DISSE FOR Å KJØRE FORSKJELLIGE ALGORITMER
    //FOR Å KJØRE FÆRRE ENN 4, SKRIV SAMME NAVN PÅ FLERE
    String alg1 = "insertionsort";
    String alg2 = "insertionsort";
    String alg3 = "insertionsort";
    String alg4 = "insertionsort";

    ArrayList<String> results = new ArrayList<>();
    String resultsHeadLine = "";
    String resultsLine = "";

    for(int i = -1; i < seq.length; i++){
      resultsLine = "";
      if(i == -1){
        resultsHeadLine += String.format("n,  ");
        resultsLine += String.format("%s","0,");
        if(alg1 == "mergesort" || alg2 == "mergesort" || alg3 == "mergesort" || alg4 == "mergesort"){
          Mergesort ms = new Mergesort();
          long msTime = System.nanoTime();
          int[] mSorted = ms.sort(empty);
          long msT = (System.nanoTime()-msTime)/1000;
          int msC = ms.comparisons();
          int msS = ms.swaps();
          String merge_cmp = "merge_cmp";
          String merge_swap = "merge_swap";
          String merge_time = "merge_time";
          resultsHeadLine += String.format("%20s,%20s,%20s,",merge_cmp,merge_swap,merge_time);
          resultsLine += String.format("%20s,%20s,%20s,",msC,msS,msT);
          writeSorted(mSorted,"mergesort");
        }

        if(alg1 == "quicksort" || alg2 == "quicksort" || alg3 == "quicksort" || alg4 == "quicksort"){
          Quicksort qs = new Quicksort();
          long qsTime = System.nanoTime();
          int[] qSorted = qs.sort(empty);
          long qsT = (System.nanoTime()-qsTime)/1000;
          int qsC = qs.comparisons();
          int qsS = qs.swaps();
          String quick_cmp = "quick_cmp";
          String quick_swap = "quick_swap";
          String quick_time = "quick_time";
          resultsHeadLine += String.format("%20s,%20s,%20s,",quick_cmp,quick_swap,quick_time);
          resultsLine += String.format("%20s,%20s,%20s,",qsC,qsS,qsT);
          writeSorted(qSorted,"quicksort");
        }

        if(alg1 == "insertionsort" || alg2 == "insertionsort" || alg3 == "insertionsort" || alg4 == "insertionsort"){
          Insertionsort is = new Insertionsort();
          long isTime = System.nanoTime();
          int[] iSorted = is.sort(empty);
          long isT = (System.nanoTime()-isTime)/1000;
          int isC = is.comparisons();
          int isS = is.swaps();
          String insert_cmp = "insert_cmp";
          String insert_swap = "insert_swap";
          String insert_time = "insert_time";
          resultsHeadLine += String.format("%20s,%20s,%20s,",insert_cmp,insert_swap,insert_time);
          resultsLine += String.format("%20s,%20s,%20s,",isC,isS,isT);
          writeSorted(iSorted,"insertionsort");
        }

        if(alg1 == "bubblesort" || alg2 == "bubblesort" || alg3 == "bubblesort" || alg4 == "bubblesort"){
          Bubblesort bs = new Bubblesort();
          long bsTime = System.nanoTime();
          int[] bSorted = bs.sort(empty);
          long bsT = (System.nanoTime()-bsTime)/1000;
          int bsC = bs.comparisons();
          int bsS = bs.swaps();
          String bubble_cmp = "bubble_cmp";
          String bubble_swap = "bubble_swap";
          String bubble_time = "bubble_time";
          resultsHeadLine += String.format("%20s,%20s,%20s,",bubble_cmp,bubble_swap,bubble_time);
          resultsLine += String.format("%20s,%20s,%20s,",bsC,bsS,bsT);
          writeSorted(bSorted,"bubblesort");
        }
        results.add(resultsHeadLine);
      }

      else{
        if(alg1 == "mergesort" || alg2 == "mergesort" || alg3 == "mergesort" || alg4 == "mergesort"){
          int k = i + 1;
          resultsLine += String.format("%s,",k);
          Mergesort ms = new Mergesort();
          long msTime = System.nanoTime();
          int[] mSorted = ms.sort(Arrays.copyOfRange(seq,0,i));
          long msT = (System.nanoTime()-msTime)/1000;
          int msC = ms.comparisons();
          int msS = ms.swaps();
          resultsLine += String.format("%20s,%20s,%20s,",msC,msS,msT);
          writeSorted(mSorted,"mergesort");
        }

        if(alg1 == "quicksort" || alg2 == "quicksort" || alg3 == "quicksort" || alg4 == "quicksort"){
          Quicksort qs = new Quicksort();
          long qsTime = System.nanoTime();
          int[] qSorted = qs.sort(Arrays.copyOfRange(seq,0,i));
          long qsT = (System.nanoTime()-qsTime)/1000;
          int qsC = qs.comparisons();
          int qsS = qs.swaps();
          resultsLine += String.format("%20s,%20s,%20s,",qsC,qsS,qsT);
          writeSorted(qSorted,"quicksort");
        }

        if(alg1 == "insertionsort" || alg2 == "insertionsort" || alg3 == "insertionsort" || alg4 == "insertionsort"){
          Insertionsort is = new Insertionsort();
          long isTime = System.nanoTime();
          int[] iSorted = is.sort(Arrays.copyOfRange(seq,0,i));
          long isT = (System.nanoTime()-isTime)/1000;
          int isC = is.comparisons();
          int isS = is.swaps();
          resultsLine += String.format("%20s,%20s,%20s,",isC,isS,isT);
          writeSorted(iSorted,"insertionsort");
        }

        if(alg1 == "bubblesort" || alg2 == "bubblesort" || alg3 == "bubblesort" || alg4 == "bubblesort"){
          Bubblesort bs = new Bubblesort();
          long bsTime = System.nanoTime();
          int[] bSorted = bs.sort(Arrays.copyOfRange(seq,0,i));
          long bsT = (System.nanoTime()-bsTime)/1000;
          int bsC = bs.comparisons();
          int bsS = bs.swaps();
          resultsLine += String.format("%20s,%20s,%20s,",bsC,bsS,bsT);
          writeSorted(bSorted,"bubblesort");
        }
      }
      results.add(resultsLine);
    }

    writeResults(results,fileName);
  }
  private static int[] input(String fileName) throws IOException {

    File file = new File(fileName);
    BufferedReader br = new BufferedReader(new FileReader(file));
    int[] arr = br.lines().mapToInt(i -> Integer.parseInt(i)).toArray();
    br.close();
    return arr;
  }
  private static void writeSorted(int[] seq, String algName) throws IOException{

    FileWriter writer = new FileWriter("example" + algName + ".out");
    for(int i : seq) {
      writer.write(i + System.lineSeparator());
    }
    writer.close();
  }
  private static void writeResults(ArrayList<String> results, String fn) throws IOException{

    FileWriter writer = new FileWriter(fn);
    for(String s : results) {
      writer.write(s + System.lineSeparator());
    }
    writer.close();
  }
}
