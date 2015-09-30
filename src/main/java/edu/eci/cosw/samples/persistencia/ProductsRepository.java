/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.persistencia;

import java.util.List;
import edu.eci.cosw.samples.model.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author 2089978
 */
public interface ProductsRepository extends CrudRepository<Producto, Integer> {

    @Query("from Producto p where p.idproducto=:lid")
    public Producto searchByID(@Param("lid") Integer searchTerm);
    
}
