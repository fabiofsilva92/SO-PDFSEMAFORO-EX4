package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread {

	private int idConta;
	private int saldoConta;
	private int valorTransacao;
	private Semaphore semaforoSaque, semaforoDeposito;
	private int opcao;

	//Construutor da Thread
	public ThreadTransacao(int idConta, int saldoConta, int valorTransacao, int opcao, Semaphore semaforoSaque,
			Semaphore semaforoDeposito) {
		this.idConta = idConta;
		this.saldoConta = saldoConta;
		this.valorTransacao = valorTransacao;
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
		this.opcao = opcao;
	}

	@Override
	public void run() {
			try {
				if(opcao == 0) {
					semaforoSaque.acquire();
					Transacao();
				}
				else{
					semaforoDeposito.acquire();
					Transacao();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if(opcao == 0) {
					semaforoSaque.release();
				}
				else {
					semaforoDeposito.release();
				}
			}
	}
	
	//Transações disponiveis.
	public void Transacao() {
		if(opcao ==0) {
			System.out.println("#ID " + idConta + " SAQUE -> Saldo: " + saldoConta + " -> Realizando saque de R$" + valorTransacao);

			saldoConta -= valorTransacao;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#ID " + idConta + " -> Saque realizado. Saldo Disponível R$" + saldoConta);
		}
		else {
			System.out.println("#ID " + idConta + " DEPOSITO -> Saldo: " + saldoConta + " -> Realizando depósito de R$"+ valorTransacao);
			
			saldoConta += valorTransacao;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#ID " + idConta + " -> Depósito realizado. Saldo Disponível R$" + saldoConta);
		}
	}
}
