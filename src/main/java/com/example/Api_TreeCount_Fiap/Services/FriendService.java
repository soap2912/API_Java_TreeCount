package com.example.Api_TreeCount_Fiap.Services;

import com.example.Api_TreeCount_Fiap.Controllers.FriendController;
import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.Models.FriendModel;
import com.example.Api_TreeCount_Fiap.Models.UserModel;
import com.example.Api_TreeCount_Fiap.Repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FriendService {

    @Autowired
    private  UserService userService;
    @Autowired
    private FriendRepository friendRepository;

    public ResponseBaseDTO addNewFriend(String token, String userId) {

        boolean isAuthenticaded = userService.isAuthenticatedUser(token, userId);

        var response = new ResponseBaseDTO();

        if (!isAuthenticaded) {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("User não autenticado");
            return response;
        }

        Optional<UserModel> user = userService.getUserByJwtToken(token);

        if (!user.isPresent()) {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("User não autenticado");
            return response;
        }

        var user2 = userService.getUserById(userId);

        if(user2 == null) {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("Usuario não  encontrado");
            return response;
        }

        FriendModel friendModel = new FriendModel();
        friendModel.setUser1_id(user.get().getId());
        friendModel.setUser2_id(user2.getId());

        friendRepository.save(friendModel);

        response.setSuccess(Boolean.TRUE);
        response.setMessage("Solicitação enviada com sucesso");
        return response;
    }

    public ResponseBaseDTO negarPedido(String token, String remetenteId) {
        var response = new ResponseBaseDTO();

        // Extrai o usuário atual via token
        Optional<UserModel> user = userService.getUserByJwtToken(token);

        if (!user.isPresent()) {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("Usuário não autenticado");
            return response;
        }

        String userAtualId = user.get().getId();

        // Busca amizade pendente onde o solicitante é o remetente e o destinatário é o usuário atual
        Optional<FriendModel> amizade = friendRepository
                .findPendingFriendship(userAtualId, remetenteId);

        if (!amizade.isPresent()) {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("Pedido de amizade não encontrado ou já processado.");
            return response;
        }

        // Marcar como não pendente (pode-se deletar ou apenas atualizar o flag)
        FriendModel model = amizade.get();
        model.setPending(false); // ou model.delete() se quiser soft delete

        friendRepository.save(model); // se usar delete(), troque por friendRepository.delete(model);

        response.setSuccess(Boolean.TRUE);
        response.setMessage("Pedido de amizade negado com sucesso.");
        return response;
    }

    public ResponseEntity<List<UserModel>> getFriends(String token) {
        Optional<UserModel> userOpt = userService.getUserByJwtToken(token);

        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userId = userOpt.get().getId();

        List<FriendModel> amizades = friendRepository.findAllFriendsByUserId(userId);

        List<String> idsAmigos = amizades.stream()
                .map(amizade -> amizade.getUser1_id().equals(userId) ? amizade.getUser2_id() : amizade.getUser1_id())
                .distinct()
                .collect(Collectors.toList());

        List<UserModel> amigos = userService.getUsersByIds(idsAmigos);

        return ResponseEntity.ok(amigos);
    }


}
