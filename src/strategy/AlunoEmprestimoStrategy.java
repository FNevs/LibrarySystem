package strategy;

import java.util.Calendar;
import java.util.Date;

public class AlunoEmprestimoStrategy implements EmprestimoStrategy {
    @Override
    public Date calcularPrazo(Date dataEmprestimo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataEmprestimo);
        cal.add(Calendar.DAY_OF_MONTH, 7); // 7 dias para alunos
        return cal.getTime();
    }
}
