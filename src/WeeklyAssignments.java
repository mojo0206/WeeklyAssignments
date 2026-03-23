import java.util.Arrays;

public class WeeklyAssignments {

    // 🔹 Linear Search for exact match
    static int linearSearch(int[] risks, int target) {
        int comparisons = 0;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear Search: Found at index=" + i + ", Comparisons=" + comparisons);
                return i;
            }
        }
        System.out.println("Linear Search: Not Found, Comparisons=" + comparisons);
        return -1;
    }

    // 🔹 Binary Search for exact match
    static int binarySearch(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int comparisons = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                System.out.println("Binary Search: Found at index=" + mid + ", Comparisons=" + comparisons);
                return mid;
            } else if (risks[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        System.out.println("Binary Search: Not Found, Comparisons=" + comparisons);
        return -1;
    }

    // 🔹 Binary Search Floor (largest ≤ target)
    static int binaryFloor(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int floor = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                floor = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else high = mid - 1;
        }
        System.out.println("Binary Floor: " + floor + ", Comparisons=" + comparisons);
        return floor;
    }

    // 🔹 Binary Search Ceiling (smallest ≥ target)
    static int binaryCeil(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int ceil = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                ceil = risks[mid];
                break;
            } else if (risks[mid] > target) {
                ceil = risks[mid];
                high = mid - 1;
            } else low = mid + 1;
        }
        System.out.println("Binary Ceiling: " + ceil + ", Comparisons=" + comparisons);
        return ceil;
    }

    public static void main(String[] args) {
        int[] riskBands = {10, 25, 50, 100}; // sorted
        int threshold = 30;

        System.out.println("Risk Bands: " + Arrays.toString(riskBands));

        // Linear search (unsorted)
        linearSearch(riskBands, threshold);

        // Binary search (sorted)
        binarySearch(riskBands, threshold);

        // Floor and Ceiling
        binaryFloor(riskBands, threshold);
        binaryCeil(riskBands, threshold);
    }
}