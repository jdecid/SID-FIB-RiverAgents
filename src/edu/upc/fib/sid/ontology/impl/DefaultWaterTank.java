package edu.upc.fib.sid.ontology.impl;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import edu.upc.fib.sid.ontology.interfaces.WaterMass;
import edu.upc.fib.sid.ontology.interfaces.WaterTank;

/**
* Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#WaterTank
* @author OntologyBeanGenerator v4.1
* @version 2018/06/19, 11:47:03
*/
public class DefaultWaterTank implements WaterTank, Serializable {
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

  public DefaultWaterTank() {
    this._internalInstanceName = "";
  }

  public DefaultWaterTank(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#hasWaterMass
   */
   private WaterMass hasWaterMass;
   public void setHasWaterMass(WaterMass value) { 
     pcs.firePropertyChange("hasWaterMass", (this.hasWaterMass==null?new WaterMass():this.hasWaterMass), value);
    this.hasWaterMass=value;
   }
   public WaterMass getHasWaterMass() {
     return this.hasWaterMass;
   }

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#ownedBy
   */
   private Object ownedBy;
   public void setOwnedBy(Object value) { 
     pcs.firePropertyChange("ownedBy", (this.ownedBy==null?new Object():this.ownedBy), value);
    this.ownedBy=value;
   }
   public Object getOwnedBy() {
     return this.ownedBy;
   }

}
