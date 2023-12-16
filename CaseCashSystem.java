
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that simulate the Case Cash System
 * @author Quan Tran
 * @refereces: Professor Erman Ayday Lecture's Slides
 */
public class CaseCashSystem {

    /** The list of student with accounts created */
    private List<Student> students;

    /**
     * Initialize the case cash system
     */
    public CaseCashSystem(){
         this.students = new ArrayList<Student>();
    }

    /**
     * A helper method to return the student list
     * @return the student list
     */
    protected List<Student> getStudentsList(){
        return students;
    }

    /**
     * A method to run the commands in the student list, such as sorting, initialize a student, get balance,
     * @param commands commands to simulate the system
     * @return the output of each command
     */
    public List<String> runSimulation(List<String> commands){

        // Clear the student list anytime the method is called
        getStudentsList().clear();

        // Create a list to store the ouput
        List<String> outputList = new ArrayList<>();
        // Looping through the list that contains commands
        for (int index = 0; index < commands.size(); index++){
            // get the arguements at a certain index
            String arguments = commands.get(index);
            // Get the information and store it in an array
            String[] strArray = arguments.split(", ");
            // If the first index of the array is "INIT", call the method init()
            if (strArray[0].equals("INIT")){
                boolean outputInit =  init(strArray[1], Integer.parseInt(strArray[2]));
                outputList.add(outputInit+"");
            }
            // If the first index of the array is "GET", call the method getBalance()
            if (strArray[0].equals("GET")){
                int outputGetBalance = getBalance(strArray[1]);
                outputList.add(outputGetBalance+"");
            }

            // If the first index of the array is "TRANSFER", call the method transfer()
            if (strArray[0].equals("TRANSFER")){
                Student student1 = null;
                Student student2 = null;
                for (int index1 = 0; index1 < getStudentsList().size(); index1++){
                    if (strArray[1].equals(getStudentsList().get(index1).getName())){
                        student1 = getStudentsList().get(index1);
                    }
                    else if (strArray[2].equals(getStudentsList().get(index1).getName())){
                        student2 = getStudentsList().get(index1);
                    }
                }
                boolean outputTransfer = transfer(student1, student2, Integer.parseInt(strArray[3]));
                outputList.add(outputTransfer+"");
            }
            // If the first index of the array is "DEPOSIT", call the method deposit()
            if (strArray[0].equals("DEPOSIT")){
                Student studentA = null;
                for (int index1 = 0; index1 < getStudentsList().size(); index1++) {
                    if (strArray[1].equals(getStudentsList().get(index1).getName())) {
                        studentA = getStudentsList().get(index1);
                    }
                }
                boolean outputDeposit = deposit(studentA, Integer.parseInt(strArray[2]));
                outputList.add(outputDeposit+"");
            }
            // If the first index of the array is "WITHDRAWAL", call the method withdrawal()
            if (strArray[0].equals("WITHDRAWAL")){
                Student studentB = null;
                for (int index2 = 0; index2 < getStudentsList().size(); index2++){
                    if (strArray[1].equals(getStudentsList().get(index2).getName())){
                        studentB = getStudentsList().get(index2);
                    }
                }
                boolean outputWithDraw = withdrawal(studentB, Integer.parseInt(strArray[2]));
                outputList.add(outputWithDraw+"");
            }
            // If the first index of the array is "SORT" and the second index is "name",  call the method sortName()
            if (strArray[0].equals("SORT") && strArray[1].equals("name")){
                List<String> outputSortName = sortName();
                outputList.add(outputSortName.toString());
            }
            // If the first index of the array is "SORT" and the second index is "balance",  call the method sortBalance()
            if (strArray[0].equals("SORT") && strArray[1].equals("balance")){
                List<String> outputSortBalance = sortBalance();
                outputList.add(outputSortBalance.toString());
            }

        }
        // Return the output from the commands
        return outputList;
    }

    /**
     * A method to initialize the new student
     * @param name the name of student to be initialized
     * @param initialBalance the initial balance of the newly initialized student
     * @return false if the initial balance is negative or the student name existed in the list
     */
    public boolean init(String name, int initialBalance){
        if (initialBalance < 0) return false;

        // Check if the student has already existed in the list
        if (getStudentsList() != null){
            for (int index = 0; index < getStudentsList().size(); index++){
                Student existedStudent = getStudentsList().get(index);
                if (existedStudent.getName().equals(name)) {
                    return false;
                }
            }
        }
        // If the student does not exist in the list, create a new student
        Student newStudent = new Student(name, initialBalance);
        // Add the newly created student to the student list
        getStudentsList().add(newStudent);
        return true;

    }

    /**
     * A method to get the balance of the existing student
     * @param name the name of the students to be retrieved their balance
     * @return the current balance of the student
     */
    public int getBalance(String name){

        for (int index = 0; index < getStudentsList().size(); index++){
            Student existedStudent = getStudentsList().get(index);
            if (existedStudent.getName().equals(name)) return existedStudent.getBalance();
        }
        return -1; // The student does not exist
    }

