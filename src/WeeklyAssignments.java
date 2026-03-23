class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    void display() {
        System.out.println(name + " : Risk=" + riskScore + ", Balance=" + accountBalance);
    }
}

public class WeeklyAssignments {

    // 🔹 Bubble Sort (Ascending by Risk Score)
    static void bubbleSort(Client[] clients) {
        int n = clients.length;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {

                    // Swap
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;

                    swapCount++;

                    // Visualization of swap
                    System.out.println("Swapped: " + clients[j].name + " <-> " + clients[j + 1].name);
                }
            }
        }
        System.out.println("Total Swaps: " + swapCount);
    }

    // 🔹 Insertion Sort (Descending by Risk Score + Account Balance)
    static void insertionSort(Client[] clients) {
        int n = clients.length;

        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            // Sort by risk DESC, if equal then by balance DESC
            while (j >= 0 &&
                    (clients[j].riskScore < key.riskScore ||
                            (clients[j].riskScore == key.riskScore &&
                                    clients[j].accountBalance < key.accountBalance))) {

                clients[j + 1] = clients[j];
                j--;
            }

            clients[j + 1] = key;
        }
    }

    // 🔹 Display Clients
    static void displayClients(Client[] clients) {
        for (Client c : clients) {
            c.display();
        }
    }

    // 🔹 Top N Highest Risk Clients
    static void topClients(Client[] clients, int topN) {
        System.out.println("\nTop " + topN + " High Risk Clients:");
        for (int i = 0; i < topN && i < clients.length; i++) {
            System.out.println(clients[i].name + " (" + clients[i].riskScore + ")");
        }
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("ClientC", 80, 5000),
                new Client("ClientA", 20, 2000),
                new Client("ClientB", 50, 3000)
        };

        System.out.println("Original Data:");
        displayClients(clients);

        // 🔹 Bubble Sort (Ascending)
        System.out.println("\nBubble Sort (Ascending Risk):");
        bubbleSort(clients);
        displayClients(clients);

        // 🔹 Insertion Sort (Descending)
        System.out.println("\nInsertion Sort (Descending Risk + Balance):");
        insertionSort(clients);
        displayClients(clients);

        // 🔹 Top 3 (or Top 10 in real case)
        topClients(clients, 3);
    }
}