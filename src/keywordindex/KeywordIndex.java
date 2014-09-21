package keywordindex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
100282N
Kumarasinghe C.U.
CS2022 Programming Project 2011
*/

//this program does work for page values less than 200 and if the number of keywords is below 500

//very importent: please make sure the file paths are set

//main class
public class KeywordIndex {
    
    public static void main(String[] args) {
        
        String line;//line that stores the readline of the input file
        
        boolean keywordListStart = false;
        //to store whether "Keyword List Start" and "Keyword List End" commands 
        //are read in the input file
        
        boolean queriesStart = false;
        //to store whether "Queries Start" and "Queries End" commands are read 
        //in the input file
        
        //instance of the hash table
        MyHashTable HT = new MyHashTable();
        
        try{
            //very importent: please make sure the file paths are set
            
            //for reading file
            BufferedReader inputFile = new BufferedReader(new FileReader("C:\\"
                    + "Users\\Owner\\Desktop\\Algo Project\\Input.txt"));
            
            //for writing to file
            BufferedWriter outputFile = new BufferedWriter(new FileWriter("C:\\"
                    + "Users\\Owner\\Desktop\\Algo Project\\Output.txt"));
            
            //reading file line by line
            while((line = inputFile.readLine()) != null){
                if(line.equalsIgnoreCase("Keyword List Start")){
                    //if line equals to "Keyword List Start"
                    keywordListStart = true;
                }       
                else if(line.equalsIgnoreCase("Keyword List End")){
                    //else if line equals to "Keyword List End" 
                    keywordListStart = false;
                }           
                else if(line.equalsIgnoreCase("Queries Start")){
                    //else if line equals to "Queries Start"
                    queriesStart = true;
                }                 
                else if(line.equalsIgnoreCase("Queries End")){
                    //else if line equals to "Queries End"
                    queriesStart = false;
                }                        
                else if(keywordListStart){
                    //if keywordListStart is true start to enter keywords
                    
                    String keywordAndPagesArray[] = line.split("\\s");
                    //seperate the keywords by whitespace
                    
                    int keywordPageNoTemp = 0;
                    //to store the converted string to integer value of the page
                    
                    try{
                        //convert string to integer value of the page
                        keywordPageNoTemp = Integer.parseInt(keywordAndPagesArray[1]);
                        
                    }catch(NumberFormatException ex){
                        //if page is not an integer or format error
                        System.out.println("------------------Invalid keyword input----------------");
                        System.out.println("Please make sure the format is as follows:");
                        System.out.println("case not sensitive, between words a tab");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Keyword page");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("the line which is making the error: "+line);//print error line
                        System.out.println("-----------------------------------------------------");
                    }
                    //change the keyword to format of first charactor is uppercase and rest to lower case
                    String keywordTempRest = keywordAndPagesArray[0].substring(1).toLowerCase();
                    String keywordTempBegin = keywordAndPagesArray[0].substring(0, 1).toUpperCase();
                    keywordAndPagesArray[0] = keywordTempBegin+keywordTempRest;
                    
                    //add keyword and page to table
                    HT.addMemberToTable(keywordAndPagesArray[0], keywordPageNoTemp);                    
                }                
                else if(queriesStart){
                    //if queriesStart is true start to enter queries
                    
                    String queriesAndPagesArray[] = line.split("\\s");
                    //seperate the queries by whitespace
                    
                    if(queriesAndPagesArray[0].equalsIgnoreCase("First")){
                        //if query line first word is "First"
                        
                        //change the requested keyword to format of first charactor is uppercase and rest to lower case
                        String keywordFirstTempRest = queriesAndPagesArray[1].substring(1).toLowerCase();
                        String keywordFirstTempBegin = queriesAndPagesArray[1].substring(0, 1).toUpperCase();
                        queriesAndPagesArray[1] = keywordFirstTempBegin+keywordFirstTempRest;
                        
                        //get the first page of the requested keyword
                        HT.getFirstPage(queriesAndPagesArray[1],outputFile);                        
                    }
                    else if(queriesAndPagesArray[0].equalsIgnoreCase("List")){
                        //if query line first word is "List"
                        
                        //change the requested keyword to format of first charactor is uppercase and rest to lower case
                        String keywordListTempRest = queriesAndPagesArray[1].substring(1).toLowerCase();
                        String keywordListTempBegin = queriesAndPagesArray[1].substring(0, 1).toUpperCase();
                        queriesAndPagesArray[1] = keywordListTempBegin+keywordListTempRest;
                        
                        //get the list of pages of the requested keyword
                        HT.getMemberPages(queriesAndPagesArray[1],outputFile);
                    }
                    else if(queriesAndPagesArray[0].equalsIgnoreCase("Keywords")){
                        //if query line first word is "Keywords"
                        
                        int pageNoForSearching = 0;
                        //to store the converted string to integer value of the page
                        
                        try{
                            //convert string to integer value of the page
                            pageNoForSearching = Integer.parseInt(queriesAndPagesArray[1]);
                            
                        }catch(NumberFormatException ex){
                            //if page is not an integer or format error
                            System.out.println("------------------Invalid query input----------------");
                            System.out.println("Please make sure the format is as follows:");
                            System.out.println("case not sensitive, between words a whitespace");
                            System.out.println("-----------------------------------------------------");
                            System.out.println("Keyword page");
                            System.out.println("-----------------------------------------------------");
                            System.out.println("the line which is making the error: "+line);//print error line
                            System.out.println("-----------------------------------------------------");
                        }
                        
                        //get the list of keywords of the requested page
                        HT.getKeywords(pageNoForSearching,outputFile);                        
                    }
                    else{
                        //if error of invalid query input, may be due to unnecessary whitespaces
                        System.out.println("------------------Invalid query input----------------");
                        System.out.println("Please make sure the format is as follows:");
                        System.out.println("case not sensitive, between words a whitespace");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("First Keyword");
                        System.out.println("List Keyword");
                        System.out.println("Keyword page");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("the line which is making the error: "+line);
                    }
                }                
                else{
                    //if error of the format of the input file, maybe due to unnecessary whitespaces
                    //or spelling mistakes
                    System.out.println("-------Error in the format of input file-------");
                    System.out.println("Please make sure the format is as follows:");
                    System.out.println("case not sensitive in this order, between words a space");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Keyword List Start");
                    System.out.println("Keyword List End");
                    System.out.println("---or---");
                    System.out.println("Queries Start");
                    System.out.println("Queries End");
                    System.out.println("-----------------------------------------------------");
                    outputFile.close();//close output file
                    System.exit(0);//end program
                }
            }
            outputFile.close();//close output file
        }catch(IOException ex){//mostly if input file path is not correct
            System.out.println("inputFile path is incorrect");
        }
    }
}

