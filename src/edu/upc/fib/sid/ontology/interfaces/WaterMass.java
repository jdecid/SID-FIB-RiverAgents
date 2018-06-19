package edu.upc.fib.sid.ontology.interfaces;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


/**
* Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#WaterMass
* @author OntologyBeanGenerator v4.1
* @version 2018/06/19, 11:47:03
*/
public interface WaterMass extends jade.content.Concept, Serializable {
   // bean stuff
   public void addPropertyChangeListener(PropertyChangeListener pcl);
   public void removePropertyChangeListener(PropertyChangeListener pcl);

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#volume
   */
   public void setVolume(String value);
   public String getVolume();

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#contaminant
   */
   public void setContaminant(String value);
   public String getContaminant();

}
