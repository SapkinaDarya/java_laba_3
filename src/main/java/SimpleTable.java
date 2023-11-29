import javax.swing.*;
import java.awt.*;

public class SimpleTable extends JFrame{

    private static Object[] columnsHeader = new String[]{"Тип операции", "Тип массива", "Время выполнения", "Кол-во элементов"};

    public static void Create(String[][] data){
        JFrame frame = new JFrame("Подсчет времени операций");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable table = new JTable(data, columnsHeader);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
