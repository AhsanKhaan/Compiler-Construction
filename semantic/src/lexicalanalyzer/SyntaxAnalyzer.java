/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Muhammad anas
 */
class SyntaxAnalyzer {
    
AtomicReference<String> T=new AtomicReference<>();
AtomicReference<String> T1=new AtomicReference<>();    
AtomicReference<String> T2=new AtomicReference<>();    
AtomicReference<String> T3=new AtomicReference<>();    
AtomicReference<String> T4=new AtomicReference<>();    
AtomicReference<String> T5=new AtomicReference<>();
AtomicReference<String> RT=new AtomicReference<>();
AtomicReference<String> NT=new AtomicReference<>();    
AtomicReference<String> TM=new AtomicReference<>();
AtomicReference<String> AM=new AtomicReference<>();  
AtomicReference<String> N2=new AtomicReference<>();
AtomicReference<String> N3=new AtomicReference<>();
AtomicReference<String> AL=new AtomicReference<>();    
AtomicReference<String> NAL=new AtomicReference<>();
AtomicReference<String> NPL=new AtomicReference<>();    
AtomicReference<String> N=new AtomicReference<>();
AtomicReference<String> N1=new AtomicReference<>();
AtomicReference<String> NET=new AtomicReference<>();    
AtomicReference<String> ET=new AtomicReference<>();    
AtomicReference<String> PN=new AtomicReference<>();
AtomicReference<String> ET2=new AtomicReference<>();    
AtomicReference<String> NET2=new AtomicReference<>();
AtomicReference<String> NN=new AtomicReference<>();
AtomicReference<String> NRT=new AtomicReference<>();
        String path = "TokenSet.txt";
        String classPart = null;
        String valuePart = null;
        String lineNum = null;
        String line;
        int numOfLines = 0;
        int scope = 0, currScope = 0;
        int n = 0;
        tokenSet[] token;
        
        ArrayList<SymbolTable> li = new ArrayList<SymbolTable>();
        
        Stack<Integer> stack = new Stack<Integer>();
        public String errors = null; 
        public SyntaxAnalyzer(){
        classPart = null;
            valuePart = null;
            lineNum = null;
            line = null;
        }
       
String lookUp(String N, String pType, char type)
        {
       
            String ret = null;
            if (type=='d')
            {
                for(SymbolTable s : li)
                {
                    if (s.name == N)
                    {
                        if (s.scope == currScope)
                        {
                            return s.type;
                        }
                    }
                }
            }
            else if (type == 'a')
            {
                for(SymbolTable s : li)
                {
                    if (s.name == N)
                    {
                        if (stack.contains(s.scope))
                        {
                            if (s.rType == null)
                                return s.type;
                        }
                    }
                }
            }
            else if (type == 'f')
            {
                for(SymbolTable s : li)
                {
                    if (s.name == N)
                    {
                        if (s.type == pType)
                        {
                            if (type == 'f')
                                return "";
                            else
                            {
                                if (s.scope != -1)
                                    return s.rType;
                            }
                        }
                    }
                }
            }           
            return ret;
        }
 String Compare(String T1, String T2, String Op)
        {
            String rType = null;
            if (T2 != null)
            {
                ArrayList<String> ArthOp =new ArrayList<>();
                ArthOp.add("+");
                ArthOp.add("-");
                ArthOp.add("*");
                ArthOp.add("/");
                ArrayList<String> AssgnOp =new ArrayList<>();
                ArthOp.add("+=");
                ArthOp.add("-=");
                ArthOp.add("*=");
                ArthOp.add("/=");
                //String[] AssgnOp = { "+=", "-=", "*=", "/="};
                ArrayList<String> RelOp =new ArrayList<>();
                ArthOp.add("G");
                ArthOp.add("L");
                ArthOp.add("E");
                ArthOp.add("GE");
                ArthOp.add("LE");
                ArthOp.add("NE");
               // String[] RelOp={ "G", "L", "E", "GE", "LE", "NE" };
                if (Op.equals(ArthOp) || AssgnOp.contains(Op))
                {
                    if ("int".equals(T1) && "int".equals(T2))
                    {
                        if ("/".equals(Op))
                            rType = "float";
                        else
                            rType = "int";
                    }
                    else if ((T1 == "int" || T1 == "float") && (T2 == "int" || T2 == "float"))
                    {
                        if (ArthOp.contains(Op))
                        {
                            rType = "float";
                        }
                        else
                        {
                            rType = T1;
                        }
                    }
                }
                else if (Op == "=")
                {
                    if (T1 == T2)
                        rType = T1;
                    else
                    {
                        if ((T1 == "int" || T1 == "float") && (T2 == "int" || T2 == "float"))
                            rType = T1;
                    }
                }
                else if (RelOp.contains(Op))
                {
                    if (T1 == T2)
                        rType = "bool";
                }
                else if (Op == "AND" || Op == "OR")
                {
                    if (T1 == "bool" && T2 == "bool")
                        rType = "bool";
                }
            }
            else{
                if (Op == "NOT")
                {
                    if (T1 == "bool")
                        rType = "bool";
                }
            }
            return rType;
        }
void insert(String N, String type, String rType, int Scope)
        {
            li.add(new SymbolTable(N, type, rType, Scope));
        }




int index=0;
    validator keywords=new validator();
   
    public String dataType(String VP){
        
        for (int j = 0; j < 3; j++) {
           if(keywords.key[0][j].equals(VP))
               return LexicalAnalyzer.tokens.get(index).CP;
        }
        if( "str".equals(LexicalAnalyzer.tokens.get(index).CP))
            return LexicalAnalyzer.tokens.get(index).CP;
        
    return "";
    }
    public String AccessModifier(String AM){
        for (int j = 0; j < 3; j++) {
           if(keywords.key[14][j].equals(AM))
               return LexicalAnalyzer.tokens.get(index).CP;
        }
    return "";
    }
    
