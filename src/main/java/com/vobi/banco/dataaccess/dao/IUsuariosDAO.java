package com.vobi.banco.dataaccess.dao;

import com.vobi.banco.dataaccess.api.Dao;
import com.vobi.banco.modelo.Usuarios;


/**
* Interface for   UsuariosDAO.
*
*/
public interface IUsuariosDAO extends Dao<Usuarios, Long> {
	public Usuarios consultarUsuarioPorUsuLogin(String usuLogin);
}
