import java.util.*;

class BalanceArray{

  public static void main(String [] args){
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    Scanner scanner = new Scanner(System.in);
    while(true){
      if(!scanner.hasNextLine()){
        break;
      }
      numbers.add(Integer.parseInt(scanner.nextLine()));
    }
    utskrift(numbers);

  }
  static void utskrift(ArrayList<Integer> numbers){
    ArrayList<Integer> v = new ArrayList<Integer>();
    ArrayList<Integer> h = new ArrayList<Integer>();

    if(numbers.size() == 0){
      return;
    }
    else if(numbers.size() == 1){
      System.out.println(numbers.remove(0));
    }
    else if(numbers.size() == 2){
      System.out.println(numbers.remove(0));
      System.out.println(numbers.remove(0));
    }
    else if(numbers.size() > 2){
      int mid = numbers.size() / 2;
      for(int i = 0; i < mid; i++){
        v.add(numbers.remove(0));
      }
      System.out.println(numbers.remove(0));
      h = numbers;

      utskrift(v);
      utskrift(h);
    }
  }
}
