package com.res.bom_system.data;

public class Reservation {

    public static class Date{
        int stunde, minute, tag, monat, jahr;

        public Date(int stunde, int minute, int tag, int monat, int jahr){
            this.stunde = stunde;
            this.minute = minute;
            this.tag = tag;
            this.monat = monat;
            this.jahr = jahr;
        }

        @Override
        public String toString() {
            return stunde +":"+minute+", " + tag+"/"+monat+"/"+jahr;
        }
    }

    final int id, phone, personen;
    final String name, email;
    final Date datum;

    public Reservation(int id, String name, int personen, String email, int phone, Date date){
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.datum = date;
        this.personen = personen;
    }

    @Override
    public String toString() {
        return name + " mit "+personen +" Gästen um " + this.datum;
    }

    // GETTERS //


    public Date getDatum() {
        return datum;
    }

    public int getId() {
        return id;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
