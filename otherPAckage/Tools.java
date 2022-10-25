package otherPAckage;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
public class Tools
{
    // A = anuitetas, L - Linijinis
    private double loanAmount;
    private double loanYear;
    private double procents;
    private boolean skip;
    private char type = 'A'; 
    private ArrayList<Fields> fields = new ArrayList<>();
    private ArrayList<Fields> filteredFields = new ArrayList<>();
    


    public Tools()
    {
        this.loanAmount = 0;
        this.loanYear = 0;
        this.procents = 0;
        this.skip = false;
        this.type = 'L';
    }
    public Tools(double amount, int year, int month, double proc, boolean skip, char type)
    {
        this.loanAmount = amount;
        this.loanYear = year * 12 + month;
        this.procents = (proc / 12) / 100;
        this.skip = skip;
        this.type = type;
    }
    public Tools(double amount, int year, int month, double proc, char type)
    {
        this.loanAmount = amount;
        this.loanYear = year * 12 + month;
        this.procents = (proc / 12) / 100;
        this.skip = false;
        this.type = type;
    }
    //setters
    public void setLoanAmount(double loanAmount)
    {
        this.loanAmount = loanAmount;
    }
    public void setLoanYear(double loanYear)
    {
        this.loanYear = loanYear * 12;
    }
    public void setSkip(boolean bool)
    {
        this.skip = bool;
    }
    public void setProcents(double proc)
    {
        this.procents = (proc / 12) / 100;
    }
    public void setType(char type)
    {
        this.type = type;
    }
    //getters
    public double getLoanAmount() {return loanAmount;}
    public double getloanYear() {return loanYear;}
    public double getProcents() {return procents;}
    public boolean getskip() {return skip;}
    public char getType() {return type;}
    public ArrayList<Fields> getFields() {return fields;}
    public ArrayList<Fields> getFilteredFields() {return filteredFields;}
    //functions
    //annuity
    public double annuity()
    {
        double num;
        num = loanAmount * (((procents * Math.pow(1+procents,loanYear)))/(Math.pow(1+procents,loanYear) - 1));
        return num;
    }
    public double annuityTest(double loanAmount2, double procents2, double loanYear2)
    {
        double num;
        num = loanAmount2 * (((procents2 * Math.pow(1+procents2,loanYear2)))/(Math.pow(1+procents2,loanYear2) - 1));
        return num;
    }

    public void tableInformationNoSkip()
    {
        if(skip == false)
        {
            if(type == 'A')
            {
                double amount = annuity();
                for(int i = 0; i < loanYear; ++i)
                {
                    fields.add(new Fields());
                    fields.get(i).setMonth(i + 1);
                    fields.get(i).setPayAmount(amount);
                    fields.get(i).setPalukanos(loanAmount * procents);
                    fields.get(i).setKreditas(amount - fields.get(i).getPalukanos());
                    fields.get(i).setLeftToPay(loanAmount);
                    loanAmount = loanAmount - fields.get(i).getKreditas();
                }
            }
            else if(type == 'L')
            {
                double credit = loanAmount / loanYear;
                for(int i = 0; i < loanYear; ++i)
                {
                    fields.add(new Fields());
                    fields.get(i).setMonth(i + 1);
                    fields.get(i).setPalukanos(loanAmount * procents);
                    fields.get(i).setPayAmount(credit + fields.get(i).getPalukanos());
                    fields.get(i).setKreditas(credit);
                    fields.get(i).setLeftToPay(loanAmount);
                    loanAmount = loanAmount - fields.get(i).getKreditas();
                }
            }
        }
        
    }
    public void tableInformationSkip(int startMonth, int howLong)
    {
        if(skip == true)
        {
            int i = 0;
            loanYear = loanYear + howLong;
            

            if(type == 'A')
            {
                double amount = annuityTest(loanAmount, procents, loanYear - (howLong * 2));
                
                
                while(i < (loanYear-howLong))
                {
                    if((i + 1) == startMonth)
                    {   
                        for(int m = howLong; m > 0; --m)
                        {
                            fields.add(new Fields());
                            fields.get(i).setMonth(i + 1);
                            fields.get(i).setPalukanos(loanAmount * procents);
                            fields.get(i).setPayAmount(0);
                            fields.get(i).setKreditas(0);
                            fields.get(i).setLeftToPay(loanAmount);
                            i++;
                        }
                    }
                    else
                    {
                        fields.add(new Fields());
                        fields.get(i).setMonth(i + 1);
                        fields.get(i).setPayAmount(amount);
                        fields.get(i).setPalukanos(loanAmount * procents);
                        fields.get(i).setKreditas(amount - fields.get(i).getPalukanos());
                        fields.get(i).setLeftToPay(loanAmount);
                        loanAmount = loanAmount - fields.get(i).getKreditas();
                        i++;
                    }
                }
            }
            else if(type == 'L')
            {
                double credit = loanAmount / (loanYear - (howLong * 2));
                
                while(i < (loanYear-howLong))
                {
                    if((i + 1) == startMonth)
                    {   
                        for(int m = howLong; m > 0; --m)
                        {

                            fields.add(new Fields());
                            fields.get(i).setMonth(i + 1);
                            fields.get(i).setPalukanos(loanAmount * procents);
                            fields.get(i).setPayAmount(0);
                            fields.get(i).setKreditas(0);
                            fields.get(i).setLeftToPay(loanAmount);
                            i++;
                        }
                    }
                    else
                    {
                        fields.add(new Fields());
                        fields.get(i).setMonth(i + 1);
                        fields.get(i).setPalukanos(loanAmount * procents);
                        fields.get(i).setPayAmount(credit + fields.get(i).getPalukanos());
                        fields.get(i).setKreditas(credit);
                        fields.get(i).setLeftToPay(loanAmount);
                        loanAmount = loanAmount - fields.get(i).getKreditas();
                        i++;
                    }

                }
            }
        }
    }
    public void draw()
    {
        DecimalFormat dec = new DecimalFormat("#0.00");
        try
        {
            File file = new File("ataskaita.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < fields.size(); ++i)
            {
                bw.write(fields.get(i).getMonth() + " " + dec.format(fields.get(i).getLeftToPay()) + " " + dec.format(fields.get(i).getPayAmount())
                + " " + dec.format(fields.get(i).getPalukanos()) + " " + dec.format(fields.get(i).getKreditas()));
                bw.newLine();
                
            }
            bw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void drawFilter()
    {
        DecimalFormat dec = new DecimalFormat("#0.00");
        try
        {
            File file = new File("ataskaita.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < filteredFields.size(); ++i)
            {
                bw.write(filteredFields.get(i).getMonth() + " " + dec.format(filteredFields.get(i).getLeftToPay()) + " " + dec.format(filteredFields.get(i).getPayAmount())
                + " " + dec.format(filteredFields.get(i).getPalukanos()) + " " + dec.format(filteredFields.get(i).getKreditas()));
                bw.newLine();
                
            }
            bw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void filter(int start, int end)
    {
        //filteredFields.clear();
        for(int i = start; i < end; ++i)
        {
            filteredFields.add(fields.get(i));
        }
    }
    public void clean()
    {
        fields.clear();
        filteredFields.clear();
    }

}   
    
