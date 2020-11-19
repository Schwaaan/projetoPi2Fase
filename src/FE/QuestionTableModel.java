package FE;
import BE.domain.Question;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class QuestionTableModel extends AbstractTableModel {
    private List<Question> questions = new ArrayList<>();
    private String[] columns = new String[] {"Id", "Id do Professor", "Questão"};

    public QuestionTableModel(List<Question> list) {
        this.questions = list;
    }

    @Override
    public String getColumnName(int column) {
        String columnName = null;

        if (column >= 0 && column <= columns.length) {
            columnName = columns[column];
        }

        return columnName;
    }

    @Override
    public int getRowCount() {
        return questions.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    // implementado apenzas porque é obrigatório, mas não iremos usar por enquanto
    
    
    
    
    //victor
    //onde tem os bot�es precisa criar o metodo recarregar 
    @Override
    public Object getValueAt(int arg0, int arg1) {
        String value = null;

        if (rowIndex >= 0 && rowIndex <= tasks.size()) {
            Task task = task.get(rowIndex);

            switch (colIndex) {
                case 0:
                    value = Integer.toString(task.getId());
                    break;
                case 1:
                    value = task.getTask();
                    break;
                case 2:
                    value = task.getDescription();
                    break;
                default:
                    System.err.printf("[ERRO] �ndice e coluna inv�lido: %d\n", colIndex);
                    break;
            }
        }

        return value;
    }

	public void load(List<Task> listTasks) {
        this.tasks = listTasks;
        fireTableDataChanged();
    }

    public Task getTask(int rowIndex) {
        Task task = null;
        
        if (rowIndex >= 0 && rowIndex <= tasks.size()) {
            task = tasks.get(rowIndex);
        }

        return task;
    }

	public void delete(Task task) {
        tasks.remove(task);
        fireTableDataChanged();
    }


}
