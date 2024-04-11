import java.util.*;
public class kruskals_algo{

  static class Edge implements Comparable<Edge>{
  int src;
  int des;
  int wt;
    public Edge(int s,int d,int w){
        this.src=s;
        this.des=d;
        this.wt=w;
    }

        @Override
        public int compareTo(Edge e2){
           return this.wt-e2.wt;     //sort in ascending order
        }
    }



   static void Creategraph(ArrayList<Edge> edges){

     edges.add(new Edge(0, 1, 10));
     edges.add(new Edge(0, 2, 15));
     edges.add(new Edge(0, 3, 30));
     edges.add(new Edge(1, 3, 40));
     edges.add(new Edge(2, 3, 50));




   }
   static int n=4;
   static int par[]= new int[n];
   static int rank[]= new int[n];

   public static void init(){
    for (int i = 0; i < n; i++) {
       par[i]=i;  //Initially everyone is there own parent 

        
    }
 }
    


   public static int find(int x){
    if(x==par[x]){
      return x;
    }
     return par[x]=find(par[x]);   //This is path compression ,make code much faster by making path wide ,see copy for more detail
     

  }
  public static void union(int a ,int b){
    int parA = find(a);
    int parB= find(b);

    if(rank[parA]==rank[parB]){
        par[parB]=parA;
        rank[parA]++;
    }
    else if (rank[parA]<rank[parB]){
        par[parA]=parB++;

    }else{
        par[parB]=parA;
    }




   } 






public static void kruskals(ArrayList<Edge> edge ,int V){
    init();
    Collections.sort(edge);
  int mincost=0;
  int count=0;
  for (int i = 0; count < V-1; i++) {  //we can also write i<edge.length but this is better than that 
     //we write V-1 bcz there is always vertex-1 no of minimum edges required to connect all vertex ,so to count edge we write vertex-1 edges 
    Edge e = edge.get(i);
    int parA=find(e.src);
    int parB= find(e.des);
    if(parA!=parB){    //if there is cycle we dont want to enetr and increase the no of edges 
        union(e.src, e.des);  //this helps too make the graph with mst
        mincost+=e.wt;
        count++;     

    }
  }
  System.out.println(mincost);

}






public static void main(String[] args) {
    ArrayList<Edge> edge = new ArrayList<>();
    int v=4;
    Creategraph(edge);
    kruskals(edge, v);
      
     
}
}

