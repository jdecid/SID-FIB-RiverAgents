package edu.upc.fib.sid.ontology.interfaces;

import java.io.Serializable;
import java.beans.PropertyChangeListener;

import jade.util.leap.*;

/**
* Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#TreatmentPlant
* @author OntologyBeanGenerator v4.1
* @version 2018/06/19, 11:47:03
*/
public interface TreatmentPlant extends jade.core.AID, Serializable {
   // bean stuff
   public void addPropertyChangeListener(PropertyChangeListener pcl);
   public void removePropertyChangeListener(PropertyChangeListener pcl);

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#hasTank
   */
   public void addHasTank(WaterTank elem);
   public boolean removeHasTank(WaterTank elem);
   public void clearAllHasTank();
   public Iterator getAllHasTank();
   public List getHasTank();
   public void setHasTank(List l);

}
