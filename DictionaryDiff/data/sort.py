
dicts=["ca.txt","danish.txt","french.txt","german.txt","italian.txt","norwegian.txt","portuguese.txt","spanish.txt"]

for dict in dicts:
    with open(dict,encoding="utf8") as f:
        words= [line.strip() for line in f]
    sorted_words = sorted(words, key=lambda w: len(w))
    # Extract the substring before the '.' character using string slicing
    substring = dict[:dict.index('.')]
    substring=substring+str("_sorted.txt")
    print(substring)
    with open(substring,'w',encoding="utf8") as f2:
        for word in sorted_words:
            f2.write(word+'\n')


# Open output file for writing
#with open('English_sorted_words.txt', 'w') as f:
    # Write each word to file on a new line
 #   for word in sorted_words:
  #      f.write(word + '\n')
