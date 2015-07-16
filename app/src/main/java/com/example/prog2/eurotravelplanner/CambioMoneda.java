package com.example.prog2.eurotravelplanner;

/**
 * Created by HellenFranchesca on 15/07/2015.
 */
public class CambioMoneda {
    Double monto;


    String moneda1, moneda2;



    public String getMoneda1() {
        return moneda1;
    }

    public String getMoneda2() {
        return moneda2;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMoneda1(String moneda1) {
        this.moneda1 = moneda1;
    }

    public void setMoneda2(String moneda2) {
        this.moneda2 = moneda2;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

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
        if(cmd.equals("Comprar")) {
            if (moneda1.equals("Peso Dominicano(DOP)")) {
                if (moneda2.equals("Euro(EUR)")) {

                    total = ("EUR$ "+Double.toString(redondear(monto/49.47)));
                }
                if (moneda2.equals("Libras Esterlinas (GBP)")) {
                    total = ("GBP$ "+Double.toString(redondear(monto/70.29)));
                }
            }

            if (moneda1.equals("Dolares Estadounidense(USD)")) {
                if (moneda2.equals("Euro(EUR)")) {
                    total = ("EUR$ "+Double.toString(redondear(monto/1.09)));
                }
                if (moneda2.equals("Libras Esterlinas (GBP)")) {
                    total = ("GBP$ "+Double.toString(redondear(monto/0.64)));
                }
            }
        }
        if(cmd.equals("Vender")) {
            if (moneda1.equals("Peso Dominicano(DOP)")) {
                if (moneda2.equals("Euro(EUR)")) {

                    total = "EUR$ "+Double.toString(redondear(monto * 50.60));
                }
                if (moneda2.equals("Libras Esterlinas (GBP)")) {
                    total = "GBP$ "+Double.toString(monto*0.014);
                }
            }

            if (moneda1.equals("Dolares Estadounidense(USD)")) {
                if (moneda2.equals("Euro(EUR)")) {
                    total = "EUR$ "+Double.toString(monto*0.91);
                }
                if (moneda2.equals("Libras Esterlinas (GBP)")) {
                    total = "GBP$ "+Double.toString(monto*0.64);
                }
            }
        }

    return total;
    }

}
