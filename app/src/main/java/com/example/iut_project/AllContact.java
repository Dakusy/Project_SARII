package com.example.iut_project;

public class AllContact {
    private String id;
    private String nom;
    private String prenom;
    private String numero;

    public AllContact(String id, String nom, String prenom, String numero) {
       this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setNumero(numero);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String toString(){

        return nom + " " + prenom + " " + numero;
    }

    public String readID() {
        return id;
    }

    public String readPrenom(){return prenom;}

    public String readNom(){return nom;}

    public String Contact(){
        return nom + " " + prenom;
    }

    public String Num(){return numero;}


}
