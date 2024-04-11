class Solution {

    //Here we have to filla all the pixels (which are connected) with same colur from starting row and col 



    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    boolean vis[][]= new boolean[image.length][image[0].length];  //These arr helps to keep track which are visited or not so that we dont eneter that same place agin

    int orgcol=image[sr][sc];   //These are  the color which we need to fill ,we have to find that color by our starting point and col 
     helper(image ,sr,sc,color,vis,orgcol); //call this and get answer
     return image;

    }

    public void helper(int [][]image ,int sr,int  sc ,int color,boolean vis[][],int orgcol){
     if(sr<0 || sc<0 || sr>=image.length || sc>=image[0].length || vis[sr][sc]|| image[sr][sc]!=orgcol){  //if we exceed boundary or vis is true or we dnt get the color we want to change  we return
         return;
     }

     //if found the color (1 change the color. 2. Make vis true for that loctaion)
     if(image[sr][sc]==orgcol){
         image[sr][sc]=color;
         vis[sr][sc]=true;
         //keep doing it recursively for all the 4 direction 
        helper(image,sr+1,sc,color,vis,orgcol);
        helper(image,sr,sc+1,color,vis,orgcol);
        helper(image,sr-1,sc,color,vis,orgcol);
        helper(image,sr,sc-1,color,vis,orgcol);



     }
    
    

    }
}