class MyHashTable{
        
    public static final int SIZE_OF_HASHTABLE = 7907;//size of the hash table
    //to increase speed increse the  size of the hash table
    //to increse memory efficiency decrese the  size of the hash table
    
    public static final int MAX_NUM_KEYWORDS = 500;//size of the maximum number of keywords
    
    private HashMember[] members;//is a hash member, that is hash member with no collision
    private HashMember[] collisionMembers;//collision hash member, that is hash member with collision
    private int collisionMembersCounter;//number of collision members
    private String[] theKeywordsOnThePage;//temp string array for printing list of keywords on a page
    
    MyHashTable(){
        members = new HashMember[SIZE_OF_HASHTABLE];//array of hash members of size of the hash table
        //initializing
        for(int k=0; k<SIZE_OF_HASHTABLE;k++){
            members[k]=null;
        }
        
        collisionMembers = new HashMember[MAX_NUM_KEYWORDS];
        //array of collision hash members of size of maximum number of keywords
        
        theKeywordsOnThePage = new String[MAX_NUM_KEYWORDS];//temp array
        for(int k=0; k<MAX_NUM_KEYWORDS;k++){
            collisionMembers[k] = null;
            theKeywordsOnThePage[k] = null;
        }
        collisionMembersCounter = 0;//number of collision members is zero
    }