    public boolean repeat(){
        keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
            //FIRST(<repeat>) = {repeat}
            if (keywords.key[2][0].equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                //<repeat> ? repeat(<F1>, <F2>, <F3>)\\r<Body>
                if (keywords.key[2][0].equals(LexicalAnalyzer.tokens.get(index).VP))
                {
                    index++;
                    if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
                    {
                        index++;
                        if (F1())
                        {
                                   
                                if (F2())
                                {
                                    if ("?".equals(LexicalAnalyzer.tokens.get(index).CP))
                                    {
                                        index++;
                                        if (F3())
                                        {
                                            if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
                                            {
                                                index++;
                                               if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)){
                                                index++;
                                                
                                                   if (Body())
                                                {
                                                    return true;
                                                }
                                            }
                                        }
                                        }
                                    }
                                
                                
                            }
                        }
                    }
                }
            }
        return false;}
  
    private  boolean linebreaking(){
    if(("\\r".equals(LexicalAnalyzer.tokens.get(index).VP) && "end".equals(LexicalAnalyzer.tokens.get(index+1).VP)))
    {   index++;
    index++;
        return true;
    }
    else if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)){
    
    return true;
    }
    
    return false;
    }
        private boolean Body()
        {
           
            
            //FIRST(<Body>) = {\\r  , jabtak , DT , Barbar , agar , return ,  inc_dec , ID , break , continue , this }
            if ("unless".equals(LexicalAnalyzer.tokens.get(index).CP) ||
              LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
               "repeat".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "check".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "ret".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP)||
                    "break".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "cont".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "this".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                ",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                 "\\r".equals(LexicalAnalyzer.tokens.get(index).VP)||
                    "end".equals(LexicalAnalyzer.tokens.get(index).CP))
            { RT.set("");
                //<Body> ? end\\r | <S_ST>end \\r| {<M_ST>}
                
                
                    if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                             if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)){
                    
                                 index++;

                                 return true;
                                 }
                    
                }
            else if (M_ST())
                    {
                        if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
                        {
                            index++;
                             
                             if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)){
                                 index++;

                                 return true;
                                 }
                          }
                
            }
                    else if (S_ST(RT))
                {
                         if("end".equals(LexicalAnalyzer.tokens.get(index).VP)){
                             
                             index++;
                             
                             if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)){
                                 index++;

                                 return true;
                                 }
                          }
                }
            
            }
         return false;
        }
        
        private boolean M_ST()
        {   RT.set("");
            //FIRST(<M_ST>) = { jabtak , DT , Barbar , agar , return ,  inc_dec , ID , break , continue, this , Null}
            if ("unless".equals(LexicalAnalyzer.tokens.get(index).CP) ||
              LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
               "repeat".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "check".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "ret".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP)||
                    "break".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "cont".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "this".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                 if (S_ST(RT))
               
                {
                    if (M_ST())
                    {
                        return true;
                    }
                }
            }
            ////FOLLOW(<M_ST>) = { end }
            else if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
               return true;
            }
            return false;
        }

        
        private boolean S_ST(AtomicReference<String> RT)
    
        {
           
            //FIRST(S_ST) = {jabtak , DT , Barbar , agar , return ,  inc_dec , ID , break , continue , this}
            if ("unless".equals(LexicalAnalyzer.tokens.get(index).CP) ||
              LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
               "repeat".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "check".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "ret".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP)||
                    "break".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "cont".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "this".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<S_ST>?<Unless> | DT <S_St_DT> | <Bar_Bar> | <check> | <Return> | inc_dec  ID<inc_dec_list>\\r|ID <S_St_ID>| <break> | <continue> |<this>
                if (Unless())
                {
                    return true;
                }
                else if (LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) )
                {   T.set(LexicalAnalyzer.tokens.get(index).VP);
                    index++;
                    if (S_St_DT())
                    {   RT=T;
                       return true;
                    }
                }
                else if (repeat())
                {
                     return true;
                }
                else if (check())
                {
                    return true;
                }
               else if (Return(RT))
                {
                   return true;
                }
                else if ( keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP))
                {
                    index++;
                    if ( keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                    {   N.set(LexicalAnalyzer.tokens.get(index).VP);
                        N.set("");
                        index++;
                        if (inc_dec_list(RT,N,T,NT))
                       
                        {
                            if (  "\\r".equals(LexicalAnalyzer.tokens.get(index).VP) )
                            {
                                index++;
                                return true;
                            }
                        }
                    }
                }
                else if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {   N.set(LexicalAnalyzer.tokens.get(index).VP);
                    T.set("");
                    index++;
                    if (S_St_ID(N,T))
                   {
                        return true;
                    }
                }
                else if (BREAK())
                {
                     return true;
                }
                else if (CONTINUE())
                {
                   return true;
                }
            }
           return false;
        }
          private boolean Unless()//while
        {       ET.set("");
                NET.set("");
            if (  "unless".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                if ( "unless".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if ( "(".equals(LexicalAnalyzer.tokens.get(index).CP))
                    {
                        index++;
                        if (Exp(ET,NET))
                        {
                    if ( ")".equals(LexicalAnalyzer.tokens.get(index).VP))
                            {
                                index++;        
                            if ( "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                            { 
                                index++;
                              
                            if (Body())
                                {
                                     return true;
                                }
                            }
                        }
                    }
                    }
                }
            }
            
           return false;
        }

         private boolean Return(AtomicReference<String> RT)
        {
            
            //FIRST(<Return>) = {return}
            if ("ret".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                if ("ret".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if (return2(RT))
                    {
                         return true;
                    }
                }
            }
           return false;
        }

        private boolean return2(AtomicReference<String> RT)
        {   N.set("");
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).CP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).CP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                    "\\r".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                {
                    index++;
                     return true;
                }
                else if(Exp(RT,N))
                {
                    if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        index++;
                        return true;
                    }
                }
            }
          return false;
        }

        private boolean BREAK()
        {
            
            if ("break".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                if ("break".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                    {   
                       return true;
                    }
                }
            }
            return false;
        }

        private boolean CONTINUE()
        {
           
            if ("cont".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                if ("cont".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                    {
                         return true;
                    }
                }
            }
           return false;
        }

        private boolean check()//if-else
        {   ET.set("");
            NET.set("");
            if ("check".equals(LexicalAnalyzer.tokens.get(index).VP) )
            {
                //<check> ? check(<Exp>)\\r <M_ST>\\r end <O_Else>
                if ("check".equals(LexicalAnalyzer.tokens.get(index).VP))
                {
                    
                    index++;
                    if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
                    {
                        index++;
                        if (Exp(ET,NET))
                        {
                            
                            if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
                            {
                                
                                index++;
                                if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)){
                                 index++;
                                    if (M_ST())
                                    {
                                            if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
                                        {
                                                                                    index++;

                                     if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)){


                                            index++;
                                            
                                            if (O_Else())
                                            {
                                               return true;
                                            }
                                        }
                                        }
                                    }
                                }
                                }
                            }
                        }
                    }
                }
            
            return false;
        }
        
        private boolean O_Else()
        {
           
            
            //FIRST(<O_Else>) = {warna , Null}
            if ("otherwise".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<O_Else> ? warna <M_ST>end | Null
                if ("otherwise".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)) {
                        index++;
                      
                    if (M_ST())
                        {
                            
                        
                        if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
                            {
                                index++;
                      if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP)) {
                        index++;
                  
                                return true;
                            }
                        }
                        }
                    }  
                    }
                }
            
            //FOLLOW(<O_Else>) = { jabtak , DT , Barbar , agar , return ,  inc_dec , ID , break , continue,end}
            else if ("unless".equals(LexicalAnalyzer.tokens.get(index).CP) ||
              LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
               "repeat".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "check".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "ret".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP)||
                    "break".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "cont".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "this".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                    "end".equals(LexicalAnalyzer.tokens.get(index).CP)){
                
                
              return true;
            }
           return false;
        }

 private boolean S_St_DT(AtomicReference<String> T)
        
        {
            N.set("");
           
            //FIRST(<S_St_DT>) = {ID , void , DT , [}
            if (LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
                  "void".equals(LexicalAnalyzer.tokens.get(index).CP) ||
keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP)||
              "[".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<S_St_DT> ? ID <S_St_DT2> | <Method_DEC> | <Array_DEC>
                if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {   N.set(LexicalAnalyzer.tokens.get(index).VP);
                   
                    index++;
                    if (S_St_DT2(T,N))
  {
                       return true;
                    }
                }
                else if (Array_DEC("","",T))
                {
                    return true;
                }
            }
             return false;
        }

        private boolean S_St_DT2(AtomicReference<String> T,AtomicReference<String> N)
        {
            
           keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
            ////FIRST(<S_St_DT2>) = { AOP , , , \\r }
            if ("Assignment Operator".equals(validator. checking_operator)||
                ",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP)
                    )
            {
                if (Variable_Link2(T,N))
                {
                    return true;
                }
            }
            return false;
        }

          private boolean S_St_ID(String N,String T)
      {     RT.set("");
            NRT.set("");
            AM.set("public");
           keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
            
          //FIRST(<S_St_ID>) = {inc_dec , = , ID ,  .  , (  }
            ////FIRST(<S_St_ID>) = {inc_dec , AOP , ID , [ ,  .  , (  ,}
            if (keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP)||
                "Assignment Operator".equals(validator. checking_operator)||
                    keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                ".".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                 "(".equals(LexicalAnalyzer.tokens.get(index).CP)||
                    "[".equals(LexicalAnalyzer.tokens.get(index).CP) )
            {
                //<S_St_ID> ? inc_dec | <Assign_Op> | <Object_link> | <Object_Call> | <Method_Call_1>7
                //inc_dec; | <Assign_Op>| <Object_link> | <Object_Call>; | <Method_Call_1>; | [<Exp>] <Assign_Op>	

                //<S_St_ID>?inc_dec; | <Assign_Op>| <Object_Call>; | <Method_Call_1>; | [ <2> | ID <Object_Creation_Exp>
                if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
                   //Changing Cfg
                   N1.set(LexicalAnalyzer.tokens.get(index).VP);
                    index++;
                    if ( "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        index++;
                         return true;
                    }
                }
                else if (Assign_Op(T))
                
               {
                    return true;
                }
                else if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
                   
                    index++;
                }
                else if (  "[".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                   
                    index++;
                }
                else if (Object_Call(N))
                
                {
                    
                    if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        index++;
                      return true;
                    }
                }
                 else if (Method_Call_1(RT,N))
  
                 {
                    if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        index++;
                        return true;
                    }
                }
                
            }
            return false;
        }
           private boolean Object_Call()
        {
           
            //FIRST(<Object_Call>) = {. , [}
            if (".".equals(LexicalAnalyzer.tokens.get(index).CP)||
                "[".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Object_Call> ? . ID <Object_Call>| <Method_Call_1> 
                //<Object_Call>? . <Exp> | [<Exp>].<Exp>
                if (".".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    ET.set("");
                    NET.set("");

                   
                 
                    
                    if (Exp(ET,NET))
                    {
                        return true;
                    }
                }
                else if ("[".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                   index++;
                   ET.set("");
                   NET.set("");
                    if (Exp(ET,NET))
                    {
                        if ("]".equals(LexicalAnalyzer.tokens.get(index).CP))
                        {
                            index++;
                            if (".".equals(LexicalAnalyzer.tokens.get(index).CP))
                            {
                                index++;
                                ET2.set("");
                                NET2.set("");
                                if (Exp(ET2,NET2))
                                {
                                   
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
          return false;
        }

        private boolean Param(AtomicReference<String> AL,String PL,AtomicReference<String> NAL,String NPL)
        {   T.set("");
            N.set("");
          if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP)
                   )
            {
                //<Param>? <Exp> <Param1> | Null
                if (Exp(T,N))
                {   PL+=T;
                    if (Param1(AL,PL,NAL,NPL))
                    {
                        return true;
                    }
                }
                else if(keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ){
                index++;
                  if (Param1(AL,PL,NAL,NPL))
                    {
                        return true;
                    }
                
                }
            }

            else if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                return true;
            }
            return false;
        }

        private boolean Param1(AtomicReference<String> AL,String PL,AtomicReference<String> NAL,String NPL)
        {   T.set("");
            N.set("");

            //FIRST(<Param1>) = {, , Null}
            if (",".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Param1> ? ,  ID <Param1> | Null
                index++;

                if (Exp(T,N))
                {
                    if (Param1(AL,PL,NAL,NPL))
                    {
                        return true;
                    }
                }
                else if(keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ){
                index++;
                    if (Param1(AL,PL,NAL,NPL))
                    {
                        return true;
                    }
                }
            }
            else if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
            {   NAL.set(NPL);
                AL.set(PL);
                return true;
            }
            return false;
        }

             private boolean Method_Call_1(AtomicReference<String> RT,String N)
        {
           
            
            if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Method_Call_1> ? (<Param>) 
                if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
                {   AL.set("");
                   NAL.set("");
                   String PL="";
                   String NPL="";
                    index++;
                    if (Param(AL,PL,NAL,NPL))
                    {
                        if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
                        {
                            index++;
                  if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP) || ".".equals(LexicalAnalyzer.tokens.get(index).VP) )
                        {
                            return true;
                        }
                        }    
                    }
                }
            }
           return false;
        }

          private boolean Assign_Op(String T)
        { String OP="";
        OP=LexicalAnalyzer.tokens.get(index).VP;
           
           keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
           
            //FIRST(<Assign_Op>) = { : }
            if ("Assignment Operator".equals(validator. checking_operator))
            {
                if ("Assignment Operator".equals(validator. checking_operator))
                {   OP=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (Assign_Op2(T,OP))
                    {
                        return true;
                    }
                }
            }
           return false;
        }

        private boolean Assign_Op2(String T,String OP)
        {   ET.set("");
            NET.set("");
            //FIRST(<Assign_Op2>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).CP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).CP ) 
          )
            {
                //<Assign_Op2> ? <Exp>\\r
                if (Exp(ET,NET))
                {
                    if (("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))|| "?".equals(LexicalAnalyzer.tokens.get(index).CP))
                    {
                        index++;
                         return true;
                    }
                }
            }
          return false;
        }


        private boolean F3()
        {
           
            //FIRST(<F3>) = {inc_dec , ID , Null}
            if ( keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP)
                    )
            {
                //<F3> ? inc_dec ID | ID inc_dec| Null
                if (keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP))
                {
                    index++;
                    if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                    {
                        String N=LexicalAnalyzer.tokens.get(index).VP;
                        
                        index++;
                        return true;
                    }
                }
                else if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if (keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        index++;
                       return true;
                    }
                }
            }
            ////FOLLOW(<F3>) = { ) }
            
            else if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                
                
                return true;
            }
            return false;
        }
