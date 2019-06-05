package cn.stonewise.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberTools {
	
	private static Map<Character,Integer> charMap = new HashMap<>();
	static{
		charMap.put('a', 1);
		charMap.put('b', 2);
		charMap.put('c', 3);
		charMap.put('d', 4);
		charMap.put('e', 5);
		charMap.put('f', 6);
		charMap.put('g', 7);
		charMap.put('h', 8);
		charMap.put('i', 9);
		
		charMap.put('g', 1);
		charMap.put('k', 2);
		charMap.put('l', 3);
		charMap.put('m', 4);
		charMap.put('n', 5);
		charMap.put('o', 6);
		charMap.put('p', 7);
		charMap.put('q', 8);
		charMap.put('r', 9);
		
		charMap.put('s', 1);
		charMap.put('t', 2);
		charMap.put('u', 3);
		charMap.put('v', 4);
		charMap.put('w', 5);
		charMap.put('x', 6);
		charMap.put('y', 7);
		charMap.put('z', 8);
	}
	
	private static Set<Character> vowelSet = new HashSet<>();
	static{
		vowelSet.add('a');
		vowelSet.add('e');
		vowelSet.add('i');
		vowelSet.add('o');
		vowelSet.add('u');
	}
	
	public static boolean isVowel(char c){
		if(vowelSet.contains(c)){
			return true;
		}
		return false;
	}
	
	public static int getNumberByChar(char c){
		return charMap.get(c);
	}

    public static int twoNumberAdd(int a, int b) {
        if (a >= 10 || b >= 10 || a < 0 || b < 0) {
            return 0;
        }

        int c = a + b;
        if (c < 10) {
            return c;
        } else {
            return c % 10 + c / 10;
        }
    }
    
    
    public static int addToNumber(int number){
    		if(number < 10){
    			return number;
    		} else {
    			String numbers = String.valueOf(number);
    			int sum = 0;
    			for(char n : numbers.toCharArray()){
    				sum += (n-48);
    			}
    			return addToNumber(sum);
    		}
    }
    
//    public static void main(String[] args) {
//        System.out.println(twoNumberAdd(8,9));
//        System.out.println(twoNumberAdd(1,9));
//        System.out.println(twoNumberAdd(1,8));
//        
//        System.out.println(getNumberByChar('a'));
//        System.out.println(getNumberByChar('i'));
//        System.out.println(getNumberByChar('r'));
//        
//        System.out.println('1'-48);
//        
//        System.out.println(addToNumber(12));
//        System.out.println(addToNumber(12345));
//        System.out.println(addToNumber(999));
//    }
}
