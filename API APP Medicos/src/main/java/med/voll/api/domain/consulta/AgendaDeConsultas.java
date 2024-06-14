package med.voll.api.domain.consulta;

import med.voll.api.domain.Paciente.PacienteRepository;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoInterface;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoInterface> validadores;

public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe");
        }

        //usando uma classe para cada validação usamos o principio SOLID de orientado objeto
        // S - Principio da responsabilidade unica - onde cada classe tem apenas uma respossabilidade
        // O - Principio aberto-fechado - aplicado na classe service que esta fechada para modificação
        // não é necessario mexer nela, porem esta aberta a extensão, pois conseguimos adicionar
        // validadores sem mexer nesta classe
        // D - Principio da inversão de dependencia - A classe service depende de uma abstração(interface), ela não depende das
        // classes validadores em especifico

        // percorre a lista de validadores validando 1 por 1
        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if(medico == null){
            throw new ValidacaoException("não existe medico disponivel nesta data!");
        }
        var consulta = new Consulta(null,medico,paciente,dados.data(), null);

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
}

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatoria quando o medico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());

    }

    public void cancelar(DadosCancelamentoConsulta dados) {

        if(!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());


    }
}
