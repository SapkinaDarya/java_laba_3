import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TimeChecker extends SimpleTable {

    private long begin;
    private long end;
    private final int AmountOfElems = 10000;
    public String[][] data;
    private int iForColumn;

    public TimeChecker() {
        iForColumn = 0;
        data = new String[20][4];
    }

    private void AddTest(List<Integer> list, int amount) {
        for (int i = list.size(); list.size() < amount; i++) {
            list.add(i);
        }
    }

    public void AddCheck(List<Integer> list, int amount, int type) {
        begin = System.nanoTime();
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
        end = System.nanoTime();
        long result = end - begin;
        DataForStr(1, type, result, amount, iForColumn);
    }

    public void DeleteCheck( List<Integer> list, int amount, int type) {
        if (list.size() < amount) AddTest(list, amount);
        begin = System.nanoTime();
        for (int i = amount - 1; i >= 0; i--) {
            list.remove(i);
        }
        end = System.nanoTime();
        long result = end - begin;
        DataForStr(2, type, result, amount, iForColumn);

    }

    public void GetCheck( List<Integer> list, int amount, int type) {
        if (list.size() < amount) AddTest(list, amount);
        begin = System.nanoTime();
        for (int i = amount - 1; i >= 0; i--) {
            list.get(i);
        }
        end = System.nanoTime();
        long result = end - begin;
        DataForStr(3, type, result, amount, iForColumn);
    }

    private void DataForStr(int op, int type, long result, int amount, int column) {
        if (op == 1) data[column][0] = "Add";
        if (op == 2) data[column][0] = "Delete";
        if (op == 3) data[column][0] = "Get";
        if (type == 1) data[column][1] = "Array List";
        if (type == 2) data[column][1] = "Linked List";
        data[column][2] = result + " ns";
        data[column][3] = Integer.toString(amount);
        iForColumn++;
    }

    public void Testing() {
        ArrayList<Integer> array = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 3; i++) {
            AddCheck(array, i * AmountOfElems, 1);
            AddCheck(list, i * AmountOfElems, 2);
            GetCheck(array, i * AmountOfElems, 1);
            GetCheck(list, i * AmountOfElems, 2);
            DeleteCheck(array, i * AmountOfElems, 1);
            DeleteCheck(list, i * AmountOfElems, 2);
        }
    }

    public static void main(String[] args) {
        TimeChecker t = new TimeChecker();
        t.Testing();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Create(t.data);
            }
        });
    }
}
