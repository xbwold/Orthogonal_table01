package com.wold.homepage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.wold.control.CreatUseCase;
import com.wold.control.ReadRule;
import com.wold.file.FileRead;

@SuppressWarnings("serial")
public class MainHome extends JFrame {
	private JTextArea area1;
	private JButton create;
	private JLabel label4;
	private JLabel label5;
	private JTextArea area2;
	private JTextArea area3;

	public MainHome() {
		init();
	}

	public static void main(String[] args) {
		new MainHome();
	}

	/**
	 * ���������
	 */
	public void init() {
		this.setTitle("���������������");// ���ñ���
		this.setBounds(500, 100, 800, 900);
		JPanel panel = new JPanel();// �������
		this.add(panel);
		
		//������
		setJPanel(panel);	
		
		//��������¼�����
		clickAction();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * ����¼�
	 */
	public void clickAction() {
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=area1.getText();
				if(!str.equals("")) {
					ReadRule readrule=new ReadRule();
					//������������Ӻ�ˮƽ
					readrule.analysisStr(str);//��������
					FileRead fileread=new FileRead();
					try {
						//����ȡ�����Ĺ���������Ĺ���ƥ�䣬��ѯ�Ƿ����
						fileread.query(readrule.getRulestr());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(fileread.getHave()) {	//������ڣ��������
						CreatUseCase cuc=new CreatUseCase();
						cuc.create(readrule.getListrule(), readrule.getMap(), readrule.getSpecial(), fileread.getRuletable());
						label4.setText(fileread.getRulestr());
						area2.setText(fileread.getRuletable());
						label5.setText(cuc.getLabel5show());
						area3.setText(cuc.getUsecase());
					}else {	//���򲻴��ڣ�������
						label4.setText("��������û�д˹���");
						area2.setText("");
						area3.setText("");
					}
				}
			}
		});
	}

	/**
	 * ������
	 */
	public void setJPanel(JPanel panel) {
		panel.setLayout(null);// ���þ��Բ���
		// label1��ʾ������ʾ
		JLabel label1 = new JLabel("ʹ��:�ֿ����Ӻ�ˮƽ��ʹ��,�ֿ���ˮƽ");
		label1.setFont(new Font("����", Font.BOLD, 16));
		label1.setBounds(10, 0, 800, 30);
		panel.add(label1);

		area1 = new JTextArea();
		area1.setFont(new Font("����", 0, 20));
		JScrollPane scrollpane1 = new JScrollPane(area1);	//��TextArea��װ��JScrollPane��ʵ�ֹ���Ч��
		scrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//����ˮƽ���������ǳ���(Ĭ���Զ�)
		scrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//���ô�ֱ���������ǳ���
		scrollpane1.setBounds(10, 40, 760, 200);
		panel.add(scrollpane1);

		JLabel label2 = new JLabel("������:");
		label2.setFont(new Font("����", Font.BOLD, 16));
		label2.setBounds(10, 250, 100, 30);
		panel.add(label2);

		area2 = new JTextArea(1, 1);
		area2.setFont(new Font("����", 0, 20));
		area2.setEditable(false); //����ֻ����д
		JScrollPane scrollpane2 = new JScrollPane(area2);	//��TextArea��װ��JScrollPane��ʵ�ֹ���Ч��
		scrollpane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//����ˮƽ���������ǳ���(Ĭ���Զ�)
		scrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//���ô�ֱ���������ǳ���
		scrollpane2.setBounds(10, 290, 760, 200);
		panel.add(scrollpane2);

		JLabel label3 = new JLabel("���������ɵĲ�������:");
		label3.setFont(new Font("����", Font.BOLD, 16));
		label3.setBounds(10, 500, 200, 30);
		panel.add(label3);

		area3 = new JTextArea(1, 1);
		area3.setFont(new Font("����", 0, 20));
		area3.setEditable(false); //����ֻ����д
		JScrollPane scrollpane3 = new JScrollPane(area3);	//��TextArea��װ��JScrollPane��ʵ�ֹ���Ч��
		scrollpane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//����ˮƽ���������ǳ���(Ĭ���Զ�)
		scrollpane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//���ô�ֱ���������ǳ���
		scrollpane3.setBounds(10, 540, 760, 300);
		panel.add(scrollpane3);

		create = new JButton("���ɲ�������");
		create.setBounds(600, 5, 150, 30);
		panel.add(create);
		
		label4=new JLabel();
		label4.setFont(new Font("����", Font.BOLD, 16));
		label4.setBounds(110, 250, 500, 30);
		panel.add(label4);
		
		label5=new JLabel();
		label5.setFont(new Font("����", Font.BOLD, 16));
		label5.setBounds(220, 500, 500, 30);
		panel.add(label5);
	}
}
