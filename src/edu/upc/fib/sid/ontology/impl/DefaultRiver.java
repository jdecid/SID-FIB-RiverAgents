package edu.upc.fib.sid.ontology.impl;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import edu.upc.fib.sid.ontology.interfaces.River;

/**
* Protege name: http://www.semanticweb.org/josepdecidrodriguez/ontologies/2018/5/untitled-ontology-17#River
* @author OntologyBeanGenerator v4.1
* @version 2018/06/19, 11:47:03
*/
public class DefaultRiver implements River, Serializable {
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

  public DefaultRiver() {
    this._internalInstanceName = "";
  }

  public DefaultRiver(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

}
