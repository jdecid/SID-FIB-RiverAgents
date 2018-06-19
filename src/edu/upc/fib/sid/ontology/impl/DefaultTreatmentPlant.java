package edu.upc.fib.sid.ontology.impl;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import edu.upc.fib.sid.ontology.interfaces.TreatmentPlant;
import edu.upc.fib.sid.ontology.interfaces.WaterTank;
import jade.util.leap.*;

/**
* Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#TreatmentPlant
* @author OntologyBeanGenerator v4.1
* @version 2018/06/19, 11:47:03
*/
public class DefaultTreatmentPlant implements TreatmentPlant, Serializable {
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

  public DefaultTreatmentPlant() {
    this._internalInstanceName = "";
  }

  public DefaultTreatmentPlant(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#hasTank
   */
   private List hasTank = new ArrayList();
   public void addHasTank(WaterTank elem) {
     hasTank.add(elem);
     pcs.firePropertyChange("hasTank", oldList, this.hasTank);
   }
   public boolean removeHasTank(WaterTank elem) {
     boolean result = hasTank.remove(elem);
     pcs.firePropertyChange("hasTank", oldList, this.hasTank);
     return result;
   }
   public void clearAllHasTank() {
     hasTank.clear();
     pcs.firePropertyChange("hasTank", oldList, this.hasTank);
   }
   public Iterator getAllHasTank() {return hasTank.iterator(); }
   public List getHasTank() {return hasTank; }
   public void setHasTank(List l) {hasTank = l; }

}
