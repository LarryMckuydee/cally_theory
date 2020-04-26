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

    
    /** 
     * Return name
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    
    /** 
     * Return email
     * 
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    
    /** 
     * Return level
     * 
     * @return int
     */
    public int getLevel() {
        return this.level;
    }

    
    /** 
     * Return isEngaging
     * 
     * @return boolean
     */
    public boolean getIsEngaging() {
        return this.isEngaging;
    }

    
    /** 
     * Set name with given name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * Set email with given email
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /** 
     * Set level with given level
     * 
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    
    /** 
     * Set isEngaging with given isEngaging
     * 
     * @param isEngaging
     */
    public void setIsEngaging(boolean isEngaging) {
        this.isEngaging = isEngaging;
    }

    /** 
     * Set isEngaging to true
     */
    public void engaging() {
        setIsEngaging(true);
    }

    /** 
     * Set isEngaging to false
     */
    public void disengaged() {
        setIsEngaging(false);
    }
}