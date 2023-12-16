
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A junit class to test the CaseCashSystem class.
 */
public class CaseCashSystemTest {

    @org.junit.jupiter.api.Test
    public void sortName() {

        CaseCashSystem newSystem = new CaseCashSystem();

        // Case 1: one-word string with capitalized initials
        List<String> unsortedName1 = Arrays.asList(
                "INIT, Tammy, 200",
                "INIT, David, 300",
                "INIT, Quyen, 400",
                "SORT, name"
        );
        List<String> output1 = newSystem.runSimulation(unsortedName1);
        String sortedName1 = output1.get(3);
        String expectedSortedName1 = "[David, Quyen, Tammy]";
        assertEquals(sortedName1, expectedSortedName1);

        // Case 2: one-word string with non-capitalized initials
        List<String> unsortedName2 = Arrays.asList(
                "INIT, quan, 200",
                "INIT, nguyen, 300",
                "INIT, john, 400",
                "SORT, name"
        );
        List<String> output2 = newSystem.runSimulation(unsortedName2);
        String sortedName2 = output2.get(3);
        String expectedSortedName2 = "[john, nguyen, quan]";
        assertEquals(sortedName2, expectedSortedName2);

        // Case 3: one-word string with the same and non-capitalized initials
        List<String> unsortedName3 = Arrays.asList(
                "INIT, james, 200",
                "INIT, nguyen, 300",
                "INIT, john, 400",
                "INIT, bob, 400",
                "SORT, name"
        );
        List<String> output3 = newSystem.runSimulation(unsortedName3);
        String sortedName3 = output3.get(4);
        String expectedSortedName3 = "[bob, james, john, nguyen]";
        assertEquals(sortedName3, expectedSortedName3);

        // Case 4: one-word string with the same and capitalized initials
        List<String> unsortedName4 = Arrays.asList(
                "INIT, James, 200",
                "INIT, Nguyen, 300",
                "INIT, John, 400",
                "INIT, Bob, 400",
                "SORT, name"
        );
        List<String> output4 = newSystem.runSimulation(unsortedName4);
        String sortedName4 = output4.get(4);
        String expectedSortedName4 = "[Bob, James, John, Nguyen]";
        assertEquals(sortedName4, expectedSortedName4);

        // Case 5: one-word string with all capitalizations as well as same initials
        List<String> unsortedName5 = Arrays.asList(
                "INIT, JAMES, 200",
                "INIT, NGUYEN, 300",
                "INIT, JOHN, 400",
                "INIT, BOB, 400",
                "SORT, name"
        );
        List<String> output5 = newSystem.runSimulation(unsortedName5);
        String sortedName5 = output5.get(4);
        String expectedSortedName5 = "[BOB, JAMES, JOHN, NGUYEN]";
        assertEquals(sortedName5, expectedSortedName5);

        // Case 6: one-word string with all capitalization as well as same initials except for one non-capitalized name
        List<String> unsortedName6 = Arrays.asList(
                "INIT, JAMES, 200",
                "INIT, nguyen, 300",
                "INIT, JOHN, 400",
                "INIT, BOB, 400",
                "SORT, name"
        );
        List<String> output6 = newSystem.runSimulation(unsortedName6);
        String sortedName6 = output6.get(4);
        String expectedSortedName6 = "[BOB, JAMES, JOHN, nguyen]";
        assertEquals(sortedName6, expectedSortedName6);

        // Case 7: two-word string with all non-capitalized and same first name initials
        List<String> unsortedName7 = Arrays.asList(
                "INIT, james marine, 200",
                "INIT, duong nguyen, 300",
                "INIT, john sea, 400",
                "INIT, bob hello, 400",
                "SORT, name"
        );
        List<String> output7 = newSystem.runSimulation(unsortedName7);
        String sortedName7 = output7.get(4);
        String expectedSortedName7 = "[bob hello, duong nguyen, james marine, john sea]";
        assertEquals(sortedName7, expectedSortedName7);

        // Case 8: two-word string with all capitalized initials and similar first name initials
        List<String> unsortedName8 = Arrays.asList(
                "INIT, James Marine, 200",
                "INIT, Duong Nguyen, 300",
                "INIT, John Sea, 400",
                "INIT, Bob Hello, 400",
                "SORT, name"
        );
        List<String> output8 = newSystem.runSimulation(unsortedName8);
        String sortedName8 = output8.get(4);
        String expectedSortedName8 = "[Bob Hello, Duong Nguyen, James Marine, John Sea]";
        assertEquals(sortedName8, expectedSortedName8);


        // Case 9: two-word string with all capitalization and similar first name initials
        List<String> unsortedName9 = Arrays.asList(
                "INIT, JAMES MARINE, 200",
                "INIT, DUONG NGUYEN, 300",
                "INIT, JOHN SEA, 400",
                "INIT, BOB HELLO, 400",
                "SORT, name"
        );
        List<String> output9 = newSystem.runSimulation(unsortedName9);
        String sortedName9 = output9.get(4);
        String expectedSortedName9 = "[BOB HELLO, DUONG NGUYEN, JAMES MARINE, JOHN SEA]";
        assertEquals(sortedName9, expectedSortedName9);

        // Case 10: two-word string with  similar first name "James"
        List<String> unsortedName10 = Arrays.asList(
                "INIT, James Marine, 200",
                "INIT, Duong Nguyen, 300",
                "INIT, James Sea, 400",
                "INIT, Bob Hello, 400",
                "SORT, name"
        );
        List<String> output10 = newSystem.runSimulation(unsortedName10);
        String sortedName10 = output10.get(4);
        String expectedSortedName10 = "[Bob Hello, Duong Nguyen, James Marine, James Sea]";
        assertEquals(sortedName10, expectedSortedName10);

        // Case 11: two-word string with  similar first name "James" and similar initials last name "M"
        List<String> unsortedName11 = Arrays.asList(
                "INIT, James Marine, 200",
                "INIT, Duong Nguyen, 300",
                "INIT, James My, 400",
                "INIT, Bob Hello, 400",
                "SORT, name"
        );
        List<String> output11 = newSystem.runSimulation(unsortedName11);
        String sortedName11 = output11.get(4);
        String expectedSortedName11 = "[Bob Hello, Duong Nguyen, James Marine, James My]";
        assertEquals(sortedName11, expectedSortedName11);

        // Case 12: two-word string with  similar first name "James", similar initials last name "M", and non-capitalized last name initials
        List<String> unsortedName12 = Arrays.asList(
                "INIT, James marine, 200",
                "INIT, Duong nguyen, 300",
                "INIT, James my, 400",
                "INIT, Bob hello, 400",
                "SORT, name"
        );
        List<String> output12 = newSystem.runSimulation(unsortedName12);
        String sortedName12 = output12.get(4);
        String expectedSortedName12 = "[Bob hello, Duong nguyen, James marine, James my]";
        assertEquals(sortedName12, expectedSortedName12);


    }

