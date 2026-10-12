package labpractice;

// IMPORT MO TO
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// public class className extends JFrame implements ActionListener <---- Fixed yan wala ka babaguhin maliban sa className = fileName
public class QueueLab extends JFrame implements ActionListener {
    
    private JTextField txtInput; // dito yung pang type
    private DefaultListModel<String> listModel; // eto yung data na ibibigay sa list para may madisplay yung list
    private JList<String> listTask; // eto yung taga display ng list
    private JScrollPane scrollPane; // yung lalagyanan ng text para pwede mag scroll
    private JButton btnEnqueue, btnDequeue, btnPeek, btnClear; // mga button familiar ka naman jan
    private Queue<String> queue;
    
    QueueLab(){
        setTitle("Queue Laboratory Activity");
        setSize(500, 500); // IMPORTANT (setSize(width, height) or setBounds(width, height) yung mismong window inaadjust na size)
        // para isize yung iba need mo i-callout name (example:  dwight.setBounds(50, 100, 400, 250);) kasi pag hindi, ganto mangyayari
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        txtInput = new JTextField();
        txtInput.setBounds(50, 50, 400, 30);
        add(txtInput);
        
        // FE (Frontend JList Model) 
        // gagawa ka ulit neto para ma-apply mo yung lalagyanan ng data or list
        listModel = new DefaultListModel<>();
        
        // same lang din dito
        // BE (Backend Queue implemented using LinkedList)
        queue = new LinkedList<>();
        
        
        // pwede ba iskip tong front end??? or di mo rin gets at daanan natin?
        // JList and ScrollPane setup
        listTask = new JList<>(listModel);
        scrollPane = new JScrollPane(listTask);
        scrollPane.setBounds(50, 100, 400, 250);
        add(scrollPane);
        
        // Buttons
        btnEnqueue = new JButton("Enqueue");
        btnEnqueue.setBounds(50, 400, 85, 30);
        add(btnEnqueue);
        
        btnDequeue = new JButton("Dequeue");
        btnDequeue.setBounds(145, 400, 90, 30);
        add(btnDequeue);
        
        btnPeek = new JButton("Peek");
        btnPeek.setBounds(245, 400, 80, 30);
        add(btnPeek);
        
        btnClear = new JButton("Clear");
        btnClear.setBounds(335, 400, 80, 30); 
        add(btnClear);
        
        // Add to ActionListener   
        // Add mo to para magkaron ng silbi mga buttons
        // kahitAnongPangalan.addActionListener(this); <---- laging (this) yan
        btnEnqueue.addActionListener(this);
        btnDequeue.addActionListener(this);
        btnPeek.addActionListener(this);
        btnClear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnEnqueue){ // if pinindot yung btnEnqueue
            // gagawin to
            
            // String kahitAnongPanglan = txtInput/JTextField().getText.trim();
            // NOTE: laging may ();
            // Explanation 
            // getText()= kunin mo yung nilagay sa textBox
            // trim() = tinatanggal spaces or blanko sa first and last example (imbis na " Paul " magiging "Paul")
            // Gets??
            String item = txtInput.getText().trim(); // <-- offer mo yung pangalan ng String dito)
            
            if(!item.isEmpty()){ // since may ! ibig sabihin kabaligtaran
                // if yung item ay HINDI EMPTY do ths
                
                // Queue offer (adds to the back of the queue)
                
                // pangalan ng queue mo in this case queue
                // queue.offer(item);
                // offer(item) = means add mo to
                queue.offer(item); // Back End
                
                // listModel.addElement(item) -->  ilalagay yung item sa list para makita na, di tulad nung sa offer na di kita
                // eto yun
                listModel.addElement(item); // Front End
                txtInput.setText(""); // <---- auto clear ng text pagkatapos pindutin yung button
            } else {
                JOptionPane.showMessageDialog(this, "Enter text first before enqueueing", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == btnDequeue){ // If pinindot ay yung dequeue button gawin to
            
            if(!queue.isEmpty()){ // (Again merong ! so meaning kabaligtaran) = if yung queue ay may laman
                
                // Queue poll (removes from the front of the queue - FIFO)
                // FIFO = First-In, First-Out <--- Explain ko mamaya
                
                // Ulit ulit lang diba 
                // String removedItem = queue.poll();
                // .poll() = aalisin yung pinaka unahan sa listahan
                // example una yung 1, kahit wala akong sinelect una siyang matatanggal
                // basta pag poll, yung una lagi aalsin
                // Kaya First In, First Out
                // back end lang to so di siya kita sa GUI kahit matanggal to
                
                String removedItem = queue.poll(); // Back End <-- Eto
                
                listModel.remove(0); // Front End (removes the first item displayed)
                // listModel.remove(0) 
                // Kaya 0 kasi 0 yung una lagi sa index
                // eto kita sa GUI
                
                JOptionPane.showMessageDialog(this, "Dequeued: " + removedItem, "Information", JOptionPane.INFORMATION_MESSAGE);
                // JOptionPane.showMessageDialog(this,"Dequeued: " + removedItem, "information", JOptionPane.INFORMATION_MESSAGE);
                // Fixed to di need baguhin
                // if error JOptionPane.ERROR_MESSAGE
                // pag hindi JOptionPane.INFORMATION_MESSAGE
                // format sa parenthesis (this, "text na lalabas" + removedItem, "What type of message (Error Message / information", JOptionPane.INFORMATON_MESSAGE or JOptionPane.ERROR_MESSAGE)
            } else {
                JOptionPane.showMessageDialog(this, "Queue is empty!", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == btnPeek){ // if pinindot ay yung peek button do this
            
            if(!queue.isEmpty()){ // (ulit lang, diba may ! so kabaligtaran) = if yung queue ay di Empty 
                // Queue peek (views the front element without removing it)
                
                String nextItem = queue.peek();
                // String kahitAnongPangalan = queue.peek();
                // peek () = Papakita sayo sino nasa harapan sa pila
                // eto example
                
                // same lang to pwede icopy paste palitan lang konti ibang items depende sa gusto mo
                
               
                // Copy if gusto mo ng success or information message
                JOptionPane.showMessageDialog(this, "Next in line: " + nextItem, "Peek", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Copy if gusto mo ng error message
                JOptionPane.showMessageDialog(this, "Queue is empty!", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == btnClear){ // if pinondot yung clear button
            
            
            queue.clear(); // clear mo yung queue
            listModel.clear(); // clear mo yung list model
        }
    }
}