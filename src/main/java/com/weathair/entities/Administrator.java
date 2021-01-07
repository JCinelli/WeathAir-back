package com.weathair.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author MIACHELL
 *
 * Class for Administrator
 * Extends User 
 * 
 * Primary Key id
 */
@Entity
@DiscriminatorValue("A")
public class Administrator extends User {

}
