public class UserScore {
    private String username;
    
    private Integer score;
    
    public UserScore(String username, Integer score){
        this.username = username;
        this.score = score;
    }
    public Integer getScore() {
        return score;
    }
    public String getUsername() {
        return username;
    }
}
