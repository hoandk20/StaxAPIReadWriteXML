/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staxtdemo;

/**
 *
 * @author loanbui
 */
public class Person {
    private String Id;
    private String Photo;
    private String given;
    private String family;
    private String email;
    private String subordinates;
    private String href;

    public Person(){
        
    }
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(String subordinates) {
        this.subordinates = subordinates;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "[ID: "+Id+"\n"
                + "Photo: "+Photo+"\n"
                + "Given: "+given+"\n"
                +"family: "+family+"\n"+
                "email: "+email+"\n"+
                "subordinates: "+subordinates+
                "href: "+href+"]";
    }

    public Person(String Id, String Photo, String given, String family, String email, String subordinates, String href) {
        this.Id = Id;
        this.Photo = Photo;
        this.given = given;
        this.family = family;
        this.email = email;
        this.subordinates = subordinates;
        this.href = href;
    }

    
    
}
