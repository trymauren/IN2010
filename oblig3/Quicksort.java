import java.util.Arrays;

public class Quicksort{
  private static int swaps = 0;
  private static int comparisons = 0;

  public int[] sort(int[] seq){
    sortHelper(seq,0,seq.length - 1);
    return seq;
  }
  private void sortHelper(int[] seq, int low, int high){
    if(low < high){
      int p = partition(seq,low,high);
      sortHelper(seq, low, p - 1);
      sortHelper(seq, p + 1, high);
    }
  }
  public int partition(int[] seq, int low, int high){
    int pivot = seq[high];
    int i = low - 1;
    for(int k = low; k < high; k++){
      if(seq[k] <= pivot){
        i++;
        swap(seq,i,k);
      }
    }
    swap(seq,i + 1, high);
    return i + 1;
    // swap(seq,p,high);
    // int pivot = seq[high];
    // int left = low;
    // int right = high - 1;
    // while(left <= right){
    //   while(left <= right && seq[left] <= pivot){
    //     left++;
    //   }
    //   while(right >= left && seq[high] >= pivot){
    //     right--;
    //   }
    //   if(left < right){
    //     swap(seq,left,right);
    //   }
    //   swap(seq,left,high);
    // }
    // return left;
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
