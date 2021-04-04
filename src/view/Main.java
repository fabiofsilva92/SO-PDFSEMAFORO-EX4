package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTransacao;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaforoSaque = new Semaphore(1);
		Semaphore semaforoDeposito = new Semaphore(1);
		
		for(int i = 1; i<=20; i++) {
			
			int saldoConta = (int)((Math.random()*5001) + 1000);
			int valorTransacao = (int)((Math.random()*501)+50);
			int opcao = (int)((Math.random()* 99) + 1);
			if(opcao % 2 != 0 ) {
				opcao = 1;
			}
			else{		
				opcao = 0;
			}
			Thread t = new ThreadTransacao(i, saldoConta, valorTransacao, opcao, semaforoSaque, semaforoDeposito);
			t.start();
			
		}
		

	}

}
