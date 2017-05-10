

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class SequenceDiagram {
	public static ArrayList<String> filePaths=new ArrayList<String>();
	public static ArrayList<String> classes=new ArrayList<String>();
	public static void main(String[] args) throws Exception {
		
		String srcfolder="D:\\umlparser\\uml-sequence-test";
		File folder=new File(srcfolder);	

		 for(File fileobj:folder.listFiles())
			{
				if(fileobj.getName().endsWith(".java"))
				{
					String path=fileobj.getPath();
					filePaths.add(path);
					
				}
				if(fileobj.getName().endsWith(".java"))
				{
					FileInputStream in = new FileInputStream(fileobj);
					CompilationUnit cu = JavaParser.parse(in);
					for (TypeDeclaration typeVar : cu.getTypes()) {
			            if(typeVar instanceof ClassOrInterfaceDeclaration && !((ClassOrInterfaceDeclaration) typeVar).isInterface())
			            {
			                classes.add(typeVar.getName());
			            }
			        }
				}
				
			}
		 for(File fileobj:folder.listFiles())
			{
			 if(fileobj.getName().endsWith(".java"))
				{
				 FileInputStream in = new FileInputStream(fileobj);
				 CompilationUnit cu = JavaParser.parse(in);
				 
				 
				 for (TypeDeclaration typeDec : cu.getTypes()) {
			            String className = typeDec.getName();
			            if(classes.contains(className))
			            {
			                List<BodyDeclaration> members = typeDec.getMembers();
			                if (members != null && members.size() > 0) {
			                    for (BodyDeclaration member : members) {
			                        if (member instanceof MethodDeclaration) {
			                            HashMap<String, ArrayList> methodMap = getMethodInfo(member);
			                           /* if (!methodMap.isEmpty()) {
			                                methodMaps.add(methodMap);
			                            }*/
			                        }
			                    }


			                }
			            }
			        }
				}
			}
		 for(String a:filePaths)
		 {
			 System.out.println(a);
		 }
		 
		 for(String b:classes)
		 {
			 System.out.println(b);
		 }
	}
	private static HashMap<String, ArrayList> getMethodInfo(BodyDeclaration member) {
		 HashMap<String, ArrayList> methodInfo = new HashMap<String, ArrayList>();
	        MethodDeclaration method = (MethodDeclaration) member;
	        String methodName = method.getName();
	        String parentClass = "";

	        if (method.getParentNode() instanceof ClassOrInterfaceDeclaration) {
	            parentClass = ((ClassOrInterfaceDeclaration) method.getParentNode()).getName().toString();
	            System.out.println(methodName+"*********"+ ((ClassOrInterfaceDeclaration) method.getParentNode()).getName().toString());
	        }
		return null;
	}

}
