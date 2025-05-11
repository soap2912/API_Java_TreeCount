package com.example.Api_TreeCount_Fiap.Controllers;

import com.example.Api_TreeCount_Fiap.Models.UserModel;
import com.example.Api_TreeCount_Fiap.Repository.FriendRepository;
import com.example.Api_TreeCount_Fiap.Services.FriendService;
import com.example.Api_TreeCount_Fiap.Services.Util.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/amigos")
    public ResponseEntity<List<UserModel>> getAmigos(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return friendService.getFriends(token);
    }

    // Deletar amizade
    @DeleteMapping("/negar")
    public Map<String, String> negarPedido(@RequestHeader("Authorization") String authHeader, @RequestParam String remetenteId) {
        String token = authHeader.replace("Bearer ", "");

        var responseDTO = friendService.negarPedido(token, remetenteId);

        return StringService.toMapStringString(responseDTO);
    }

    // Solicitar pedido de amizade
    @PostMapping("/solicitar")
    public Map<String, String> solicitarPedido(@RequestHeader("Authorization") String authHeader, @RequestParam String destinatarioId) {
        String token = authHeader.replace("Bearer ", "");

        var responseDTO = friendService.addNewFriend(token, destinatarioId);

        return StringService.toMapStringString(responseDTO);
    }

}
