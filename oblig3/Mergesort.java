import java.util.Arrays;

public class Mergesort{
  private static int swaps = 0;
  private static int comparisons = 0;

  public int[] sort(int[] seq){
    if(seq.length <= 1){
      return seq;
    }
    int mid = (seq.length)/2;
    int[] l = sort(Arrays.copyOfRange(seq,0,mid));
    int[] r = sort(Arrays.copyOfRange(seq,mid,seq.length));
    return merge(l,r);
  }
  public int[] merge(int[] l, int[] r){
    int[] seq = new int[l.length + r.length];
    int i = 0;
    int k = 0;
    while(i < l.length && k < r.length){
      if(l[i] <  r[k]){
        seq[i + k] = l[i];
        i++;
      }
      else{
        seq[i + k] = r[k];
        k++;
      }
    }
    while(i < l.length){
      seq[i + k] = l[i];
      i++;
    }
    while(k < r.length){
      seq[i + k] = r[k];
      k++;
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
