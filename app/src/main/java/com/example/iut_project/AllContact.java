package com.example.iut_project;

public class AllContact {
    private int id;
    private String nom;
    private String prenom;
    private String numero;

    public AllContact(int id, String nom, String prenom, String numero) {
       this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setNumero(numero);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int readID() {
        return id;
    }

    public String Contact(){
        return nom + " " + prenom;
    }
}
