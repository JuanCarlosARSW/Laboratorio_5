/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.model;

import java.util.List;
import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import edu.eci.cosw.samples.persistencia.*;
import org.springframework.stereotype.Service;
import edu.eci.cosw.springdataintro.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 2089978
 */
@Service
public class Logica {
    
    @Autowired
    private ClientsRepository clientsrepository;
    @Autowired
    private ClientEvaluator clientevaluator;
    @Autowired
    private OrderRepository orderrepository;
    @Autowired
    private ProductsRepository productsrepository;
    @Autowired
    private OrderDetailRepository orderdetailrepository;
        
    /*
        *@obj: obtener los clientes cuyo nombre contenga un apellido
        *determinado, y que se encuentren reportados en una central de riesgo.
        *@param: apellido del cliente
        *@return: la lista de los clientes reportados
    */
    public List<Cliente> clientesReportadosPorApellido(String apellido) throws ClientEvaluationException{
        List<Cliente> lc = new ArrayList<>();
        for(Cliente c:clientsrepository.search("%"+apellido+"%"))if(clientevaluator.reportedUser(c.getIdcliente()))lc.add(c);
        return lc;
    }

    /*
        *@obj: obtener el pedido solicitado
        *@param: id del pedido
        *@return: el pedido solicitado
    */
    public Pedido consultarPedido(int id){
        return orderrepository.findOne(id);
    }    

    /*
        *@obj: obtener el cliente solicitado
        *@param: id del cliente
        *@return: el cliente solicitado
    */
    public Cliente consultarCliente(int id){
        return clientsrepository.findOne(id);
    }    
    
    /*
        *@obj: registrar un nuevo cliente local y remotamente, garantizando
        * que sólo se registrará localmente si queda registrado en el
        * sistema remoto.
        *@param id identificador del cliente
        *@param nombre nombre del cliente
    */
    @Transactional(rollbackFor = ClientEvaluationException.class)
    public void registrarCliente(int id, String nombre) throws ClientEvaluationException{
        Cliente cl = new Cliente();
        cl.setIdcliente(id);
        cl.setNombre(nombre);
        cl.setDireccion(nombre + " "+id);
        cl.setTelefono(id+""+id+""+id);
        clientsrepository.save(cl);
        clientevaluator.addNewClient(id, nombre);
    }
    
    /*
        * Hacer persistente un nuevo pedido, creado a partir del identificador de cliente dado, los identificadores
        * de los productos, sus cantidades, y la fecha.
        * @pre identificadorProductos.lenght==cantidades.lenght
        * @param idCliente identificador del cliente que hace el pedido
        * @param identificadoresProductos conjunto de identificadores de los productos que componen el pedido
        * @param cantidades conjunto de cantidades de los productos pedidos. La cantidad i, será entonces,
        * la cantidad asociada al producto i. 
    */
    public void registrarPedido(int idCliente, int[] identificadoresProductos, int[] cantidades, Date fecha){
        Cliente c = clientsrepository.searchByID(idCliente);
        List<DetallePedidoId> ldpid = new ArrayList<>();
        Pedido p = new Pedido(c, fecha);
        orderrepository.save(p);
        for(int i:identificadoresProductos)
            ldpid.add(new DetallePedidoId(i, p.getIdpedido()));
        List<DetallePedido> ldp = new ArrayList<>();
        for(int i=0; i<ldpid.size(); i++){
            DetallePedido detail = new DetallePedido(ldpid.get(i), productsrepository.searchByID(identificadoresProductos[i]), cantidades[i]);
            ldp.add(detail);
            orderdetailrepository.save(detail);
        }
        for(int i:identificadoresProductos){
            Producto detail = productsrepository.findOne(i);
            detail.setDetallesPedidos(new HashSet<DetallePedido>(ldp));
            productsrepository.save(detail);
        }
        p.setDetallesPedidos(new HashSet<DetallePedido>(ldp));
        orderrepository.save(p);
    }

}