    //adding member to hash table
    public boolean addMemberToTable(String keywordToBeAdded, int page){
        int mhash = 0;
        //to store modulated hash value of keyword to be added        
        
        mhash = getHashCode(keywordToBeAdded);
        //get modulated hash value of keyword to be added
        
        if(members[mhash] == null){
            //if mhash'th member of hash members is null
            members[mhash] = new HashMember(keywordToBeAdded,page);//create new hash member
        }
        else if(members[mhash].getKeyword().equalsIgnoreCase(keywordToBeAdded)){
            //if mhash'th member of hash members is not null
            //and if mhash'th member of hash members keyowrd is equal to the keyword to be added
            members[mhash].getPagesList().insert(page);//insert page to existing hash member
        }
        else{
            //if mhash'th member of hash members is not null
            //and if mhash'th member of hash members keyowrd is not equal to the keyword to be added
            
            //add to next collisiom member
            int i = 0;
            while(i<collisionMembersCounter){
                if(collisionMembers[i].getKeyword().equalsIgnoreCase(keywordToBeAdded)){
                    //and if i'th member of collision hash members keyowrd is equal to the keyword to be added
                    collisionMembers[i].getPagesList().insert(page);//insert page to existing collison hash member
                    i = collisionMembersCounter+1;//if member found exit while loop
                }
                else{
                    //and if i'th member of collision hash members keyowrd is not equal to the keyword to be added
                    i++;//increse while loop counter by 1
                }
            }
            if(i == collisionMembersCounter){//if the member not present in collision hash members
                //create new collision hash member
                collisionMembers[collisionMembersCounter] = new HashMember(keywordToBeAdded,page);
                collisionMembersCounter++;//increase the collision hash members by 1 
            }
        }
        return true;
    }
    
