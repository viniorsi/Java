package br.com.alurafood.pagamentos.Service;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.http.PedidoClient;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoClient pedido;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> obterTodos(Pageable paginacao) {
        return pagamentoRepository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDto.class));
    }


    public PagamentoDto obterPorId(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);

        pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public void confirmarPagamento(Long id) {
        // Verifica se o pagamento existe
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException("Pagamento não encontrado com o ID: " + id);
        }

        // Atualiza o status do pagamento
        Pagamento pagamentoAtual = pagamento.get();
        pagamentoAtual.setStatus(Status.CONFIRMADO);
        pagamentoRepository.save(pagamentoAtual);

        // Chama o cliente HTTP para atualizar o pedido
        try {
            pedido.atualizaPagamento(pagamentoAtual.getPedidoId());
        } catch (Exception e) {
            // Loga ou trata erros de comunicação com o serviço de pedidos
            throw new RuntimeException("Erro ao confirmar o pedido: " + e.getMessage());
        }
    }
}
