import java.util.*;
public class Create_Graph{
    
     static class Edge{
      int src;
      int des;
      int wt;
      
      public Edge(int s,int d,int w){
        this.src=s;
        this.des=d;
        this.wt=w;

      }

}

//This will print 123456 like that for look structure in copy


//This algorithm contains  a quen , a boolean array and our old arraylist
//We add first elemnt ie 0 in the q and then remove the first elemnt ,if elemt is true then nothing do ,but if flase print it and mark it true and filla ll the neigbours into quen.



public static void BFS(ArrayList<Edge>graph[]){
  boolean arr[]=new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if(!arr[i]){    //boolean array if true dont do anything ,but if false means not visited
        BFSutil(graph,arr);
      }
    }
}
public static void BFSutil(ArrayList<Edge>[] graph,boolean arr[]){ //O(n)  or O(V+E) ,Whatever is bigger v or e becomes the tc 
 Queue<Integer>q = new LinkedList<>();
//  Boolean arr[] = new Boolean[graph.length];
 q.add(0);

 

 while(!q.isEmpty()){
          int r=q.remove();
 if(!arr[r]){
   System.out.println(r);
   arr[r]=true;
   for (int i = 0; i < graph[r].size(); i++) {
      Edge e = graph[r].get(i);
      q.add(e.des);
   }

 }
}
 

  
 };

 //TC:- O(V+E)

 
 public static void DFS(ArrayList<Edge> graph[]){
  boolean vis[]= new boolean[graph.length];
  for (int i = 0; i < graph.length; i++) {
    DFSutil(graph,i,vis);
  }
 }
public static void DFSutil(ArrayList<Edge>graph[] ,int curr, boolean vis[]){   //use current also
   System.out.print(curr);
   vis[curr]=true;
   
   for (int i = 0; i < graph[curr].size(); i++) {
      Edge e = graph[curr].get(i);
      if(!vis[e.des]){
        DFSutil (graph,e.des,vis);
      }
    
   } 



}


//Has path used recurssion 

public static boolean haspath(ArrayList<Edge> graph[], int src ,int des ,boolean vis[]){
  if(src ==des){
    return true;
  }
   vis[src]=true;
  for (int i = 0; i < graph[src].size(); i++) {
    Edge e = graph[src].get(i);
    if(!vis[src] && haspath(graph, e.des, des, vis))
       return true;

  }
  return false;

}





//DETect cycle in undirected graph
//TC same as dfs 
public static boolean detectcycle(ArrayList<Edge> graph[]){
  boolean vis[]=new boolean[graph.length];
  for (int i = 0; i < graph.length; i++) {
     if(!vis[i]){
       if( detectcycleutil(graph,vis,i,-1)){
        return true;
       }
     }
  }
  return false; 

}
public static boolean detectcycleutil(ArrayList<Edge> graph[],boolean vis[],int curr,int par){
   
 vis[curr]=true;
 for (int i = 0; i < graph[curr].size(); i++) {
  Edge e = graph[curr].get(i);
  //case 3
  if (!vis[e.des]){
    if( detectcycleutil(graph, vis, e.des, curr)){
    return true;
    }
  }

  //case 1
  else if(vis[e.des] && e.des!=par){
    return true;

  }
   //case 2 :do nothing
  
 }

return false;


}

//Biparted
public static boolean isBipartite(ArrayList<Edge>graph[]){
  int col[]=new int[graph.length];
 Arrays.fill(col, -1); //no color


 Queue<Integer> q = new LinkedList<>();

 for (int i = 0; i < graph.length; i++) {
  if(col[i]==-1){  //Simple BFS
    q.add(i);
    col[i]=0;
   while(!q.isEmpty()){
      int curr =q.remove();
      for (int j = 0; j < graph[curr].size(); j++) {
        Edge e =graph[curr].get(j);
        if(col[e.des]==-1){
            int nextcol=col[curr]==0?1:0;
            col[e.des]=nextcol;
            q.add(e.des);



        }else if (col[e.des]==col[curr]){
          return false;
        }
        
      }
 





    }

  }
  
 }
 return true;


}






