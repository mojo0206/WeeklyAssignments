import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp; // format "HH:mm"

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    void display() {
        System.out.println(id + ": Fee=" + fee + ", TS=" + timestamp);
    }

    // Optional for concise print
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class WeeklyAssignments {

    // 🔹 Bubble Sort (Ascending by fee) - small batches <= 100
    static void bubbleSort(List<Transaction> transactions) {
        int n = transactions.size();
        int swapCount = 0;
        int passCount = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    // Swap
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                    swapped = true;
                    swapCount++;

                    System.out.println("Swapped: " + transactions.get(j).id + " <-> " + transactions.get(j + 1).id);
                }
            }
            passCount++;
            if (!swapped) break; // Early termination if already sorted
        }
        System.out.println("Total passes: " + passCount + ", Total swaps: " + swapCount);
    }

    // 🔹 Insertion Sort (fee + timestamp) - medium batches 100-1000
    static void insertionSort(List<Transaction> transactions) {
        int n = transactions.size();

        for (int i = 1; i < n; i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;

            // Stable sort: fee ascending, then timestamp ascending
            while (j >= 0 &&
                    (transactions.get(j).fee > key.fee ||
                            (transactions.get(j).fee == key.fee &&
                                    transactions.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                transactions.set(j + 1, transactions.get(j));
                j--;
            }
            transactions.set(j + 1, key);
        }
    }

    // 🔹 Flag High-Fee Outliers (> $50)
    static void flagHighFee(List<Transaction> transactions) {
        System.out.println("\nHigh-Fee Outliers (> $50):");
        boolean any = false;
        for (Transaction t : transactions) {
            if (t.fee > 50) {
                System.out.println(t);
                any = true;
            }
        }
        if (!any) System.out.println("None");
    }

    // 🔹 Display Transactions
    static void displayTransactions(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 60.0, "11:00")); // high-fee outlier demo

        System.out.println("Original Transactions:");
        displayTransactions(transactions);

        // 🔹 Bubble Sort Example
        System.out.println("\nBubble Sort (Fee Ascending):");
        bubbleSort(transactions);
        displayTransactions(transactions);

        // 🔹 Insertion Sort Example
        System.out.println("\nInsertion Sort (Fee + Timestamp Ascending):");
        insertionSort(transactions);
        displayTransactions(transactions);

        // 🔹 Flag High-Fee Transactions
        flagHighFee(transactions);
    }
}