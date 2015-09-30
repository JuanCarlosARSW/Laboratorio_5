/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.aplication;

import edu.eci.cosw.samples.model.*;
import edu.eci.cosw.springdataintro.components.ClientEvaluationException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author 2089978
 */
public class Programa {

    /**
     * @param args the command line arguments
     * @throws edu.eci.cosw.springdataintro.components.ClientEvaluationException
     */
    public static void main(String[] args) throws ClientEvaluationException {
        // TODO code application logic here
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        Logica l = ac.getBean(Logica.class);
        
        /*
            Operacion en la Que al intentar registrar un cliente con identificador 333 
            (identificador que generará un error en el ‘sistema externo’ simulado), 
            se reverse la transacción (es decir, no debe quedar el usuario en la base de datos).
        */
        //l.registrarCliente(333, "Pablo Perez");
        
        //Operacion en la se que se pueden consultar los clientes a partir de su nombre.
        for(Cliente c : l.clientesReportadosPorApellido("Juan"))
            System.out.println("Cliente"+c+ " : "+c.getIdcliente()+" : "+c.getNombre());
        
        //Operacion en la que se puedan registrar clientes.
        for(int i=65; i<65; i++)l.registrarCliente(i, "Pedro"+i+" Polo");
        for(Cliente c : l.clientesReportadosPorApellido("Polo"))
            System.out.println("Cliente "+c.getIdcliente()+" : "+c.getNombre());
        
        //Operacion en la que se puedan registrar pedidos
        Calendar s = new GregorianCalendar();
        int gh [] = {1,2,3,4,5};
        l.registrarPedido(3, gh, gh, s.getTime());

    }
    
}
