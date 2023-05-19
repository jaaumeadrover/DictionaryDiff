dicts=["CAT.txt","DEN.txt","FRA.txt","DEU.txt","ITA.txt",
       "NOR.txt","POR.txt","ESP.txt","SWE.txt","ENG.txt"]
dicts2=["CAT_sorted.txt","DEN_sorted.txt","FRA_sorted.txt",
        "DEU_sorted.txt","ITA_sorted.txt","NOR_sorted.txt","POR_sorted.txt",
        "ESP_sorted.txt","SWE_sorted.txt","ENG_sorted.txt"]


#Bucle per a convertir tot a minúscules i ordenar per longitud
for dict in dicts:
    with open("Raw/"+dict,encoding="utf8") as f:
        words= [line.strip().lower() for line in f]
    #Ordenam per longitud
    sorted_words = sorted(words, key=lambda w: len(w))
    substring = dict[:dict.index('.')] #Extreim nom del fitxer
    substring=substring+str("_sorted.txt") #Cream nou fitxer optimitzat
    with open(substring,'w',encoding="utf8") as f2:
        for word in sorted_words:
            f2.write(word+'\n')


# Open output file for writing
for dict in dicts2:
    indexos={}
    idx=1
    pos=0
    with open(dict, 'r+',encoding="utf8") as f:
        line=f.readline()
        while line:
            lon=len(line)-1
            if lon==idx:
                pos=pos+1
            else:#canviam longitud
                idx=idx+1
                indexos[idx]=pos
                pos=pos+1
            line=f.readline()
        print(indexos)
        substring=dict[:dict.index('_')]+"_metadata.txt"
        with open(substring,'w',encoding="utf8") as f2:
            f2.seek(0) #Ubicam al punter a l'inici del fitxer
            for key,value in indexos.items():
                f2.write(str(key)+","+str(value)+'\n')