//CYCLE IN DIRECTED GRAPH
public static boolean iscycle(ArrayList<Edge>[] graph ){
boolean vis[] =new boolean[graph.length];
boolean stack[]= new boolean[graph.length];



for (int i = 0; i < graph.length; i++) {
   if(!vis[i]){
    if(iscycleutil(graph,i,vis,stack)){
      return true;
    }
   }
  
}
return false;


}

public static boolean iscycleutil(ArrayList<Edge>[] graph,int curr,boolean vis[],boolean stack[]){

vis[curr]=true;
stack[curr]=true;


for (int i = 0; i < graph[curr].size(); i++) {
   Edge e = graph[curr].get(i);
   if(stack[e.des]){
    return true;

   }
   if(!vis[e.des] && iscycleutil(graph, e.des, vis, stack)){
    return true;
   }
  
}
stack[curr]=false;
return false;


}


//topological sort
public static void topsort(ArrayList<Edge>graph[]){
  boolean vis[]= new boolean[graph.length];
  Stack<Integer> s = new Stack<>();

 for (int i = 0; i < graph.length; i++) {
  
   if(!vis[i]){
    topsortutil(graph,i,vis,s);
   }  


 }
 while (!s.empty()) {
  System.out.println(s.pop());
  
 }



}
public static void topsortutil(ArrayList<Edge>graph[],int curr,boolean vis[],Stack<Integer> s){
  vis[curr]=true;
  for (int i = 0; i < graph[curr].size(); i++) {
    Edge e = graph[curr].get(i);
    if(!vis[e.des]){
      topsortutil(graph, e.des, vis, s);
    }
    s.push(curr);
  }






}

//toopological sort using bfs
//



//calculating the indegree
public static void indegree(ArrayList<Edge> graph[],int indegree[]){
  for (int i = 0; i < graph.length; i++) {
    int v= i;
    for (int j = 0; j < graph[v].size(); j++) {
      Edge e = graph[v].get(j);
      indegree[e.des]++;  
    }
    
    


  }
}
public static void topsortBFS(ArrayList<Edge> graph []){
int indegree[]=new int[graph.length];
indegree(graph,indegree);
Queue<Integer> q =new LinkedList<>();


for (int i = 0; i < indegree.length; i++) {
  if(indegree[i]==0){
    q.add(i);
  }
  
}
//bfs
while (!q.isEmpty()) {
  int curr=q.remove();
  System.out.println(curr);
  for (int i = 0; i < graph[curr].size(); i++) {
    Edge e=graph[curr].get(i);
    indegree[e.des]--;
    if(indegree[e.des]==0){
      q.add(e.des);
    }  
    
  }
}
}
//All path from source
public static void printallpath(ArrayList<Edge> graph[],int src ,int des,String path){
  if(src==des){
    System.out.println(path+des);
    return;
  }
  for (int i = 0; i < graph[src].size(); i++) {
    Edge e =graph[src].get(i);
    printallpath(graph, e.des, des, path+src);
    
  }
}
//Sorting on the base of path
static class pair implements Comparable<pair>{
  int n;
  int path;



public pair(int n,int path){
  this.n=n;
  this.path=path;
}
@Override
public int compareTo(pair p2){
  return this.path-p2.path; //path based soorting for my pairs
}

}
 
