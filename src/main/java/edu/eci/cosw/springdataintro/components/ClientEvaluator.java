/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.springdataintro.components;

/**
 *
 * @author hcadavid
 */
public interface ClientEvaluator {
    
    /**
     * Add a new client to the external system evaluation database
     * @param id client identifier
     * @param name client name
     * @throws ClientEvaluationException if an error occurs in the external
     * system during the operation
     */
    public void addNewClient(int id,String name) throws ClientEvaluationException;
    
    /**
     * Check if a user is reported 
     * @param id user identifier
     * @return true if the user is reported, false if not
     * @throws ClientEvaluationException if the given id is invalid (i.e., 
     * negative).
     */
    public boolean reportedUser(int id) throws ClientEvaluationException;
    
}
