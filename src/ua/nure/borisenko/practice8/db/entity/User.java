package ua.nure.borisenko.practice8.db.entity;

import java.util.Objects;

public class User {
    private int id;
    private String login;

    public static User createUser(String login) {
        User user = new User();
        user.setLogin(login);
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Override
    public boolean equals(Object o) {
  
       if(o==null){
           return false;
       }
       if(this.getClass()==o.getClass()){
           return login.equals(((User)o).getLogin());
       }
       return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return  login;
    }

    public static void main(String[] args) {
        User user1;
        user1 = createUser("A");
        User user2 = createUser("A");

        System.out.println(user1.equals(user2));
    }
}
