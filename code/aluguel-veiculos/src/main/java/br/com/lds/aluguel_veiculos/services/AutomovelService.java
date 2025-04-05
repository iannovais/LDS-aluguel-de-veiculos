package br.com.lds.aluguel_veiculos.services;

import br.com.lds.aluguel_veiculos.exceptions.ResourceNotFoundException;
import br.com.lds.aluguel_veiculos.models.Automovel;
import br.com.lds.aluguel_veiculos.repositories.AutomovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomovelService {
    private final AutomovelRepository automovelRepository;

    public List<Automovel> listarTodos() {
        return automovelRepository.findAll();
    }

    public Automovel obterPorId(Integer id) {
        return automovelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Automóvel não encontrado"));
    }

    @Transactional
    public Automovel cadastrar(Automovel automovel) {
        if (automovelRepository.existsByPlaca(automovel.getPlaca())) {
            throw new IllegalStateException("Placa já cadastrada");
        }
        if (automovelRepository.existsByMatricula(automovel.getMatricula())) {
            throw new IllegalStateException("Matrícula já cadastrada");
        }
        return automovelRepository.save(automovel);
    }

    @Transactional
    public Automovel atualizar(Integer id, Automovel automovelAtualizado) {
        Automovel automovelExistente = automovelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Automóvel não encontrado"));

        automovelExistente.setMatricula(automovelAtualizado.getMatricula());
        automovelExistente.setAno(automovelAtualizado.getAno());
        automovelExistente.setMarca(automovelAtualizado.getMarca());
        automovelExistente.setModelo(automovelAtualizado.getModelo());
        automovelExistente.setPlaca(automovelAtualizado.getPlaca());
        automovelExistente.setProprietario(automovelAtualizado.getProprietario());

        return automovelRepository.save(automovelExistente);
    }

    @Transactional
    public void excluir(Integer id) {
        if (!automovelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Automóvel não encontrado");
        }
        automovelRepository.deleteById(id);
    }
}