package IO;

import java.util.Scanner;

public class Main {

    public static class Zadaca
    {
        private String opis;
        private int broj_casovi;
        private boolean status;
        public Zadaca(){}

        public Zadaca(String opis, int broj_casovi, boolean status) {
            this.opis = opis;
            this.broj_casovi = broj_casovi;
            this.status = status;
        }

        public String getOpis() {
            return opis;
        }

        public void setOpis(String opis) {
            this.opis = opis;
        }

        public int getBroj_casovi() {
            return broj_casovi;
        }

        public void setBroj_casovi(int broj_casovi) {
            this.broj_casovi = broj_casovi;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }


        @Override
        public String toString() {
            return "opis='" + opis + '\'' + ", broj_casovi=" + broj_casovi + ", status=" + status + '}';
        }
    }

    public static class Vraboten
    {
        private String Ime;
        private String Prezime;
        private static double vrednost_bod=50;
        private double plata;
        private int stazh;
        private int broj_bodovi;
        private Zadaca[] zadaci;
        private int broj_zadaci;

        Vraboten()
        {
            zadaci = new Zadaca[10];
            broj_zadaci = 0;
        }

        Vraboten(String Ime,String Prezime,int stazh,int broj_bodovi)
        {
            this.Ime = Ime;
            this.Prezime = Prezime;
            this.stazh = stazh;
            this.broj_bodovi = broj_bodovi;
            plata = broj_bodovi * vrednost_bod;
        }

        public String getIme() {
            return Ime;
        }

        public void setIme(String ime) {
            Ime = ime;
        }

        public String getPrezime() {
            return Prezime;
        }

        public void setPrezime(String prezime) {
            Prezime = prezime;
        }

        public int getStazh() {
            return stazh;
        }

        public void setStazh(int stazh) {
            this.stazh = stazh;
        }

        public int getBroj_bodovi() {
            return broj_bodovi;
        }

        public void setBroj_bodovi(int broj_bodovi) {
            this.broj_bodovi = broj_bodovi;
        }

        @Override
        public String toString() {
            return Ime + " " + Prezime;
        }
        public void dodadi_zadaca(Zadaca z)
        {
            if(broj_zadaci == 10)
            {
                System.out.println("NEMOZE DA SE DODADE ZADACA");
            }
            else
            {
                zadaci[broj_zadaci++] = z;
            }
        }
        public double procentZavrseniZadaci()
        {
            int broj = 0;
            for(int i=0;i<broj_zadaci;i++)
            {
                if(zadaci[i].getStatus())
                {
                    broj++;
                }
            }
            return (float)broj/broj_zadaci;
        }

        public int VkupnoCasovi()
        {
            int suma=0;
            for(int i=0;i<broj_zadaci;i++)
            {
                suma+=zadaci[i].getBroj_casovi();
            }
            return suma;
        }

    }

    public static class Kompanija
    {
        public Vraboten[] vraboteni;
        Kompanija(Vraboten[] vraboteni)
        {
            this.vraboteni=vraboteni;
        }

        public Vraboten najangaziran()
        {
            int max=0,k=0;
            for(int i=0;i<vraboteni.length;i++)
            {
                if(vraboteni[i].VkupnoCasovi()>max)
                {
                    max=vraboteni[i].VkupnoCasovi();
                    k=i;
                }
            }
            return vraboteni[k];
        }
        public void pecatiPoUspesnost()
        {
            boolean flag = true;
            while(flag)
            {
                flag = false;
                for(int j=0;j<vraboteni.length;j++)
                {
                    if(vraboteni[j].procentZavrseniZadaci()<vraboteni[j+1].procentZavrseniZadaci())
                    {
                        Vraboten temp = vraboteni[j];
                        vraboteni[j] = vraboteni[j+1];
                        vraboteni[j+1] = temp;
                        flag = true;
                    }
                }
            }
            for(int i=0;i<vraboteni.length;i++)
            {
                System.out.println("Vraboten: " + vraboteni[i].getIme() + " ," +vraboteni[i].getPrezime() + " Uspesnost: %.2f\n" + (vraboteni[i].procentZavrseniZadaci()*100));
            }
        }
        public void pecati()
        {
            for(Vraboten v : vraboteni)
            {
                v.toString();
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Vraboten []temp = new Vraboten[n];
        for(int i=0;i<n;i++)
        {
            Vraboten v = new Vraboten();
            v.setIme(input.next());
            v.setPrezime(input.next());
            v.setStazh(input.nextInt());
            v.setBroj_bodovi(input.nextInt());
            temp[i] = v;
            int p = input.nextInt();
            for(int j=0;j<p;j++)
            {
                Zadaca z = new Zadaca();
                z.setBroj_casovi(input.nextInt());
                z.setOpis(input.next());
                z.setStatus(input.nextBoolean());
                v.dodadi_zadaca(z);
            }
        }
        Kompanija k = new Kompanija(temp);
        k.pecati();
        k.pecatiPoUspesnost();
        System.out.println("Najangaziran vraboten e: " + k.najangaziran());


    }
}
