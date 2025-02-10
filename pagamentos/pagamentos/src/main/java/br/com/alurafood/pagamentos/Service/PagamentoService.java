package br.com.alurafood.pagamentos.Service;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagamentoService {

    Page<PagamentoDto> obterTodos(Pageable paginacao);

    PagamentoDto obterPorId(Long id);

    PagamentoDto criarPagamento(PagamentoDto dto);

    PagamentoDto atualizarPagamento(Long id, PagamentoDto dto);

    void excluirPagamento(Long id);

    void confirmarPagamento(Long id);
}