    @org.junit.jupiter.api.Test
    public void sortBalance() {
        CaseCashSystem newSystem = new CaseCashSystem();

        // Case 1: Balance in ascending order
        List<String> unsortedName1 = Arrays.asList(
                "INIT, Tammy, 200",
                "INIT, David, 300",
                "INIT, Quyen, 400",
                "SORT, balance"
        );
        List<String> output1 = newSystem.runSimulation(unsortedName1);
        String sortedName1 = output1.get(3);
        String expectedBalanceSort = "[Tammy, David, Quyen]";
        assertEquals(sortedName1, expectedBalanceSort);

        // Case 2: Student have the same balance (Failed)
        // This case fails because it copies the name twice.
        List<String> unsortedName2 = Arrays.asList(
                "INIT, Tammy, 200",
                "INIT, David, 300",
                "INIT, Quyen, 300",
                "SORT, balance"
        );
        List<String> output2 = newSystem.runSimulation(unsortedName2);
        String sortedName2 = output2.get(3);
        String expectedBalanceSort2 = "[Tammy, David, Quyen]";
        assertEquals(expectedBalanceSort2, sortedName2);


        // Case 3: Student name is sorted, but not the balance
        List<String> unsortedName3 = Arrays.asList(
                "INIT, David, 200",
                "INIT, Quyen, 500",
                "INIT, Tammy, 300",
                "SORT, balance"
        );
        List<String> output3 = newSystem.runSimulation(unsortedName3);
        String sortedName3 = output3.get(3);
        String expectedBalanceSort3 = "[David, Tammy, Quyen]";
        assertEquals(sortedName3, expectedBalanceSort3);
    }


}