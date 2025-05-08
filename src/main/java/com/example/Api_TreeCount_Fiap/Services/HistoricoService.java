package com.example.Api_TreeCount_Fiap.Services;

import com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO.CreateHistoricoDTO;
import com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO.CreateHistoricoResponseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.HistoricoDTO.ListHistoricoByIdResponse;
import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.Models.HistoricoModel;
import com.example.Api_TreeCount_Fiap.Repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository; // Injeção de dependência

    public CreateHistoricoResponseDTO create(CreateHistoricoDTO model) {
        CreateHistoricoResponseDTO response = new CreateHistoricoResponseDTO();

        try {
            // Salvar o objeto HistoricoModel no banco de dados
            HistoricoModel historico = new HistoricoModel(model);
            historico = historicoRepository.save(historico);

            // Preencher a resposta com dados de sucesso, se necessário
            response.setId(historico.getId());  // Exemplo, ajuste conforme sua necessidade
            response.setMessage("Histórico criado com sucesso!");
            response.setSuccess(Boolean.TRUE);

        } catch (Exception e) {
            // Adicionar logging de erro (melhor do que deixar vazio)
            e.printStackTrace();
            response.setMessage("Erro ao criar histórico: " + e.getMessage());
            response.setSuccess(Boolean.FALSE);
        }

        return response;
    }

    public ListHistoricoByIdResponse buscarPorUsuario(String userId) {
        var response = new ListHistoricoByIdResponse();

        try {
            List<HistoricoModel> historico = historicoRepository.findByUserId(userId);

            response.setHistorico(historico);
            response.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            response.setHistorico(null);
            response.setMessage("Erro ao buscar histórico: " + e.getMessage());
            response.setSuccess(Boolean.FALSE);
        }

        return response;
    }

    public ResponseBaseDTO deleteHistorico(Long id) {
        ResponseBaseDTO responseDTO = new ResponseBaseDTO();

        try {
            Optional<HistoricoModel> historicoOpt = historicoRepository.findById(id);

            if (historicoOpt.isEmpty()) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Histórico não encontrado para exclusão");
                return responseDTO;
            }

            HistoricoModel historico = historicoOpt.get();
            historico.delete(); // Soft delete (preenche o campo deletedAt)
            historicoRepository.save(historico); // Persiste o soft delete

            responseDTO.setSuccess(true);
            responseDTO.setMessage("Histórico deletado com sucesso");

        } catch (Exception e) {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Erro ao tentar excluir o histórico: " + e.getMessage());
        }

        return responseDTO;
    }



}
