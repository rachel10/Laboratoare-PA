public class Game {
    private String player1;
    private String player2 = null;
    private Board board;
    private int turn = 1;
    private boolean win=false;

    private String raportHtml;

    Game(String player1) {
        this.player1 = player1;
        this.board = new Board();
        GamesController.getInstance().addGame(this);
        raportHtml="<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>Game</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" ;
    }

    public void addPlayer1ToHtml(String username){
        raportHtml+="<h2>Player1: "+username+"</h2>";
    }
    public void addPlayer2ToHtml(String username){
        raportHtml+="<h2>Player2: "+username+"</h2>"+"<br> <h4> All moves: </h4><br>";
    }

    public void addMoveToHtml(int row, int column,int player){
        raportHtml+="Player"+player+": ["+row+"]"+"["+column+"]<br>";
    }

    public void addWinnerToHtml(int player){
        raportHtml+="<h2>WINNER: Player"+player+"</h2><br></body></html>";
    }
    public String getRaportHtml(){
        return raportHtml;
    }
    public synchronized int getTurn() {
        return turn;
    }

    public synchronized void setTurn(int turn) {
        this.turn = turn;
    }

    public synchronized boolean isWin() {
        return win;
    }

    public synchronized void setWin(boolean win) {
        this.win = win;
    }

    public synchronized void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getTable() {
        String table = ";n;";
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                table += board.getTable()[i][j] + " ";
            }
            table += " ;n;";
        }
        return table;
    }

    public String getPlayer1() {
        return player1;
    }

    public synchronized String getPlayer2() {
        return player2;
    }

    public void setMove(int row, int column, int player) {
        board.setMove(row, column, player);
    }

    public int validMove(int row, int column) {
        if (row > 14 || row < 0) {
            return -1;
        } else {
            if (column > 14 || column < 0) {
                return -2;
            } else {
                if (board.getTable()[row][column] != 0) {
                    return -3;
                }
            }
        }
        return 1;
    }

    public void setPlayersInTable(int forBlack, int forWhite) {
        int black1i=-1,black1j=-1,black2i=-1,black2j=-1,whitei=-1,whitej=-1;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board.getTable()[i][j] == -1) {
                    board.setMove(i, j, forBlack);
                    if(black1i==-1){
                        black1i=i;
                        black1j=j;
                    }else {
                        black2i=i;
                        black2j=j;
                    }
                } else {
                    if (board.getTable()[i][j] == -2) {
                        board.setMove(i, j, forWhite);
                        whitei=i;
                        whitej=j;
                    }
                }
            }
        }
        this.addMoveToHtml(black1i,black1j,forBlack);
        this.addMoveToHtml(whitei,whitej,forWhite);
        this.addMoveToHtml(black2i,black2j,forBlack);
    }

    public boolean victory(int row, int column) {
        int player = board.getTable()[row][column];
        int contor = 0;
        int linie = row - 1, coloana = column;
        while (linie >= 0 && board.getTable()[linie][coloana] == player ) {
            contor++;
            linie--;
        }
        linie = row + 1;
        while ( linie < 15 && board.getTable()[linie][coloana] == player ) {
            contor++;
            linie++;
        }
        if (contor + 1 >= 5) {
            return true;
        }
        contor = 0;
        linie = row;
        coloana = column - 1;

        while (coloana >= 0 && board.getTable()[linie][coloana] == player ) {
            contor++;
            coloana--;
        }

        coloana = column + 1;
        while (coloana < 15 && board.getTable()[linie][coloana] == player) {
            contor++;
            coloana++;
        }
        if (contor+1>=5){
            return true;
        }

        contor=0;
        linie=row-1;
        coloana=column-1;
        while (linie>=0 && column>=0 && board.getTable()[linie][coloana] == player){
            contor++;
            coloana--;
            linie--;
        }
        linie=row+1;
        coloana=column+1;
        while (linie<15 && column<15 && board.getTable()[linie][coloana] == player){
            contor++;
            coloana++;
            linie++;
        }
        if (contor+1>=5){
            return true;
        }

        contor=0;
        linie=row-1;
        coloana=column+1;
        while (linie>=0 && column<15 && board.getTable()[linie][coloana] == player){
            contor++;
            coloana++;
            linie--;
        }
        linie=row+1;
        coloana=column-1;
        while (linie<15 && column>=0 && board.getTable()[linie][coloana] == player){
            contor++;
            coloana--;
            linie++;
        }

        if (contor+1>=5){
            return true;
        }
        return false;
    }
}
