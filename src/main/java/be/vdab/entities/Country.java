package be.vdab.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "countries")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Version
    private long version;

    @OneToMany(mappedBy = "country")
    private Set<Customer> customers;

    public Country(String name) {
	this.name = name;
	customers = new LinkedHashSet<>();
    }

    public Country() {

    }

    public long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

}
