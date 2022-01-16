

/* Trabalho 1: Dieta de exercicios
* @authors:
*  Edson Carlos
*  Elen Serra
*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import BackEnd.*;

public class App {
public static void main(String[] args) {
	
	String nameUser, date; 
	float height, weight, fatPercentage, time;
	int opcao = 1;
		
	Scanner input = new Scanner(System.in);
	BackEndUser back = new BackEndUser();
	String[] listExercise = back.listExercises();
	boolean continued;
		
	do {
		try {
			continued = true;
			menu();
			opcao = input.nextInt();
			input.nextLine();
			
			switch (opcao) {
			//cadastro usuario
			case 1: {
			
				while (continued) {
										
					try {
						System.out.print("\n\n======= CADASTRO =======");
						System.out.println("\nDigite seu nome: ");
						nameUser = input.nextLine().trim().toUpperCase();
						if (nameUser.equals("0")) {
							break;
						}
						System.out.println("Digite sua altura: ");
						height = Float.parseFloat(input.nextLine());
						System.out.println("Digite seu peso: ");
						weight = Float.parseFloat(input.nextLine());
						System.out.println("Fator de gordura: ");
						fatPercentage = Float.parseFloat(input.nextLine());
				
						//criando o objeto, construindo ->user e passando o que foi digitado pelo teclado
						if (back.addUser(nameUser, height, weight, fatPercentage)) {
							System.out.println("\n>> Usuário cadastrado <<");
						} else {
							System.out.println("\n>> Erro a cadastrar <<");
						}

						continued = false;
					
					} catch(NumberFormatException e) {
						System.out.println("\n\n>>Error. Entrada inválida<<");
						System.out.println("Se quiser voltar para o menu principal digite 0");
						input.nextLine();
					}
				}
			
				break;
			}
		//Gerar o relatórios de exercícios de determinado usuário
			case 2: {
			
			while (continued) {
				try {
					System.out.println("\n\nNome: ");
					nameUser = input.nextLine().trim().toUpperCase();
					if (nameUser.equals("0")) {
						break;
					}
					if (back.isRegistered(nameUser)) {
						while (continued) {
							
							System.out.println("\n1 - Gerar relatórios de "+ nameUser);
							System.out.println("2 - Visualizar um relátório diário.");
							System.out.println("3 - Voltar para o menu principal");
							try {
								opcao = input.nextInt();
								input.nextLine();
								switch (opcao) {
									// Gerando relatórios de exercícios de um usuário
									case 1: {
										
										do { 
											System.out.println("Data do exercicio (Formato: dd/mm/aaaa): ");
											date = input.nextLine();
											
										} while(dateIsValid(date)==false);
										
										while(true) {
											int i = 0;
											System.out.println("\nExercícios");
											while (i < listExercise.length) {
												System.out.println((i+1) + "- "+listExercise[i]);
												i++;
											}
											
											System.out.println("\nSelecione o exercício, ou digite um valor menor ou igual a 0 para sair.");
											try {
												i = input.nextInt()-1;
												input.nextLine();
												
												if (i >= 0 && i < listExercise.length) {
													System.out.println("Quantos minutos? ");
													time = Float.parseFloat(input.nextLine());
													back.generateReport(nameUser, listExercise[i], date, time);												
												} else if (i < 0) {
													break;
												} else {
													System.out.println("\nNão existe um exercício para esse número.");
												}
											} catch (InputMismatchException e) {
												System.out.println("\n\n>>Error. Entrada inválida<<");
												input.nextLine();
											} catch(NumberFormatException e) {
												System.out.println("\n\n>>Error. Entrada inválida<<");    
												input.nextLine();
											}
										}
										break;
									}
									// Visualizando o relatório diário
									case 2: {
										do { 
											System.out.println("Data do exercicio (Formato: dd/mm/aaaa): ");
											date = input.nextLine();
											
										} while(dateIsValid(date)==false);
										
										System.out.println("\n\n------------------------------------------");
										System.out.println(back.getValueReportDay(nameUser, date));
										System.out.println("------------------------------------------");
										break;
									}
									
									case 3: {
										continued = false;
									}
								}
							} catch (InputMismatchException e) {
								System.out.println("\n\n>>Error. Entrada invalida<<\n");
								input.nextLine();
							}
						}
							
					} else {
						System.out.println("\n\n Usuário não cadastrado, tente novamente");
						System.out.println("Se quiser voltar para o menu principal digite 0");
					}
						
						
				} catch(InputMismatchException e) {
					
				}
			}
			opcao = 2;
			}
		}
				
			} catch(InputMismatchException e){
				System.out.println("\n\n>>Error. Entrada invalida<<");
				input.nextLine();
			}
	} while(opcao != 0);
	input.close();


	 }
	 
	public static void menu(){
		System.out.println("\n\n======== GOOGLE FIT ========");
		System.out.println("1 - Cadastrar usuário");
		System.out.println("2 - Gerar e pesquisar relatórios de um usuário");
		System.out.println("0 - Sair");              
		System.out.println("============================");
		System.out.print("Escolha uma opcao: "); 
	}
	 
	public static boolean dateIsValid(String date) {
		if (date == null) {
				return false;
		}
		//retorna um date nesse formato "dd/MM/yyyy", passando uma string como data
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (date.trim().length() != dateFormat.toPattern().length()) {
				return false;
		}
		dateFormat.setLenient(false); //a análise de data nao deve ser tolerante. Interpreta as entradas que não correspondem precisamente ao formato do objeto date
		try {
				dateFormat.parse(date.trim());
		} catch (ParseException ex) {
				//se algum passo dentro do "try" der errado quer dizer que sua data é falsa, então,
				//retorna falso
				return false;
		}
		return true;
	}
 
}