//
//        private boolean F4()
//        {   keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
//          
//            //FIRST(<F4>) = {inc_dec , AOP}
//            if ( keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
//               "Assignment Operator".equals(validator. checking_operator)) 
//            {
//                //<F4> ? inc_dec | AOP <Exp>
//                if (keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP))
//                {
//                   
//                    index++;
//                }
//                else if ("Assignment Operator".equals(validator. checking_operator))
//                {
//                    index++;
//                    if (Exp())
//                    {
//                       return true;
//                    }
//                }
//            }
//           return false;
//        }
        
        private boolean Exp(AtomicReference T,AtomicReference N)
        {
           
            //FIRST(<Exp>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST , ! , ( , inc_dec  }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP)
                )
            {
                //<Exp> ? <OR_Exp>
                if (OR_Exp(T,N))
                {
                    
                   return true;
                }
            }
          return false;
        }

        private boolean OR_Exp(AtomicReference T,AtomicReference N)
        {
            
            //FIRST(<OR_Exp>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST  }
            //FIRST(<OR_Exp>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST , ! , ( , inc_dec }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP)
                      )
            {
                //<OR_Exp> ? <AND_Exp> <OR_Exp2>
                T.set("");
                N.set("");
                String T1="";
                String N1="";
                if (AND_Exp(T,N))
                {
                    if (OR_Exp2(T2,T,N2,N))
                    {
                         return true;
                    }
                }
            }
           return false;
        }

        private boolean OR_Exp2(AtomicReference<String> T3,String T,AtomicReference<String> M3,String N)
        {
           
            //FIRST(<OR_Exp2>) = {|| , Null}
            if ("||".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                //<OR_Exp2> ? || <AND_Exp> <OR_Exp2> | Null
                if ("||".equals(LexicalAnalyzer.tokens.get(index).VP))
                {
                    String OP=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    RT.set("");
                    NT.set("");
                    
                    if (AND_Exp(RT,NT))
                    {
                       
                        if (OR_Exp2(T3,T,N3,N))
                        {
                            
                             return true;
                        }
                    }
                }
            }

            //FOLLOW(<OR_Exp2>) = { ,  , ) , } , ] , \\r}
            else if (",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                ")".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "}".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).CP)||
                "?".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                N3.set(N);
                
                T3.set(T);
                
               return true;
            }
            return false;
        }

        private boolean AND_Exp(AtomicReference T2,AtomicReference N2)
        {
           
            //FIRST(<AND_Exp>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST  }
     if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP)
                 
                )
            {
                //<AND_Exp> ? <ROP> <AND_Exp2>
                AtomicReference<String> T=new AtomicReference<>("");
                AtomicReference<String> N=new AtomicReference<>("");
                if (ROP(T,N))
                {
                    if (AND_Exp2(T2,T,N2,N))
                    {
                        
                       return true;
                    }
                }
            }
             return false;
        }

        private boolean AND_Exp2(AtomicReference<String> T3,String T,AtomicReference<String> N3,String N)
        {
            
            //FIRST(<AND_Exp2>) = {&& , Null}
            if ("&&".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                //<AND_Exp2> ? && <ROP> <AND_Exp2> | Null
                if ("&&".equals(LexicalAnalyzer.tokens.get(index).VP))
                {   String OP=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    RT.set("");
                    NT.set("");
                    if (ROP(RT,NT))
                    {
                        
                        if (AND_Exp2(T3,T,N3,N))
                        {
                            
                             return true;
                        }
                    }
                }
            }
            ///FOLLOW(<AND_Exp2>) = {||, ,  , ) , } , ] , \\r}
            
            else if ("||".equals(LexicalAnalyzer.tokens.get(index).VP) ||
            ",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                ")".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "}".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).CP)||
                "?".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
            
            {
               return true;
            }
            return false;
        }

        private boolean ROP(AtomicReference<String> T2,AtomicReference<String> N2)
        {
            //FIRST(<ROP>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST  }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP ))
            {
                //<ROP> ? <E> <ROP2>
           T.set("");
           N.set("");
                    
                if (E(T,N))
                {
                    if (ROP2(T2,T,N2,N))
                    {
                        
                        return true;
                    }
                }
            }
          return false;
        }

        private boolean ROP2(AtomicReference T3,AtomicReference T,AtomicReference N3,AtomicReference N)
        {
         keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
            //FIRST(<ROP2>) = {ROP , Null}
            if ("Relational Operator".equals(validator.checking_operator)) //can be '=' TEMP
            {
                //<ROP2> ? ROP <E> <ROP2> | Null
                if ("Relational Operator".equals(validator.checking_operator))
                {
                    index++;
                    if (E( RT,NT))
                    {
                      
                        if (ROP2( T3,T2,N3,N2))
                        {
                            
                            return true;
                        }
                    }
                }
            }

            ////FOLLOW(<ROP2>) = {&& ,||, ,  , ) , } , ] , \\r}
            else if ("&&".equals(LexicalAnalyzer.tokens.get(index).VP)||
                    "||".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                    ",".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                ")".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "}".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "?".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                    {
               return true;
            }
            return false;
        }

       
        private boolean E(AtomicReference T2,AtomicReference N2)
        {
            //FIRST(<E>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST  }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP )
                )
            {
                //<E> ? <T> <E2>
           T.set("");
           N.set("");
          
                if (this.T( T, N))
                {
                    if (E2(T2,T,N2,N))
                    {
                        
                      return true;
                    }
                }
            } return false;
        }

        private boolean E2(AtomicReference T3,AtomicReference T,AtomicReference N3,AtomicReference N)
        {
                  keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
   
            //FIRST(<E2 >) = {Plus_Minus , Null}
            if ("+".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "-".equals(LexicalAnalyzer.tokens.get(index).VP)
                )
            {
                //<E2> ? Plus_Minus <T > <E2> | Null
                if ("+".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "-".equals(LexicalAnalyzer.tokens.get(index).VP)
                )
                {
                   RT.set("");
                   NT.set("");
                    index++;
                    if (this.T(RT,NT))
                    {
                        if (E2(T3,T2,N3,N2))
                        {
                            
                           return true; 
                        }
                    }
                }
            }
            
            //FOLLOW(<E2>) = {ROP , && ,||, ,  , ) , } , ] , \\r}}
            else if ( "Relational Operator".equals(validator.checking_operator)||
                    "&&".equals(LexicalAnalyzer.tokens.get(index).VP)||
                    "||".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                    ",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                ")".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "}".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).CP)||
                "?".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                N3=N;
                T3=T;
                return true;
            }return false;
        }
        
        private boolean T(AtomicReference T2,AtomicReference N2)
        {
            //FIRST(<T>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST  }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP 
                ))
            {
T.set("");
N.set("");
//<T> ? <F> <T2>
                if (F(T,N))
                {
                    if (this.T2(T2,T,N2,N))
                    {
                        
                        return true;
                    }
                }
            }return false;
        }

        private boolean T2(AtomicReference T3,AtomicReference T,AtomicReference N3,AtomicReference N)
        {
                    keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
   
            //FIRST(<T2>) = { M_D_M , Null}
            if (keywords.Validate_MultiDivideMode(LexicalAnalyzer.tokens.get(index).VP))
            {
                //<T2> ? M_D_M <F> <T2> | Nulll
                if (keywords.Validate_MultiDivideMode(LexicalAnalyzer.tokens.get(index).VP))
                {
                    index++;
           RT.set("");
           NT.set("");
                    if (F(RT,NT))
                    {
                       
                        if (this.T2(T3,T2,N3,N2))
                        {
                            
                            return true;
                        }
                    }
                }
            }
            //FOLLOW(<T2>) = { Plus_Minus , ROP , && ,||, ,  , ) , } , ] , \\r}

            else if ("+".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "-".equals(LexicalAnalyzer.tokens.get(index).VP) ||
          "Relational Operator".equals(validator.checking_operator)||
                    "&&".equals(LexicalAnalyzer.tokens.get(index).VP)||
                    "||".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                    ",".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                ")".equals(LexicalAnalyzer.tokens.get(index).VP) ||
//                "}".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "?".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
          
            {
                T3=T;
                N3=N;
                
              return true;
            }
             return false;
        }
        
        private boolean F(AtomicReference<String> RT ,AtomicReference<String> NT)
        {
            //FIRST(<F>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST  }
            //FIRST(<F>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST , ! , ( , inc_dec }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) ||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP ) )
            {
                AtomicReference<String> constT =new AtomicReference<>("");
                //<F> ? ID | <CONST>
                //<F>? ID <id_op>  |<Const> |!<F> | (<Exp>) | Inc_Dec  ID<inc_dec_list>
                if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) )
                {
                   String N=LexicalAnalyzer.tokens.get(index).VP;
                   
                           
                    index++;
                    if (id_op(RT,N,T,NT))
                    {
                       return true;
                    }
                }
                else if (CONST(constT))
                {
                    RT=constT;
                    NT.set(LexicalAnalyzer.tokens.get(index-1).VP);
                        
                    return true;
                }
                else if (  keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP))
                {
              String OP=LexicalAnalyzer.tokens.get(index).VP;
              T.set("");
              NN.set("");
                    index++;
                    if (F(T,NN))
                    {
                        return true;
                    }
                }
                else if (    "(".equals(LexicalAnalyzer.tokens.get(index).CP ))
                {T.set("");
                    index++; 
                    if (Exp(T,NT))
                    {
                        if (    ")".equals(LexicalAnalyzer.tokens.get(index).CP ))
                        {
                            index++;
                           return true;
                        }
                    }
                }
                else if (   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) )
                {
                    index++;
                    if (   keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) )
                    { 
String N=LexicalAnalyzer.tokens.get(index).VP;
N2.set("");
T2.set("");
//N.set(LexicalAnalyzer.tokens.get(index).VP);
                        index++;
                        if (inc_dec_list(T2,N,T,N2))
                        {
                           return true;
                        }
                    }
                }
            }
           return false;
        }
