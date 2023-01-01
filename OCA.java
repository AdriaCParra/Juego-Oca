import java.lang.Thread;
import java.util.Scanner;

public class OCA {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String noms[];
        int numero_jugadors[];
        int AR;
        int daus;
        int daus2;
        boolean guanyar = false;
        boolean oca = false;
        int[] oca4 = {5, 14, 23, 32, 41, 50, 59};
        boolean OCA4 = false;
        int[] oca5 = {9, 18, 27, 36, 45, 54};
        boolean OCA5 = false;
        int aux;
        int exces;
        int contadorJ = 1;
        
        //Intro + numero de jugadors
        
        System.out.println("Benvinguts a la Oca.\nQunats jugadors sou?");
        AR = scanner.nextInt();
        
        int tauler[] = new int[63];
        int casella[] = new int [AR+1];//Te una posicio mes per fer coincidir el numero del jugador amb la casella del array
        int penalitzacions[] = new int [AR+1];
        
        while(AR<2 || AR>4) {
            System.out.println("Introdueix un nombre de jugadors valids");
            AR = scanner.nextInt();
        }
        
        numero_jugadors = new int[AR+1];
        
        for (int i=1; i<=AR; i++) {
        	numero_jugadors[i] = (i);
        }

        noms = new String[AR+1];
        
        for (int i=1; i<=AR; i++) {
            	System.out.println("Introdueix el nom del jugador numero "+(numero_jugadors[i]));
            	noms[i] = scanner.next();
        }
        
        //Bucle joc
        
        while (!guanyar) {
        	
        	// Daus
        	
        	daus = (int)((Math.random() * 6)+1);
        	daus2 = (int)((Math.random() * 6)+1);
        	
        	System.out.println();
        	System.out.println("Es el torn del jugador "+numero_jugadors[contadorJ]+" ,"+noms[contadorJ]);
        	System.out.println(">> tiro");
        	
        	// Sistema de penalitzacions
        	if (penalitzacions[contadorJ] != 0) {
        		aux = penalitzacions[contadorJ];
        		aux--;
        		penalitzacions[contadorJ] = aux;
        		if (penalitzacions[contadorJ] != 0) {
        			System.out.println("El jugador "+contadorJ+" li queden "+penalitzacions[contadorJ]+" tirades de penalització.\nSegueixes a la casella "+casella[contadorJ]);
        			scanner.nextLine();
        			System.out.println();
            		System.out.println("--------------------------------------");
        		} else {
        			System.out.println("S'ha acabat la penalització, al següent torn podras tornar a tirar");
        			scanner.nextLine();
        			System.out.println();
        			System.out.println("--------------------------------------");
        		}
        		contadorJ++;
        		if (contadorJ>=AR+1) {
                	contadorJ=1;
                }
        		aux = 0;
        		continue;
        	}
        	
        	if (casella[contadorJ] >= 60) {
        		System.out.println("Tires amb un dau i treus un "+daus);
        		casella[contadorJ] += daus;
        	} else {
        		System.out.println("Tires amb dos daus i treus un "+daus+" i un "+daus2+" = "+(daus+daus2));
        		
        		//Primera tirada
        		if (casella[contadorJ]==0) {
            		
            		if ((daus==3 && daus2==6) || (daus==6 && daus2==3)) {
            			System.out.println("De dado a dado y tiro porque me ha tocado, abançes a la casella 26");
            			casella[contadorJ] = 26;
            		} else if ((daus==4 && daus2==5) || (daus2==4 && daus==5)) {
            			System.out.println("Avances a la casella 53");
            			casella[contadorJ] = 53;
            		} else {
            			casella[contadorJ] += (daus+daus2);
            		}
            	} else {
            		casella[contadorJ] += (daus+daus2);
            	}
        		
        	}
        	
        	//Exces caselles
        	
        	if (casella[contadorJ] > 63) {
        		exces = (casella[contadorJ] - 63);
        		casella[contadorJ] = 63 - exces;
        		System.out.println("Rebotes "+exces+" caselles cap enrere");
        	}
        	
        	//La mort
        	
        	if(casella[contadorJ] == 58) {
        		System.out.println("Has MORT, tornes a la casella 0");
        		casella[contadorJ] = 0;
        	}
        	
        	//Laberint
        	
        	if (casella[contadorJ] == 42) {
        		System.out.println("Has caigut en el laberint i tornes a la casella 39");
        		casella[contadorJ] = 39;
        	}
        	
        	//Preso
        	
        	if (casella[contadorJ] == 52) {
        		System.out.println("Has caigut a la preso. Estas penalitzat tres torns");
        		penalitzacions[contadorJ] += 3;
        	} 
        	
        	//Fonda
        	
        	if (casella[contadorJ] == 19) {
        		System.out.println("Has caigut a la fonda. Estas penalitzat durant un torn");
        		penalitzacions[contadorJ] += 1;
        	}
        	
        	//Pou
        	
        	if (casella[contadorJ] == 38) {
        		System.out.println("Has caigut al pou. Estas penalitzat durant dos torn");
        		penalitzacions[contadorJ] += 2;
        	}
        	
        	//Puente
        	
        	if (casella[contadorJ] == 6) {
        		System.out.println("De puente a puente y tiro porque me da la corriente.\nAvances fins la casella 12");
        		casella[contadorJ] = 12;
        		oca = true;
        	} else if (casella[contadorJ] == 12) {
        		System.out.println("De puente a puente y tiro porque me da la corriente.\nRetrocedeixes fins la casella 6");
        		casella[contadorJ] = 6;
        		oca = true;
        	}
        	
        	//La oca
        	// De 4 i 5
        	
        	for (int i=0; i<oca4.length; i++) {
        		if (casella[contadorJ] == oca4[i]) {
        			OCA4 = true;
        		}
        	}
        	
        	for (int i=0; i<oca5.length; i++) {
        		if (casella[contadorJ] == oca5[i]) {
        			OCA5 = true;
        		}
        	}
        	
        	if (OCA4) {
        		System.out.println("De oca a oca y tiro porque me tocan\nAvances 4 fins a la casella "+(casella[contadorJ]+4));
        		casella[contadorJ] += 4;
        		oca = true;
        	} else if (OCA5) {
        		System.out.println("De oca a oca y tiro porque me tocan\nAvances fins a la casella "+(casella[contadorJ]+5));
        		casella[contadorJ] += 5;
        		oca = true;
        	}
        	
        	
        	//guanyar oca
        	if (casella[contadorJ] == 63) {
        		System.out.println("Has arribat a la casella 63!");
        		guanyar = true;
        		continue;
        	}
        	
        	
            System.out.println("Estas en la casella "+casella[contadorJ]);
        	
        //Funcio següent jugador
        if (!oca) {
        	contadorJ++;
            if (contadorJ>=AR+1) {
            	contadorJ=1;
            }
            } else {
            	oca = false;
                OCA4 = false;
                OCA5 = false;
            }
        
        
        System.out.println("Premi enter per a passar al següent jugador");
        scanner.nextLine();
        System.out.println();
        System.out.println("--------------------------------------");
        }
        System.out.println("Felicitats el jugador "+contadorJ+" ha GUANYAT");
	}


}


