package calculator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
public class calculator 
{
	String str=" ";//�ı��������
	JFrame frame;
	JTextField result_Jtf;
	JPanel panel1,panel2,panel3;
	String[] History=new String[5];
	int count_H=0;//���� ��ʷ��¼ �Ĵ���
	int count_equ=0;//���� = �Ĵ���
	
	public calculator ()
	{
		frame=new JFrame("Calculator");
		frame.setLayout(new BorderLayout());
		frame.setLocation(300,200);
		frame.setSize(450,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		result_Jtf=new JTextField(30);
		result_Jtf.setHorizontalAlignment(JTextField.RIGHT);//�Ҷ���
		result_Jtf.setEditable(false);//�ı����ֹ�༭
		
		panel3=new JPanel();
		panel3.setLayout(new GridLayout(1,2,5,5));
		JButton CE=new JButton("CE");
		JButton history=new JButton("��ʷ��¼");
		panel3.add(CE);
		panel3.add(history);
		frame.add(panel3, BorderLayout.SOUTH);
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel1.setLayout(new GridLayout(4,4,5,5));//4*4���񲼾�
		panel2.setLayout(new BorderLayout());//�߽粼��
		panel2.add(result_Jtf);//�ı�������panel2�����
		
		CE.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e)
		        {
		                str=" ";
		                result_Jtf.setText(str);
		        }
		});
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				count_H=count_H%5;//ֻ����ʾ���������¼
				String his_str=History[count_H];
				result_Jtf.setText(his_str);
				count_H++;
				str=" ";
			}
		});
		
		String[] butname= {"7","8","9","+","4","5","6","-","1","2","3","*",".","0","=","/"};
		for (int i=0;i<butname.length;i++)
		{
			JButton button=new JButton(butname[i]);
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					String actionbut=e.getActionCommand();
					int flag=1;
					if (actionbut.equals("+")||actionbut.equals("-")||actionbut.equals("*")||actionbut.equals("/"))
					{
						str=str+" "+actionbut+" ";
					}
					else if (actionbut.equals("="))
					{
						str=str+actionbut+calculate(str);
						result_Jtf.setText(str);
						count_equ=count_equ%5;//���������������¼
						History[count_equ]=str;
						count_equ++;
						str=" ";
						flag=0;
					}
					else
					{
						str=str+actionbut;
					}
					if (flag==1)
						{
							result_Jtf.setText(str);
						}
				}
				public String calculate(String str) 
				{
					String[] comput=str.split(" ");//�ָ������������
					Stack<Double> stack=new Stack<Double>();//�洢����
					stack.push(Double.parseDouble(comput[1]));
					for (int i=2;i<comput.length;i++)
					{
						if (i%2==0)
						{
							if (comput[i].equals("+"))
							{
								stack.push(Double.parseDouble(comput[i+1]));//���ַ���ת������
							}
							if (comput[i].equals("-"))
							{
								stack.push(-Double.parseDouble(comput[i+1]));
							}
							if (comput[i].equals("*"))
							{
								Double m=stack.peek();
								stack.pop();
								Double n=Double.parseDouble(comput[i+1]);//��*ǰ�������������ѹջ
								stack.push(m*n);
							}
							if (comput[i].equals("/"))
							{
								Double m=stack.peek();
								stack.pop();
								Double n=Double.parseDouble(comput[i+1]);
								stack.push(m/n);
							}
						}
					}
					double sum=0;
					while (!stack.isEmpty())
					{
						sum=sum+stack.peek();
						stack.pop();
					}
					String result=String.valueOf(sum);//ת�����ַ�����
					return result;
				}
			});
			panel1.add(button);
		}
		frame.add(panel2,BorderLayout.NORTH);//���ڶ���
		frame.add(panel1,BorderLayout.CENTER);
	}
	public static void main (String[] args)
	{
		new calculator();
	}
}

