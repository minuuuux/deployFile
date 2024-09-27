package com;

import java.util.Scanner;


public class deployCopy {
	
	public static void main(String[] args) {
		
		try
		{
			String message;
	        Scanner scan = new Scanner(System.in);      // 문자 입력을 인자로 Scanner 생성
	
	        String base = "";
	        String input = "";
	        /*
	        System.out.println("사용법 : 복사할 파일, 복제될 위치의 파일(d:\\src/aaa.txt c:\\deploy/aaa.txt)");
	        System.out.print("입력 : ");
	        input = scan.nextLine();
	        base = input;
	
	        String[] tmp = base.split(" ");
	        String source = tmp[0];    C:\project_svn\branches\iiac\mtg
	        String target = tmp[1];
	        boolean retCopy = false;   d:\deploy
	    	if (source != null && source.length() > 0
	    		&& target != null && target.length() > 0) {
	    		retCopy = EgovFileTool.copyFile(source, target);
	    	}*/
	    	
	        String srcDir = "";
	        String destDir = "";
	        String sourceYN = "";
	        System.out.print("원본 소스 폴더 경로(Maven Root Directory) 마지막 /는 제외 :  ");
	        srcDir = scan.nextLine();
	        System.out.print("목적지 폴더 경로(베포할 폴더) 마지막 /는 제외 :  ");
	        destDir = scan.nextLine();
	        System.out.print("사용법 : 소스(java) 파일 포함여부 [포함(Y) 미포함(N)] : ");
	        sourceYN = scan.nextLine();
	        System.out.println("사용법 : svn show log 에서 보이는 Path 복사.");
	        String[] tmp = new String[2];
	        String source = "";
	        String target = "";
	        String ext = "";
	        int egovIndex;
	        int webappIndex;
	        String path = "";
	        do{
		        System.out.print("입력(입력을 마치려면 q를 입력하세요 ) : ");
		        input = scan.nextLine();

		        if (input.equalsIgnoreCase("q")) break;
	        	
		        ext = input.substring(input.lastIndexOf(".")+1);
		        egovIndex = input.indexOf("egovframework");
		        webappIndex = input.indexOf("webapp");
		        //System.out.println("ext:"+ext);
	        	if (egovIndex != -1)
	        	{
	        		path = input.substring(egovIndex); // java, xml, properties
	        	}
	        	else
	        	{
	        		path = input.substring(webappIndex+7); // jsp, css, images, ...
	        	}
		        //System.out.println("path:"+path);
	        	
	        	if (webappIndex != -1 )
		        {	
	        		if (egovIndex == -1) // ROOT 폴더 이하의 jsp, images, css, js 등
	        		{
			        	source = srcDir + "/src/main/webapp/" + path;
			        	target = destDir + "/" + path;
	        		}
	        		else  // WEB-INF 폴더의 jsp 파일들
	        		{
			        	source = srcDir + "/src/main/webapp/WEB-INF/jsp/" + path;
			        	target = destDir + "/WEB-INF/jsp/" + path;
	        			
	        		}
		        } 
	        	else 
		        {
	        		if (ext.equals("java") || ext.equals("xml") || ext.equals("properties") )
			        {
			        	source = srcDir + "/target/classes/" + path;
			        	source = source.replace(".java", ".class");
			        	target = destDir + "/WEB-INF/classes/" + path;
			        	target = target.replace(".java", ".class");
			        }
	        		else
	        		{
	        	        System.out.println("컴파일된 target 폴더의 java(class), xml, properties 이외의 확장자가 있습니다.");
	        		}
		        }
		        System.out.println("source["+source + "] " + "target["+target+"]");
		        
		    	if (source != null && source.length() > 0
		    		&& target != null && target.length() > 0) {
		    		EgovFileTool.copyFile(source, target);
		    		
		    		if(ext.equals("java")){
			    		String sourceIC = source.replace(".class", "$1.class");
			    		String targetIC = target.replace(".class", "$1.class");
			    		int ineerclassCnt = 2;
			    		while(EgovFileTool.copyFile(sourceIC, targetIC)){
			    			sourceIC = source.replace(".class", "$"+ineerclassCnt+".class");
			    			targetIC = target.replace(".class", "$"+ineerclassCnt+".class");
			    			ineerclassCnt++;
			    		};
		    		}
		    	}
		    	
     	        System.out.println("sourceYN:"+sourceYN);
        	    System.out.println("ext"+ext);
        		if (sourceYN.equals("Y") && ext.equals("java"))
        		{
		        	source = srcDir + "/src/main/java/" + path;
        	       /* System.out.println("srcDir:"+srcDir);
        	        System.out.println("path:"+path);
        	        System.out.println("source:"+source);*/
		        	target = destDir + "/WEB-INF/classes/" + path;
        	        //System.out.println("target:"+target);
		    		EgovFileTool.copyFile(source, target);
        		}

	        }while(!input.equalsIgnoreCase("q"));
	        System.out.println("프로그램을 종료합니다.");
	        scan.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
