package com.eurotravel.prog2.eurotravelplanner;

/**
 * Created by HellenFranchesca on 15/07/2015.
 */
public class CambioMoneda {
    Double monto;


    String moneda1, moneda2;


    public double redondear(double numero) {

        int cifras = (int) Math.pow(10, 2);
        return Math.rint(numero * cifras) / cifras;
    }

    public void CambioMoneda(double monto, String moneda1, String moneda2){
        this.monto=monto;
        this.moneda1=moneda1;
        this.moneda2=moneda2;

    }

    public String cambioM(double monto, String moneda1, String moneda2, String cmd){
        String total=null;

            if (moneda1.equals("Peso Dominicano(DOP)")) {
                if (moneda2.equals("Dolares Estadounidense(USD)")) {

                    total = "USD$ "+Double.toString(redondear(monto * 0.02));
                }
                if (moneda2.equals("Euro(EUR)")) {

                    total = ("EUR$ "+Double.toString(redondear(monto*0.02)));
                }
                if (moneda2.equals("Libras Esterlinas (GBP)")) {
                    total = ("GBP$ "+Double.toString(redondear(monto*0.01)));
                }
            }

            if (moneda1.equals("Dolares Estadounidense(USD)")) {
                if (moneda2.equals("Peso Dominicano(DOP)")) {

                    total = "DOP$ "+Double.toString(redondear(monto * 45.03));
                }
                if (moneda2.equals("Euro(EUR)")) {
                    total = ("EUR$ "+Double.toString(redondear(monto*0.91)));
                }
                if (moneda2.equals("Libras Esterlinas (GBP)")) {
                    total = ("GBP$ "+Double.toString(redondear(monto*0.64)));
                }
            }


            if (moneda1.equals("Euro(EUR)")) {
                if (moneda2.equals("Peso Dominicano(DOP)")) {

                    total = "DOP$ "+Double.toString(redondear(monto * 49.27));
                }
                if (moneda2.equals("Dolares Estadounidense(USD)")) {

                    total = "USD$ "+Double.toString(redondear(monto * 1.09));
                }
                if (moneda2.equals("Libras Esterlinas (GBP)")) {
                    total = "GBP$ "+Double.toString(redondear(monto*0.70));
                }
            }

            if (moneda1.equals("Libras Esterlinas (GBP)")) {
                if (moneda2.equals("Peso Dominicano(DOP)")) {

                    total = "DOP$ "+Double.toString(redondear(monto * 70.1));
                }
                if (moneda2.equals("Dolares Estadounidense(USD)")) {

                    total = "USD$ "+Double.toString(redondear(monto * 1.56));
                }
                if (moneda2.equals("Euro(EUR)")) {
                    total = "EUR$ "+Double.toString(redondear(monto*1.42));
                }

            }



    return total;
    }

}
