package com.raylinetech.ssh2.logistics.common.util;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

public class FontUtil {

	private static Font eng30;
	private static Font eng15;
	private static Font eng12;
	private static Font eng10;
	private static Font eng8;
	private static Font eng6;
	private static Font eng5;
	private static Font eng4;

	private static Font chi15bold ;
	private static Font chi15 ;
	private static Font chi12 ;
	private static Font chi12bold ;
	private static Font chi10 ;
	private static Font chi10bold ;
	private static Font chi8;
	private static Font chi6;
	private static Font chi5;
	private static Font chi4;
	
	 public   static Font getEng30(){
         if (eng30 == null) {    
        	 eng30 = new Font();
        	 eng30.setSize(30);
        	 eng30.setStyle(Font.NORMAL);
         }    
        return eng30;  
	 }
	
	 public   static Font getEng10(){
         if (eng10 == null) {    
        	 eng10 = new Font();
        	 eng10.setSize(10);
        	 eng10.setStyle(Font.BOLD);
         }    
        return eng10;  
	 }
	 
	 public   static Font getEng15(){
         if (eng15 == null) {    
        	 eng15 = new Font();
        	 eng15.setSize(15);
        	 eng15.setStyle(Font.BOLD);
         }    
        return eng15;  
	 }
	 
	 public   static Font getEng12(){
         if (eng12 == null) {    
        	 eng12 = new Font();
        	 eng12.setSize(12);
         }    
        return eng12;  
	 }
	 
	 public   static Font getChi15() {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			if (chi15 == null) {    
				chi15 = new Font(bf, 15, Font.BOLD);
			}    
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return chi15;  
	 }
	 
	 public   static Font getChi12() {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			if (chi12 == null) {    
				chi12 = new Font(bf, 12, Font.BOLD);
			}    
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return chi12;  
	 }
	 
	 public   static Font getChi12BOLD() {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			if (chi12bold == null) {    
				chi12bold = new Font(bf, 12, Font.BOLD);
			}    
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return chi12bold;  
	 }
	 
	 public   static Font getChi15BOLD() {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			if (chi15bold == null) {    
				chi15bold = new Font(bf, 15, Font.BOLD);
			}    
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return chi15bold;  
	 }
	 public   static Font getChi10() {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			if (chi10 == null) {    
				chi10 = new Font(bf, 10, Font.BOLD);
			}    
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return chi10;  
	 }
	 public   static Font getChi10BOLD() {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			if (chi10bold == null) {    
				chi10bold = new Font(bf, 10, Font.BOLD);
			}    
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return chi10bold;  
	 }
	 
	 public   static Font getChi8() {
		BaseFont bf;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			if (chi8 == null) {    
				chi8 = new Font(bf, 8, Font.BOLD);
			}    
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return chi8;  
	 }
	 
	 public   static Font getEng8(){
         if (eng8 == null) {    
        	 eng8 = new Font();
        	 eng8.setSize(8);
        	 eng8.setStyle(Font.BOLD);
         }    
        return eng8;  
	 }
	 
	 public   static Font getChi6(){
		try {
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			 if (chi6 == null) {    
				 chi6 = new Font(bf, 6, Font.BOLD);
			 }
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        return chi6;  
	 }
	 
	 public   static Font getEng6(){
         if (eng6 == null) {    
        	 eng6 = new Font();
        	 eng6.setSize(6);
         }    
        return eng6;  
	 }
	 
	 public   static Font getChi5(){
		try {
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			 if (chi5 == null) {    
				 chi5 = new Font(bf, 5, Font.BOLD);
			 }
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        return chi5;  
	 }
	 
	 public   static Font getEng5(){
         if (eng5 == null) {    
        	 eng5 = new Font();
        	 eng5.setSize(5);
         }    
        return eng5;  
	 }
	 
	 public   static Font getChi4(){
		try {
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			 if (chi4 == null) {    
				 chi4 = new Font(bf, 4, Font.BOLD);
			 }
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        return chi4;  
	 }
	 
	 public   static Font getEng4(){
         if (eng4 == null) {    
        	 eng4 = new Font();
        	 eng4.setSize(4);
         }    
        return eng4;  
	 }
	 
}
