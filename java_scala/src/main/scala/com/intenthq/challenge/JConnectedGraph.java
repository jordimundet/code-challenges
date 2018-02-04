package com.intenthq.challenge;


import java.util.Collections;
import java.util.List;

public class JConnectedGraph {
  // Find if two nodes in a directed graph are connected.
  // Based on http://www.codewars.com/kata/53897d3187c26d42ac00040d
  // For example:
  // a -+-> b -> c -> e
  //    |
  //    +-> d
  // run(a, a) == true
  // run(a, b) == true
  // run(a, c) == true
  // run(b, d) == false
	
  public static boolean equalNodes(JNode source, JNode target) {
	  boolean result = false;

		  if(source.value == target.value) {
			  if(source.edges.size() == target.edges.size()) {
				  if(source.edges.size() == 0) {
					  result = true;
				  } else {
					  for(int i=0; i< source.edges.size();i++) {
						  result = equalNodes(source.edges.get(i), target.edges.get(i));
					  }
				  }
			  }
		  }	  

		  
	  
	  
	 return result;
  }
  public static boolean run(JNode source, JNode target) {  
	  if(source == target){
		  return true;
	  }
	 if(JConnectedGraph.equalNodes(source, target)) {
		  return true;
	  }
	  for(JNode child : source.edges) {
		  if(JConnectedGraph.run(child,target)){
			  return true;
		  }
	  }
	  
	  return false;
  }

  public static class JNode {
    public final int value;
    public final List<JNode> edges;
    public JNode(final int value, final List<JNode> edges) {
      this.value = value;
      this.edges = edges;
    }
    public JNode(final int value) {
      this.value = value;
      this.edges = Collections.emptyList();
    }
  }

}


