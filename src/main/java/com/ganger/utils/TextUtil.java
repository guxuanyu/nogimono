package com.ganger.utils;

import com.ganger.exception.MyException;

/**
 * 文本工具
 */
public class TextUtil {

    /**
     * 文本非空判断
     * @param s
     * @return
     */
    public static boolean isNull(String s){
		if(s==null) {
			return true;
		}
		if(s.equals("")) {
			return true;
		}
		return false;
	}

    /**
     * 文本字数统计
     * @param s
     * @return
     */
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

    /**
     * 昵称长度检查
     * @param s
     */
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
