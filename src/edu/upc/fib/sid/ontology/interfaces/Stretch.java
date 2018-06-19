package edu.upc.fib.sid.ontology.interfaces;

import java.io.Serializable;
import java.beans.PropertyChangeListener;


/**
* Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#Stretch
* @author OntologyBeanGenerator v4.1
* @version 2018/06/19, 11:47:03
*/
public interface Stretch extends River, Serializable {
   // bean stuff
   public void addPropertyChangeListener(PropertyChangeListener pcl);
   public void removePropertyChangeListener(PropertyChangeListener pcl);

   /**
   * Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#nextStretch
   */
   public void setNextStretch(Stretch value);
   public Stretch getNextStretch();

}