//            private boolean id_op(ref string RT, string N, string T, ref string NT)
            private boolean id_op(AtomicReference RT,String N,String T,AtomicReference NT)
        {
 keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
            
          
            //FIRST(<id_op>) = { Null , ( , [ , . , inc_dec}
            if ("(".equals(LexicalAnalyzer.tokens.get(index).CP ) ||
                "[".equals(LexicalAnalyzer.tokens.get(index).CP ) ||
                ".".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP))
            {
                //<id_op> ? Null | <Method_Call_1> | [ <Exp> ] |<Member_exp> |  Inc_Dec 

//                if (Method_Call_1(ref RT, N))
                if (Method_Call_1(RT,N))
                {
                    NT.set(N);
                    return true;
                }
                else if ("[".equals(LexicalAnalyzer.tokens.get(index).CP ) 
                )
                {
                    ET.set("");
                    NN.set("");
                    index++;
                    if (Exp(ET,NN))
                    {
                        if ("]".equals(LexicalAnalyzer.tokens.get(index).CP ) )
                        {
                          RT.set(T);
                          NN.set(N);
                            index++;
                            return true;
                        }
                    }
                }
                else if (Member_exp(RT,N,NT))
                {
                   return true;
                }
                else if (keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP))
                {
                    
                    index++;
                    NT.set(N);
                    RT.set(T);
                   return true;
                }
            }
