import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;



//Consider a tree data structure implemented by the following Javaclass:
//
//public class Tree {
//    private List<Tree> leaves = new LinkedList<Tree>();
//    private Tree parent = null;
//    private String data;
//
//    public Tree(String data, Tree parent) {
//        this.data = data;
//        this.parent = parent;
//    }
//}
//
//Implement search functionality using a method 
//with the footprint: Tree firstMatch(Tree, Regex). This method takes a tree, 
//searches that tree for leaves/branches in which the data  matches the given 
//regex expression and returns the first branch/leaf (another Tree object) that matches. 
//You should implement two different search algorithms: a Depth-First Search (DFS), and 
//another search algorithm of your choice.



public class Tree {

	private List<Tree> leaves = new LinkedList<Tree>();
	private Tree parent = null;
	private String data;

	public Tree(String data, Tree parent) {
      this.data = data;
      this.parent = parent;
	}
	
	public Tree ()
	{
		
	}
	//Given a list of strings, construct a Tree of characters (Trie)
	public static Tree getATree (List<String> str )
	{
		Tree root = new Tree();
		
		
		for (String s : str)
		{
			
			Tree curr = root;
			
			for (int i = 0; i< s.length(); i++)
			{
				Character c = s.charAt(i); 
				int  j;
				for (j =0; j < curr.leaves.size(); j++)
				{
					if (curr.leaves.get(j).data.equals(c.toString()))
						break;
				
				}
				if (j==curr.leaves.size()) 
				{
					curr.leaves.add(new Tree(c.toString(),curr));
					curr = curr.leaves.get(j);
				}
				
				else
				{
					curr = curr.leaves.get(j);
				}
							
				
			}
		}
		return root;
		 
	}
	
	
		
	// Display the current Trie 
	public static void displayTree (Tree root)
	{
		Queue<Tree> visited = new LinkedList <Tree> ();
		List<Tree> curr_leaves = root.leaves;
		for (Tree node : curr_leaves)
		{	
			visited.add(node);
		}
		
		System.out.println();
		while (!visited.isEmpty())
		{
		   int count = visited.size();	
		   for (int i = 0 ; i < count ; i++)
		   {	   
			   Tree current = visited.remove();
			   System.out.print(current.data+"$ "); 
			   
			   if (current.leaves.isEmpty()) 
				   continue;
			  
			   for (Tree node : current.leaves)
			   {
				   visited.add(node);
			   }
			   
		   }
		   System.out.println();
		}
	}
	
	
	// Return the first matching node or path that matches the input string	
	public static Tree firstMatch(Tree root, String regex)
	{
		List <String>s = new ArrayList<String>();
		String regex_copy = new String();
		Tree subtree = new Tree();
		
		Tree curr = root;
		
		for (int i=0; i<regex.length(); i ++)
		{
			Character c = regex.charAt(i);
			
			
			int pos;
			for (pos =0; pos < curr.leaves.size(); pos++)
			{
				if (curr.leaves.get(pos).data.equals(c.toString()))
					break;
				
			}
			if (pos == curr.leaves.size())
			{
				return subtree;
			}
			else
			{
				curr = curr.leaves.get(pos);
				regex_copy = regex_copy+ curr.data;
			}
			
		}
		
		s.add(regex_copy);
		subtree = Tree.getATree(s);
		
		return subtree;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("How many strings do you want to insert into the tree?");
		Scanner scan = new Scanner (System.in);
		int num = scan.nextInt();
		List<String> s = new ArrayList<String>();
		
		System.out.println("Enter the strings");
		
		for (int i=0; i< num+1 ; i++)
		{
			
			s.add(scan.nextLine());
			
		}
		
//		Sample strings: 		
//		ape
//		apt
//		apex
//		apply
//		arc
//		book
				
		Tree root = Tree.getATree(s);
		
		
		// Display entire Trie
		displayTree(root);
		
		
		
		while (true)
		{
			System.out.println("1. first match 2. Exit");
			int ch = scan.nextInt();
			switch (ch)
			{
			case 1: 
				System.out.println("Enter string to be matched");

				String regex = scan.next();

				Tree matched = Tree.firstMatch(root, regex);

				if (matched.leaves.isEmpty()) 
					System.out.println("Pattern not found.");
				else
				{
					System.out.println("regex matched in subtree:");
					//Display only sub-trie if it is exists
					displayTree(matched);
				}
				break;


			default:
				System.out.println("Program exiting..");
				System.exit(1);

			}
		
		}
		
	}

}
