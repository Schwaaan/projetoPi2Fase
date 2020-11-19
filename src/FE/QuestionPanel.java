package FE;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BE.services.QuestionService;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionPanel extends JPanel {
    HomeFrame frame;
    QuestionTableModel tableModel;

    private JButton btnCreate;
    private JButton btnChange;
    private JButton btnRemove;

    private JTable tableQuestions;
    
    public QuestionPanel(HomeFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout(10, 10));

        createBtns();
        createTable();
    }

    private void createTable() {
        tableModel = new QuestionTableModel(QuestionService.list());
        tableQuestions = new JTable();
        tableQuestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableQuestions.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    if (tableQuestions.getSelectedRow() >= 0) {
                        ablebtns();
                    } else {
                        disableBtns();
                    }
                }
            }

        });

        JScrollPane scroll = new JScrollPane(tableQuestions);

        add(scroll, BorderLayout.CENTER);
    }
    
    private void createBtns() {
        JPanel panelBtn = new JPanel();
        FlowLayout layout = (FlowLayout) panelBtn.getLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        
        btnCreate = new JButton("Adicionar");

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO chamar o m�todo que mostra a tela de formul�rio de quest�o
            }
        });

        panelBtn.add(btnChange);
        
        btnChange = new JButton("Alterar");

        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {   
            	//quando tu clica aqui, dispara um evento que pega a questao do table model,
            			// pegando ela pela linha. OBJETO DO TIPO TAREFA
                Task task = tableModel.getTask();	
                //ELE RECEBE A TAREFA, PASSA PRO FRAME PRINCIPAL E O FRAME PRINCIPAl,PASSA PRO FORMULARIO
                frame.showFormPanel(task);
            }
        });
        //aqui eu atualizo minha tabela que ja veio com os valores
        //mas agora ela volta com os valores que mando a ela
        public void reload() {
        	tableModel.load(QuestionService.list());
        }
             
        
        panelBtn.add(btnChange);
        
        btnRemove = new JButton("Remover");

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO aplicar os m�todos de remo��o
            }
        });

        panelBtn.add(btnRemove);
        
        add(panelBtn, BorderLayout.NORTH);
        
    }

    private void disableBtns() {
        btnChange.setEnabled(false);
        btnRemove.setEnabled(false);
    }
    
    private void ablebtns() {
        btnChange.setEnabled(true);
        btnRemove.setEnabled(true);
    }
}