package edu.upc.fib.sid.ontology.impl;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import edu.upc.fib.sid.ontology.interfaces.WaterMass;

/**
* Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#WaterMass
* @author OntologyBeanGenerator v4.1
* @version 2018/06/19, 11:47:03
*/
public class DefaultWaterMass implements WaterMass, Serializable {
   // bean stuff
   protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   public void addPropertyChangeListener(PropertyChangeListener pcl) {
     pcs.addPropertyChangeListener(pcl);
   }

   public void removePropertyChangeListener(PropertyChangeListener pcl) {
     pcs.removePropertyChangeListener(pcl);
   }


  private static final long serialVersionUID = 3087186635179339273L;

  private String _internalInstanceName = null;

  public DefaultWaterMass() {
    this._internalInstanceName = "";
  }

  public DefaultWaterMass(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#volume
   */
   private String volume;
   public void setVolume(String value) { 
     pcs.firePropertyChange("volume", (this.volume==null?new String():this.volume), value);
    this.volume=value;
   }
   public String getVolume() {
     return this.volume;
   }

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#contaminant
   */
   private String contaminant;
   public void setContaminant(String value) { 
     pcs.firePropertyChange("contaminant", (this.contaminant==null?new String():this.contaminant), value);
    this.contaminant=value;
   }
   public String getContaminant() {
     return this.contaminant;
   }

}
