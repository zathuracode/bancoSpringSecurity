package com.vobi.banco.modelo;
// Generated Apr 27, 2017 10:38:54 AM by Hibernate Tools 5.1.0.Final


import java.util.HashSet;
import java.util.Set;

/**
 * TiposUsuarios generated by hbm2java
 */
public class TiposUsuarios  implements java.io.Serializable {


     private Long tusuCodigo;
     private String tusuNombre;
     private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);

    public TiposUsuarios() {
    }

	
    public TiposUsuarios(Long tusuCodigo, String tusuNombre) {
        this.tusuCodigo = tusuCodigo;
        this.tusuNombre = tusuNombre;
    }
    public TiposUsuarios(Long tusuCodigo, String tusuNombre, Set<Usuarios> usuarioses) {
       this.tusuCodigo = tusuCodigo;
       this.tusuNombre = tusuNombre;
       this.usuarioses = usuarioses;
    }
   
    public Long getTusuCodigo() {
        return this.tusuCodigo;
    }
    
    public void setTusuCodigo(Long tusuCodigo) {
        this.tusuCodigo = tusuCodigo;
    }
    public String getTusuNombre() {
        return this.tusuNombre;
    }
    
    public void setTusuNombre(String tusuNombre) {
        this.tusuNombre = tusuNombre;
    }
    public Set<Usuarios> getUsuarioses() {
        return this.usuarioses;
    }
    
    public void setUsuarioses(Set<Usuarios> usuarioses) {
        this.usuarioses = usuarioses;
    }




}


