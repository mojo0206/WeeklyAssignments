import java.util.Arrays;
import java.util.Random;

class Asset {
    String symbol;
    double returnRate; // in percent
    double volatility; // optional for quick sort tie-breaker

    Asset(String symbol, double returnRate, double volatility) {
        this.symbol = symbol;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return symbol + ":" + returnRate + "%";
    }
}

public class WeeklyAssignments {

    // 🔹 Merge Sort (ascending returnRate, stable)
    static void mergeSort(Asset[] assets) {
        if (assets.length < 2) return;
        mergeSortHelper(assets, 0, assets.length - 1);
    }

    static void mergeSortHelper(Asset[] assets, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortHelper(assets, left, mid);
        mergeSortHelper(assets, mid + 1, right);
        merge(assets, left, mid, right);
    }

    static void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = Arrays.copyOfRange(assets, left, mid + 1);
        Asset[] R = Arrays.copyOfRange(assets, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                assets[k++] = L[i++];
            } else {
                assets[k++] = R[j++];
            }
        }
        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    // 🔹 Quick Sort (descending returnRate, tie-breaker: volatility ascending)
    static void quickSort(Asset[] assets) {
        quickSortHelper(assets, 0, assets.length - 1);
    }

    static void quickSortHelper(Asset[] assets, int low, int high) {
        if (low < high) {
            // Use median-of-3 pivot selection
            int pivotIndex = medianOfThree(assets, low, high);
            swap(assets, pivotIndex, high);
            int pi = partition(assets, low, high);
            quickSortHelper(assets, low, pi - 1);
            quickSortHelper(assets, pi + 1, high);
        }
    }

    static int medianOfThree(Asset[] assets, int low, int high) {
        int mid = low + (high - low) / 2;
        double a = assets[low].returnRate;
        double b = assets[mid].returnRate;
        double c = assets[high].returnRate;

        if ((a > b) != (a > c)) return low;
        else if ((b > a) != (b > c)) return mid;
        else return high;
    }

    static int partition(Asset[] assets, int low, int high) {
        double pivot = assets[high].returnRate;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivot ||
                    (assets[j].returnRate == pivot && assets[j].volatility < assets[high].volatility)) {
                i++;
                swap(assets, i, j);
            }
        }
        swap(assets, i + 1, high);
        return i + 1;
    }

    static void swap(Asset[] assets, int i, int j) {
        Asset temp = assets[i];
        assets[i] = assets[j];
        assets[j] = temp;
    }

    // 🔹 Display Assets
    static void displayAssets(Asset[] assets) {
        for (Asset a : assets) System.out.print(a + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        Asset[] portfolio = {
                new Asset("AAPL", 12, 0.25),
                new Asset("TSLA", 8, 0.40),
                new Asset("GOOG", 15, 0.20),
                new Asset("MSFT", 12, 0.15) // tie with AAPL for demo
        };

        System.out.println("Original Portfolio:");
        displayAssets(portfolio);

        // Merge Sort (ascending)
        Asset[] mergeSorted = portfolio.clone();
        mergeSort(mergeSorted);
        System.out.println("\nMerge Sort (Ascending Return):");
        displayAssets(mergeSorted);

        // Quick Sort (descending with volatility tie-breaker)
        Asset[] quickSorted = portfolio.clone();
        quickSort(quickSorted);
        System.out.println("\nQuick Sort (Descending Return + Volatility ASC):");
        displayAssets(quickSorted);
    }
}