    //getting the list pages of a member of hash table
    public boolean getMemberPages(String keywordToBeRetrieved, BufferedWriter BW){
        int mhash = 0;
        //to store modulated hash value of keyword to be retrieved          
        
        mhash = getHashCode(keywordToBeRetrieved);
        //get modulated hash value of keyword to be retrieved
        
        if(members[mhash]== null){//if mhash'th member of hash members is not present
            //the keyword to be retrieved is a invalid keyword
            try {
                System.out.println("Invalid Keyword");
                BW.write("Invalid Keyword");//write to file
                BW.newLine();
            } catch (IOException ex) {
                Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{//if mhash'th member of hash members is presnet            
            if(members[mhash].getKeyword().equalsIgnoreCase(keywordToBeRetrieved)){
                //if mhash'th member of hash members keyowrd is equal to the keyword to be retrieved
                //show pages list of mhash'th member of hash members
                members[mhash].getPagesList().showPagesList(BW);
            }
            else{//if mhash'th member of hash members keyowrd is not equal to the keyword to be retrieved                
                int i = 0;
                while(i<collisionMembersCounter){
                    if(collisionMembers[i].getKeyword().equalsIgnoreCase(keywordToBeRetrieved)){
                        //if i'th member of collision hash members keyowrd is equal to the keyword to be retrieved
                        collisionMembers[i].getPagesList().showPagesList(BW);//show pages list of i'th member of collision hash members
                        i = collisionMembersCounter+1;//if member found exit while loop
                    }
                    else{
                        //if i'th member of hash members keyowrd is not equal to the keyword to be retrieved
                        i++;//increse while loop counter by 1
                    }
                }
                if(i == collisionMembersCounter){//if the member not present in collision hash members
                    //the keyword to be retrieved entered is a invalid keyword
                    try {
                        System.out.println("Invalid Keyword");
                        BW.write("Invalid Keyword");//write to file
                        BW.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return true;
    }
    
    //getting the first page of a member of hash table
    public boolean getFirstPage(String keywordFirstPageToBeRetrieved, BufferedWriter BW){
        int mhash = 0;
        //to store modulated hash value of keyword to be retrieved
        
        mhash = getHashCode(keywordFirstPageToBeRetrieved);
        //get modulated hash value of keyword to be retrieved
        
        if(members[mhash]== null){//if mhash'th member of hash members is null
            //the keyword to be retrieved is a invalid keyword
            try {
                System.out.println("Invalid Keyword");
                BW.write("Invalid Keyword");//write to file
                BW.newLine();
            } catch (IOException ex) {
                Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //if mhash'th member of hash members is not null
            if(members[mhash].getKeyword().equalsIgnoreCase(keywordFirstPageToBeRetrieved)){
                //if mhash'th member of hash members keyowrd is equal to the keyword to be retrieved
                members[mhash].getPagesList().showFirstpage(BW);//show first page of mhash'th member of hash members
            }
            else{
                //if mhash'th member of hash members keyowrd is not equal to the keyword to be retrieved
                int i = 0;
                while(i<collisionMembersCounter){
                    if(collisionMembers[i].getKeyword().equalsIgnoreCase(keywordFirstPageToBeRetrieved)){
                        //if i'th member of collision hash members keyowrd is equal to the keyword to be retrieved
                        
                        collisionMembers[i].getPagesList().showFirstpage(BW);
                        //show first page of i'th member of collision hash members
                        
                        i = collisionMembersCounter+1;//if member found exit while loop
                    }
                    else{
                        //if i'th member of hash members keyowrd is not equal to the keyword to be retrieved
                        i++;//increse while loop counter by 1
                    }
                }
                if(i == collisionMembersCounter){//if the member not present in collision hash members
                    //the keyword to be retrieved entered is a invalid keyword
                    try {
                        System.out.println("Invalid Keyword");
                        BW.write("Invalid Keyword");
                        BW.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return true;
    }
    
    //get ketwords of a page number
    public boolean getKeywords(int thePageEnterd, BufferedWriter BW){
        int counter = 0;
        //for looping through the hash members
        
        while(counter < SIZE_OF_HASHTABLE){
            if(members[counter] == null){//if hash member not present
                counter++;//go to next member              
            }
            else if(members[counter] != null){//if hash member present
                members[counter].getPagesList().sortPages(BW);//sort pages of the hash member
                //get sorted pages list of the hash member
                int[] pagesOfMemberToBeSearched = members[counter].getPagesList().getSortedPagesList();
                //get counter value of the pages list of the hash member
                int counterVal = members[counter].getPagesList().getNumberOfPages();
                int coun = 0;
                while(coun < counterVal){
                    if(pagesOfMemberToBeSearched[coun] == thePageEnterd){
                        //if page of the member searched is equal to the page enterd
                        
                        //enter keyword to the next element of theKeywordOnThePage
                        int r = 0;
                        while(theKeywordsOnThePage[r]!=null){
                            r++;
                        }
                        theKeywordsOnThePage[r] = members[counter].getKeyword();
                        
                        coun = counterVal;//exit while loop, keyword found
                    }
                    else{
                        //if page of the member searched is not equal to the page enterd
                        coun++;//continue while loop
                    }
                }
                counter++;//go to next member
            }
        }
        
        counter = 0;
        //for looping through the collision hash members
        
        while(counter < collisionMembersCounter){
            if(collisionMembers[counter] != null){//if collision hash member present
                collisionMembers[counter].getPagesList().sortPages(BW);//sort pages of the collision hash member
                
                //get sorted pages list of the hash member
                int[] pagesOfMemberToBeSearched = collisionMembers[counter].getPagesList().getSortedPagesList();
                
                //get counter value of the pages list of the hash member
                int counterVal = collisionMembers[counter].getPagesList().getNumberOfPages();
                
                int coun = 0;
                while(coun < counterVal){
                    if(pagesOfMemberToBeSearched[coun] == thePageEnterd){
                        //if page of the collision member searched is equal to the page enterd
                        int r = 0;
                        while(theKeywordsOnThePage[r]!=null){
                            r++;
                        }
                        //enter keyword to the next element of theKeywordOnThePage
                        theKeywordsOnThePage[r] = collisionMembers[counter].getKeyword();
                        coun = counterVal;//exit while loop, keyword found
                    }
                    else{
                        //if page of the member searched is not equal to the page enterd
                        coun++;//continue while loop
                    }
                }
                counter++;//go to next member               
            }
            else{//if collision hash member not present
                counter = collisionMembersCounter;//exit while loop                
            }
        }
        //if searching finished show keywords
        showKeywords(BW);
        return true;
    }
    
    public void showKeywords(BufferedWriter BW){
        //get the number of keywords in the page
        int numberOfKeywordInThePage = 0;
        while(theKeywordsOnThePage[numberOfKeywordInThePage] != null){
            numberOfKeywordInThePage++;
        }
        
        //if number of keywords in the page is greater than 0
        //sort keywords alphabetically
        //bubble sort
        for(int u=0;u<numberOfKeywordInThePage;u++){
            //if string element 2 is greater than string element 1 by character by charactor ASCII values  
            if((u<numberOfKeywordInThePage-1) && (theKeywordsOnThePage[u].compareTo(theKeywordsOnThePage[u+1])>0)){
                
                //swap strings
                String temp = theKeywordsOnThePage[u];
                theKeywordsOnThePage[u] = theKeywordsOnThePage[u+1];
                theKeywordsOnThePage[u+1] = temp;
            }
            //if there is members in the page
            if(u == numberOfKeywordInThePage - 1){
                
                //if member is the last element of the list
                try {
                    System.out.print(theKeywordsOnThePage[u]);
                    BW.write(theKeywordsOnThePage[u]);//write keyword to file
                    System.out.print("\n");//file new line
                    BW.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                //if member is the last element of the list
                System.out.print(theKeywordsOnThePage[u]+" ");
                try {
                    BW.write(theKeywordsOnThePage[u]);//write keyword to file
                    BW.write(" ");//write space to file
                } catch (IOException ex) {
                    Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(numberOfKeywordInThePage == 0){
            //if there is no members in the page
            try {
                System.out.println("Invalid Page Number");
                BW.write("Invalid Page Number");//write to file
                BW.newLine();//write new line to file
            } catch (IOException ex) {
                Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //reset theKeywordOnThePage array elements to null
        for(int i = 0; i< numberOfKeywordInThePage; i++){
            theKeywordsOnThePage[i] = null;
        }
    }
    
    //get the modulated hash code of a keyword
    public int getHashCode(String keyword){
        long hash = 0;
        //to store hash value
        
        int mhash = 0;
        //to store modulated hash value
        
        for(int charIndex=0; charIndex<keyword.length();charIndex++){
            //new hash value = earlier hash value mutiplied by 47 add ASCII value of charactor at charactor index
            hash = hash*47 + keyword.charAt(charIndex);//get charactor ASCII value
        }
        mhash = (int)hash % SIZE_OF_HASHTABLE;//modulate hash by the size of the hash table
        
        //if madulated hash is minus convert it to plus
        if(mhash<0){
            mhash = -mhash;
        }
        return mhash;
    }    
}


class HashMember{
    
    private String keyword;//keyword of the member
    private MyLinkedList pages = new MyLinkedList();//pages of the member
    
    HashMember(String thekeyword, int pageNo){
        keyword = thekeyword;
        pages.insert(pageNo);
    }
    
    public String getKeyword(){
        return keyword;
    }
    
    public MyLinkedList getPagesList(){
        return pages;
    }
}

class MyLinkedList{
    
    public static final int SIZE_OF_LINKEDLIST = 201;//size of the Linked List
    //if more pages are to be stored increase this
    
    private LinkedListMember first;//first linked list member
    private int[] sortedPages;//sorted pages array
    private int numberOfPages;//number of pages in the list
    private boolean listIsAlreadySorted;//to store whether list is already sorted
    
    MyLinkedList(){
        first = new LinkedListMember(-1,null);
        sortedPages = new int[SIZE_OF_LINKEDLIST];
        numberOfPages = 0;
        listIsAlreadySorted = false;
    }
    
    public void insert(int thePage){
        //creates a new linked list member
        LinkedListMember LM = new LinkedListMember(thePage,null);
               
        if(first.getPage() == -1){//if the page is null of first linked list member 
            first.setPage(thePage);//set the page of first linked list member to the page inserted 
        }
        else{
            //if the next linked list member of the first linked list member is not present
            if(first.getNextLinkedListMember() == null){
                //set the next linked list member of the first linked list member to the new linked list member
                first.setNextLinkedListMember(LM);
            }
                    
            else{//if the next linked list member of the first linked list member is present
                LinkedListMember memberNow = first;//set linked list member now to the first linked list member
                
                //loop till member now doesn't have a next member
                while(memberNow.getNextLinkedListMember() != null){
                    memberNow = memberNow.getNextLinkedListMember();
                }
                
                //set next linked list member as new linked list of the member now linked list
                memberNow.setNextLinkedListMember(LM);
            }
        }       
    }
    
    //sort pages of the list
    public void sortPages(BufferedWriter BW){
        if(!listIsAlreadySorted){//sort if list is not already sorted
            
            int[] pages = new int[SIZE_OF_LINKEDLIST];
            //temp int array for keeping the pages      

            LinkedListMember memberNow = first;
            //set linked list member now to the first linked list memebr

            while((memberNow != null && memberNow.getPage() != -1)){
                //if the first linked list member is present and there is a page
                
                //if page is within the size of 0 to size of the linked list
                if(memberNow.getPage()>0 && memberNow.getPage()<SIZE_OF_LINKEDLIST){
                    pages[memberNow.getPage()]= memberNow.getPage();//set array index value to the page value 
                    memberNow = memberNow.getNextLinkedListMember(); //loop through the while
                }
                else{//if page value is out of bounds
                    System.out.println("Page Number is more than 200 or less than 0");
                    try {
                        BW.close();//close file
                    } catch (IOException ex) {
                        Logger.getLogger(MyLinkedList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);//quit program
                }           
            }
            storeToSortedPagesList(pages);//store pages to sorted pages list            
            listIsAlreadySorted = true;
        }
    }
    
    //store sorted pages to the sorted pages list
    public void storeToSortedPagesList(int[] pages){
        int counter1 = 0; 
            while(counter1<SIZE_OF_LINKEDLIST){//
                if(pages[counter1] != 0){             
                    sortedPages[numberOfPages] = pages[counter1];
                    counter1++;
                    numberOfPages++;
                }
                else{
                    counter1++;
                }          
            }
    }
    
    //show first page of the sorted list
    public void showFirstpage(BufferedWriter BW){
        if(!listIsAlreadySorted){//if list is not 
            sortPages(BW);
            printFirstPage(BW);
        }
        else{
            printFirstPage(BW);
        }
    }
    
    //print first page on file
    public void printFirstPage(BufferedWriter BW){
        try {
                System.out.println(sortedPages[0]);
                //convert integer to string then write to file
                BW.write(Integer.toString(sortedPages[0]));
                BW.newLine();
            } catch (IOException ex) {
                Logger.getLogger(MyLinkedList.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    //show sorted pages list
    public void showPagesList(BufferedWriter BW){
        if(!listIsAlreadySorted){
            sortPages(BW);
            printPagesList(BW);
        }
        else{
            printPagesList(BW);
        }
    }
    
    //print pages list to file
    public void printPagesList(BufferedWriter BW){
        for(int i=0; i < numberOfPages; i++){
            if(i == numberOfPages - 1){
                System.out.print(sortedPages[i]);
                try {
                    //convert integer to string then write to file
                    BW.write(Integer.toString(sortedPages[i]));
                } catch (IOException ex) {
                    Logger.getLogger(MyLinkedList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.out.print(sortedPages[i]+" ");
                try {
                    //convert integer to string then write to file
                    BW.write(Integer.toString(sortedPages[i])+" ");
                } catch (IOException ex) {
                    Logger.getLogger(MyLinkedList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        System.out.print("\n");
        try {
            BW.newLine();
        } catch (IOException ex) {
            Logger.getLogger(MyLinkedList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int[] getSortedPagesList(){
        return sortedPages;
    }
    
    public boolean isListAlreadySorted(){
        return listIsAlreadySorted;
    }
    
    public int getNumberOfPages(){
        return numberOfPages;
    }
}

class LinkedListMember{
    
    private int page;//page of the linked list member
    private LinkedListMember nextLinkedListMember = null;//next linked list member
    
    LinkedListMember(int thePage, LinkedListMember theNextLinkedListMember){
        page = thePage;
        nextLinkedListMember = theNextLinkedListMember;
    }
    
    public int getPage(){
        return page;
    }
    
    public LinkedListMember getNextLinkedListMember(){
        return nextLinkedListMember;
    }
    
    public void setPage(int tempPage){
        page = tempPage;
    }
    
    public void setNextLinkedListMember(LinkedListMember theLLM){
        nextLinkedListMember = theLLM;
    }
}