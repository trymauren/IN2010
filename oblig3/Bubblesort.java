import java.util.Arrays;

class Bubblesort{
  private static int swaps = 0;
  private static int comparisons = 0;

  public int[] sort(int[] seq){
    for(int i = 0; i < seq.length - 1; i++){
      boolean flag = false;
      for(int k = 0; k < seq.length - i - 1; k++){
        compare(); // sammenlikning av element k med element k + 1, i linja under
        if(seq[k] > seq[k + 1]){
          swap(seq,k,k + 1);
          flag = true;
        }
      }
      if(!flag) break;
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
