package project.CrimeRec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inmate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long indent;

    private String name;

    private int age;

    private String sex;

    private String offense;

    public Inmate() {

    }

    public long getIndent() {
        return indent;
    }

    public void setIndent(long indent) {
        this.indent = indent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOffense() {
        return offense;
    }

    public void setOffense(String offense) {
        this.offense = offense;
    }
}
