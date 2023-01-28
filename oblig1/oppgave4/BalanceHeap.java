import java.util.*;

class BalanceHeap{

  public static void main(String [] args){

    Scanner scanner = new Scanner(System.in);
    PriorityQueue<Integer> tempQ = new PriorityQueue<Integer>();
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    while(true){
      if(!scanner.hasNextLine()){
        break;
      }
      pq.offer(Integer.parseInt(scanner.nextLine()));
    }
    for(int i = 0; i < pq.size(); i++){
      tempQ.offer(pq.poll());
    }
    utskrift(tempQ);
  }
  static void utskrift(PriorityQueue<Integer> pq){
    PriorityQueue<Integer> v = new PriorityQueue<Integer>();
    PriorityQueue<Integer> h = new PriorityQueue<Integer>();
    if(pq.size() == 0){
      return;
    }
    else if(pq.size() == 1){
      System.out.println(pq.poll());
    }
    else if(pq.size() == 2){
      System.out.println(pq.poll());
      System.out.println(pq.poll());
    }
    else if(pq.size() > 2){
      int mid = pq.size() / 2;
      for(int i = 0; i < mid; i++){
        v.offer(pq.poll());
      }
      System.out.println(pq.poll());
      h = pq;

      utskrift(v);
      utskrift(h);
    }
  }
}
