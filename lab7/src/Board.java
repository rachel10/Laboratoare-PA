import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int numberOfTokens;
    private List<Token> tokenList;

    public Board(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
        tokenList = new ArrayList<>();
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        if (tokenList.size()==numberOfTokens) {
            this.tokenList = tokenList;
        }else
        {
            this.tokenList=tokenList;
            while(this.tokenList.size()<numberOfTokens)
            {
                this.tokenList.add(new Token(0));
            }
        }
    }

    private int randomInIntreval(int min, int max) {
        Random rand = new Random();
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return rand.nextInt((max - min) + 1) + min;
    }

    public synchronized Token getToken(){
        if(numberOfTokens>0) {
            int index = randomInIntreval(0, numberOfTokens - 1);
            Token token = tokenList.get(index);
            tokenList.remove(index);
            numberOfTokens = tokenList.size();
            return token;
        }
        else {
            return null;
        }
    }
}
