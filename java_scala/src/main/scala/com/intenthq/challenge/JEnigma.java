package com.intenthq.challenge;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JEnigma {

  // We have a system to transfer information from one place to another. This system
  // involves transferring only list of digits greater than 0 (1-9). In order to decipher
  // the message encoded in the list you need to have a dictionary that will allow
  // you to do it following a set of rules:
  //    > Sample incoming message: (​1,2,3,7,3,2,3,7,2,3,4,8,9,7,8)
  //    > Sample dictionary (​23->‘N’,234->‘ ’,89->‘H’,78->‘Q’,37 ->‘A’)
  //  - Iterating from left to right, we try to match sublists to entries of the map.
  //    A sublist is a sequence of one or more contiguous entries in the original list,
  //    eg. the sublist (1, 2) would match an entry with key 12, while the sublist (3, 2, 3)
  //    would match an entry with key 323.
  //  - Whenever a sublist matches an entry of the map, it’s replaced by the entry value.
  //    When that happens, the sublist is consumed, meaning that its elements can’t be used
  //    for another match. The elements of the mapping however, can be used as many times as needed.
  //  - If there are two possible sublist matches, starting at the same point, the longest one
  //    has priority, eg 234 would have priority over 23.
  //  - If a digit does not belong to any matching sublist, it’s output as is.
  //
  // Following the above rules, the message would be: “1N73N7 HQ”
  // Check the tests for some other (simpler) examples.

  private final Map<Integer, Character> map;
 
  static int maxDigits;
  static String decodedMessage;
  
  private JEnigma(final Map<Integer, Character> map) {
    this.map = map;
  }

  public static JEnigma decipher(final Map<Integer, Character> map) {
    return new JEnigma(map);
  }

  public String deciphe(List<Integer> message) {
	  maxDigits = MaxDigitsDictionary();
	  List<Integer> messageAuxiliar= message;

	  decodedMessage = "";
	  if(message.size() < maxDigits) {
		  maxDigits = message.size();
	  }
	  return decodeSubmessage(messageAuxiliar,messageAuxiliar.subList(0, maxDigits),maxDigits);

	
	
	 
  }

private String decodeSubmessage(List<Integer> message, List<Integer> submessage, int numDigits) {
	int submessageInt = convertToInt(submessage, numDigits);

	if(map.containsKey(submessageInt)) {
			message = message.subList(numDigits, message.size());
			decodedMessage = decodedMessage + map.get(submessageInt).toString();
			if(message.size() == 0) {
				return decodedMessage;
			} else if(message.size() < maxDigits) {
				maxDigits = message.size();
				
			}
			return decodeSubmessage(message,message.subList(0,maxDigits),maxDigits);
	} else {
		if(submessage.size() == 1) {
			message = message.subList(1, message.size());
			decodedMessage = decodedMessage + submessage.get(0).toString();
			if(message.size() == 0) {
				return decodedMessage;
			} else if(message.size() < maxDigits) {
				maxDigits = message.size();				
			}
			return decodeSubmessage(message,message.subList(0,maxDigits),maxDigits);
		} else {
			if(message.size() == 0) {
				return decodedMessage;
			} else if(message.size() < maxDigits) {
				maxDigits = message.size();				
			}
			return decodeSubmessage(message,message.subList(0,numDigits-1),numDigits-1);
		}
	}
	//return "ERROR";
}

private int convertToInt(List<Integer> submessage, int maxDigits) {
	int result = 0;
	for(int i=maxDigits-1; i>=0;i--) {
		result = result + submessage.get(i)*(int)Math.pow(10,maxDigits-(i+1));
	}
	return result;
}

private int MaxDigitsDictionary() {
	int digits = 1;
	for(Entry<Integer, Character> code : map.entrySet()) {
		int codeDigits = String.valueOf(code.getKey()).length();
		if(codeDigits > digits) {
			digits = codeDigits;
		}
	}
	return digits;
}

}