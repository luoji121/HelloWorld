package normal.test;

import java.util.HashSet;
import java.util.Set;

public class LongestReverseString {
	public int longestPalindrome(String inputS) {
        Set<String> ss = new HashSet<>();
        if(inputS==null || "".equals(inputS)){
        	return 0;
        }
        int count = 0;
        for(String s:inputS.split("")){
        	if(ss.contains(s)){
        		count+=2;
        		ss.remove(s);
        	}else{
        		ss.add(s);
        	}
        }
        if(ss.size()!=0){
        	count++;
        }
        return count;
    }
}
