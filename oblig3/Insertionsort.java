import java.util.Arrays;

class Insertionsort{
  private static int swaps = 0;
  private static int comparisons = 0;

  public int[] sort( int[] seq){
    for(int i = 1; i < seq.length; i++){
      int k = i;
      while(k > 0 && seq[k-1] > seq[k]){
        compare(); //sammenlikning av element k-1 med k, i linja over
        swap(seq,k,k-1);
        k--;
      }
      compare(); // legger til én ekstra sammenlikning, siden denne ikke telles i while- loopen
    }
    return seq;
  }
  public void compare(){
    comparisons ++;
  }
  public void swap(int[] arr, int i, int k){
    int temp = arr[i];
    arr[i] = arr[k];
    arr[k] = temp;
    swaps ++;
  }
  public int comparisons(){
    return comparisons;
  }
  public int swaps(){
    return swaps;
  }
}