////FOLLOW(<id_op>) = {M_D_M , Plus_Minus , ROP , && ,||, ,  , ) , } , ] , ;}
            else if (
                    keywords.Validate_MultiDivideMode(LexicalAnalyzer.tokens.get(index).VP)||
            "+".equals( LexicalAnalyzer.tokens.get(index).VP) ||
            "-".equals( LexicalAnalyzer.tokens.get(index).VP) ||
                    
                 "Relational Operator".equals( validator.checking_operator) ||
            "&&".equals(LexicalAnalyzer.tokens.get(index).VP )||
                                "||".equals(LexicalAnalyzer.tokens.get(index).VP )||        
                    ")".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "}".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                ",".equals(LexicalAnalyzer.tokens.get(index).VP ))
            {
               
                return true;
            }
           return false;
        }

        private boolean Member_exp(AtomicReference RT,String N,AtomicReference NT)
        {
            //FIRST(<Member_exp>) = { . }
            if (".".equals(LexicalAnalyzer.tokens.get(index).VP ) )
            {
                //<Member_exp> -> .ID < Member_exp_2>
                if (".".equals(LexicalAnalyzer.tokens.get(index).VP ) )
                {
                   
                    index++;
                    if (        keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP)){
                        String N1=LexicalAnalyzer.tokens.get(index).VP;
                        T1.set("");
                        index++;
                        if (ME_3(RT,N1,T1,NT))
                        {
                            if(Member_exp(RT,N,NT)){
                            
                            return true;
                            }
                        }
                    }
                }
                }
            
            else if("\\r".equals(LexicalAnalyzer.tokens.get(index).VP )){
            return true; 
            }
            return false;
           
        }

        private boolean ME_3(AtomicReference RT,AtomicReference N1,AtomicReference T1,AtomicReference NT){
        if("(".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "[".equals(LexicalAnalyzer.tokens.get(index).VP )||
                ".".equals(LexicalAnalyzer.tokens.get(index).VP )
                ){
        if(Member_exp(RT,N,NT)){
        return true;
        }
        else if(Member_exp_2(RT,N,T,NT)){
        return true;
        }
        }
        
        
        //follow
        else if(   keywords.Validate_MultiDivideMode(LexicalAnalyzer.tokens.get(index).VP)||
            "+".equals(LexicalAnalyzer.tokens.get(index).VP) ||
            "-".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                    
                 "Relational Operator".equals( validator.checking_operator) ||
            "&&".equals(LexicalAnalyzer.tokens.get(index).VP )||
                                "||".equals(LexicalAnalyzer.tokens.get(index).VP )||        
                    ")".equals(LexicalAnalyzer.tokens.get(index).CP ) ||
                "}".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                ",".equals(LexicalAnalyzer.tokens.get(index).VP )||
                ".".equals(LexicalAnalyzer.tokens.get(index).VP )){
        return true;
        }
        return false;
        }
        
        private boolean Member_exp_2(AtomicReference RT,String N,String T,AtomicReference NT )
        {  
            //FIRST(< Member_exp_2>) = {Null , ( , [}
            if ("(".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "[".equals(LexicalAnalyzer.tokens.get(index).VP ) )
            {
                //< Member_exp_2> -> Null | <Method_Call_1> | [<Exp>]
//                if (Method_Call_1(ref RT, N))
                if (Method_Call_1(RT,N,T,NT))
                {
                    NT.set(N);
                  return true;
                }
                else if ("[".equals(LexicalAnalyzer.tokens.get(index).CP ) )
                {
                   ET.set("");
                   NN.set("");
                    index++;
                    if (Exp(ET,NN))
                    {
                        if ("]".equals(LexicalAnalyzer.tokens.get(index).CP ) )
                        {
                          
                           RT.set(T);
                           NT.set(N);
                            index++;
                            return true;
                        }
                    }
                }
            }
            else if (     keywords.Validate_MultiDivideMode(LexicalAnalyzer.tokens.get(index).VP)||
            "+".equals( LexicalAnalyzer.tokens.get(index).VP) ||
            "-".equals( LexicalAnalyzer.tokens.get(index).VP) ||
                    
                 "Relational Operator".equals( validator.checking_operator) ||
            "&&".equals(LexicalAnalyzer.tokens.get(index).VP )||
                                "||".equals(LexicalAnalyzer.tokens.get(index).VP )||        
                    ")".equals(LexicalAnalyzer.tokens.get(index).CP ) ||
                "}".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP ) ||
                ",".equals(LexicalAnalyzer.tokens.get(index).VP )
                   || ".".equals(LexicalAnalyzer.tokens.get(index).VP )
                 )
            {
                
           
              return true;
            }
           return false;
        }

    

        private boolean inc_dec_list(AtomicReference RT,AtomicReference N,AtomicReference T1,AtomicReference NT)
        {
            keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
   
            //FIRST(<inc_dec_list>) = { [ , . , Null}
            if ( "[".equals(LexicalAnalyzer.tokens.get(index).CP )||
                 ".".equals(LexicalAnalyzer.tokens.get(index).VP ))
            {
                //<inc_dec_list> ? [<Exp>] | .ID[<Exp>] |Null 
                if( "[".equals(LexicalAnalyzer.tokens.get(index).CP )){

                    index++;
                    ET.set("");
                    NN.set("");
                    if(Exp(ET,NN))
                    {
                        if( "]".equals(LexicalAnalyzer.tokens.get(index).CP ))
                        {
                           
                            index++;
                           return true;
                        }
                    }
                }else if( ".".equals(LexicalAnalyzer.tokens.get(index).VP ))
                {
                   
                    index++;
                    if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                    {
                      String N1=LexicalAnalyzer.tokens.get(index).VP;
                        index++;
                        if ( "[".equals(LexicalAnalyzer.tokens.get(index).VP ))
                        { 
                           ET.set("");
                           NN.set("");
                            index++;
                            if(Exp(ET,NN))
                            {
                                if( "]".equals(LexicalAnalyzer.tokens.get(index).CP ))
                                {
                                    index++;
                                
                                if( "\\r".equals(LexicalAnalyzer.tokens.get(index).VP ))
                                {
                                    
                                   return true;
                                }
                            }
                            }
                        }
                        }
                    }
                }
                
            
            //FOLLOW(<inc_dec_list>) = {M_D_M , Plus_Minus , ROP , && ,||, ,  , ) , } , ] , \\r}
            else if(keywords.Validate_MultiDivideMode(LexicalAnalyzer.tokens.get(index).CP) ||
                   "+".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "-".equals(LexicalAnalyzer.tokens.get(index).VP) ||
          "Relational Operator".equals(validator.checking_operator)||
                    "&&".equals(LexicalAnalyzer.tokens.get(index).VP)||
                    "||".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                    ",".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                ")".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                //"}".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "]".equals(LexicalAnalyzer.tokens.get(index).VP)||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                
               return true;
            }
             return false;
        }

        
        private boolean CONST(AtomicReference T)
{
            //FIRST(<CONST>) = { INT_CONST, FLOAT_CONST , STRING_CONST , CHAR_CONST ,BOOL_CONST }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ) )
            {
                //<CONST>  ? INT_CONST| FLOAT_CONST | STRING_CONST | CHAR_CONST | BOOL_CONST
                if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP ))
                {
                    if ( keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        T.set("aur_int");
                    }
                    else if ( keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        T.set("aur_float");
                    }
                    else if ( keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        T.set("aur_String");
                    }
                    else if ( keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP))
                    {T.set("aur_Char");
                    }
                    else if ( keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP))
                    {
                        T.set("aur_bool");
                    }
                    index++;
               
                    return true;
                }
            }
            return false;
        }


 public boolean F2(){
     //FIRST(<F2>) = { ID, INT_CONST , FLOAT_CONST , STRING_CONST , CHAR_CONST , BOOL_CONST , Null }
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP)
       )
            {
                ET.set("");
                N.set("");
                //<F2> ? <Exp> <X> | Null
                if (Exp(ET,N))
                {
                   if(X())
                    {
                        return true;
                    }
                }
            }
                ////FOLLOW(<F2>) = { ?}
             
            else if ("?".equals(LexicalAnalyzer.tokens.get(index).CP))
            {      
                return true;
            }
            return false;
        }
 
 public  boolean X()
        {
           
            //FIRST(<X>) = { , , Null}
            if (",".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<X> ? , <Exp> <X> | Null
                ET.set("");
                N.set("");
                index++;
                if (Exp(ET,N))
                {
                   
                    if (X())
                    {
                        return true;
                    }
                }
            }

            ////FOLLOW(<X>) = { ? }
            
            else if ("?".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                return true;
            }
            return false;
        }

        
    
 
    
    public boolean Condition_for(){
keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
 if(ID_const()){
        if("Relational Operator".equals(validator.checking_operator)){
        if(ID_const()){
        return true;
        }
        }
        }
        
        return false;}

    public boolean ID_const(){
    if(keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).CP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).CP))
    {
        return true;
    }
    
    return false;}
    
     private boolean F1()
        {
            //FIRST(<F1>) = {DT , ID , Null}
            if ( keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP ) ||
           LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)))
            {
                //<F1> ? <DEC> |ID <Assign_Op> | Null
                if (DEC())
                {
                    return true;
                }
                else if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP ))
                { String N=LexicalAnalyzer.tokens.get(index).VP;
                
                   index++;
                    if (Assign_Op(T))
                    {
                         return true;
                    }
                }
            }
            //FOLLOW(<F1>) = { ?}
            else if ("?".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                index++;
              return true;
            }
             return false;
        }
     public boolean assignment_(){
                keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
           
           
            //FIRST(<Assign_Op>) = { : }
            if ("Assignment Operator".equals(validator. checking_operator))
            {
                //<Assign_Op>? AOP <Assign_Op2>	
                if ("Assignment Operator".equals(validator. checking_operator))
                {  index++;
                    if (Id_Const())
                    {
                        return true;
                    }
                }
            }
           return false;
        }
