import java.util.*;

class Kattunge{

  public static void main(String[] args){
    HashMap<Integer,Integer> tall = new HashMap<Integer,Integer>();

    Scanner scanner = new Scanner(System.in);
    int katt = Integer.parseInt(scanner.nextLine());
    tall.put(katt,null);
    while(true){
      String linje = scanner.nextLine();
      if(!linje.equals("-1")){
        String[] lineStr = linje.split(" ");
        int dataP = Integer.parseInt(lineStr[0]);
        for(int i = 1; i < lineStr.length; i++){
          tall.put(Integer.parseInt(lineStr[i]),dataP);
        }
      }
      else break;
    }

    String ut = "";
    ut += katt;
    while(true){
      if(tall.get(katt) != null){
        int value = tall.get(katt);
        katt = value;
        ut += " ";
        ut += katt;
      }
      else break;
    }
    System.out.print(ut);

  }
}
