package controller.ModelTabs;

public class UserTest {
    private String name;
    private String prenom;

    public UserTest(String name, String prenom) {
        this.name = name;
        this.prenom = prenom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