public boolean Id_Const()
{if(  keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).CP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
        keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
        keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).CP)
           ){
        index++;
    if(";".equals(LexicalAnalyzer.tokens.get(index).CP))
    index++;
    return true;
   }
return false;
}
private boolean DEC()
        {
           
            //T.set("");
String T="";            
//FIRST(<DEC>) = { DT}
            if (LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)))
            {
                //<DEC> ? DT <Variable_Link>
                if (LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)))
                {
                    T=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (Variable_Link(T))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean Variable_Link(String T)
        {
          
            String N="";
            //FIRST(<Variable_Link>) = {ID} 
            if ( keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Variable_Link> ? ID <Varaiable_Link2>
                if ( keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
                    N=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (Variable_Link2(T,N))
                    {
                        
                         return true;
                    }
                }
            }
           return false;
        }

            private boolean Variable_Value(AtomicReference T,AtomicReference N)
        {
            if (keywords.Validate_Char(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_Float(LexicalAnalyzer.tokens.get(index).VP) ||
        keywords.Validate_String(LexicalAnalyzer.tokens.get(index).VP)  ||
        keywords.Validate_Int(LexicalAnalyzer.tokens.get(index).VP)   ||
           keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).VP) ||
           keywords.Validate_bool(LexicalAnalyzer.tokens.get(index).VP)||
                   keywords.Validata_IncDec(LexicalAnalyzer.tokens.get(index).VP) ||
                keywords.Validate_not(LexicalAnalyzer.tokens.get(index).VP)||
                "(".equals(LexicalAnalyzer.tokens.get(index).VP )
        )
            {
                //<Variable_Value>  ? <Exp><LIST>  	
                if (Exp(T,N))
                {
                  
                    if (LIST(T))
                    {
                        return true;
                         }
                }
            }
           return false;
           }
   

    private boolean LIST(String T)
        {
            String N="";
            //FIRST(<LIST >) = {, , \\r}
            if (",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                    "?".equals(LexicalAnalyzer.tokens.get(index).VP) ||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP) )
            {
                //<LIST > ? , ID <Variable_Link2> | \\r | ?
                if (",".equals(LexicalAnalyzer.tokens.get(index).CP)) {
                    
                    //semanticAnalyzer.insertVariables(N, T, semanticAnalyzer.currentScope());
                    index++;

                    if ( keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP ))
                    { N=LexicalAnalyzer.tokens.get(index).VP;
                        index++;
                        if (Variable_Link2(T,N))
                        {
                             return true;
                        }
                    }
                }
                else if ( "\\r".equals(LexicalAnalyzer.tokens.get(index).VP) )
                {
                    index++;
                    return true;
                }
                
                else if ( "?".equals(LexicalAnalyzer.tokens.get(index).VP) )
                {
                    index++;
                    return true;
                }
            }
           return false;
        }
     private boolean Variable_Link2(String T,String N)
        {String OP="";
                           keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
      
                           if (",".equals(LexicalAnalyzer.tokens.get(index).CP)||
                    "\\r".equals(LexicalAnalyzer.tokens.get(index).VP)||
                    "Assignment Operator".equals(validator. checking_operator));
                    {
                //<Variable_Link2>  ?:<Variable_Value>| <LIST>
                //<Variable_Link2>  ?:<Value_Method>| <LIST>
               
                        if ("Assignment Operator".equals(validator. checking_operator))
                {
                    OP=LexicalAnalyzer.tokens.get(index).VP;
                    
                    index++;
                    if (Variable_Value(T,OP,N))
                    {
                        return true;
                    }
                }
                else if (LIST(T))
                {
                    return true;
                }
            }
            return false;
        }

    
    public boolean class_(){
        int a=1;
        if(LexicalAnalyzer.tokens.get(a).CP.equals("a")){
            
       
        
        }
return true;        
        }
    
        private boolean Class_Base(AtomicReference N)
        {
           
            if ("extends".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                index++;
                if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
                     N.set(LexicalAnalyzer.tokens.get(index).VP);
                    index++;
                   return true;
                }
            }

            else if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                 return true;
            }

            return false;
        }

        private boolean Class_Link(String AM)
        {
            
            if ("class".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Class_Link> ? class ID <Class_Base><Class_Body>end
                index++;
                if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
                    PN.set("");
                    String N=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (Class_Base(PN))
                    {
                            if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                            {
                                index++;
                                if (Class_Body())
                                {
                                    
                                  if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
                                { index++;
                              if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                            {
                                index++;
                            }  
 
                                         return true;
                                    }               
//                                    
                                }
                            }
                        }
                    }
                }
            
            

            // 
            return false;
        }

        private boolean Class_Body()
        {
            
            //FIRST(<Class_Body>) = { access_modifier , static , DT ,void ,ID , class  , Null
            if (
                    
                    LexicalAnalyzer.tokens.get(index).CP.equals("Access Modifier") ||
              LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
                 "void".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP)||  
               "class".equals(LexicalAnalyzer.tokens.get(index).CP)
                    ) {
                //<Class_Body> ? <Class_Member> <Class_Body> | Null
                if (Class_Member())
                {
                    if (Class_Body())
                    {
                        return true;
                    }
                }
            }

            //FOLLOW(<Class_Body>) = { end }
            else if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
              return true;
            }
            System.out.println(index);
