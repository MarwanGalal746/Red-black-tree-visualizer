package gui;

public class Main {
	public static void main(String[] args) {
        RBT tree = new RBT();
        
        tree.insertRBT(1);
        tree.insertRBT(2);
        tree.insertRBT(3);
        //tree.insertRBT(4);
//        tree.insertRBT(5);
//        tree.insertRBT(6);
//        tree.insertRBT(7);
//        tree.insertRBT(8);
//        tree.insertRBT(9);
        tree.print2D();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

//        tree.deletionRBT(2);
//        tree.deletionRBT(3);
//        tree.deletionRBT(1);
        tree.clearTree();
        tree.print2D();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

//        tree.insertRBT(2);
//        tree.print2D();

        System.out.println();
    }
}
