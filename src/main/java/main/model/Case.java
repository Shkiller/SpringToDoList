package main.model;

import javax.persistence.*;

@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "case_string",nullable = true)
    private String caseString;

    public String getCaseString() {
        return caseString;
    }

    public void setCaseString(String caseString) {
        this.caseString = caseString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
