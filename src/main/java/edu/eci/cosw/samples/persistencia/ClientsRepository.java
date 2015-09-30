/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.persistencia;

import edu.eci.cosw.samples.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 2089978
 */
public interface ClientsRepository extends CrudRepository<Cliente, Integer> {
    
    @Query("from Cliente c where c.nombre like :ln")
    public List<Cliente> search(@Param("ln") String searchTerm);    
    
    @Query("from Cliente c where c.idcliente=:lid")
    public Cliente searchByID(@Param("lid") Integer searchTerm);    
    
}
