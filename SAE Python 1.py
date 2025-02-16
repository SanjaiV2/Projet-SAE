#!/usr/bin/env python
# coding: utf-8

# # <center> Etude de communautés dans un réseau social</center>
# <center> SAE 1.01 / 2024 - 25 </center>
# 
# 

# Cette SAE est à faire en **binôme**.
# 
# **Calendrier**
# - Un contrôle de 2h en lien avec le contenu de cette SAE aura lieu le mercredi **30 octobre** 2024.
# - Le projet est à rendre le mardi **29 octobre** 2024. Les modalités de rendu vous seront précisées par votre enseignant.
# 
# **Evaluation**
# - Le projet comptera pour 40% de la note de SAE 1.01. <BR>
#     Il sera particulièrement tenu compte de la qualité du code, des **commentaires** et **docstrings**, des fonctions de **tests unitaires** pour les fonctions renvoyant des résultats. <BR><BR>
#     
# - Le contrôle compte pour 60% de la note finale.

# ## <center> Sujet </center>
# 
# Une *communauté* est un ensemble de personnes développant des interactions dans un réseau social.
# 
# Dans ce projet, on étudie des communautés modélisées sous différentes formes. Pour cela, on développe des fonctions permettant d'extraire des informations relatives à ces réseaux. 

# On modélise, dans un premier temps, les interactions entre personnes dans un tableau `amis` de chaînes de caractères contenant les prénoms des membres du réseau et tel que `amis[2*i]` a des interactions avec `amis[2*i+1]`.
# 
# On suppose que chaque interaction n'est décrite qu'une seule fois dans le tableau, et qu'une personne n'a pas d'interaction avec elle-même.
# 
# **Exemple** : 

# In[8]:


amis = ["Alice", "Bob", "Alice", "Charlie", "Bob", "Denis"]


# Ici, 
# - Alice a des interactions Bob et Charlie, 
# - Bob a des intractions avec Alice et Denis,
# - Charlie a des interactions avec Alice,
# - Denis a des interactions avec Bob.

# #### Question préliminaire : Modélisation d'un réseau par un tableau
# 
# Muriel, Yasmine et Joël sont amis. Yasmine est amie avec Thomas. 
# Joël, Nassim, Andrea et Ali sont amis. Thomas est ami de Daria et Carole. Thierry, Axel et Léo sont amis. Léo est ami de Valentin qui est ami d'Andrea.
# 
# - Construire un tableau `p_amis` qui modélise ce réseau d'amitié en selon le principe qui vient d'être décrit.

# In[10]:


p_amis=["joel","yasmine","thomas","daria","thomas","carole","daria","carole","thomas","yasmine","muriel","joel","muriel","yasmine","joel","nassim","ali","joel","andrea","nassim","joel","ali","nassim","andrea","ali","andrea","valentin","leo","axel","leo","thierry","axel","thierry","leo","valentin","andrea","joel"]



# #### Question 1 : Nombre d'amis d'une personne
# 
# - Étant donné un tableau `amis`, écrire une fonction `nb_amis(amis, prenom)` qui retourne le nombre d'amis de `prenom` à partir des données du tableau `amis`. 

# In[15]:


p_amis=["thomas","daria","thomas","carole","daria","carole","thomas","yasmine","muriel","yasmine","joel","muriel","joel","yasmine","joel","nassim","ali","joel","andrea","nassim","joel","ali","nassim","andrea","ali","andrea","valentin","leo","axel","leo","thierry","axel","thierry","leo","valentin","andrea","joel"]
def nb_amis(prenom,amis): 
    i = 0    
    nb = 0
    while i!=len(amis)-1:
        if amis[i]==prenom:
            nb+=1
        i+=1
    print(nb)

   
prenom ="thomas"
nb_amis(prenom,p_amis)


# #### Question 2 : Nombre de membres d'un réseau social 
# 
# - Ecrire une fonction `taille_reseau(amis)` qui à partir d'un tableau `amis`retourne le nombre de personnes distinctes participant à ce réseau social.

# In[16]:


def taille_reseau(amis) :
    d = []
    i = 0
    while i < len(amis) :
        if amis[i] not in d : 
            d.append(amis[i])
        i = i + 1
        
        
    return len(d)

amis=["thomas","daria","thomas","carole","daria","carole","thomas","yasmine","muriel","yasmine","joel","muriel","joel","yasmine","joel","nassim","ali","joel","andrea","nassim","joel","ali","nassim","andrea","ali","andrea","valentin","leo","axel","leo","thierry","axel","thierry","leo","valentin","andrea","joel"]

x = taille_reseau(amis)
print(x)


# #### Question 3 :  Lecture des données d'un réseau à partir d'un fichier
# On suppose que les données sur un réseau social sont stockées dans un fichier CSV de la manière suivante :
# ```
# prenom1;prenom2
# prenom3;prenom4
# prenom5;prenom6
# ...
# ```
# Autrement dit, chaque ligne du fichier contient une paire de prénoms séparés par un `';'` correspondant à deux personnes ayant des interactions. 
# 
# **NB** : Quatre fichiers CSV de ce type sont fournis dans le répertoire `files/`, il s'agit des fichiers`Communaute1.csv`, `Communaute2.csv`,`Communaute3.csv` et `Communaute4.csv`. 
# Il est recommandé d'en fabriquer d'autres. 
# 
# - Ecrire une fonction `lecture_reseau(path)` prenant en paramètre un chemin vers un tel fichier CSV et retournant un tableau modélisant les interactions entre les personnes du fichier.

