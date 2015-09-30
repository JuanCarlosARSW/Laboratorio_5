/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.samples.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.cosw.springdataintro.components.ClientEvaluationException;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("/clientes")
public class ControllerClient {
    @Autowired
    Logica logica;     
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@RequestBody Cliente c) throws ClientEvaluationException {
        //hacer persistente el cliente
        logica.registrarCliente(c.getIdcliente(), c.getNombre());
        //retornar el estado 201 en caso de que la operación haya sido exitosa
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Cliente consped(@PathVariable int id) throws OperationFailedException {
        Cliente cliente = logica.consultarCliente(id);
        if(cliente==null)throw new OperationFailedException();
        return cliente;
    }   
}