//dijkastra's algorithm
//TC:-V + ElogV (this comes bcz of internal sorting inside the priority q.)
//src here in this case 0
public static void dijkastra(ArrayList<Edge> graph[],int src){
  int dist[]= new int[graph.length];
  for (int i = 0; i < graph.length; i++) {
    if(i!=src){   //in first case i is equal to src  ,and its distance is 0 
       dist[i]=Integer.MAX_VALUE;
    }
    
  }
  boolean vis[]=new boolean[graph.length];
  PriorityQueue<pair> pq = new PriorityQueue<>();
  pq.add(new pair(src, 0));
  //loop
  while ((!pq.isEmpty())) {
    pair curr=pq.remove();

    if (!vis[curr.n]){
     vis[curr.n]=true;
     //neigbours
     for (int i = 0; i < graph[curr.n].size(); i++) {
      Edge e =graph[curr.n].get(i);
      int u=e.src;
      int v=e.des;
      int wt=e.wt;

      if(dist[u]+wt < dist[v]){
        dist[v]=dist[u] + wt;
        pq.add(new pair(v, dist[v]));

      }
      
     }



    }
  }
//print all source to vertices shortest dist
for (int i = 0; i < dist.length; i++) {
  System.out.println(dist[i]);
  
}


}//bellman ford
public static void bellmanford(ArrayList<Edge> graph[],int src){
   int dis[]= new int[graph.length];
   for (int i = 0; i < dis.length; i++) {
        if(i!=src){
          dis[i]=Integer.MAX_VALUE;
        }    
       }
 int V = graph.length;
 //algo
 for (int i = 0; i < V-1; i++) {
   //TC:-   edged -O(E)  //The time complexity is O(e) not on square bcz we are finding all the edges so the tc is that and we are not looping just going throgh all the edges 

    for (int j = 0; j < graph.length; j++) {
        for (int k = 0; k < graph[j].size(); k++) {
              Edge e = graph[j].get(k);
              //u,v,wt
              int u = e.src;
              int v= e.des;
              int wt=e.wt;
              //releaxation
              if(dis[u]!= Integer.MAX_VALUE && dis[u]+wt <dis[v]){
                dis[v]=dis[u]+wt;

              }
      
     }
    
   }
  
 }
 
//There is also an another way to crate graph fo this which require two loop only but doesnt make any differnce ,if u think go to graph part 4 


}
//THsi prims algo conatins
 static class pairr implements Comparable<pairr>{
  int v;
  int cost; 

  public pairr(int v ,int c){
    this.v=v;
    this.cost=c;

  }
  @Override
  public int compareTo(pairr p2){
    return this.cost- p2.cost ;  //ascending
  }

 }
//prims algo to get mst
public static void prims(ArrayList<Edge> graph[]){
  boolean vis[]= new  boolean[graph.length];
  PriorityQueue<pairr> pq = new PriorityQueue<>();
  pq.add(new pairr(0, 0));
  int finalcost =0; //MMST cost /total min cost
  

  while (!pq.isEmpty()) {
     pairr curr=pq.remove();
     if(!vis[curr.v]){
         vis[curr.v]=true;
         finalcost+=curr.cost;

         for (int i = 0; i < graph[curr.v].size(); i++) {
              Edge e = graph[curr.v].get(i);
              pq.add(new pairr(e.des, e.wt));
          
         }



     }
    
  }





System.out.println(finalcost);
}

 



 
 public static void main(String[] args) {
/*            (5)
          0 ------- 1
               (1) / \  (3)
                  /   \
                 2-----3  
 *               | (1)
 *            (2)|
 *               |
 *               4
 * 
 */
int V=5;
ArrayList<Edge>[] graph=new ArrayList[V];   //null --> empty arraylist


for(int i=0 ;i<V ;i++){
    graph[i]=new ArrayList<>();

}
//0th vertex
graph[0].add(new Edge(0, 1, 5));


//1th vertex
graph[1].add(new Edge(1, 0, 5));
graph[1].add(new Edge(1, 2, 1));
graph[1].add(new Edge(1, 3, 3));

//2 vertex 
graph[2].add(new Edge(2, 1, 1));
graph[2].add(new Edge(2, 3, 1));
graph[2].add(new Edge(2, 4, 2));
//3rd vertex
graph[3].add(new Edge(3,1,3) );
graph[3].add(new Edge(3, 2, 1));
//4 vertex

graph[4].add(new Edge(4, 2, 2));

//2's neighbours
for(int i=0;i<graph[2].size();i++){
  Edge s = graph[2].get(i);
  System.out.println(s);
}






 

 
//bfs call
BFS(graph);




 }




 




 




 
}
 



