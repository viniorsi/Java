package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.Paciente.PacienteRepository;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoInterface {

    @Autowired
    private PacienteRepository repository;
    public void validar(DadosAgendamentoConsulta dados){

        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta náo pode ser agendadda com paciente excluido");
        }

    }
}
