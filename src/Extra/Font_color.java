package Extra;

import java.awt.Color;
import java.awt.Font;

public class Font_color {
	
	public Font[] f = new Font[6];
	public Color[] color = new Color[13];
	public Font_color()
	{
		f[0] = new Font("�s�ө���",Font.BOLD,30);
		f[1] = new Font("�L�n������",Font.ITALIC,25);
		f[2] = new Font("�з���",Font.BOLD,30);
		f[3] = new Font("�s�ө���",Font.PLAIN,25);
		f[4] = new Font("�з���",Font.PLAIN,25);
		f[5] = new Font("�ө���" , Font.LAYOUT_RIGHT_TO_LEFT,20);
		
		//�w�]
		color[0] = new Color(220,225,205);//�̶�
		color[1] = new Color(245,250,230);
		color[2] = new Color(235,240,220);
		// 
		// ��
		color[3] = new Color(155,185,225);
		color[4] = new Color(135,165,195);
		color[5] = new Color(155,205,245);
		//
		//��
		color[6] = new Color(225,175,185);
		color[7] = new Color(195,145,155);
		color[8] = new Color(205,165,175);
		//
		//�t
		color[9] = new Color(105,105,105);
		color[10] = new Color(160,160,160);
		color[11] = new Color(145,145,145);
		color[12] = new Color(200,220,200);
		//
	}
}
