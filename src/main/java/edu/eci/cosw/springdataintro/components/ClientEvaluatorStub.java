/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.springdataintro.components;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class ClientEvaluatorStub implements ClientEvaluator{

    @Override
    public boolean reportedUser(int id) throws ClientEvaluationException{
        if (id<0){
            throw new ClientEvaluationException("Invalid id (negative):"+id);
        }
        return id%2==0;        
    }

    @Override
    public void addNewClient(int id, String name) throws ClientEvaluationException {
        if (id==333){
            throw new ClientEvaluationException("User already exist in the external"
                    + " database:"+id);
        }
    }
    
    
}
