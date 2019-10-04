import os

tf_dit = {}
idf_dit = {}

idf_file = open(r'',mode='r',encoding='utf-8')  #记得加地址
idf_list = idf_file.read().split()
num_idf = int(len(idf_list)/2)
for i in range(0,num_idf):  #考虑可观测性,可以加一个打印数字之类的环节
    idf_word = idf_list[i*2]
    idf_num = idf_list[i*2+1]
    idf_dit[idf_word] = idf_num
idf_word_list = idf_dit.keys()
tf_idf_dit = idf_dit

txt_list = os.listdir("")  #记得加文本所在目录地址
for txt in txt_list:
    txt_file = open(r''+txt,mode='r',encoding='utf-8') #记得补地址
    word_list = txt_file.read().split()
    txt_file.close()
    word_count =[]
    for word in  word_list:
        if word not in word_count:
            word_count.append(word)
        if word in idf_word_list:
            tf_idf_dit[word] = idf_dit[word]*int(word_list.count(word))
    tf_idf_file = open('',mode='w',encoding='utf-8')    #记得加保存地址
    for word in word_count:
        tf_idf_file.write(str(tf_idf_dit[word])+'\n')
    tf_idf_file.close()



    '''for word in word_list:  #创建tf词频统计数据使用
        if word not in word_count:
            word_count.append(word)
            tf_dit[word] = word_list.count(word)      
    tf_file = open(''+txt,mode='w',encoding='utf-8') #记得补地址
    for word in word_count:
        #tf_file.write(word + " " + str(tf_dit[word])+'\n')
    tf_file.close()'''