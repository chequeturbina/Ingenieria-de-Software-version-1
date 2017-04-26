/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Mapeo.Puesto;
import Modelo.PuestoModel;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Emmanuel
 */
@Controller
public class controladorbase {
   
    @Autowired
    PuestoModel puesto_db;

    @RequestMapping(value="/crearPuesto", method = RequestMethod.POST)
    public ModelAndView creaPuesto(ModelMap model, HttpServletRequest request) throws ServletException, IOException, ParseException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String menu = request.getParameter("menu");
        float latitud = Float.parseFloat(request.getParameter("latitud"));
        float longitud = Float.parseFloat(request.getParameter("longitud"));
        puesto_db.guardar(nombre, descripcion, menu,latitud,longitud);
        return new ModelAndView("/principalbase"); 
        
    }
    
    @RequestMapping(value="/listapuesto", method=RequestMethod.GET)
    public ModelAndView listapuesto(ModelMap model,HttpServletRequest request){
        List u = puesto_db.listarpuestos();
        model.addAttribute("puestos",u);
        return new ModelAndView("listapuesto",model);
    }
    
    @RequestMapping(value="editar/Puesto", method = RequestMethod.POST)
               public ModelAndView editapuesto(ModelMap model, HttpServletRequest request) throws ServletException, IOException, ParseException {
                 long id_puesto = Long.parseLong(request.getParameter("id_puesto"));
                 String nombre = request.getParameter("name");
                 String descripcion = request.getParameter("descripcion");
                 String menu = request.getParameter("menu");
                 puesto_db.update(id_puesto,nombre, descripcion, menu);
                 return new ModelAndView("lista", model); 
               }
    
}