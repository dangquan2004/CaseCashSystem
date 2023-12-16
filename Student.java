public class Student {

    /** Student name */
    private String name;

    /** Student's balance */
    private int balance;

    /** Constructor to initialize student */
    public Student(String name, int balance){
        this.name = name;
        this.balance = balance;
    }

    /**
     * Get the balance of the student
     * @return the balance of the student
     */
    public int getBalance(){
        return balance;
    }

    /**
     * Update the balance of a student
     * @param newAmount new balance of the student
     */
    public void updateBalance(int newAmount){
        balance = newAmount;
    }

    /**
     * Get the name of the student
     * @return name of the student
     */
    public String getName(){
        return name;
    }
}
