package com.example.x_splitter;

public class TransactionInfo {
    String group;
    String event;
    String paidBy;
    double amountToPay;
    double amountToGet;
    double amountInvested;


    String amountInvestedTemp;
//List<String> groupMembers;
    //int equallySplittedAmount;


    //String ItemPaidBy;
    //int membersize;





//    public TransactionInfo( int equallySplittedAmount, String amountToPay, String amountToGet, String amountInvested) {
//        this.amountInvested = amountInvested;
//        amountInvestedTemp = amountInvested;
//        this.amountToGet = Integer.toString(equallySplittedAmount);

//        int difference = ((Integer.parseInt(amountInvested)) - equallySplittedAmount);
//        if(difference>=0)
//            {
//                this.amountToGet =Integer.toString(difference);

//                if(Integer.parseInt(amountToPay) != 0){
//                    if (Integer.parseInt(amountToGet)<Integer.parseInt(amountToPay)){
//                        this.amountToPay=Integer.toString((Integer.parseInt(amountToPay))-(Integer.parseInt(amountToGet)));
//                        this.amountToGet=Integer.toString(0);
//                    }
//                    else {
//                        this.amountToGet=Integer.toString((Integer.parseInt(amountToGet))-(Integer.parseInt(amountToPay)));
//                        this.amountToPay=Integer.toString(0);
//                    }
//                }
//            }else {
//            this.amountToPay=Integer.toString((Integer.parseInt(amountToPay))-difference);
//            this.amountToGet=Integer.toString(0);

//        }
        //    }

//    public TransactionInfo( int equallySplittedAmount, String itemPaidBy, String amountToPay, String amountToGet, String amountInvested) {
//        this.amountToPay = Integer.toString(equallySplittedAmount);
//    }

    public TransactionInfo(double amountToPay, double amountToGet, double amountInvested) {
        this.amountToPay = amountToPay;
        this.amountToGet = amountToGet;
        this.amountInvested = amountInvested;
    }
//

//
//    public TransactionInfo(String paidBy, String amount){
//        this.paidBy = paidBy;
//        this.amount = amount;
//    }

    public TransactionInfo() {
    }

//    public TransactionInfo( String amountInvested){
//        this.amountInvested = amountInvested + amountInvestedTemp;
//
//
//    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public double getAmountToGet() {
        return amountToGet;
    }

    public void setAmountToGet(double amountToGet) {
        this.amountToGet = amountToGet;
    }

    public double getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(double amountInvested) {
        this.amountInvested = amountInvested;
    }

    public String getAmountInvestedTemp() {
        return amountInvestedTemp;
    }

    public void setAmountInvestedTemp(String amountInvestedTemp) {
        this.amountInvestedTemp = amountInvestedTemp;
    }
}

