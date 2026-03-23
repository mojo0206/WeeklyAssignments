class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

public class WeeklyAssignments {

    // 🔹 Merge Sort (ascending, stable)
    static void mergeSort(Trade[] trades) {
        if (trades.length < 2) return;
        mergeSortHelper(trades, 0, trades.length - 1);
    }

    static void mergeSortHelper(Trade[] trades, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortHelper(trades, left, mid);
        mergeSortHelper(trades, mid + 1, right);
        merge(trades, left, mid, right);
    }

    static void merge(Trade[] trades, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++) L[i] = trades[left + i];
        for (int i = 0; i < n2; i++) R[i] = trades[mid + 1 + i];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                trades[k++] = L[i++];
            } else {
                trades[k++] = R[j++];
            }
        }
        while (i < n1) trades[k++] = L[i++];
        while (j < n2) trades[k++] = R[j++];
    }

    // 🔹 Quick Sort (descending, in-place)
    static void quickSort(Trade[] trades) {
        quickSortHelper(trades, 0, trades.length - 1);
    }

    static void quickSortHelper(Trade[] trades, int low, int high) {
        if (low < high) {
            int pivotIndex = lomutoPartition(trades, low, high);
            quickSortHelper(trades, low, pivotIndex - 1);
            quickSortHelper(trades, pivotIndex + 1, high);
        }
    }

    static int lomutoPartition(Trade[] trades, int low, int high) {
        int pivot = trades[high].volume; // Pivot
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (trades[j].volume > pivot) { // Descending
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    // 🔹 Merge two sorted lists (ascending)
    static Trade[] mergeTwoSorted(Trade[] list1, Trade[] list2) {
        Trade[] merged = new Trade[list1.length + list2.length];
        int i = 0, j = 0, k = 0;
        while (i < list1.length && j < list2.length) {
            if (list1[i].volume <= list2[j].volume) merged[k++] = list1[i++];
            else merged[k++] = list2[j++];
        }
        while (i < list1.length) merged[k++] = list1[i++];
        while (j < list2.length) merged[k++] = list2[j++];
        return merged;
    }

    // 🔹 Total Volume
    static int totalVolume(Trade[] trades) {
        int total = 0;
        for (Trade t : trades) total += t.volume;
        return total;
    }

    // 🔹 Display
    static void displayTrades(Trade[] trades) {
        for (Trade t : trades) System.out.print(t + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        System.out.println("Original Trades:");
        displayTrades(trades);

        // Merge Sort ascending
        Trade[] mergeSorted = trades.clone();
        mergeSort(mergeSorted);
        System.out.println("\nMerge Sort (Ascending Volume):");
        displayTrades(mergeSorted);

        // Quick Sort descending
        Trade[] quickSorted = trades.clone();
        quickSort(quickSorted);
        System.out.println("\nQuick Sort (Descending Volume):");
        displayTrades(quickSorted);

        // Merge two sorted lists (example: morning + afternoon)
        Trade[] morning = {new Trade("t1", 100), new Trade("t2", 300)};
        Trade[] afternoon = {new Trade("t3", 200), new Trade("t4", 400)};
        Trade[] merged = mergeTwoSorted(morning, afternoon);
        System.out.println("\nMerged Morning + Afternoon Sorted (Ascending):");
        displayTrades(merged);

        // Total volume
        System.out.println("Total Volume: " + totalVolume(merged));
    }
}
