package app.models;

public class Employee {
    private String name;
    private String email;
    private int level;
    private boolean isEngaging;

    public Employee(String name, String email, int level) {
        this.name = name;
        this.email = email;
        this.level = level;
        this.isEngaging = false;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean getIsEngaging() {
        return this.isEngaging;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setIsEngaging(boolean isEngaging) {
        this.isEngaging = isEngaging;
    }

    public void engaging() {
        setIsEngaging(true);
    }

    public void disengaged() {
        setIsEngaging(false);
    }
}