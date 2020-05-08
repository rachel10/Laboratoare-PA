public class Board {
    private int[][] table;
    Board(){
        this.table=new int[15][15];

    }

    public int[][] getTable(){
        return table;
    }

    public void setMove(int row, int column,int player){
        table[row][column]=player;
    }
}
