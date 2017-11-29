/*
	JEMOUI Nader
				*/

//--------- Importation ---------
#include<iostream>
#include<map>
#include<queue>
#include<vector>
//------------------------------


using namespace std;    //utilisation de bib std


typedef map<int,vector<int> > graph;    // definir la graph


bool cBiparti(graph maGraph,int sommetD, int V){
    
    int colorArr[V];                // tableau des sommet colorer -1 pas colorer; 1 pour le rouge; 0 pour le vert;
    
    for (int i = 0; i < V ; ++i)   //boucle initialisation de colorArr
        colorArr[i] = -1; 
    
    colorArr[sommetD] = 1;         // colorer le premier sommet en rouge

    
    queue <int> q;                  //file d'attente F
    q.push(sommetD);                //defiler s dans F

    vector<int> vec;                // vecteur de sommet adj

    while(!q.empty()){
        
        int x = q.front(); q.pop();

        // application de la premiere partie de l'algo
        if (colorArr[x] == 1){ // si la couleur est rouge
            
            vec = maGraph[x];
            
            for(int i = 0; i < vec.size(); i++){
                
                if(colorArr[vec[i]] == 1 ){//...et que la suivante est rouge
                    return false;// retourner false
                }
                
                else if (colorArr[vec[i]] ==  -1){// si le sommet sommet n'est pas colorer
                    colorArr[vec[i]] = 0; // mettre la couleur verte
                    q.push(vec[i]);
                }
            }
        }

        // application de la deuxime partie de l'algo
        else if (colorArr[x] == 0){// l'inverse
            
            vec = maGraph[x];
            
            for(int i = 0; i < vec.size(); i++){
                
                if(colorArr[vec[i]] == 0 ){
                    return false; 
                }
                
                else if (colorArr[vec[i]] ==  -1){
                    colorArr[vec[i]] = 1;//pour le vert;
                    q.push(vec[i]);
                }

            }
        }
    }
     
    
}

int main(){
    graph maGraph;
    maGraph[0] = {1,3};
    maGraph[1] = {0};
    maGraph[2] = {3};
    maGraph[3] = {0,2,5};
    maGraph[4] = {6};
    maGraph[5] = {3,6};
    maGraph[6] = {4,5};

    if(cBiparti(maGraph,0,maGraph.size()))
    cout << "graph biparti\n";
    else
    cout << "graph non biparti\n";

    return 0;

}

