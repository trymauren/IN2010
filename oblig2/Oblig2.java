import java.util.*;
import java.io.*;

class Oblig2{

  private static HashMap<Movie,LinkedList<Actor>> mapping = new HashMap<>();
  private static HashMap<String,Movie> movieMapping = new HashMap<>();
  private static HashMap<String,Actor> actorMapping = new HashMap<>();

  public static void main(String [] args){

    Oblig2 o = new Oblig2();
    System.out.println("--------------------------------------- \n");

    System.out.println("OPPGAVE 1:");
    o.readMovies();
    o.readNodes();
    System.out.println(o.countEdges());
    System.out.println(o.countNodes());
    System.out.println("--------------------------------------- \n");

    System.out.println("OPPGAVE 2:");
    System.out.println(o.findWay("nm2255973","nm0000460"));
    System.out.println(o.findWay("nm0424060","nm0000243"));
    System.out.println(o.findWay("nm4689420","nm0000365"));
    System.out.println(o.findWay("nm0000288","nm0001401"));
    System.out.println(o.findWay("nm0031483","nm0931324"));
    System.out.println("--------------------------------------- \n");

    System.out.println("OPPGAVE 3:");
    System.out.println(o.findChillWay("nm2255973","nm0000460"));
    System.out.println(o.findChillWay("nm0424060","nm0000243"));
    System.out.println(o.findChillWay("nm4689420","nm0000365"));
    System.out.println(o.findChillWay("nm0000288","nm0001401"));
    System.out.println(o.findChillWay("nm0031483","nm0931324"));
    System.out.println("--------------------------------------- \n");

    System.out.println("OPPGAVE 4:");
    System.out.println(o.findComponents());

  }

  private String findComponents(){
    String out = "";

    for(Actor a : actorMapping.values()) a.reset();

    PriorityQueue<Actor> q = new PriorityQueue<>(new distanceComparator());
    ArrayList<Actor> actors = new ArrayList<>(actorMapping.values());
    HashMap<Integer,Integer> c = new HashMap<>();

    float distance = 0;
    int thisCompSize = 0;

    for(int i = 0; i < actors.size(); i++){
      Actor ac = actors.get(i);
      if(ac.isVisited()){
        continue;
      }
      q.offer(ac);
      thisCompSize = 1;
      while(q.size() > 0){
        Actor popped = q.poll();
        popped.visited();
        distance = popped.getDistance() + 1; //kun med for å bruke pq
        for(Movie m : popped.getMovies().values()){
          for(Actor a : mapping.get(m)){
            if(!a.isVisited()){
              a.visited();
              q.offer(a);
              a.setDistance(distance);
              thisCompSize++;
            }
          }
        }
      }
      if(c.keySet().contains(thisCompSize)){
        c.put(thisCompSize,c.get(thisCompSize)+1);
      }
      else{
        c.put(thisCompSize,1);
      }
    }
    for(Map.Entry<Integer,Integer> entry : c.entrySet()){
      out += "\n" + entry.getValue() + " components of size " + entry.getKey();
    }
    return out;
  }

  private String findChillWay(String idStart, String idDest){
    for(Actor a : actorMapping.values()) a.reset();

    float distance = 0;
    Actor start = actorMapping.get(idStart);
    Actor destination = actorMapping.get(idDest);
    PriorityQueue<Actor> q = new PriorityQueue<>(new distanceComparator());

    start.setDistance(distance);
    q.offer(start);

    int tempDistance = 0;

    while(q.size() > 0){
      Actor popped = q.poll();
      popped.visited();
      distance = popped.getDistance();
      for(Movie m : popped.getMovies().values()){
        for(Actor a : mapping.get(m)){
          //Setter distanse fra start til nabo lik eksisterende distanse + rating på film,
          // men kun hvis denne er kortere enn lengde på tidligere funnet vei
          if((popped.getDistance() + 10 - m.getRating()) < a.getDistance()){
            a.setDistance(popped.getDistance() + 10 - m.getRating());
            a.setPrevious(m,popped);
            q.offer(a);
          }
        }
      }
    }
    return printChillWay(start,destination);
  }

  private String findWay(String idStart, String idDest){
    String out = "";

    for(Actor a : actorMapping.values()) a.reset();

    float distance = 0;
    Actor start = actorMapping.get(idStart);
    Actor destination = actorMapping.get(idDest);
    PriorityQueue<Actor> q = new PriorityQueue<>(new distanceComparator());

    start.setDistance(0);
    q.offer(start);

    outerLoop:
    while(q.size() > 0){
      Actor popped = q.poll();
      popped.visited();
      distance = popped.getDistance() + 1;
      for(Movie m : popped.getMovies().values()){
        for(Actor a : mapping.get(m)){
          if(!a.isVisited()){
            a.visited();
            a.setDistance(distance);
            q.offer(a);
            a.setPrevious(m,popped);
          }
          if(a.equals(destination)){
            out += printWay(start,a);
            break outerLoop;
          }
        }
      }
    }
    return out;
  }

