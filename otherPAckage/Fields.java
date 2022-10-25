package otherPAckage;

import java.text.DecimalFormat;

public class Fields 
{
    private int month;
    private double payAmount;
    private double leftToPay;
    private double palukanos;
    private double kreditas;

    //setters
    public void setMonth(int month) {this.month = month;}
    public void setPayAmount(double payAmount) {this.payAmount = payAmount;}
    public void setLeftToPay(double leftToPay) {this.leftToPay = leftToPay;}
    public void setPalukanos(double palukanos) {this.palukanos = palukanos;}
    public void setKreditas(double kreditas) {this.kreditas = kreditas;}
    //getters
    public int getMonth() {return month;}
    public double getPayAmount() {return payAmount;}
    public double getLeftToPay() {return leftToPay;}
    public double getPalukanos() {return palukanos;}
    public double getKreditas() {return kreditas;}
    //functions
    public void draw()
    {
        //when we get interface this will be the same but we will make this a parent class
        DecimalFormat dec = new DecimalFormat("#0.00");
        System.out.println(month + " " + dec.format(payAmount) + " " + dec.format(leftToPay)+ " " + dec.format(palukanos) + " " + dec.format(kreditas));
    }

}
