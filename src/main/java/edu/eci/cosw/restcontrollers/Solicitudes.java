/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.samples.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 2089978
 */
@RestController
@RequestMapping("/pedidos")
public class Solicitudes {
    
    @Autowired
    Logica logica; 

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Pedido consped(@PathVariable int id) throws OperationFailedException {
        Pedido pedido = logica.consultarPedido(id);
        if(pedido==null)throw new OperationFailedException();
        return pedido;
    }    
}
