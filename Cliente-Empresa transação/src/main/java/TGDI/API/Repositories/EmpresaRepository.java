package TGDI.API.Repositories;


import TGDI.API.Entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

}
