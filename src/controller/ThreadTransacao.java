package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread {

	private int idConta;
	private int saldoConta;
	private int valorTransacao;
	private Semaphore semaforoSaque, semaforoDeposito;
	private int opcao;

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
		
		if(opcao == 0) {
			try {
				semaforoSaque.acquire();
				Saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforoSaque.release();
			}
		}
		else {
			try {
				semaforoDeposito.acquire();
				Deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforoDeposito.release();
			}
			
		}

	}

	public void Saque() {
		
		System.out
				.println("#ID " + idConta + " SAQUE -> Saldo: " + saldoConta + " -> Realizando saque de R$" + valorTransacao);
		saldoConta = saldoConta - valorTransacao;
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("#ID " + idConta + " -> Saque realizado. Saldo Disponível R$" + saldoConta);
	}

	public void Deposito() {
		System.out.println(
				"#ID " + idConta + " DEPOSITO -> Saldo: " + saldoConta + " -> Realizando depósito de R$" + valorTransacao);
		saldoConta = saldoConta + valorTransacao;
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("#ID " + idConta + " -> Depósito realizado. Saldo Disponível R$" + saldoConta);
	}

}
