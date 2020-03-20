package app.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "profile_pic")
    private String profile_pic;
    @Column(name = "about_me")
    private String about_me;

    public User() {

    }

    public User(Integer id, String firstName, String last_name, String email, String about_me, String profile_pic) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = last_name;
        this.email = email;
        this.about_me = about_me;
        this.profile_pic = profile_pic;
    }

    public User(String firstName, String last_name, String email, String about_me, String profile_pic) {
        this.first_name = firstName;
        this.last_name = last_name;
        this.email = email;
        this.about_me = about_me;
        this.profile_pic = profile_pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }
}