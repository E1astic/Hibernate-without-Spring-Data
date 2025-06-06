package ru.fil.training.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fil.training.model.dto.ClientRequest;
import ru.fil.training.model.dto.ClientResponse;
import ru.fil.training.model.dto.ResponseBody;
import ru.fil.training.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientResponse getClientById(@PathVariable("id") int id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseBody> addClient(@RequestBody ClientRequest client) {
        clientService.addClient(client);
        return ResponseEntity.ok().body(new ResponseBody("Клиент успешно добавлен"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> updateClient(@PathVariable("id") int id,
                                                     @RequestBody ClientRequest updatedClient) {
        clientService.updateClient(id, updatedClient);
        return ResponseEntity.ok().body(new ResponseBody("Данные клиента успешно обновлены"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteClient(@PathVariable("id") int id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().body(new ResponseBody(
                String.format("Клиент с id = %d успешно удален", id)));
    }
}
