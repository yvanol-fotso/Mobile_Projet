package model;

public class User {
    private String mFirstName;


    public User(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    // Ajout des accesseur en consultation et en modification

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }
}
