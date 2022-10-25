package InterfaceTools;

import javax.swing.*;

import otherPAckage.Tools;

import java.awt.*;
import java.awt.event.*;

public class Window
{
    //Tool object
    Tools tool = new Tools();
    JFrame window, table, chart;
    Font font1 = new Font("Times New Roman", Font.PLAIN, 14);
    JPanel skipContainer;
    JPanel filterContainer;
    JPanel editorPanel;
    JTextField textField[] = new JTextField[3];
    JLabel label[] = new JLabel[4];
    Choice choice = new Choice();
    JButton buttons[] = new JButton[6]; //Atidejimas, filtras, skaiciuoti palukanas, save to file, show a table
    boolean filterOn = false;
    int startSkip, range, filterstart, filterend;
    

    //one button for filter, the other one for skip button whick when pressed open the range 
    //of skip

    public Window()
    {
        window = new JFrame("Interface");
        window.setSize(new Dimension(1050, 712));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.getContentPane().setBackground(new Color(0,0,0));
        window.setResizable(false);  
        //Make it scrollable
        createTextFields();
        window.setVisible(true);
    }

    public void createTextFields()
    { 
        /* EditorPanel */
        editorPanel = new JPanel();
        editorPanel.setBounds(50, 50, 600, 400);
        editorPanel.setLayout(null);
        editorPanel.setBackground(Color.gray);
        
        /* Labels */
        label[0] = new JLabel();
        label[0].setText("Įveskite kredito sumą");
        label[0].setForeground(Color.white);
        label[0].setFont(font1);
        label[0].setBounds(50, 20, 300, 20);
        editorPanel.add(label[0]);

        label[1] = new JLabel();
        label[1].setText("Įveskite metinę palūkanų normą ir tipą");
        label[1].setForeground(Color.white);
        label[1].setFont(font1);
        label[1].setBounds(50, 83, 300, 20);
        editorPanel.add(label[1]);

        label[2] = new JLabel();
        label[2].setText("%");
        label[2].setForeground(Color.white);
        label[2].setFont(font1);
        label[2].setBounds(153, 110, 20, 20);
        editorPanel.add(label[2]);

        label[3] = new JLabel();
        label[3].setText("Terminas metais");
        label[3].setForeground(Color.white);
        label[3].setFont(font1);
        label[3].setBounds(50,143, 150, 20 );
        editorPanel.add(label[3]);

        /* CHOICE button */
        choice.setBounds(180, 110, 100, 30);
        choice.add("Linijinis");
        choice.add("Anuitetas");
        editorPanel.add(choice);

        /* Text Fields */
        //Amount
        textField[0] = new JTextField(20);
        textField[0].setLocation(50, 50);
        textField[0].setSize(300,30);
        editorPanel.add(textField[0]);

        //palukanu norma
        textField[1] = new JTextField(20);
        textField[1].setLocation(50, 110);
        textField[1].setSize(100,30);
        editorPanel.add(textField[1]);

        //terminas metams
        textField[2] = new JTextField(20);
        textField[2].setLocation(50, 170);
        textField[2].setSize(100,30);
        editorPanel.add(textField[2]);
        
        /* Skip Container Panel */

        skipContainer = new JPanel(new GridLayout(5, 2));
        JTextField textstart = new JTextField(10);
        JTextField textrange = new JTextField(10);
        JLabel labelStart = new JLabel();
        labelStart.setText("Pradžia:");
        labelStart.setForeground(Color.black);
        labelStart.setFont(font1);

        JLabel labelRange = new JLabel();
        labelRange.setText("Kiek:");
        labelRange.setForeground(Color.black);
        labelRange.setFont(font1);
        skipContainer.add(labelStart);
        skipContainer.add(textstart);
        skipContainer.add(labelRange);
        skipContainer.add(textrange);
        

        /* Buttons */

        //Atidejimo migtukas
        buttons[0] = new JButton();
        buttons[0].setOpaque(true);
        buttons[0].setBackground(Color.white);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttons[0].setText("Atidejimas");
        buttons[0].setLocation(180, 170);
        buttons[0].setSize(100, 30);
        buttons[0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(tool.getskip() == false)
                {
                    buttons[0].setBackground(Color.red);
                    tool.setSkip(true);
                    JOptionPane.showMessageDialog(null, skipContainer);
                    startSkip = Integer.parseInt(textstart.getText());
                    range = Integer.parseInt(textrange.getText());
                }
                else if(tool.getskip() == true)
                {
                    buttons[0].setBackground(Color.white);
                    tool.tableInformationSkip(startSkip, range);
                    tool.setSkip(false);
                }
            }   
        });
        editorPanel.add(buttons[0]);
        
        //Filter container
        filterContainer = new JPanel(new GridLayout(5, 2));
        JTextField st = new JTextField(10);
        JTextField end = new JTextField(10);
        JLabel sta = new JLabel();
        sta.setText("Range:");
        sta.setForeground(Color.black);
        sta.setFont(font1);

        filterContainer.add(sta);
        filterContainer.add(st);
        filterContainer.add(end);

        //filter migtukas
        buttons[1] = new JButton();
        buttons[1].setOpaque(true);
        buttons[1].setBackground(Color.white);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttons[1].setText("Filtras");
        buttons[1].setLocation(310, 170);
        buttons[1].setSize(100, 30);
        buttons[1].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(filterOn == true)
                {
                    filterOn = false;
                    buttons[1].setBackground(Color.white);
                }
                else if(filterOn == false)
                {
                    filterOn = true;
                    buttons[1].setBackground(Color.red);
                    JOptionPane.showMessageDialog(null, filterContainer);
                    filterstart = Integer.parseInt(st.getText());
                    filterend = Integer.parseInt(end.getText());
                    tool.filter(filterstart, filterend);
                    
                }
                
            }   
        });
        editorPanel.add(buttons[1]);

        //skaiciuoti Palukanas
        buttons[2] = new JButton();
        buttons[2].setOpaque(true);
        buttons[2].setBackground(Color.white);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttons[2].setText("Skaičiuoti");
        buttons[2].setLocation(50, 230);
        buttons[2].setSize(100, 30);
        buttons[2].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tool.clean();
                tool.setLoanAmount(Double.parseDouble(textField[0].getText()));
                tool.setProcents(Double.parseDouble(textField[1].getText()));
                tool.setLoanYear(Double.parseDouble(textField[2].getText()));
                if(choice.getItem(choice.getSelectedIndex()).equals("Linijinis"))
                {
                    tool.setType('L');
                }
                else if(choice.getItem(choice.getSelectedIndex()).equals("Anuitetas"))
                {
                    tool.setType('A');
                }
                
                if(tool.getskip() == true)
                {
                    tool.tableInformationSkip(startSkip, range);
                }
                else if(tool.getskip() == false)
                {
                    tool.tableInformationNoSkip();
                }
            }
        });
        editorPanel.add(buttons[2]);

        //save to file
        buttons[3] = new JButton();
        buttons[3].setOpaque(true);
        buttons[3].setBackground(Color.white);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttons[3].setText("Save to File");
        buttons[3].setLocation(180, 230);
        buttons[3].setSize(100, 30);
        buttons[3].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(filterOn == true)
                {
                    tool.drawFilter();
                }
                else if(filterOn == false)
                {
                    tool.draw();
                }
                
            }
        });
        editorPanel.add(buttons[3]);

        buttons[4] = new JButton();
        buttons[4].setOpaque(true);
        buttons[4].setBackground(Color.white);
        buttons[4].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttons[4].setText("Table");
        buttons[4].setLocation(50, 290);
        buttons[4].setSize(100, 30);
        buttons[4].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(filterOn == false)
                {
                    DrawTable temp = new DrawTable();
                    temp.create(tool.getFields());
                }
                else if(filterOn == true)
                {
                    DrawTable temp1 = new DrawTable();
                    temp1.create(tool.getFilteredFields());
                }
            }
        });
        editorPanel.add(buttons[4]);

        //this will create lineChart
        buttons[5] = new JButton();
        buttons[5].setOpaque(true);
        buttons[5].setBackground(Color.white);
        buttons[5].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttons[5].setText("Show chart");
        buttons[5].setLocation(180, 290);
        buttons[5].setSize(100, 30);
        buttons[5].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(filterOn == false)
                {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            DrawGraph.createShow(tool.getFields());
                        }
                     });
                    
                }
                else if(filterOn == true)
                {
                    DrawGraph.createShow(tool.getFilteredFields());
                }

            }
        });
        editorPanel.add(buttons[5]);

        window.add(editorPanel);
    }
}
