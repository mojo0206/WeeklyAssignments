import java.util.Arrays;

public class WeeklyAssignments {

    // 🔹 Linear Search: first occurrence
    static int linearFirst(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear First: Index=" + i + ", Comparisons=" + comparisons);
                return i;
            }
        }
        System.out.println("Linear First: Not Found, Comparisons=" + comparisons);
        return -1;
    }

    // 🔹 Linear Search: last occurrence
    static int linearLast(String[] logs, String target) {
        int comparisons = 0;
        int index = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) index = i;
        }
        System.out.println("Linear Last: Index=" + index + ", Comparisons=" + comparisons);
        return index;
    }

    // 🔹 Binary Search: first occurrence
    static int binaryFirst(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int result = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                high = mid - 1; // look left for first occurrence
            } else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        System.out.println("Binary First: Index=" + result + ", Comparisons=" + comparisons);
        return result;
    }

    // 🔹 Binary Search: last occurrence
    static int binaryLast(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int result = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                low = mid + 1; // look right for last occurrence
            } else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        System.out.println("Binary Last: Index=" + result + ", Comparisons=" + comparisons);
        return result;
    }

    // 🔹 Count occurrences using binary first/last
    static int countOccurrences(String[] logs, String target) {
        int first = binaryFirst(logs, target);
        if (first == -1) return 0;
        int last = binaryLast(logs, target);
        return last - first + 1;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC", "accB"};

        // Linear Search
        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        // Binary Search requires sorted logs
        String[] sortedLogs = logs.clone();
        Arrays.sort(sortedLogs);
        System.out.println("\nSorted Logs: " + Arrays.toString(sortedLogs));

        binaryFirst(sortedLogs, "accB");
        binaryLast(sortedLogs, "accB");
        int count = countOccurrences(sortedLogs, "accB");
        System.out.println("Total Occurrences of accB: " + count);
    }
}