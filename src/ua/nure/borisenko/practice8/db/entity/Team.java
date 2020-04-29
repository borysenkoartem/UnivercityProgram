package ua.nure.borisenko.practice8.db.entity;


import java.util.Objects;

public class Team {
    private int id;
    private String name;

    public static Team createTeam(String name) {
        Team team = new Team();
        team.setName(name);
        return team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean equals(Object o) {
       
        if(o==null){
            return false;
        }
        if(this.getClass()==o.getClass()){
            return name.equals(((Team)o).getName());
        }
        return false;
    }

   
}
