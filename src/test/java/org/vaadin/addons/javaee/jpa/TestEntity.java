package org.vaadin.addons.javaee.jpa;

import javax.persistence.Entity;

import com.googlecode.javaeeutils.jpa.AuditableEntity;


@Entity
public class TestEntity extends AuditableEntity {

    private String testString;

    public TestEntity() {
    }

    public TestEntity(String testString) {
        this.setTestString(testString);
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

}
