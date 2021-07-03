package client.shared;

public class Response {

    int[][] myMatrix;
    int[][] oppMatrix;
    int[][] myShips;
    int type;

    Response(int[][] matrix,int[][] oppMatrix,int[][]myShips, int type){
        this.myMatrix = matrix;
        this.oppMatrix = oppMatrix;
        this.myShips = myShips;
        this.type = type;
    }

    public int[][] getMyMatrix() {
        return myMatrix;
    }

    public int[][] getOppMatrix() {
        return oppMatrix;
    }

    public int[][] getMyShips() {
        return myShips;
    }
}
