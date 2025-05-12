package com.example.Api_TreeCount_Fiap.Services;



import com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO.CreateArvoreDTO;
import com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO.CreateArvoreResponseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO.GetArvoreByIdResponseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO.ListAllResponseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;
import com.example.Api_TreeCount_Fiap.Repository.ArvoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArvoreService {

    @Autowired
    private ArvoreRepository arvoreRepository;

    public CreateArvoreResponseDTO salvar(CreateArvoreDTO dto) {

        CreateArvoreResponseDTO response = new CreateArvoreResponseDTO();

        try {
            ArvoreModel arvore = new ArvoreModel(dto);

            arvore = arvoreRepository.save(arvore);

            if(arvore == null)
            {
                response.setSuccess(Boolean.FALSE);
                response.setMessage("Não foi possivel salvar o arquivo");

                return response;
            }

            response.setSuccess(Boolean.TRUE);
            response.setMessage("Registro Salvo com sucesso");

            return response;
        } catch ( Exception e){

            response.setSuccess(Boolean.FALSE);
            response.setMessage("Não foi possivel salvar o arquivo. Erro: " + e.getMessage());

            return response;
        }
    }

    public ListAllResponseDTO listarTodas() {
        ListAllResponseDTO response = new ListAllResponseDTO();

        try {
            // Tenta buscar todas as árvores no repositório
            List<ArvoreModel> listaArvores = arvoreRepository.findAll();

            // Define os dados de sucesso
            response.setSuccess(true);
            response.setMessage("Lista de árvores carregada com sucesso.");
            response.setArvores(listaArvores);

        } catch (Exception e) {
            // Se ocorrer algum erro, captura a exceção e configura a resposta de erro
            response.setSuccess(false);
            response.setMessage("Erro ao carregar lista de árvores: " + e.getMessage());
            // Opcional: Logar a exceção para análise posterior
            // logger.error("Erro ao carregar a lista de árvores", e);
        }

        return response;
    }

    public GetArvoreByIdResponseDTO buscarPorId(Long id) {

        var response = new GetArvoreByIdResponseDTO();

        try {
            // Usando Optional de forma segura
            ArvoreModel arvore = arvoreRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Arvore não encontrada"));

            response.setSuccess(Boolean.TRUE);
            response.setArvore(arvore);
        } catch (RuntimeException e) {
            // Tratando o erro de não encontrar a árvore ou erro de banco
            response.setMessage(e.getMessage());
            response.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            // Caso outro tipo de erro ocorra
            response.setMessage("Erro ao buscar arvore: " + e.getMessage());
            response.setSuccess(Boolean.FALSE);
        }

        return response;
    }

    //Metodo update da arvore
    public ResponseBaseDTO update(Long id, CreateArvoreDTO dto) {
        var response = new ResponseBaseDTO();

        try {
            Optional<ArvoreModel> arvoreOpt = arvoreRepository.findById(id);

            if (arvoreOpt.isEmpty()) {
                response.setSuccess(false);
                response.setMessage("Árvore não encontrada");
                return response;
            }

            ArvoreModel arvore = arvoreOpt.get();
            arvore.setNomePopular(dto.getNomePopular());
            arvore.setNomeCientifico(dto.getNomeCientifico());
            arvore.setDescricao(dto.getDescricao());
            arvore.setFormulaCarbono(dto.getFormulaCarbono());
            arvore.setTipo(dto.getTipo());

            arvoreRepository.save(arvore);

            response.setSuccess(true);
            response.setMessage("Árvore atualizada com sucesso");

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Erro ao atualizar árvore: " + e.getMessage());
        }

        return response;
    }


    public ResponseBaseDTO deletar(Long id) {
        ResponseBaseDTO responseDTO = new ResponseBaseDTO();

        try {
            Optional<ArvoreModel> arvoreOpt = arvoreRepository.findById(id);

            if (arvoreOpt.isEmpty()) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Árvore não encontrada para exclusão");
                return responseDTO;
            }

            ArvoreModel arvore = arvoreOpt.get();
            arvore.delete(); // aplica o soft delete (data atual no deletedAt)

            arvoreRepository.save(arvore); // salva as alterações

            responseDTO.setSuccess(true);
            responseDTO.setMessage("Árvore deletada com sucesso");

        } catch (Exception e) {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Erro ao tentar excluir a árvore: " + e.getMessage());
        }

        return responseDTO;
    }

}
