package InterfaceTools;
//import java.awt.*;
import javax.swing.*;

import java.text.*;
import java.util.*;
import otherPAckage.*;
public class DrawTable
{
    JFrame frame;
    String data[][];
    String column[] = {"Month","Left to Pay","Pay amount","palukanos", "Kreditas"};
    JTable table;
    int rowL;
    JScrollPane scroll;

    public DrawTable()
    {
        frame = new JFrame();
        //clear();
    }
    public void create(ArrayList<Fields> name)
    {
        
        int row = 0;
        rowL = name.size();
        data = new String[name.size()][5];
        DecimalFormat dec = new DecimalFormat("#0.00");
        clear();
        while(row < name.size())
        {
            data[row][0] = name.get(row).getMonth() + "";
            data[row][1] = dec.format(name.get(row).getLeftToPay());
            data[row][2] = dec.format(name.get(row).getPayAmount());
            data[row][3] = dec.format(name.get(row).getPalukanos());
            data[row][4] = dec.format(name.get(row).getKreditas());
            row++;
        }

        table = new JTable(data, column);
        table.setBounds(30,40,200,300);      
        scroll = new JScrollPane(table);      
        frame.add(scroll);
        frame.setSize(700, 600);
        frame.setVisible(true);  
    }
    public void clear()
    {
        int col = 0;
        int row = 0;
        while(col < 5 && row < rowL)
        {
            data[row][col] = null;
            col++;
            if(col == 5)
            {
                col = 0;
                row++;
            }
        }
    }

}
