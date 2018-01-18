import java.util.InputMismatchException;

import java.util.LinkedList;

import java.util.Queue;

import java.util.Scanner;

 

public class Biparti

{

    private int nombreDeSommet;

    private Queue<Integer> queue;

 

    public static final int NO_COLOR = 0;

    public static final int RED = 1;

    public static final int BLUE = 2;

 

    public Biparti(int nombreDeSommet)

    {

        this.nombreDeSommet = nombreDeSommet;

        queue = new LinkedList<Integer>();

    }

 

    public boolean isBipartite(int adjacencyMatrix[][], int src)

    {

        int color_tab[] = new int[nombreDeSommet +  1]; // tableau de couleur

        for (int sommet = 1; sommet <= nombreDeSommet; sommet++)

        {

            color_tab[sommet] = NO_COLOR; // pas de couleur assignée

        }

        color_tab[src] = RED; // on assigne la première couleur au sommet source

        queue.add(src); // on enfile le premier sommet

 

        int u, v;

        while (!queue.isEmpty())

        {

            u = queue.remove(); // on défile le sommet enfiler en premier 

            v = 1;

            while (v <= nombreDeSommet)

            { 

                if (adjacencyMatrix[u][v] == 1 && color_tab[u]== color_tab[v]) // si il existe une arête de de u vers v et que v possède la même couleur que u

                {

                    return false;

                }

                if (adjacencyMatrix[u][v] == 1 && color_tab[v]== NO_COLOR) // si il existe une arête de u vers v et que v n'est pas coloré assigné une couleur différente de u

                {

                    color_tab[v] = (color_tab[u] == RED ) ? BLUE :RED;

                    queue.add(v); // enfiler le sommet v

                }

                v++;


            }


        }

        return true;

    }

 

    public static void main(String... arg)

    {

        int nombre_de_noeud, src;

        Scanner scanner = null;

        try 

        {




           System.out.println("Entrer le nombre de sommet :");

           scanner = new Scanner(System.in);

           nombre_de_noeud = scanner.nextInt();


 

           int adjacency_matrix[][] = new int[nombre_de_noeud + 1][nombre_de_noeud + 1];

           System.out.println("Entrer la matrice d'adjacence :");

           for (int i = 1; i <= nombre_de_noeud; i++)

           {

               for (int j = 1; j <= nombre_de_noeud; j++)

               {	

                   adjacency_matrix[i][j] = scanner.nextInt();

               }

           }

 

           for (int i = 1; i <= nombre_de_noeud; i++)

           {

               for (int j = 1; j <= nombre_de_noeud; j++)

               {	

                   if(adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)

                   {

                       adjacency_matrix[j][i] = 1;

                   }

               }

           }


          //  if(adjacency_matrix[1][2]== 1 && adjacency_matrix[2][1] == 1){


          //   System.out.println("A -------> B"); 


          //  }

          // if(adjacency_matrix[1][2]== 0 && adjacency_matrix[2][1] == 0){


          //   System.out.println("AB"); 


          //  }

 

           System.out.println("Entrer la couleur source du graphe (1: RED or 2: BLUE) : ");

           src = scanner.nextInt();

 

           Biparti biparti = new Biparti(nombre_de_noeud);

           if (biparti.isBipartite(adjacency_matrix, src)) 

           {

               System.out.println("Le graphe est biparti.");

           } else

           {

               System.out.println("Le graphe n'est pas biparti.");

           }

       } catch (InputMismatchException inputMismatch) 

       {

           System.out.println("Erreur. Mauvais caractère entré !");

       }

       scanner.close();

     }
   }