# In[17]:


def lecture_reseau_bis(path):
    f = open(path,"r",encoding="utf-8")
    tab = f.readlines()
    f.close()

    i=0
    while i < len(tab) :
        tab[i] = tab[i].strip()
        
        i = i + 1
    f.close()
    
    
    return tab

r = lecture_reseau_bis("/iutv/Mes_Montages/12402149/R101_1/SAE_2024/files/Communaute1.csv")      
print(r)


# #### Question  4 :  Données d'un réseau dupliquées dans un fichier
# On suppose toujours que les données sur un réseau social sont stockées dans un fichier CSV mais certaines lignes du fichier contiennent les mêmes informations.  
# 
# **NB** : Quatre fichiers CSV de ce type sont fournis dans le répertoire `filesbis/`, il s'agit des fichiers`Communaute1bis.csv`, `Communaute2bis.csv`,`Communaute3bis.csv` et `Communaute4bis.csv`. 
# Il est recommandé d'en fabriquer d'autres. 
# 
# - Ecrire une fonction `lecture_reseau_bis(path)` prenant en paramètre un chemin vers un tel fichier CSV et retournant un tableau modélisant les interactions entre les personnes du fichier en éliminant les répétitions.

# In[18]:



def lecture_reseau_bis(path):
    f = open(path, "r", encoding="utf-8")
    tab = []
    line = f.readline()
    while line != "":
        tab_li = line.strip().split(";")
        x = (tab_li[0], tab_li[1])
        if (x not in tab) and ((tab_li[1], tab_li[0]) not in tab):
            tab.append(x)
        line = f.readline()
        
    f.close()
    return tab

lecture_reseau_bis("/iutv/Mes_Montages/12402149/R101_1/SAE_2024/filesbis/Communaute2bis.csv")


                                                                                                                                                                                           


# #### Question 5 : Modélisation d'un réseau par un dictionnaire
# On préfère pour la suite, utiliser une modélisation du réseau social par un dictionnaire dont les clés sont les prénoms des personnes du réseau et la valeur associé à une clé est le tableau des amis de la personne indiquée par la clé.
# 
# - A partir d'un tableau `amis` modélisant les interactions entre personnes d'un réseau, écrire une fonction `dico_reseau(amis)` qui retourne un dictionnaire dont les clés sont les prénoms des membres du réseau et les valeurs le tableau de leurs amis.

# In[19]:


def taille_reseau_tab(amis) :
    d = []
    i = 0
    while i < len(amis) :
        if amis[i] not in d :
            d.append(amis[i])
        i = i + 1
   
   
    return d
def dico_amis(amis):
    tab=taille_reseau_tab(amis)
    tab2=amis
    i=0
    dico={}
    
    while i< len(tab):
        tab3=[]
        y=0
        while y<len(tab2)-1:
            if tab[i]==tab2[y]:
                tab3.append(tab2[y+1])
        
            y+=1
        
        print(tab3)
        dico[tab[i]]=tab3
        i+=1
    return dico
dico = dico_amis(p_amis)

    
    


# #### Question 6 : Nombre d'amis des personnes les plus populaires
# 
# - A partir d'un dictionnaire `dico_reseau` modélisant les interactions dans un réseau d'amis, écrire une fonction`nb_amis_plus_pop (dico_reseau)` qui retourne le nombre d'amis des personnes les plus populaires du réseau.

# In[20]:



def nb_amis_plus_pop(dico_reseau) :
    prenom = list(dico_reseau)
    i = 0
    Populaire = 0
    prenom = list(dico_reseau)
    
    while i < len(prenom) : 
        compte  = len(dico_reseau[prenom[i]])
        if compte > Populaire : 
            Populaire = compte
        i = i + 1
        
    return Populaire


psg = nb_amis_plus_pop(dico_amis(p_amis))
print(psg)
        
    
        
    
        


# #### Question 7 : Personnes les plus populaires
# 
# - A partir d'un dictionnaire `dico_reseau` modélisant les interactions dans un réseau d'amis, écrire une fonction `les_plus_pop (dico_reseau)` qui retourne un tableau contenant les prénoms de toutes les personnes les plus populaires du réseau.

# In[21]:



def amis_plus_pop(dico_reseau) :
    prenom = list(dico_reseau)
    i = 0
    Populaire = 0
    prenom = list(dico_reseau)
    nb=0
    while i < len(prenom) :
        compte  = len(dico_reseau[prenom[i]])
        if compte > Populaire :
            Populaire = compte
            nb=i
        i = i + 1

    return dico_reseau[prenom[nb]]

amis_plus_pop(dico_amis(p_amis))


        
    


# In[ ]:




