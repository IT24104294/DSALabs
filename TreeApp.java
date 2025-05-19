import java.util.Scanner;

class Node {
    public int employeeId;
    public String employeeName;
    public Node leftChild;
    public Node rightChild;

    public Node(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public void displayNode() {
        System.out.print("{\n" + "id: " + employeeId + ", \n" + "Name: " + employeeName + "\n}\n");
    }
}

class Tree {
    public Node root;

    public Tree() {
        root = null;
    }

    public Node find(int empId) {
        Node current = root;
        while (current != null && current.employeeId != empId) {
            if (empId < current.employeeId) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(int empId, String empName) {
        Node newNode = new Node(empId, empName);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (empId < current.employeeId) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            localRoot.displayNode();
            inOrder(localRoot.rightChild);
        }
    }

    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            localRoot.displayNode();
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            localRoot.displayNode();
        }
    }

    public Node findRecursive(int empId, Node localRoot) {
        Node current = localRoot;
        if (localRoot == null) {
            return null;
        }
        if (current.employeeId == empId) {
            return current;
        }
        if (current.employeeId < empId) {
            return findRecursive(empId, current.leftChild);
        } else {
            return findRecursive(empId, current.rightChild);
        }
    }

    public void deleteAll() {
        root = null;
    }
}

public class TreeApp {
    public static void main(String[] args) {
        Scanner inputRef = new Scanner(System.in);
        Tree theTree = new Tree();
        theTree.insert(149, "Anusha");
        theTree.insert(167, "Kosala");
        theTree.insert(47, "Dinusha");
        theTree.insert(66, "Mihiri");
        theTree.insert(159, "Jayani");
        theTree.insert(118, "Nimal");
        theTree.insert(195, "Nishantha");
        theTree.insert(34, "Avodya");
        theTree.insert(105, "Bimali");
        theTree.insert(133, "Sampath");

        // In-order traversal
        System.out.print("In-order traversal: ");
        theTree.inOrder(theTree.root);

        // Pre-order traversal
        System.out.print("\nPre-order traversal: ");
        theTree.preOrder(theTree.root);

        // Post-order traversal
        System.out.print("\nPost-order traversal: ");
        theTree.postOrder(theTree.root);

        // Enter details by User
        System.out.print("\nEnter employee ID: ");
        int empId = inputRef.nextInt();
        inputRef.nextLine();
        System.out.print("Enter employee name: ");
        String empName = inputRef.nextLine();
        theTree.insert(empId, empName);

        theTree.inOrder(theTree.root);
        Node foundNode = theTree.find(empId);
        if (foundNode != null) {
            System.out.println("Employee found: " + empName);
        } else {
            System.out.println("Employee not found.");
        }

        inputRef.close();
    }
}