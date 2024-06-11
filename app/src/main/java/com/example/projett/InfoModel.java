package com.example.projett;

public class InfoModel {
    String image;
    String prix;
    String type;
    String size;
    String nb_pieces;
    String nb_chambre;
    String localition;
    String annexes;
    String equip;
    String Accees;
    String espace_exter;
    String description;

    public InfoModel() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InfoModel(String image, String prix, String type, String size, String nb_pieces, String nb_chambre, String localition, String annexes, String equip, String accees, String espace_exter, String description) {
        this.image = image;
        this.prix = prix;
        this.type = type;
        this.size = size;
        this.nb_pieces = nb_pieces;
        this.nb_chambre = nb_chambre;
        this.localition = localition;
        this.annexes = annexes;
        this.equip = equip;
        Accees = accees;
        this.espace_exter = espace_exter;
        this.description = description;
    }

    public String getAnnexes() {
        return annexes;
    }

    public void setAnnexes(String annexes) {
        this.annexes = annexes;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getAccees() {
        return Accees;
    }

    public void setAccees(String accees) {
        Accees = accees;
    }

    public String getEspace_exter() {
        return espace_exter;
    }

    public void setEspace_exter(String espace_exter) {
        this.espace_exter = espace_exter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNb_pieces() {
        return nb_pieces;
    }

    public void setNb_pieces(String nb_pieces) {
        this.nb_pieces = nb_pieces;
    }

    public String getNb_chambre() {
        return nb_chambre;
    }

    public void setNb_chambre(String nb_chambre) {
        this.nb_chambre = nb_chambre;
    }

    public String getLocalition() {
        return localition;
    }

    public void setLocalition(String localition) {
        this.localition = localition;
    }
}
