package strategy;

import java.util.Date;

public interface EmprestimoStrategy {
    Date calcularPrazo(Date dataEmprestimo);
}