    /**
     * A method that deposit a certain amount to the student
     * @param student the student
     * @param amount the amount to be deposited
     * @return
     */
    public boolean deposit(Student student, int amount){
        // If the deposit is negative, return false
        if (amount < 0){
            return false;
        }
        else {
            // Get the current balance of the student
            int currentBalance = student.getBalance();
            // Update the balance after deposit
            student.updateBalance(currentBalance + amount);
            return true;
        }
    }

    public boolean transfer(Student studentA, Student studentB, int amount){
        // If the transfer amount is negative, return false
        if (amount < 0) return false;

        // Get the current balance for both student A and student B
        int currentBalanceA = studentA.getBalance();
        int currentBalanceB = studentB.getBalance();

        // Assume the transfer is successful, update the balance for student A after the transfer
        studentA.updateBalance(currentBalanceA - amount);
        // Assume the transfer is successful, update the balance for student B after the transfer
        studentB.updateBalance(currentBalanceB + amount);

        // If the transfer makes either student A balance or student B balance becomes negative,
        // return to the original amount
        if (studentA.getBalance() < 0 || studentB.getBalance() < 0) {
            studentA.updateBalance(studentA.getBalance() + amount);
            studentB.updateBalance(studentB.getBalance() - amount);
            return false;
        }
        else
            return true;
    }

    /**
     * A method to withdraw a certain amount in the student balance
     * @param student the student to have their balance withdrawn
     * @param amount the amount to be withdrawn
     * @return true if withdraw successfully
     */
    public boolean withdrawal(Student student, int amount) {

        // Get the student balance
        int currentBalance = student.getBalance();
        // Assume the withdrawal is successful, update the balance after the withdrawal
        student.updateBalance(currentBalance - amount);
        // Check if the withdrawal leads to the negative balance. If negative, return the amount of withdrawal back and return false.
        // Otherwise, return true
        if (student.getBalance() < 0) {
            student.updateBalance(student.getBalance() + amount);
            return false;
        }
        else
            return true;

    }

