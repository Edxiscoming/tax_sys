package com.edison.nsfw.complain;

import java.util.Timer;

public class MyTimer {

	public static void main(String[] args) {
		Timer timer = new Timer();
		//�ӳ�2��ִ�У�ÿ3��ִ��һ��
		timer.schedule(new MyTimerTask(), 2000, 3000);
		
	}

}
