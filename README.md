# Frequency Counter

Author: Joseph Tomada
Approximate time to complete: 2 weeks
Created: November 2020

This was a school project that was designed to count the frequency of unique **tokens.** The program will request for a text file which will then to proceed to count the frequency of different tokens. The program will then output both the top 20 most and least frequent tokens which appeared throughout the text. 

**Note: This program is a finalized version and will no longer be worked on.**

# How to use

The main method is inside Driver.java (Compile and run this file). The program requests an additional command-line argument which corresponds to the file that you wish to extract its tokens. To do so, input:

**javac Driver.java**
**java Driver text.txt**

## FrequencyCount
The Driver class mainly uses methods from the FrequencyCount class in order to successfully run this program. It utilizes java's Random, ArrayList, List, and HashMap API in order to create these methods.

**FrequencyCount (List<String> text, int degree)**
        -Parametized constructor which gets a list of words from the text file. Depending on the 
        degree (amount of words in each token), the constructor will create new tokens provided 
        that they are unique. If a token exists, it will increment the value at the given key.

**sortedMap()**
	-this method, essentially, stores the HashMap into a list of string from lowest to highest count of appearances. It acccomplishes by using temporary HashMap where each count in the token is linked to ArrayList of all the tokens that have that count.

**head()**
	-Returns a list of the top 20 most utilized keys in the text File

**tail()**
	-Returns a list of the top 20 least utilized keys in the text File

**count(String token)**
	-Return the number of times that this token has appeared in the text File

**percent(String token)**
	-Returns the percentage that the token take from the text File. Does this by counting the amount of words from the token, multiplying that number by the count of the token and dividing it by the amount of words in the text File.

**randomToken()**
	-Returns a randomToken from the HashMap. Primarily used to create random generated poems using the tokens.