    /**
     * A method to merge the sub arrays during merge sorting
     * @param mergedArray completely merged array
     * @param leftArray   left sub array
     * @param rightArray  right sub array
     * @references: CSDS 233 Lecture's Note
     */
    private static void merge(String[] mergedArray, String[] leftArray, String[] rightArray){
        // Initialize the index of the left sub array
        int leftIndex;
        // Initialize the index of the right sub array
        int rightIndex;
        // Initialize the index of the merged array
        int mergedIndex = 0;

        // Compare the elements of left  and right arrays, then add the smaller one into the merged array
        for (leftIndex = 0, rightIndex = 0;leftIndex < leftArray.length && rightIndex < rightArray.length;){
            // If the name in the left array comes before the initials of the right array in alphabetical order,
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) < 0){
                // Add the name to the merged array
                mergedArray[mergedIndex] = leftArray[leftIndex];
                // Increment the index of the left array only
                leftIndex++;
            }
            // If the name of the right array come before the one from the left array
            else{
                // Add that name into the merged array
                mergedArray[mergedIndex] = rightArray[rightIndex];
                // Increment the index of the right array only
                rightIndex++;
            }
            // After adding the names into the array, increment the index of the merged array
            mergedIndex++;
        }

        // Collect all the remaining initial in the left array and add it to the merged array
        for (int i = leftIndex; i < leftArray.length; i++ ){
            mergedArray[mergedIndex] = leftArray[i];
            mergedIndex++;
        }
        // Collect all the remaining initial in the right array and add it to the merged array
        for (int j = rightIndex; j < rightArray.length; j++){
            mergedArray[mergedIndex] = rightArray[j];
            mergedIndex++;
        }
    }

    /**
     * A method to sort array using merge sort
     * @param sortArray the array to be sorted
     * @references CSDS 233 Lecture's Note
     */
    private static void mergeSort(String[] sortArray){
        // Base case: if the sort array length is 0, stop the method
        if (sortArray.length > 1){
            // The left array is half the length of the array to be sorted
            String[] leftArray = new String[sortArray.length  / 2];
            // The right array is half the length of the array to be sorted.
            // It should be "sortArray.length - sortArray.length / 2" to differentiate odd and even cases
            String[] rightArray = new String[sortArray.length - sortArray.length / 2];
            // add the first half of element of the sortArray into the left array
            for (int i = 0; i < sortArray.length / 2; i++){
                leftArray[i] = sortArray[i];
            }
            // Add the second half of element of the sortArray into the right array
            int indexTracker = 0;
            for (int i = sortArray.length / 2 ;  i < sortArray.length; i++){
                rightArray[indexTracker] = sortArray[i];
                indexTracker++;
            }
            // Recursively split the left and right array. Meaning continue to split until sortArray = 0
            mergeSort(leftArray);
            mergeSort(rightArray);
            // Merge all the array into a final sorted array.
            merge(sortArray, leftArray, rightArray);
        }
    }

    /**
     * A wrapper method to sort array using mergeSort
     * @param sortArray the array to be sorted
     * @references CSDS 233 Lecture's Note
     */
    public void myMergeSort(String[] sortArray){
        mergeSort(sortArray);
    }
    /**
     * A method to sort names using merge sort
     * @return a list of sorted names in alphabetical order
     */
    public List<String> sortName(){
        // Save a temporary student name list for the output
        ArrayList<String> studentName = new ArrayList<>();
        // Create an array that stores students' names as an array
        String[] studentArrayName = new String[getStudentsList().size()];
        // Copy all the student name into the array
        for (int index =0; index < getStudentsList().size(); index++){
            studentArrayName[index] = getStudentsList().get(index).getName();
        }
        // Sort the array using merge sort
        myMergeSort(studentArrayName);
        // Add the sorted array back to the list
        for (int index = 0; index < studentArrayName.length; index ++){
            studentName.add(studentArrayName[index]);
        }
        // return the list with sorted names
        return studentName;
    }


    /**
     * A wrapper method to sort an array using quick sort
     * @param array the array to be sorted
     * @references CSDS 233 Lecture's Slide
     */
    public static void myQuickSort(int[] array){
        quickSort(array, 0, array.length - 1);
    }

    /**
     * A method to sort an array
     * @param array the array to be sorted
     * @param first the first index of the array
     * @param last  the last index of the array
     * @references CSDS 233 Lecture Slide
     */
    private static void quickSort(int[] array, int first, int last){
        // Base Case: if the first index tracker still less than the last index tracker
        // "first" advances to the right of the array, "last" advances to the left of the array
        if (first < last){
            int split = partition(array, first, last);
            // Recursively sort the array
            quickSort(array, first, split);
            quickSort(array, split + 1, last);
        }
    }

    /**
     * A method to split the array until its length is 1
     * @param arr the array to be splitted
     * @param first the first index of the array
     * @param last the last index of the array
     * @return the pivot values
     * @references: CSDS 233 Lecture's Slide
     */
    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first + last)/2];
        int i = first - 1; // index going from left to right
        int j = last + 1; // index going from right to left
        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);
            if (i < j)
                swap(arr, i, j);
            else
                return j; // arr[j] = end of left array
        }
    }

    /**
     * A helper method to swap two indices in an array
     * @param array the array which two indices would be swap
     * @param leftIndex the index to be swapped  with the right
     * @param rightIndex the index to be swapped with the left
     * @refereces: CSDS 233 Lecture Slide
     */
    private static void swap(int[] array, int leftIndex, int rightIndex){
        int temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }

    /**
     * A method to sort the balance using quicksort
     * @return a list of students' name in the ascending order of the amount of their balance
     */
    public List<String> sortBalance(){
        // A list of student name which stores the sorted order
        ArrayList<String> studentName = new ArrayList<>();
        // Initialize An array to store the balance
        int[] studentBalanceArray = new int[getStudentsList().size()];
        // Add the balance into the array
        for (int index = 0; index < getStudentsList().size(); index++){
            studentBalanceArray[index] = getStudentsList().get(index).getBalance();
        }
        // Sort the balance using quick sort
        myQuickSort(studentBalanceArray);

        // Check if the balance matches the original balance in the student's list
        for (int index = 0; index < studentBalanceArray.length; index++){
            // Retrieve the current balance in the array
            int balance = studentBalanceArray[index];
            // Looping through to compare the current balance with the original balance
            for (int index1 = 0; index1 < getStudentsList().size(); index1++){
                // if the current balance matches with a certain student's balance
                if (balance == getStudentsList().get(index1).getBalance()){
                    // Add the student name to the list
                    studentName.add(getStudentsList().get(index1).getName());
                }
            }
        }
        // Finally, return the sorted student list
        return studentName;
    }


    public static void main(String[] args) {

        CaseCashSystem newSystem = new CaseCashSystem();

        List<String> inputs = Arrays.asList(
                "INIT, Tammy, 200",
                "INIT, Kim, 300",
                "INIT, Quyen, 400",
                "SORT, name",
                "SORT, balance",
                "TRANSFER, Kim, Tammy, 110",
                "SORT, name",
                "SORT, balance"
        );

        List<String> outputs = newSystem.runSimulation(inputs);
        System.out.println(outputs);

        List<String> inputs1 = Arrays.asList(
                "INIT, Quan, 200",
                "INIT, Chau, 300",
                "INIT, Tien, 300",
                "SORT, name",
                "SORT, balance",
                "TRANSFER, Chau, Tien, 110",
                "SORT, name",
                "SORT, balance"
        );

        List<String> outputs1 = newSystem.runSimulation(inputs1);
        System.out.println(outputs1);

    }
}