System.out.println(""+LexicalAnalyzer.tokens.get(index).CP);
            
           return false;
        }

        private boolean Class_Member()
        {
                     
            //FIRST(<Class_ Member >) = { access_modifier , static , DT ,void ,ID , class }
            if (    LexicalAnalyzer.tokens.get(index).CP.equals("Access Modifier") ||
              LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
                 "void".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP)||  
               "class".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "null".equals(LexicalAnalyzer.tokens.get(index).CP))            {
                AM.set("");
                String AM1="";
                if (Access_Modifier(AM))
                {
                    if (Member_Link(AM1))
                    {
                        return true;
                    }
                }
            }
             return false;
        }
        
        public boolean validateCFG(){
            if(Start()){
            if(LexicalAnalyzer.tokens.get(index).CP.equals("$")){
            return true;
            }
            
            }
            
            
            return false;
        
        }
        public boolean Start()
        {
            
            //FIRST(<Class_Dec>) = { access_modifier, class}
            if (LexicalAnalyzer.tokens.get(index).CP.equals("Access Modifier") ||
               "class".equals(LexicalAnalyzer.tokens.get(index).CP)  )
            {

                //<Class_Dec>  ? <Access_Modifier><Class_Link><secondclass>
                if (Access_Modifier())
                {
                    if (Class_Link(String AM))
                    {
                        if(secondclass()){
                         return true;
                        }
                    }
                }
            }
            // 
          return false;
        }
        private boolean secondclass(){
    if(LexicalAnalyzer.tokens.get(index).CP.equals("Access Modifier") ||
               "class".equals(LexicalAnalyzer.tokens.get(index).CP) ){
        if(Start()){
            return true;}
    
    }
    else if(LexicalAnalyzer.tokens.get(index).CP.equals("$")
            ){
        
        return true;}
 
    return false;  
}

        private boolean Access_Modifier(AtomicReference AM)
        {
            
            //FIRST(<Access_Modifier>) = { access_modifier, Null}
            if (LexicalAnalyzer.tokens.get(index).CP.equals("Access Modifier") )
            {
                //<Access_Modifier> ? access_modifier | Null
                if (LexicalAnalyzer.tokens.get(index).CP.equals("Access Modifier") )
                {
                    AM.set(LexicalAnalyzer.tokens.get(index).VP);
                    index++;
                    return true;
                }
            }

            //FOLLOW(<Access_Modifier>) = { class , DT ,void ,ID  }
            else if ( LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
                 "void".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP)||  
               "class".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                AM.set("public");
              return true;
            }

         return false;
        }


        private boolean Member_Link(String AM)
        {
           
            if (  "static".equals(LexicalAnalyzer.tokens.get(index).CP) ||
              LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
                 "void".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP)||  
               "class".equals(LexicalAnalyzer.tokens.get(index).CP) )
            { 
                String TM1="";
                    if (SS_A(AM,TM1))
                    {
                        return true;
                    
                }

                else if ("void".equals(LexicalAnalyzer.tokens.get(index).CP))
                { 
                    String RT=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    
                    if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                    {String N=LexicalAnalyzer.tokens.get(index).VP;
                        index++;
                        if (Method_Link3(AM,TM1,RT,N))
                        {
                            return true;
                        }
                    }
                }

                else if (LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)))
                {
                    String T=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (DT_2(T,AM))
                    {
                        return true;
                    }
                }

                else if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
                   String N=LexicalAnalyzer.tokens.get(index).VP;
                   

                    index++;
                    if (Object_Constructor_Dec(AM,N))
                    {
                         return true;
                    }
                }
            }
            return false;
        }
        

        private boolean Object_Constructor_Dec(String AM,String N)
        {
           
            ////FIRST(<Object_Constructor_DEC>) = { ID, [ , (}
            if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                "[".equals(LexicalAnalyzer.tokens.get(index).CP)||
                    "(".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Object_Constructor_DEC> ?  <Constructor_DEC>
               if(Constructor_DEC(AM,N))
                {
                  return true;
                }
            }
          return false;
        }

        private boolean DT_2(String T,String AM)
        {
            //FIRST(<DT_2>) = {ID , [}
            if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP)||
               "[".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<DT_2>? ID <ID_1>|< Array_DEC>
                if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {
String N=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (ID_1(AM,T,N))
                    {
                       return true;
                    }
                }

                else if (Array_DEC(AM,"",T))
                {
                     return true;
                }
            }
            return false;
        }

        private boolean ID_1(String AM,String RT,String N)
        {
           
              keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
          
            //FIRST(<ID_1>) = {( , AOP , , , \\r }
            if( "(".equals(LexicalAnalyzer.tokens.get(index).CP) ||
             "Assignment Operator".equals(validator. checking_operator) ||
                ",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                ";".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<ID_1>?<Varaiable_Link2> | <Method_Link 3>
                if (Variable_Link2(RT,N))
                {
                    return true;
                }
                else if (Method_Link3(AM,"",RT,N))
                {
                   return true;
                }
            }
           return false;
        }

        
        
        
        
        
        
        
        
        
        
        
        public boolean SS_A(String AM,String TM)
        {
            System.out.println("token index:"+LexicalAnalyzer.tokens.get(index).CP);
            if (   LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) ||
                 "void".equals(LexicalAnalyzer.tokens.get(index).CP) ||
               keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))         {
                //<SS_A> ?  DT <DT_1> |ID <Id_OArray> |void ID<Method_Link3> 
                if (LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)))
                {   String RT=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (DT_1(AM,TM,RT))
                    {
                         return true;
                    }
                }
                else if ("void".equals(LexicalAnalyzer.tokens.get(index).CP))
                {   String RT=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                    {   String N=LexicalAnalyzer.tokens.get(index).VP;
                        index++;
                        if (Method_Link3(AM,TM,RT,N))
                        {
                           return true;
                        }
                    }
                }