  private String printChillWay(Actor start, Actor dest){
    String out = "";
    float weight = 0;
    Actor a = dest;
    ArrayList<Actor> aWay = new ArrayList<>();
    ArrayList<Movie> mWay = new ArrayList<>();

    while(a != null){
      aWay.add(actorMapping.get(a.getId()));
      if(!(a.getPreviousMovie() == null)){
        mWay.add(movieMapping.get(a.getPreviousMovie().getId()));
        weight += 10 - a.getPreviousMovie().getRating();
      }
      a = a.getPreviousActor();
    }

    Collections.reverse(aWay);
    Collections.reverse(mWay);

    out += "\n";
    for(int i = 0; i < aWay.size()-1; i++){
      out += aWay.get(i).getName() + "\n" + "===[ ";
      out += mWay.get(i).getName() + " (" + mWay.get(i).getRating() + ") ===> ";
    }
    out += "\n" + dest.getName();
    out += "\n" + "Weight: " + weight;
    return out;
  }

  private String printWay(Actor start, Actor dest){
    String out = "";
    Actor a = dest;
    ArrayList<Actor> aWay = new ArrayList<>();
    ArrayList<Movie> mWay = new ArrayList<>();

    while(a != null){
      aWay.add(actorMapping.get(a.getId()));
      if(!(a.getPreviousMovie() == null)) mWay.add(movieMapping.get(a.getPreviousMovie().getId()));
      a = a.getPreviousActor();
    }

    Collections.reverse(aWay);
    Collections.reverse(mWay);

    out += "\n";
    for(int i = 0; i < aWay.size()-1; i++){
      out += aWay.get(i).getName() + "\n" + "===[ ";
      out += mWay.get(i).getName() + " (" + mWay.get(i).getRating() + ") ===> ";
    }
    out += "\n" + dest.getName();
    return out;
  }

  private int countEdges(){
    int edgeQuant = 0;
    for(Movie m : mapping.keySet()){
      int temp = mapping.get(m).size();
        edgeQuant += (temp*(temp-1))/2; //Stk1100
    }
    return edgeQuant;
  }

  private int countNodes(){
    return actorMapping.size();
  }

  private void readMovies(){
    try{
      BufferedReader br = new BufferedReader(new FileReader("movies.tsv"));
      String line = br.readLine();
      while(line != null){
        String [] movieinformation;
        movieinformation = line.split("\t");
        String id = movieinformation[0];
        String name = movieinformation[1];
        float rating = Float.parseFloat(movieinformation[2]);
        Movie mn = new Movie(id,name,rating);
        movieMapping.put(id,mn);
        line = br.readLine();
      }
    }
    catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

  private void readNodes(){
    try{
      BufferedReader br = new BufferedReader(new FileReader("actors.tsv"));
      String line = br.readLine();
      while(line != null){
        String [] actorInformation;
        actorInformation = line.split("\t");
        String actorId = actorInformation[0];
        String actorName = actorInformation[1];
        Actor actor = new Actor(actorId, actorName);
        actorMapping.put(actorId,actor);
        for(int i = 2; i < actorInformation.length; i++){
          Movie movie = movieMapping.get(actorInformation[i]);
          if(movie != null){
            actor.addMovie(actorInformation[i],movie);
            if(mapping.containsKey(movie)){
              mapping.get(movie).add(actor);
            }
            else{
              LinkedList<Actor> actorList = new LinkedList<Actor>();
              actorList.add(actor);
              mapping.put(movie,actorList);
            }
          }
        }
        line = br.readLine();
      }
    }
    catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

}

class Actor {
  String id;
  String name;
  float distance = Float.MAX_VALUE;
  boolean visited = false;
  Actor previousActor;
  Movie previousMovie;
  HashMap<String,Movie> movies = new HashMap<>();
  Actor(String id, String name){
    this.id = id;
    this.name = name;
  }
  public void reset(){
    distance = Integer.MAX_VALUE;
    visited = false;
    previousActor = null;
    previousMovie = null;
  }
  public void setPrevious(Movie m, Actor a){
    previousMovie = m;
    previousActor = a;
  }
  public Actor getPreviousActor(){
    return previousActor;
  }
  public Movie getPreviousMovie(){
    return previousMovie;
  }
  public String getName(){
    return name;
  }
  public String getId(){
    return id;
  }
  public void addMovie(String id, Movie movie){
    movies.put(id,movie);
  }
  public HashMap<String,Movie> getMovies(){
    return movies;
  }
  public void setDistance(float i){
    distance = i;
  }
  public float getDistance(){
    return distance;
  }
  public void visited(){
    visited = true;
  }
  public boolean isVisited(){
    return visited;
  }

}

class Movie{
  String id;
  String name;
  float rating;
  boolean visited = false;
  Movie(String id, String name, float rating){
    this.id = id;
    this.name = name;
    this.rating = rating;
  }
  public void visited(){
    visited = true;
  }
  public boolean isVisited(){
    return visited;
  }
  public String getName(){
    return name;
  }
  public String getId(){
    return id;
  }
  public float getRating(){
    return rating;
  }
}

class distanceComparator implements Comparator<Actor> {
  @Override
  public int compare(Actor x, Actor y) {
    if (x.getDistance() < y.getDistance()) return -1;
    if (x.getDistance() > y.getDistance()) return 1;
    return 0;
  }
}
