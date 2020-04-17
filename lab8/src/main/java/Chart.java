public class Chart {
    private int idAlbum;
    private int votes;

    public Chart() {
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "idAlbum=" + idAlbum +
                ", votes=" + votes +
                '}';
    }
}
