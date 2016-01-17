

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class A extends JFrame{

	private JButton b1,b2;
	private JProgressBar bar1,bar2;
	
	public A(){
		
		setLayout(new FlowLayout());

		
		b1 = new JButton("Interdeterminate Bar Button");
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				Interdetminate();
				
				
			}
			
			
			
			
		});
		
		b2 = new JButton("Percentage Bar Button");
		b2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				
				Percentage();
			}

		});

		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar2.setStringPainted(true);
		
		add(b1);
		add(bar1);
		add(b2);
		add(bar2);

		
		setSize(607,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	
	private void Interdetminate(){
		
		SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				bar1.setIndeterminate(true);
				b1.setEnabled(false);
				for(int i =0;i<30;i++){
					
					Thread.sleep(100);
					System.out.println("Process : "+i);
					
				}
				
				return null;
			}

			@Override
			protected void done() {
				bar1.setIndeterminate(false);
				b1.setEnabled(true);

				JOptionPane.showMessageDialog(null, "Job is DONE");

				
				
			}};
			worker.execute();
	}
	
	private void Percentage(){
		
		SwingWorker<Boolean,Integer> worker = new SwingWorker<Boolean,Integer>(){
			@Override
			protected Boolean doInBackground() throws Exception {
				bar2.setValue(0);
				b2.setEnabled(false);
	            for(int i =1;i<101;i++){
					
					Thread.sleep(100);
					System.out.println("Process : "+i);
					publish(i);
				}
				
				
				return true;
			}
			
			
			@Override
			protected void process(List<Integer> chunks) {

			
			Integer value = chunks.get(chunks.size() - 1);
			
			bar2.setValue(value);
			}

			
			
			

			@Override
			protected void done() {
				try {
					Boolean status = get();
					b2.setEnabled(true);
					JOptionPane.showMessageDialog(null, "Job 2 is DONE"+status);
					//Again reset progress bar
					bar2.setValue(0);

					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

				
			}};
			worker.execute();
	}
	
	
}
