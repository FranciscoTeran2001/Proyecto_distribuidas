package notificaciones.controller;

import notificaciones.entity.Notificacion;
import notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService Service;

    @GetMapping
    public List<Notificacion> listarTodas()
    {
        return Service.listarTodas();
    }
}
