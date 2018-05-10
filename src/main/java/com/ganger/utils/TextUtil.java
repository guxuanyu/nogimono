package com.ganger.utils;

import com.ganger.exception.MyException;

public class TextUtil {

	public static boolean isNull(String s){
		if(s==null) {
			return true;
		}
		if(s.equals("")) {
			return true;
		}
		return false;
	}
	
	public static int getWordCount(String s)
	{  
        int length = 0;  
        for(int i = 0; i < s.length(); i++)  
        {  
            int ascii = Character.codePointAt(s, i);  
            if(ascii >= 0 && ascii <=255)  
                length++;  
            else  
                length += 2;  

        }  
        return length;  

    }
	
	public static void nickNameLengthCheck(String s)  
    {  
        int length = 0;  
        for(int i = 0; i < s.length(); i++)  
        {  
            int ascii = Character.codePointAt(s, i);  
            if(ascii >= 0 && ascii <=255)  
                length++;  
            else  
                length += 2;  

        }  
        if(length>24) {
        	String err="昵称有点长，不要超过12个字哦";
        	throw new MyException(err,"nickname:["+s+"] 昵称过长");
        }

    }
}
