import java.util.ArrayList;
import java.util.Scanner;

class Teque{

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int n = (Integer.parseInt(scan.nextLine()));
    if(n > 1000000){
      n = 1000000;
    }
    ArrayList<Integer> al = new ArrayList<>();
    for(int i = 0; i < n; i++){
      String input = scan.nextLine();
      String[] inputsep = input.split(" ");
      int indeks = Integer.parseInt(inputsep[1]);
      String funksjon = inputsep[0];
      if(funksjon.equals("push_front")){
        al.add(0,indeks);
      }
      else if(funksjon.equals("push_back")){
        al.add(indeks);
      }
      else if(funksjon.equals("push_middle")){
        al.add((al.size()+1)/2,indeks);
      }
      else if(funksjon.equals("get")){
        System.out.println(al.get(indeks));
      }
    }
  }
}
