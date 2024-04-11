 import java.util.*;

public class Cheapest_flight_within_k_stops {
    
    static class Edge{
        int src;
        int des;
        int wt;

    
    public Edge (int s ,int d,int wt ){
        this.src=s;
        this.des=d;
        this.wt=wt;


    }


}
 public static void Creategraph(int flight[][],ArrayList<Edge> graph []){
    for (int i = 0; i < graph.length; i++) {
        graph[i]= new ArrayList<>();
    }
    for (int i = 0; i < flight.length; i++) {
        int src =flight[i][0];
        int des=flight[i][1];
        int wt=flight[i][2];
        Edge e = new Edge(src, des, wt);

        graph[src].add(e); //make changes

        
    }
   

 }
 static class info{
    int v;
    int cost;
    int stops;

    public info(int v ,int c,int k){
       this.v=v;
       this.cost=c;
       this.stops=k;
    


    }
 }
 public static int cheapflight(int flight[][], int src ,int des,int k,int n){
   ArrayList<Edge> graph[] = new ArrayList[n];
  Creategraph(flight, graph);
   

 Queue<info> q = new LinkedList<>() ;
 int dis[]= new int[n];
  
 for (int i = 0; i < n; i++) {
    if(src!=des){
        dis[i]=Integer.MAX_VALUE;

    }
 }

q.add(new info(src, 0,0));  //src why

while (!q.isEmpty()) {

    

     info curr= q.remove();
     if(curr.stops>k){
        break;
     }
     for (int i = 0; i < graph[curr.v].size(); i++) {
        Edge e = graph[curr.v].get(i);
        int u =e.src;
        int v= e.des;
        int wt = e.wt;
        if (curr.cost+wt <dis[v] && curr.stops<=k){  //not written dis[u]+wt 
            dis[v]=dis[u]+wt;
            q.add(new info(v, dis[v], curr.stops+1));


        }  
     }
}


if(dis[des]==Integer.MAX_VALUE){
    return -1;
} else{
    return dis[des];
}



    
 











 }
public static void main(String[] args) {
    int n=4;
    int flight[][]={{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
    int src=0, des=3, k=1;
    // ArrayList<Edge> graph = new ArrayList<>(n);
   System.out.println(cheapflight(flight, src, des, k,n)); 
}
}
