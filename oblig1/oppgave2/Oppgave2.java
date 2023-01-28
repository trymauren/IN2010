import java.util.*;

class Oppgave2{

  public static void main(String[] args){
    HashMap<Integer,Node> noder = new HashMap<Integer,Node>();
    Node root;
    System.out.println("Skriv inn:");
    Scanner scanner = new Scanner(System.in);
    int katteNode = Integer.parseInt(scanner.nextLine());
    Node katt = new Node(katteNode,true);
    noder.put(katteNode,katt);

    while(true){
      String linje = scanner.nextLine();
      if(!linje.equals("-1")){
        String[] lineStr = linje.split(" ");
        int dataP = Integer.parseInt(lineStr[0]); //data er int til første node i linja
        //Node subParent;
        Node nyP = new Node(0);
        boolean pIMap = true;
        for(HashMap.Entry<Integer,Node> entry : noder.entrySet()){
          int key = entry.getKey();
          Node tempP = entry.getValue();
          if(key == dataP){
            nyP = tempP;
          }
          else{
            nyP = new Node(dataP);
            pIMap = false;
          }
        }
        if(pIMap != true){
          noder.put(dataP,nyP);
        }

        for(int i = 1; i < lineStr.length; i++){
          Node nyC = new Node(0);
          int dataC = Integer.parseInt(lineStr[i]);
          boolean ikke = false;
          for(HashMap.Entry<Integer,Node> entry : noder.entrySet()){
            int key = entry.getKey();
            Node tempC = entry.getValue();
            if(key == dataC){
              nyP.addChild(tempC);
              tempC.setParent(nyP);
              nyC = tempC;
              ikke = true;
            }
          }
          if(ikke){
            noder.put(dataC,nyC);
          }
          nyC = new Node(dataC);
          nyP.addChild(nyC);
          nyC.setParent(nyP);
          noder.put(dataC,nyC);

        }
      }
      else{
        break;
      }
    }


    //System.out.println(katt.parent().data());
    katt.findWay();

  }
}
class Tree{
  Node root;

  // public findWay(Node katt){
  //   LAG REKURSIV METODE FOR A FINNE VEI
  // }

}
class UgyldigListeIndeks extends RuntimeException{
  UgyldigListeIndeks(int indeks){
    super("Ugyldig indeks:" + indeks);
  }
}

class Node{
  int data;
  Node parent = null;
  ArrayList<Node> children = new ArrayList<Node>();
  boolean cat = false;

  public Node(int data){
    this.data = data;
  }
  public Node(int data, boolean cat){
    this.data = data;
    this.cat = cat;
  }
  public void addChild(Node n){
    children.add(n);
  }
  public void setParent(Node n){
    this.parent = n;
  }
  public ArrayList<Node> children(){
    return children;
  }
  public Node parent(){
    return parent;
  }
  public int data(){
    return data;
  }
  public void findWay(){
    System.out.println(data);
    if(parent == null){
      return;
    }
    else{
      parent.findWay();
    }
  }

}
