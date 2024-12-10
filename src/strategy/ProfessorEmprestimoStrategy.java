package strategy;

import java.util.Calendar;
import java.util.Date;

public class ProfessorEmprestimoStrategy implements EmprestimoStrategy {
    @Override
    public Date calcularPrazo(Date dataEmprestimo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataEmprestimo);
        cal.add(Calendar.DAY_OF_MONTH, 14); // 14 dias para professores
        return cal.getTime();
    }
}
