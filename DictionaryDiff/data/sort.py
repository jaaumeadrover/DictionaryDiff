
dicts=["CAT.txt","DEN.txt","FRA.txt","DEU.txt","ITA.txt","NOR.txt","POR.txt","ESP.txt","SWE.txt",]
dicts2=["CAT_sorted.txt","DEN_sorted.txt","FRA_sorted.txt","DEU_sorted.txt","ITA_sorted.txt","NOR_sorted.txt","POR_sorted.txt","ESP_sorted.txt","SWE_sorted.txt"]


for dict in dicts:
    with open(dict,encoding="utf8") as f:
        words= [line.strip().lower() for line in f]
    sorted_words = sorted(words, key=lambda w: len(w))
    # Extract the substring before the '.' character using string slicing
    substring = dict[:dict.index('.')]
    substring=substring+str("_sorted.txt")
    print(substring)
    with open(substring,'w',encoding="utf8") as f2:
        for word in sorted_words:
            f2.write(word+'\n')


# Open output file for writing
for dict in dicts2:
    indexos=[0]
    idx=1
    pos=0
    with open(dict, 'r+',encoding="utf8") as f:
        line=f.readline()
        while line:
            lon=len(line)-1
            #print("Paraula: "+str(line))
            #print("Longitud: "+str(lon))
            if lon==idx:
                pos=pos+1
            else:#canviam longitud
                idx=idx+1
                #print("Paraula: "+str(line))
                #print("Longitud: "+str(lon))
                #print(idx)
                indexos.append(pos)
                pos=pos+1
            line=f.readline()
        print(indexos)
    # Write each word to file on a new line
 #   for word in sorted_words:
  #      f.write(word + '\n')