//                else if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
//                {
//                
//                    if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
//                    {
//                        index++;
//                        if (Method_Link3())
//                        {
//                           return true;
//                        }
//                    }
//                }
            }

          return false;
        }

        private boolean DT_1(String AM,String TM,String RT)
       {
           
            //FIRST(<DT_1>) = {ID , [}
            if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP) ||
                "[".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<DT_1>?  ID <ID_2>| <Array_DEC>
                if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                {   String N=LexicalAnalyzer.tokens.get(index).VP;
                    index++;
                    if (ID_2(AM,TM,RT,N))
                    {
                        return true;
                    }
                }
                else if (Array_DEC(AM,TM,RT))
                {
                    System.out.println("in Array Dec"+index);
                   return true;
                }
            }
            return false;
        }

        private boolean ID_2(String AM,String TM,String RT,String N)
        {
           keywords.Validate_Operator(LexicalAnalyzer.tokens.get(index).VP);
            System.out.println(validator. checking_operator);
      //FIRST(<ID_2>) = {( , AOP , , , \\r }
            if ("(".equals(LexicalAnalyzer.tokens.get(index).CP)||  
                    "Assignment Operator".equals(validator. checking_operator) ||
",".equals(LexicalAnalyzer.tokens.get(index).CP) ||
                "\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                //<ID_2>?  <Method_Link3> | <Variable_Link2>
                if (Method_Link3(AM,TM,RT,N))
                {
                    return true;
                }
                else if (Variable_Link2(RT,N))
                {
                  return true;
                }
            }
           return false;
        }
private boolean Method_Link3(String AM,String TM,String RT,String N)
        {
            
            //FIRST(<Method_Link 3>) = { ( }
            if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Method_Link 3>  ? (<List_Param>)\\r <M_St>end\\r 
                if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    
                    index++;
                    String PL="";
                    String NPL="";
                    AL.set("");
                    NAL.set("");

                    if (List_Param(AL,PL,NAL,NPL))
                    {
                        if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
                        {
                            
                            index++;
                           
                            
                            if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                            {
                                index++;
                              if (M_ST())
                                {
                                  if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
                                    {
                                        index++;
                                        if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                                    {index++;
                                  
                                        return true;
                                    } 
                                    }
                                }
                              return true;
                            }                            
                        }
                    }
                }
            }
           return false;
        }
private boolean List_Param(AtomicReference<String> AL,String PL,AtomicReference<String> NAL,String NPL)
        {
        
            
            //FIRST(<List_Param>) = {DT , Null}
            if ( LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) )
            {
                //<List_Param> ? DT ID <List_Param1> | Null
                if ( LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) )
                {   String T=LexicalAnalyzer.tokens.get(index).VP;
                    PL+=T;
                    index++;
                    if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                        
                    {   String N=LexicalAnalyzer.tokens.get(index).VP;
                        NPL+=N;
                        index++;//during SA
                        if (List_Param1(AL,PL,NAL,NPL))
                        {
                            return true;
                        }
                    }
                }
            }
            //FOLLOW(<List_Param>) = { ) }
            else if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
            {   NAL.set(NPL);
                AL.set(PL);
                return true;
            }
            return false;
        }

        private boolean List_Param1(AtomicReference<String> AL,String PL,AtomicReference<String> NAL,String NPL)
           {
          
            
            //FIRST(<List_Param>) = {DT , Null}
            if (",".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<List_Param> ?, DT ID <List_Param1> | Null
                if (",".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if ( LexicalAnalyzer.tokens.get(index).CP.equals(dataType(LexicalAnalyzer.tokens.get(index).VP)) )
                    {   String T=LexicalAnalyzer.tokens.get(index).VP;
                        PL+=","+T;
                        index++;
                        if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                        {String N=LexicalAnalyzer.tokens.get(index).VP;
                            NPL+=","+N;
                            index++;
                            if (List_Param1(AL,PL,NAL,NPL))
                            {
                               return true;
                            }
                        }
                    }
                }
            }
            //FOLLOW(<List_Param>) = { ) }//
            else if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
            {NAL.set(NPL);
            AL.set(PL);
              return true;
            }
            return false;
        }


        private boolean Constructor_DEC(String AM,String N)
                        {
           
            //FIRST(<Constructor_DEC>) = {ID}
            //FIRST(<Constructor_DEC>) = { ( }
            if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Constructor_DEC> ?  ID (<List_Param>) <M-St>end
                //<Constructor_DEC>? (<List_Param>) {<M-St>}
                if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
                {   AL.set("");
                String PL="",NPL="";
                NAL.set("");
                    index++;
                    if (List_Param(AL,PL,NAL,NPL))
                    {
                        if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
                        {
                            index++;
                           
                       if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                            {
                                index++;
                                
                                //string MT = "";
                                if (M_ST())
                                {
                                    if ("end".equals(LexicalAnalyzer.tokens.get(index).CP))
                                    {
                                        index++;
                                    if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                                    {
                                        index++;
                                        return true;
                                    }
                                }
                            
                        }
                            }
                        }
                    }
                    
                }

            }
          return false;
        }
        
        
private boolean Array_DEC()
        {
          
            //FIRST(<Array_DEC>) = {[}
            if ("[".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Array_DEC> ?  [] ID <INIT_Array>
                if ("[".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
    System.out.println("in DEC:"+index);
                   
                    index++;
                    if ("]".equals(LexicalAnalyzer.tokens.get(index).CP))
                    {
                       
                        index++;
                        if (keywords.Validate_Identifire_SA(LexicalAnalyzer.tokens.get(index).CP))
                        {
                            index++;
                         
                            if (INIT_Array())
                            {
                                return true;
                            }
                        }
                    }
                }
            }
           return false;
        }
 private boolean INIT_Array()
        {
            //FIRST(<INIT_Array>) = {\\r , :}
            if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP) ||
               ":".equals(LexicalAnalyzer.tokens.get(index).VP))
            {
                //<INIT_Array> ? \\r | :[<ID_Const>]<INIT_Array>
                if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                {
                    index++;
                     return true;
                }
                else if (":".equals(LexicalAnalyzer.tokens.get(index).VP))
                {
                    System.out.println(""+index);
                    index++;
                            if ("[".equals(LexicalAnalyzer.tokens.get(index).CP))
                            {
                               
                                index++;
                                if (Array_const())
                                {
                                    if ("]".equals(LexicalAnalyzer.tokens.get(index).CP))
                                    {
                                       index++;
                                       if(INIT_Array())
                                           return true;
                                        }
                                    
                        }
                    }
                }
            }
           return false;
        }

    
        private boolean Array_const()
        {
            //FIRST(<Array_const>) = {{ , ;}
            if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Array_const> ? <Array_C> | \\r
                if ("\\r".equals(LexicalAnalyzer.tokens.get(index).VP))
                {
                    index++;
                   return true;
                }
                else if (Array_C())
                {
                    return true;
                }
            }
              else if ("]".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                index++;
              return true;
            }
            return false;
        }

        private boolean Array_C()
        {
           
            // FIRST(<Array_C>) = { ( }
            if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Array_C> ? { <Const> <Array_C2>
                //<Array_C>?{ <Exp><Array_C2>
                if ("(".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if (Exp())
                    {
                       
                        if (Array_C2())
                        {
                             return true;
                        }
                    }
                }
            }
            
              else if ("]".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
              return true;
            }
         return false;
        }

        private boolean Array_C2()
        {
            //FIRST(<Array_C2>) = {, , ) }
            if (")".equals(LexicalAnalyzer.tokens.get(index).CP)||
                ",".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
                //<Array_C2> ? , <Const> | } ;
                //<Array_C2>? , <Exp> | } \\r
                if (")".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    
                    index++;
                    return true;
                }
                else if (",".equals(LexicalAnalyzer.tokens.get(index).CP))
                {
                    index++;
                    if (Exp())
                    {
                        
                        if (Array_C2())
                        {
                             return true;
                        }
                    }
                }
            }
            
              else if ("]".equals(LexicalAnalyzer.tokens.get(index).CP))
            {
              return true;
            }
            return false;
        }

 
}
